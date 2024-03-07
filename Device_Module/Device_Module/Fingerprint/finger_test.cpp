#include "Adafruit_Fingerprint.h"
#include <mosquitto.h>
#include <cstring>
#include <thread>

#define MQTT_HOST "127.0.0.1"
#define MQTT_PORT 1883
#define MQTT_REQUEST_TOPIC "fpRequestTopic"
#define MQTT_RESPONSE_TOPIC "fpResponseTopic"

void getFingerprintEnroll();
void printHex(int num, int precision);

char deviceName[100] = "/dev/ttyAMA3";

Adafruit_Fingerprint finger = Adafruit_Fingerprint(deviceName);
struct mosquitto *mosq = NULL;
char clientId[30] = "deviceClient";
int rc = 0;
char countResponse[15] = {0};
volatile bool cancelFlag = false;
int _qos = 0;
bool _retain = false;
std::thread th;

void connect_callback(struct mosquitto *mosq, void *obj, int result){
    fprintf(stdout,"Connected\n");
}

void message_callback(struct mosquitto *mosq, void *userdata, const struct mosquitto_message * message){
    fprintf(stdout,"Received message on topic:%s\n",message->topic, (char*)message->payload);

	if(strcmp((char*)message->payload, "FP_UPLOAD_CMD") == 0){
		_qos = message->qos;
		_retain = message->retain;

		if(th.joinable()){
			th.join();
		}
		th = std::thread(getFingerprintEnroll);
	}
	else if(strcmp((char*)message->payload, "CANCEL") == 0){
		cancelFlag = true;
		th.join();
	}
	else{
		fprintf(stderr,"Received wrong command\n");
	}

}


int main(){

	fprintf(stdout,"Adafruit finger detect test\n\n");

	finger.begin(57600);
	sleep(5);

	if (finger.verifyPassword()) {
    	fprintf(stdout,"Found fingerprint sensor!\n");
  	} else {
    	fprintf(stdout,"Did not find fingerprint sensor...\n");
		exit(0);
  	}

	fprintf(stdout,"Reading sensor parameters\n");
	finger.getParameters();
  	fprintf(stdout,"Status: %x\n",finger.status_reg);
	fprintf(stdout,"Sys ID: %x\n",finger.system_id);
  	fprintf(stdout,"Capacity: %d\n",finger.capacity);
	fprintf(stdout,"Security level: %d\n",finger.security_level);
  	fprintf(stdout,"Device address: %x\n",finger.device_addr);
  	fprintf(stdout,"Packet len: %d\n",finger.packet_len);
  	fprintf(stdout,"Baud rate: %d\n",finger.baud_rate);

	finger.LEDcontrol(false);

	printf("Start to take images\n");

	mosquitto_lib_init();

    mosq = mosquitto_new(clientId, true, NULL);

    if (!mosq){
        fprintf(stderr,"Failed to establish mosquitto configuration...\n");
        exit(1);
    }

    fprintf(stdout,"Established mosquitto configuration\n");

    mosquitto_connect_callback_set(mosq, connect_callback);
    mosquitto_message_callback_set(mosq,message_callback);

	sprintf(countResponse, "IMGCOUNT");

    fprintf(stdout,"Connecting...\n");

    rc = mosquitto_connect(mosq, MQTT_HOST, MQTT_PORT, 60);

    if(rc != 0){
        printf("Client could not connect to broker! Error Code: %d\n", rc);
        mosquitto_destroy(mosq);
        exit(1);
    }

    // MQTT CODE
    mosquitto_subscribe(mosq, nullptr, MQTT_REQUEST_TOPIC, 0);

    mosquitto_loop_forever(mosq, -1, 1);

    mosquitto_destroy(mosq);
    mosquitto_lib_cleanup();

	finger.finalize();

	return 0;
}

void sendCountMsg(int qos, bool retain){
	mosquitto_publish(mosq, nullptr, MQTT_RESPONSE_TOPIC, sizeof(countResponse),countResponse, qos, retain);
}

void getFingerprintEnroll() {
	int id = 1;
	int p = -1;

	char countResponse[15] = {0};
	sprintf(countResponse, "IMGCOUNT");

	//finger.emptyDatabase();
	finger.deleteModel(id);
	finger.LEDcontrol(true);

	fprintf(stdout,"Waiting for valid finger to enroll as #");
	fprintf(stdout,"%d\n",id);
	while (p != FINGERPRINT_OK) {
		if(cancelFlag){
			cancelFlag = false;
			finger.LEDcontrol(false);
			fprintf(stdout,"canceled!\n");
			return;
		}
		p = finger.getImage();
		switch (p) {
		case FINGERPRINT_OK:
		fprintf(stdout,"Image taken\n");
		break;
		case FINGERPRINT_NOFINGER:
		break;
		case FINGERPRINT_PACKETRECIEVEERR:
		fprintf(stderr,"Communication error\n");
		break;
		case FINGERPRINT_IMAGEFAIL:
		fprintf(stderr,"Imaging error\n");
		break;
		default:
		fprintf(stderr,"Unknown error\n");
		break;
		}
	}

	fprintf(stdout,"Remove finger\n");
	finger.LEDcontrol(false);
	sleep(1);

	sendCountMsg(_qos, _retain);
	fprintf(stdout,"Uploading user's finger print image to pc\n");

	const int img_weight = 128;
  	const int img_height = 288;
	uint8_t img_Buffer[img_height][img_weight];
	memset(img_Buffer, 0, sizeof(img_Buffer));
	p = finger.upImage(img_Buffer);

	switch(p){
		case FINGERPRINT_OK:
			fprintf(stdout, "Upload done\n");
			break;
		default:
			fprintf(stderr,"Failed to upload image\n");
			break;
	}


	//Data Transfer
	mosquitto_publish(mosq, nullptr, MQTT_RESPONSE_TOPIC, sizeof(img_Buffer), img_Buffer, _qos, _retain);

	printf("Image transfered...\n");

	return;
}
