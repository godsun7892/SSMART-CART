<template>
    <!-- <LoginHead /> -->
    <div class="phoneGround">
        <div class="updown">
            <div class="left" @click="hideKeyboard">
                <div class="mainSection">
                    <div class="phoneInfo">
                        <div class="txt"><b>비밀번호</b>를<br> 입력하세요.</div>
                    </div>
                    
                    <div class="inputWithImage" @click.stop="">
                        <img src="/image/pw.png" class="inputImage" />
                        <input id="password" type="password" v-model="inputPw" placeholder="비밀번호" @click="keyRangeShow('pw')">
                    </div>
                    <button class="nextBtn" @click="loginStore.loginRequest(enrollflag)">로그인</button>
                </div>
            </div>
            <div class="right">
                <div class="rightInside">
                    <div class="rightInsideInside">
                        <div class="iconBackground iconBackground1">
                            <img class="icon" src="/image/firstScreen/productSearch.png">
                        </div>
                        <div class="explanation explanation1"><span class="hot-deal">상품 검색</span>을 통해 원하는 상품을<br> 찾아보세요! 위치도 알 수 있어요.</div>
                    </div>
                
                    <div class="rightInsideInside">
                        <div class="iconBackground iconBackground2">
                            <img class="icon" src="/image/firstScreen/marketMap.png">
                        </div>
                        <div class="explanation explanation2">언제든 <span class="hot-deal">매장 지도</span>를 확인하세요.<br> 어디에 어떤 상품이 있을까요?</div>
                    </div>
                </div>
                <div class="rightInside">
                    <div class="rightInsideInside">
                        <div class="iconBackground iconBackground3">
                            <img class="icon" src="/image/firstScreen/hotDealIcon.png">
                        </div>
                        <div class="explanation explanation3">할인 중인 상품을 한눈에 확인<br>하세요. <span class="hot-deal">오늘의 핫딜</span> 추천!!</div>
                    </div> 
                    <div class="rightInsideInside">
                        <div class="iconBackground iconBackground4">
                            <img class="icon" src="/image/firstScreen/menuRecommend.png">
                        </div>
                        <div class="explanation explanation4">오늘의 <span class="hot-deal">메뉴를 추천</span>합니다.  필요<br>한 재료의 위치도 보여드려요.</div>
                    </div>
                </div>
            </div>
        </div>
        <transition name="keyboard">
            <div class="keyboardRange" v-if="keyRangeUp !== 'off'" @click.stop="">
                <div v-for="keyrow in keyInputlist" class="kbrow" >
                    <div v-for="key in keyrow" class="keyArray">
                        <button class="keycap shiftkey" v-if="key==='shift'" @click="shiftChange" >{{ key }}</button>
                        <button class="keycap" v-else-if="key==='abc'" @click="abcChange(0)" >{{ key }}</button>
                        <button class="keycap" v-else-if="key==='!#1'" @click="abcChange(1)" >{{ key }}</button>
                        <button class="keycap shiftkey" v-else-if="key==='prev'" @click="abcChange(2)" >{{ key }}</button>
                        <button class="keycap shiftkey" v-else-if="key==='next'" @click="abcChange(3)" >{{ key }}</button>
                        <button class="keycap backspacekey" v-else-if="key==='backspace'" @click="clearBtn" >
                            <svg xmlns="http://www.w3.org/2000/svg" width="35px" height="35px" fill="none" stroke="currentColor" stroke-width="1" class="bi bi-arrow-left" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                            </svg>
                        </button>
                        <!-- <button class="empty1" v-else-if="key==='empty1'"></button>
                        <button class="empty2" v-else-if="key==='empty2'"></button>
                        <button class="empty3" v-else-if="key==='empty3'"></button> -->
                        <button class="keycap" @click="appendWord(key)" v-else>{{ key }}</button>
                    </div>
                </div>
            </div>
        </transition>

        <div class="back">
            <button class="backBtn" @click="$router.push('/home')">
                <img src="/image/goBack.png" width="90px" height="100px">
            </button>
        </div>
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
import { useFlagStore } from '@/stores/flag';

const flagStore = useFlagStore();
const loginStore = useLoginStore();
const {inputId, inputPw, loginMessageOn } = storeToRefs(loginStore);
const { enrollflag } = storeToRefs(flagStore);

console.log(enrollflag.value);

const fingerEnroll = () => {
    router.push('fingerEnroll');
}

const hideKeyboard = () => {
    keyRangeUp.value = 'off'; // 키패드를 숨기기 위해 상태를 'off'로 변경
}
// const lowerkeylist2 = [
//     ['`','1','2','3','4','5','6','7','8','9','0','-','='],
//     ['q','w','e','r','t','y','u','i','o','p','[',']','\\'],
//     ['a','s','d','f','g','h','j','k','l',';','\'','shift'],
//     ['z','x','c','v','b','n','m',',','.','/', 'backspace']
// ];

// const upperkeylist2 = [
//     ['~','!','@','#','$','%','^','&','*','(',')','_','+'],
//     ['Q','W','E','R','T','Y','U','I','O','P','{','}','|'],
//     ['A','S','D','F','G','H','J','K','L',':','\"','shift'],
//     ['Z','X','C','V','B','N','M','<','>','?', 'backspace']
// ]

const lowerkeylist = [
    ['1','2','3','4','5','6','7','8','9','0','backspace'],
    ['q','w','e','r','t','y','u','i','o','p','!#1'],
    ['a','s','d','f','g','h','j','k','l','shift'],
    ['z','x','c','v','b','n','m',',','.']
];

const upperkeylist = [
    ['!','@','#','$','%','^','&','*','(',')','backspace'],
    ['Q','W','E','R','T','Y','U','I','O','P','!#1'],
    ['A','S','D','F','G','H','J','K','L','shift'],
    ['Z','X','C','V','B','N','M','!','?']
];

const lowerspkeylist = [
    ['1','2','3','4','5','6','7','8','9','0','backspace'],
    ['+','×','÷','=','/','_','<','>','[',']','abc'],
    ['@','#','$','%','^','&','*','(',')','next'],
    ['-','\'','\"',':',';',',','?','!','.']
];

const upperspkeylist = [
    ['1','2','3','4','5','6','7','8','9','0','backspace'],
    ['`','~','\\','|','{','}','€','￡','￥','￦','abc'],
    ['·','○','●','□','■','♤','♡','◇','♧','prev'],
    ['☆','∙','¤','《','》','¡','¿','˚','.']
];


const keyInputlist =ref(lowerkeylist);
const shiftFlag = ref(false);
const keyRangeUp = ref('off');

const appendWord =(char) =>{
    if(keyRangeUp.value === 'id') inputId.value += char;
    else if(keyRangeUp.value === 'pw') inputPw.value += char;
}

const keyRangeShow = (char) => {
    keyRangeUp.value = (keyRangeUp.value !== char) ? char : 'off';
}

const clearBtn = () =>{
    if(keyRangeUp.value === 'id') inputId.value = inputId.value.slice(0,-1);
    else if(keyRangeUp.value === 'pw')  inputPw.value = inputPw.value.slice(0,-1);
}

const shiftChange = () => {
    if(!shiftFlag.value){
        shiftFlag.value = true;
        keyInputlist.value = upperkeylist;
    }
    else{
        shiftFlag.value = false;
        keyInputlist.value = lowerkeylist;
    }
}

const abcChange = (num) => {
    if(num===0) {
        shiftFlag.value = false;
        keyInputlist.value = lowerkeylist;
    }
    if(num===2 || num === 1) keyInputlist.value = lowerspkeylist;
    if(num===3) keyInputlist.value = upperspkeylist;
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




</script>

<style scoped>
/* 여기서 부터 phone 페이지 */
.phoneMargin {
margin: 5% 5%;
}

.mainSection{
    /* width:30%;
    height:80%; */
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
font-size: 30px;
width: 70%;
height: 60px;
border: none;
outline: none;
background-color: transparent;

}

.underline {
width: 90%;
border-bottom: 1px solid black; /* 가로선 스타일 및 색상 설정 */
margin-top: 1%; /* 가로선과 입력란 사이의 간격 조절 */

}

.notUserText{
    width:40%;
}

.sideline{
    width:30%;
    border-bottom: 1px solid #999999;
    height:49%;
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


.nextBtn {
width: 90%;
height: 65px;
margin-bottom: 15%;
font-size: 30px;
background-color: white;
border-radius: 30px;
border: 0;
box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.exp1 {
    font-size: 36px;
}
.exp2 {
    margin-top: -15px;
    font-size: 30px;
}

#nextBtn:active {
  transform: scale(0.95); /* 버튼 축소 효과 */
}

.notUser{
    background-color: #FFF;
    margin-bottom: 0%;
    margin-top: 3%;
}

.phoneGround{
position: relative;
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
position: relative;
}

.left {
    width: 45%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #4892f3;
}

.fingerPrintBtn{
    width: 40%;
    height: 80%;
    display: flex;
    flex-direction: column;
    align-items: center;

}

.fpText{
    font-size: 36px;
}

.inputWithImage {
    margin-top: 50px;
}
.inputWithImage:focus-within {
  border: 2px solid skyblue; /* 포커스 시 테두리 색상 */
}

input:focus {
    outline: none;
}


.inputWithImage {
    display: flex;
    flex-direction: row;
    align-items: center;
    background-color: #fff;
    width: 90%; /* 조절 가능한 너비 */
    height: 10%;
    margin-bottom: 15px;
    border-radius: 10px;
    border : 1px solid black;
}

.inputImage {
  width:30px;
  height:30px;
  margin-left:10px;
}

#password {
    margin-left:10px;
    width: 80%;
    height: 100%;
    border-radius: 10px;
    border: 0px;
}

.btn {
    margin-top: 10px;
    width: 30%;
    height: 8%;
    border-radius: 30px;
    border: none;
    background: #78EAEF;
    font-weight: 600;
    font-size: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.btn:active {
      transform: scale(0.95); /* 버튼 축소 효과 */
}

.keyboardRange{
    display: flex;
    flex-direction: column;
    align-items: center;
    width:100%;
    height:40%;
    background-color: rgb(208, 211, 217);
    margin-top:0%;
    position: absolute;
    bottom:0px;
    z-index: 3;
}

.kbrow{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width:100%;
    height:25%;
    margin:0.2%;
}

.keyArray{
    margin:0 0.4%;
}

.keycap{
    font-size: 40px;
    /* font-weight: bold; */
    width:105px;
    margin:0.5%;
    height:100%;
    background-color: white;
    border-radius:10px;
}

.shiftkey{
    width:170px;
}

.empty1{
    width:38px;
    border:none;
}

.empty2{
    width:38px;
    border:none;
}

.empty3{
    width:38px;
    border:none;
}


.backspacekey{
    width:105px;
}

/* 키패드가 나타나는 애니메이션 */
@keyframes slideUp {
    from {
        transform: translateY(100%);
    }
    to {
        transform: translateY(0);
    }
}

/* 키패드가 사라지는 애니메이션 */
@keyframes slideDown {
    from {
        transform: translateY(0);
    }
    to {
        transform: translateY(100%);
    }
}

.keyboard-enter-active, .keyboard-leave-active {
    transition: all 0.5s ease;
}

.keyboard-enter, .keyboard-leave-to /* .keyboard-leave-active in <2.1.8 */ {
    transform: translateY(100%);
}

.keyboard-leave-active {
    animation: slideDown 0.5s forwards;
}

.keyboard-enter-active {
    animation: slideUp 0.5s forwards;
}

.keycap:hover {
    background-color: #95989c;
    color: white;
}

.keycap:active {
    transform: scale(0.95); /* 버튼 축소 효과 */
}

.back{
    position: absolute;
    right: 0px;
    top:0px
}

.backBtn{
    /* margin-left: auto; */
    /* margin-right:3%; */
    /* height:80%; */
    width:100%;
    height: 5%;
    font-size: 20px;
    border-radius: 10px;
    border: none;
    background-color: rgb(235, 244, 246);
}

.right {
    display: flex;
    flex-direction: column;
    width: 55%;
    height: 100%;
    background-color: rgb(235, 244, 246);
}

.rightInside {
    height: 50%;
    display: flex;
    flex-direction: row;
}

.rightInsideInside {
    display: flex;
    flex-direction: column;
    width: 50%;
}

.iconBackground {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 250px;
    height: 250px;
    border-radius: 50%;
    background-color: white;
}

.iconBackground1 {
    margin-left : 140px;
    margin-top : 55px;
}

.iconBackground2 {
    margin-left : 70px;
    margin-top : 55px;
}

.iconBackground3 {
    margin-left : 140px;
    margin-top : 30px;
}

.iconBackground4 {
    margin-left : 70px;
    margin-top : 30px;
}

.icon {
    width: 150px;
    height: 150px;
}

.hot-deal {
    color: red; /* 빨간색 텍스트 */
}

.explanation {
    font-weight: bold;
    font-size: 20px;
}

.explanation1 {
    margin-left: 110px;
    margin-top: 15px;
}

.explanation2 {
    margin-left: 60px;
    margin-top: 15px;
}

.explanation3 {
    margin-left: 140px;
    margin-top: 15px;
}

.explanation4 {
    margin-left: 50px;
    margin-top: 15px;
}

.rightBottom {
    height: 50%;
    display: flex;
    flex-direction: row;
}

</style>