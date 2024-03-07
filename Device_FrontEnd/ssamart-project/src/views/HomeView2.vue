<template>
    <!-- <LoginHead /> -->
    <div class="phoneGround">
        <div class="updown">
            <div class="left">
                <div class="mainSection">
                    <div class="phoneInfo">
                        <div class="txt"><b>휴대폰 번호</b>를 <br>입력하세요.</div>
                    </div>
            
                    <input type="text" class="phoneInput" v-model="phoneNumber" placeholder="010 - " maxlength="17" />
                    <!-- <div class="underline"></div> -->
                    <!-- <div class="invalidPhone" v-if="invalidPhone">{{ errorNumMessage }}</div> -->
                    <div class="invalidPhone">{{ errorNumMessage }}</div>
                    <button class="nextBtn" @click="NumCheck"><div class="txt2">확인</div></button>
                    <div class="notUsermessage">
                        <div class="sideline"></div>
                        <div class="notUserText">계정이 없으신가요?</div>
                        <div class="sideline"></div>
                    </div>
                    
                    <button class="nextBtn notUser" @click="notUserLogin(4)">비회원 로그인</button>
                </div>
            </div>
            <div class="right">
                <div class="numberSection">
                    <button class="numBtn" v-for="num in numberBtn" @click="appendNumber(`${num}`)">{{num}}</button>
                    <!-- <button class="numBtn" @click="appendNumber(1)">1</button>
                    <button class="numBtn" @click="appendNumber(2)">2</button>
                    <button class="numBtn" @click="appendNumber(3)">3</button>
                    <button class="numBtn" @click="appendNumber(4)">4</button>
                    <button class="numBtn" @click="appendNumber(5)">5</button>
                    <button class="numBtn" @click="appendNumber(6)">6</button>
                    <button class="numBtn" @click="appendNumber(7)">7</button>
                    <button class="numBtn" @click="appendNumber(8)">8</button>
                    <button class="numBtn" @click="appendNumber(9)">9</button> -->
                    <button class="numBtn" @click="backInput">
                        <svg xmlns="http://www.w3.org/2000/svg" width="50px" height="45px" fill="none" stroke="currentColor" stroke-width="1" class="bi bi-arrow-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                        </svg>

                    </button>
                    <button class="numBtn" @click="appendNumber(0)">0</button>
                    <button class="numBtn" @click="clearInput">
                        <svg xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16" stroke="currentColor" stroke-width="0.5">
                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
        <!-- <button class="nextBtn" @click="$router.push('/fingerPrint')">확인</button> -->
        <LoginMessage v-if="loginMessageOn"/>
    </div>
</template>

<script setup>

import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import LoginHead from '@/components/LoginHeader.vue';
import { useLoginStore } from '@/stores/login';
import { storeToRefs } from 'pinia';
import LoginMessage from '@/components/loginMessageModal.vue';

const loginStore = useLoginStore();
const {username, userSeq, loginMessageOn, inputId } = storeToRefs(loginStore);

const numberBtn = ref([1,2,3,4,5,6,7,8,9]);

const phoneNumber = ref('');
const dataNum = ref('');
const invalidPhone = ref(false);
const errorNumMessage=ref('');
var flag = 0;

const appendNumber = (number) => {
if (flag == 0){
    phoneNumber.value = "010 - ";
    dataNum.value = '010';
    errorNumMessage.value = '';
    flag = 1;
}


if (phoneNumber.value.length < 17) {
    phoneNumber.value += number;
    dataNum.value +=number;
}

if(phoneNumber.value.length === 10){
    phoneNumber.value += ' - ';
}

};

const clearInput = () => {
phoneNumber.value = '';
flag = 0;
}

const backInput = () => {

var sliceCnt = 0;
const tL = phoneNumber.value.length;
if (tL > 7) {
    if(tL === 14 || tL === 13) sliceCnt = -4;
    else sliceCnt = -1;

    phoneNumber.value = phoneNumber.value.slice(0,sliceCnt);
    dataNum.value = dataNum.value.slice(0,-1);
}else{
    phoneNumber.value = '';
    flag = 0;
}
console.log(phoneNumber.value);
console.log(dataNum.value);
}

const NumCheck = () =>{
if(dataNum.value.length < 11){
    invalidPhone.value=true;
    errorNumMessage.value = '형식이 올바르지 않습니다.';
}
else{
    

    // 실제 사용 시 
    inputId.value = dataNum.value;

    // 테스트용
    //inputId.value = '01041661034';
    // pw : test111!
    invalidPhone.value=false;
    console.log(dataNum.value)
    phoneNumber.value = '';
    flag=0;
    router.push('/fingerPrint');
}
}

function LoadingWithMask() {
//화면의 높이와 너비를 구합니다.    
var maskHeight = $(document).height();
var maskWidth = window.document.body.clientWidth;
//화면에 출력할 마스크를 설정해줍니다.
var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
var loadingImg = '';

loadingImg += "<div id='loadingImg' style='position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);'>";
loadingImg += "<img src='../../public/image/LoadingImg.gif' style='display: block; margin: 0px auto;'/>"; 
loadingImg += "</div>";

//화면에 레이어 추가    
$('body')
    .append(mask)
    .append(loadingImg)
    
    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.    
    $('#mask').css({
        'width' : maskWidth, 
        'height': maskHeight, 
        'opacity' : '0.3'
    }); 

    //마스크 표시    
    $('#mask').show();
    //로딩중 이미지 표시
    $('#loadingImg').show();
}


const notUserLogin = (num) =>{
    userSeq.value = 9999;
    username.value = '비회원'
    loginStore.loginMessageModal(num);
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
    width:40%;
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
    font-weight: 600;
    color: rgb(215, 6, 6);
    
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
    margin-bottom: 0%;
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
    justify-content: center;
    align-items: center;
    background-color: rgb(235, 244, 246);
}
</style>