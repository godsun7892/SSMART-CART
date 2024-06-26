$(document).ready(function(){
    fncStartMqtt();
});
        
     
    // MQTT client
    var mqttClient= null;
     
    //MQTT info
    // 각자 상황에 맞는 host, port, topic 을 사용합니다.
    var mqtt_host = "127.0.0.1";
    var mqtt_port = "9001";
    var mqtt_clientId = "clientID-" + parseInt(Math.random() * 100);        // 랜덤 클라이언트 ID 
    var mqtt_topic = "testTopic";
     
    // MQTT 클라이언트 연결
    function fncStartMqtt() 
{
        mqttClient = new Paho.MQTT.Client(mqtt_host, Number(mqtt_port), mqtt_clientId);
     
        mqttClient.onConnectionLost = onConnectionLost;
        mqttClient.onMessageArrived = onMessageArrived;
     
        mqttClient.connect({
            onSuccess : onConnect
            , onFailure : onFailure
        });
    }
     
    // 연결 성공 시 실행되는 function
    function onConnect() 
    {
        console.log("connet : onConnect..");
     
        mqttClient.subscribe(mqtt_topic);
    }
     
    // 연결 실패 시 실행되는 function
    function onFailure() 
    {
        console.log("connet : onFailure..");
     
    }
     
     
     
    function onConnectionLost(responseObject) 
    {
        if (responseObject.errorCode !== 0) 
        {
            console.log("onConnectionLost : " + responseObject.errorMessage);
     
            // 연결 재시도
            fncConnMqtt();
        }
    }
     
    // 메시지 받는 부분
    function onMessageArrived(message) 
    {
        console.log("onMessageArrived : " + message.payloadString);
     
        // mqtt 받은 메시지 
        // 받은 메시지를 각 화면에서 사용하려면 각 화면에서 아래 function 선언하여 사용
        fncMqttAction(message.payloadString);
    }
     
     
     
    // 메시지 보내기
    // 각 화면에서 메시지를 보내려면 각 화면에서 아래 function 선언하여 사용
    function fncMqttDoSend(sendMsg) 
    {
        mqttClient.send(mqtt_topic, sendMsg);
    }
    
function fncMqttAction(message)
{
    console.log("mqtt success");
}