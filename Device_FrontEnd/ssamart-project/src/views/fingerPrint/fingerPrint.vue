<template>
    <LoginHead />
    <div class="HomeBackGround">
        <div class="fp_con">
            <div class="fp_con_1">지문을 인식해 주세요</div>
            <div class="fp_con_2">
                <!-- <img src="/image/free-icon-fingerprint-scan-1898646 1.png" alt=""> -->
                <img src="/image/fingerprintUnscreen.gif" alt="" width="120%" height="120%">
            </div>
            <!-- <div class="fp_con_3"><button class="btn" @click="$router.push('loginMain')"> <u>지문 등록하기</u></button></div> -->
            
        </div>
        <div class="pagefooter">
            <button class="backBtn" @click="$router.push('/login_fp')">지문등록</button>
            <button class="backBtn" @click="$router.push('/loginMain')">뒤로가기</button>
        </div>
        <LoginMessage v-if="loginMessageOn"/>
    </div>

</template>

<script setup>

import { useRouter } from 'vue-router';
const router = useRouter();
import LoginHead from '@/components/LoginHeader.vue';
import axios from "@/util/http-common.js"
import { useLoginStore } from '@/stores/login';
import {storeToRefs} from "pinia";
import LoginMessage from '@/components/loginMessageModal.vue';

const loginStore = useLoginStore();
const { loginMessageOn } = storeToRefs(loginStore);


        // $(document).ready(function(){
        //     fncStartMqtt();
        // });
            
         
        // // MQTT client
        // var mqttClient= null;
         
        // //MQTT info
        // // 각자 상황에 맞는 host, port, topic 을 사용합니다.
        // var mqtt_host = "127.0.0.1";
        // var mqtt_port = "9001";
        // var mqtt_clientId = "clientID-" + parseInt(Math.random() * 100);        // 랜덤 클라이언트 ID 
        // var mqtt_topic = "testTopic";
         
        // // MQTT 클라이언트 연결
        // function fncStartMqtt() 
	    // {
        //     mqttClient = new Paho.MQTT.Client(mqtt_host, Number(mqtt_port), mqtt_clientId);
         
        //     mqttClient.onConnectionLost = onConnectionLost;
        //     mqttClient.onMessageArrived = onMessageArrived;
         
        //     mqttClient.connect({
        //         onSuccess : onConnect
        //         , onFailure : onFailure
        //     });
        // }
         
        // // 연결 성공 시 실행되는 function
        // function onConnect() 
        // {
        //     console.log("connect : onConnect..");
         
        //     mqttClient.subscribe(mqtt_topic);
        // }
         
        // // 연결 실패 시 실행되는 function
        // function onFailure() 
        // {
        //     console.log("connet : onFailure..");
         
        // }
         
         
         
        // function onConnectionLost(responseObject) 
        // {
        //     if (responseObject.errorCode !== 0) 
        //     {
        //         console.log("onConnectionLost : " + responseObject.errorMessage);
         
        //         // 연결 재시도
        //         fncConnMqtt();
        //     }
        // }
         
        // // 메시지 받는 부분
        // function onMessageArrived(message) 
        // {
        //     console.log("onMessageArrived : " + message.payloadString);
         
        //     // mqtt 받은 메시지 
        //     // 받은 메시지를 각 화면에서 사용하려면 각 화면에서 아래 function 선언하여 사용
        //     fncMqttAction(message.payloadString);
        // }
         
         
         
        // // 메시지 보내기
        // // 각 화면에서 메시지를 보내려면 각 화면에서 아래 function 선언하여 사용
        // function fncMqttDoSend(sendMsg) 
        // {
        //     mqttClient.send(mqtt_topic, sendMsg);
        // }
        
        // function fncMqttAction(message)
        // {
        //     console.log("mqtt success");
        //     router.push('/shoppingBasket');
        // }

</script>

<style scoped>
.btn {
    font-size: 30px;
    color: gray;
}

.HomeBackGround{
    flex-direction: column;
}

.fp_con{
    margin-top: 8%;
}

.pagefooter{
    width:100%;
    height:10%;
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-top: auto;
    justify-content: right;
}

.backBtn{
    margin-right:3%;
    height:80%;
    width:10%;
    font-size: 30px;
    border-radius: 10px;
    border: none;
    background-color: #78EAEF;
}

</style>