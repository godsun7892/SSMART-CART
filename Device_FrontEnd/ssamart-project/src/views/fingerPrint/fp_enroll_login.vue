<template>
    <LoginHead />
    <div class="HomeBackGround" @click="keyRangeShow('off')">
        <div class="mainSection">
            <div class="phoneInfo">
                <div>먼저, <b>비밀 번호</b>를 입력해주세요</div>
            </div>
            
            <div class="inputWithImage" @click.stop="">
                <img src="/image/pw.png" class="inputImage" />
                <input id="password" type="password" v-model="inputPw" placeholder="비밀번호" @click="keyRangeShow('pw')">
            </div>
            <button class="nextBtn" @click="loginStore.loginRequest(2)">로그인</button>
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
                        <button class="empty1" v-else-if="key==='empty1'"></button>
                        <button class="empty2" v-else-if="key==='empty2'"></button>
                        <button class="empty3" v-else-if="key==='empty3'"></button>
                        <button class="keycap" @click="appendWord(key)" v-else>{{ key }}</button>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import LoginHead from '@/components/LoginHeader.vue';
import {storeToRefs} from "pinia";
import { useLoginStore } from '@/stores/login';

const loginStore = useLoginStore();
const { inputPw } = storeToRefs(loginStore);

const lowerkeylist = [
    ['1','2','3','4','5','6','7','8','9','0','backspace'],
    ['q','w','e','r','t','y','u','i','o','p','!#1'],
    ['empty1','a','s','d','f','g','h','j','k','l','shift'],
    ['empty2','z','x','c','v','b','n','m',',','.','empty3']
];

const upperkeylist = [
    ['!','@','#','$','%','^','&','*','(',')','backspace'],
    ['Q','W','E','R','T','Y','U','I','O','P','!#1'],
    ['empty1','A','S','D','F','G','H','J','K','L','shift'],
    ['empty2','Z','X','C','V','B','N','M','!','?','empty3']
];

const lowerspkeylist = [
    ['1','2','3','4','5','6','7','8','9','0','backspace'],
    ['+','×','÷','=','/','_','<','>','[',']','abc'],
    ['empty1','@','#','$','%','^','&','*','(',')','next'],
    ['empty2','-','\'','\"',':',';',',','?','!','.','empty3']
];

const upperspkeylist = [
    ['1','2','3','4','5','6','7','8','9','0','backspace'],
    ['`','~','\\','|','{','}','€','￡','￥','￦','abc'],
    ['empty1','·','○','●','□','■','♤','♡','◇','♧','prev'],
    ['empty2','☆','∙','¤','《','》','¡','¿','˚','.','empty3']
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





</script>

<style scoped>
.mainSection{
    width:30%;
    height:80%;
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

.nextBtn {
width: 90%;
height: 10%;
margin-top: 7%;
margin-bottom: 15%;
font-size: 30px;
background-color: #80E1FF;
border-radius: 30px;
border: 0;
box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

#nextBtn:active {
  transform: scale(0.95); /* 버튼 축소 효과 */
}

.keyboardRange{
    display: flex;
    flex-direction: column;
    align-items: center;
    width:100%;
    height:40%;
    background-color: aliceblue;
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
    font-weight: bold;
    width:105px;
    margin:0.5%;
    height:100%;
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
        background-color: lightblue;
        color: white;
}

.keycap:active {
    transform: scale(0.95); /* 버튼 축소 효과 */
}

</style>