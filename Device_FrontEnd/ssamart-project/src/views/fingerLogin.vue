<template>
    <!-- <LoginHead /> -->
    <div class="phoneGround">
        <div class="updown">
            <div class="left">
                <div class="mainSection">
                    <div class="phoneInfo">
                        <div class="txt"><b>지문</b>을 인식해 <br>주세요.</div>
                    </div>
                    <!-- <button class="nextBtn" @click="NumCheck"><div class="txt2">비밀번호 로그인</div></button> -->
                    <div class="notUsermessage">
                        <div class="sideline"></div>
                        <div class="notUserText">지문을 등록하기 전이라면</div>
                        <div class="sideline"></div>
                    </div>
                    
                    <button class="nextBtn notUser" @click="PWlogin(2)">지문 등록하기</button>
                    <button class="nextBtn" @click="PWlogin(1)"><div class="txt2">비밀번호 로그인</div></button>
                </div>
            </div>
            <div class="right">
                <div class="fingetText">화면 아래 지문 인식기에<br><b>오른손 검지손가락</b>을 대주세요.</div>
                <!-- <img src="/image/fingerprintUnscreen.gif" alt="" width="70%" height="70%"> -->
                <img src="/image/fingerprintUnscreen.gif" alt="" width="50%" height="50%">

            </div>
        </div>
        <LoginMessage v-if="loginStore.loginMessageOn"/>
        <Loading v-if="loadingFlag" />
    </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useFlagStore } from '@/stores/flag';
import { useRouter } from 'vue-router';
import { useLoginStore } from '@/stores/login';
import Loading from '@/views/Loading.vue';
import LoginMessage from '@/components/loginMessageModal.vue';
import axios from "@/util/http-common.js"
const router = useRouter();
const flagStore = useFlagStore();
const { enrollflag, loadingFlag } = storeToRefs(flagStore);
const loginStore = useLoginStore();
const {username, userSeq} = storeToRefs(loginStore);
const { matchFlag} = storeToRefs(loginStore);

const PWlogin = (num) =>{
    enrollflag.value = num;
    // MQTT 연결 안되있을 경우 fncMQTTSend 주석 처리 할 것
    matchFlag.value = false;
    loadingFlag.value = false;
    fncMqttDoSend("CANCEL");
    //mqttClient.disconect();
    //mqttClient.socket.close();
    if(num == 2){
        router.push('loginProcess')
    }
    else {
        router.push('/loginMain')
    }
}

const matchFunc = (phoneNum, data) =>{
		//console.log(data)
		axios
			.post("fingerprint/match", {
				phoneNum,
				data
			})
			.then((response) => {
                if(response.status===200){
                    username.value = response.data.name;
				    userSeq.value = response.data.seq;
                    loginStore.loginMessageModal(1);
                }
                else{

                }
			})
			.catch(() => {
                alert('지문을 다시 인식해주세요.')
                fncMqttDoSend("FP_UPLOAD_CMD");
			})
	    }


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
        var mqtt_request_topic = "fpRequestTopic";
        var mqtt_response_topic = "fpResponseTopic";

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
            console.log("connect : onConnect..");

            //fncMqttDoSend("CANCEL");

            mqttClient.subscribe(mqtt_response_topic);
            fncMqttDoSend("FP_UPLOAD_CMD");
        }
        
        // 연결 실패 시 실행되는 function
        function onFailure() 
        {
            console.log("connect : onFailure..");
            
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

            var msgLen = message.payloadBytes.length
            console.log("onMessageArrived... length: " + msgLen);

            // mqtt 받은 메시지 
            // 받은 메시지를 각 화면에서 사용하려면 각 화면에서 아래 function 선언하여 사용

            if(msgLen <= 15 ){
                console.log(message.payloadString)
                console.log("COUNT UP")
                // 이미지 카운팅 추가 필요
                loadingFlag.value = true;
            }
            else if (msgLen > 15 && matchFlag.value){
                const arrayBuf = message.payloadBytes
                loadingFlag.value = false;
                const biArray = []

                for(var i = 0; i < arrayBuf.length; i++){
                    biArray[i] = arrayBuf[i]
                }
                
                let userPhoneNumber = loginStore.inputId

                matchFunc(userPhoneNumber,biArray)

            }
            else{
                console.log("Wrong command error...")
            }
        }

        function fncMqttDoSend(cmd) 
        {
            console.log(cmd)

            mqttClient.send(mqtt_request_topic, cmd);
        }


</script>

<style scoped>
/* 여기서 부터 phone 페이지 */
.phoneMargin {
margin: 5% 5%;
}

.mainSection{
    /* width:30%; */
    /* height:80%; */
    display: flex;
    flex-direction: column;
    align-items:flex-start;
    margin: 0% 10%;
}

.phoneInfo{
font-size: 55px;
width: 450px;
margin-left: 2%;
margin-bottom:5%;
height:35%;
}

.txt {
    color: white;
    margin-bottom: 10%;
}
.phoneInput {
font-weight: bold;
font-size: 33px;
width: 70%;
height: 60px;
border: none;
outline: none;
background-color: transparent;
color: white;

}

input::placeholder {
  color: rgb(217, 217, 218);
}

.underline {
width: 90%;
border-bottom: 1px solid white; /* 가로선 스타일 및 색상 설정 */
margin-top: 1%; /* 가로선과 입력란 사이의 간격 조절 */
margin-bottom: 1%;
}

.notUserText{
    width:60%;
    color: white;
}

.sideline{
    width:30%;
    border-bottom: 1px solid white;
    /* height: 5px; */
    margin-bottom: 10px;
}

.invalidPhone{
    width:100%;
    height:6%;
    margin-top:1%;
    font-size: 20px;
    color: red;
    
}

.notUsermessage{
    display: flex;
    flex-direction: row;
    width:90%;
    text-align: center;
    color: #999999;
}

.numberSection {
overflow: hidden;
width: 70%;
height: 90%;
/* padding: 0 5%; */
display: flex;
flex-wrap: wrap;
justify-content: center;
align-items: center;
}

.numBtn{
    font-size: 60px;
    font-weight: 700;
    width: 120px; 
    height: 120px;
    border-radius: 60px; /* 버튼의 width 또는 height의 절반으로 설정하여 원 모양 만들기 */
    background-color: #FFF; /* 버튼의 배경색을 하얀색으로 설정 */
    border: 1px solid rgba(0,0,0,0.1); /* 원형 버튼에 경계선을 추가하려면 이 줄을 유지 */
    margin: 15px; /* 필요에 따라 여백 조정 */
    color: #000; /* 글자 색상을 검정색으로 설정 */
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    padding-bottom: 8px;
}

.numBtn:hover {
    background-color: darkgray;
    color: gray;
}

.numBtn:active {
  transform: scale(0.95); /* 버튼 축소 효과 */
}

.nextBtn {
width: 90%;
height: 65px;
margin-bottom: 15%;
font-size: 30px;
background-color: #7ab9f3;
border-radius: 30px;
border: 0;
box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.nextBtn:active {
  transform: scale(0.95); /* 버튼 축소 효과 */
}

.txt2 {
    color: white;
    /* font-weight: bold; */
}
.notUser{
    background-color: #FFF;
    /* margin-bottom: 0%; */
    margin-bottom: 5%;
    margin-top: 3%;
}

.phoneGround{
display: flex;
flex-direction: column;
align-items: center;
justify-content: center;
width: 100%;
height: 100%;
flex-shrink: 0;
background: rgba(217, 217, 217, 0.32);
}

.updown{
/* margin: 0% 5%; */
display: flex;
flex-direction: row;
align-items: center;
justify-content: center;
width: 100%;
height: 100%;
flex-shrink: 0;
}

.left {
    width: 45%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #4892f3;
}

.right {
    width: 55%;
    height: 100%;
    display: flex;
    flex-direction : column;
    justify-content: center;
    align-items: center;
    background-color: rgb(235, 244, 246);
}
.fingetText {
    /* margin-top : 10%; */
    font-size : 36px;
    text-align: center;
}
</style>