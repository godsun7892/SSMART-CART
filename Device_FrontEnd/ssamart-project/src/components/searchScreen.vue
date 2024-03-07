<template>
    <div class="background">       
        <div class="left">
            <div class="searchHeader">
                <div class="searchBarbox" @click="search">
                    <img src="/image/searchProduct.png" width="4%" height="70%">
                    <div class="searchWordRange">{{ keyWord }}<span class="blinking-cursor">I</span></div>
                    <div class="numBtn" @click="clearInput">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="lightgray" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                        </svg>
                    </div>
                </div> 
            </div>
            
            <!-- 검색 시 상품 목록이 보일 영역 -->
            <div class="content">
                <SearchResultScreen v-if="searchScreenFlag" />
            </div>
        </div>

        <div class="right">
            <!-- 광고 섹션 -->
            <div class="ad-section">
                <!-- 광고 이미지 또는 컨텐츠 -->
                <img src="/image/discount.png" class="img">
            </div> 
        </div>
        
        <transition name="keyboard">
            <div class="keyboardRange" v-if="!keypadflag">
                <div class="kbrow" v-for="(row, rowIndex) in (isShiftActive ? keyboardLayoutBig : keyboardLayout)" :key="rowIndex">
                    <button class="keycap" :class="{ spacekey: key === 'space', back: key === 'back', shift: key === 'shift', keypad: key === 'keypad', search: key === 'search'}" v-for="(key, keyIndex) in row" :key="keyIndex" @click="key === 'space' ? appendWord(' ') : key === 'back' ? backInput() : key === 'shift' ? toggleShift() : key === 'search' ? searchKeyWord() : key === 'keypad' ? changeKeypadFlag() : appendWord(key)">
                        <template v-if="key === 'space'">space</template>
                        <template v-else-if="key === 'back'">
                            <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-backspace" viewBox="0 0 16 16">
                                <path d="M5.83 5.146a.5.5 0 0 0 0 .708L7.975 8l-2.147 2.146a.5.5 0 0 0 .707.708l2.147-2.147 2.146 2.147a.5.5 0 0 0 .707-.708L9.39 8l2.146-2.146a.5.5 0 0 0-.707-.708L8.683 7.293 6.536 5.146a.5.5 0 0 0-.707 0z"/>
                                <path d="M13.683 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-7.08a2 2 0 0 1-1.519-.698L.241 8.65a1 1 0 0 1 0-1.302L5.084 1.7A2 2 0 0 1 6.603 1zm-7.08 1a1 1 0 0 0-.76.35L1 8l4.844 5.65a1 1 0 0 0 .759.35h7.08a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1z"/>
                            </svg>
                        </template>
                        <template v-else-if="key === 'shift'">
                            <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-shift" viewBox="0 0 16 16">
                                <path d="M7.27 2.047a1 1 0 0 1 1.46 0l6.345 6.77c.6.638.146 1.683-.73 1.683H11.5v3a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1v-3H1.654C.78 10.5.326 9.455.924 8.816zM14.346 9.5 8 2.731 1.654 9.5H4.5a1 1 0 0 1 1 1v3h5v-3a1 1 0 0 1 1-1z"/>
                            </svg>
                        </template>
                        <template v-else-if="key === 'keypad'">
                            <svg xmlns="http://www.w3.org/2000/svg" width="70" height="50" fill="currentColor" class="bi bi-keyboard" viewBox="0 0 16 16">
                                <path d="M14 5a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V6a1 1 0 0 1 1-1zM2 4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2z"/>
                                <path d="M13 10.25a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25zm0-2a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25zm-5 0A.25.25 0 0 1 8.25 8h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5A.25.25 0 0 1 8 8.75zm2 0a.25.25 0 0 1 .25-.25h1.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-1.5a.25.25 0 0 1-.25-.25zm1 2a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25zm-5-2A.25.25 0 0 1 6.25 8h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5A.25.25 0 0 1 6 8.75zm-2 0A.25.25 0 0 1 4.25 8h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5A.25.25 0 0 1 4 8.75zm-2 0A.25.25 0 0 1 2.25 8h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5A.25.25 0 0 1 2 8.75zm11-2a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25zm-2 0a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25zm-2 0A.25.25 0 0 1 9.25 6h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5A.25.25 0 0 1 9 6.75zm-2 0A.25.25 0 0 1 7.25 6h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5A.25.25 0 0 1 7 6.75zm-2 0A.25.25 0 0 1 5.25 6h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5A.25.25 0 0 1 5 6.75zm-3 0A.25.25 0 0 1 2.25 6h1.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-1.5A.25.25 0 0 1 2 6.75zm0 4a.25.25 0 0 1 .25-.25h.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-.5a.25.25 0 0 1-.25-.25zm2 0a.25.25 0 0 1 .25-.25h5.5a.25.25 0 0 1 .25.25v.5a.25.25 0 0 1-.25.25h-5.5a.25.25 0 0 1-.25-.25z"/>
                            </svg>
                        </template>
                        <template v-else-if="key === 'search'">
                            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                            </svg>
                        </template>
                        <template v-else>{{ key }}</template>
                    </button>
                </div>
            </div>
        </transition>
        
        <!-- <div class="btnRange">
            <button class="BackBtn" @click="closeModal">
                <svg xmlns="http://www.w3.org/2000/svg" width="35px" height="35px" fill="none" stroke="currentColor" stroke-width="1" class="bi bi-arrow-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                        </svg>
                X
            </button>
        </div> -->
    </div>
    <!--  -->

</template>
  
<script setup>

import { ref, defineAsyncComponent } from 'vue';
import * as Hangul from 'hangul-js';
import {storeToRefs} from "pinia";
import { useFlagStore } from "@/stores/flag.js";
import { useSearchListStore } from '@/stores/searchList';

const flagSwitch = useFlagStore();
const searchListStore = useSearchListStore();
const { keyWord } = storeToRefs(searchListStore);

const SearchResultScreen = defineAsyncComponent(() => import('@/components/searchResultScreen.vue'));
const searchScreenFlag = ref(false);

const searchWord = ref('');
var listChar = [];

const keypadflag = ref(false);
const isShiftActive = ref(false); // shift 키 활성화 상태

const searchHeaderStyle = ref({
    marginTop: '8%'
});

const appendWord =(char) =>{
    listChar.push(char);
    keyWord.value = Hangul.a(listChar);
}

const backInput = () => {
    
    if (keyWord.value.length > 0) {
        keyWord.value = keyWord.value.slice(0, -1);
        listChar = Hangul.d(keyWord.value);
    }
}

const search = () => {
    keypadflag.value = !keypadflag.value;
    searchHeaderStyle.value.marginTop = '8%';
    //searchScreenFlag.value = false;

    listChar=[];
    keyWord.value='';
}

const clearInput = () => {
    // keyWord.value = keyWord.value.slice(0, -keyWord.value.length);
    keyWord.value = '';
    listChar = [];
}

const closeModal = () =>{
    console.log('hi');
    flagSwitch.modalflagChange();
    keypadflag.value = false;
}

const searchKeyWord = () =>{
    if(keyWord.value){
        searchListStore.productSearch();
        searchHeaderStyle.value.marginTop = '0%';
        keypadflag.value = true;
        searchScreenFlag.value = true;

        // listChar=[];
        // keyWord.value='';
    }
    else alert('검색어를 입력해주세요')
}

const toggleShift = () => {
    isShiftActive.value = !isShiftActive.value; // shift 상태 토글
};

const changeKeypadFlag = () => {
    keypadflag.value = !keypadflag.value;
}

const keyboardLayout = [
    ['ㅂ', 'ㅈ', 'ㄷ', 'ㄱ', 'ㅅ', 'ㅛ', 'ㅕ', 'ㅑ', 'ㅐ', 'ㅔ'],
    ['ㅁ', 'ㄴ', 'ㅇ', 'ㄹ', 'ㅎ', 'ㅗ', 'ㅓ', 'ㅏ', 'ㅣ'],
    ['shift', 'ㅋ', 'ㅌ', 'ㅊ', 'ㅍ', 'ㅠ', 'ㅜ', 'ㅡ', 'back'],
    ['keypad', 'space', 'search']
];

const keyboardLayoutBig = [
    ['ㅃ', 'ㅉ', 'ㄸ', 'ㄲ', 'ㅆ', 'ㅛ', 'ㅕ', 'ㅑ', 'ㅒ', 'ㅖ'],
    ['ㅁ', 'ㄴ', 'ㅇ', 'ㄹ', 'ㅎ', 'ㅗ', 'ㅓ', 'ㅏ', 'ㅣ'],
    ['shift', 'ㅋ', 'ㅌ', 'ㅊ', 'ㅍ', 'ㅠ', 'ㅜ', 'ㅡ', 'back'],
    ['keypad', 'space', 'search']
];

</script>

<style scoped>
.background {
    display: flex;
    /* position: relative; */
    flex-direction: row;
    justify-content: center;
    align-items: center;
    /* align-items: center; */
    height: 99%;
    background-color: white;
}

.left {
    width: 77%;
    height: 96%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.right {
    width: 19%;
    height: 96%;
    /* margin-right: auto; */
}

.ad-section {
    margin-left: 10%;
    height: 102%;
    width: 100%;
    display: flex;
    justify-content: center;
}

.img {
    border-radius: 20px;
}
.searchHeader{
    /* position: absolute; */
    width: 100%;
    height: 8%;
    background:none;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}



.searchBarbox{
    display: flex;
    flex-direction: row;
    align-items: center;
    width:100%;
    height:100%;
    font-size: 40px;
    border-radius: 10px;
    background-color: #fff;
    border : 2px solid black;
    padding-left: 5px;
}

.searchImage{
    margin-left:10px;
    width:5%;
    height: 60%;
}

.searchWordRange{
    margin-left: 20px;
    font-size: 25px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.numBtn {
    margin-left: auto;
    margin-right: 10px;
    background-color: white;
    border: none;
    width: 40px;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.keyboardRange{
    position: absolute;
    top: 57%;
    left: 0%;
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    align-items: center;
    width: 81%;
    height: 43%;
    background:none;
    background-color: rgb(208, 211, 217);
    /* opacity: 70%; */
    /* margin-top:22%; */
}

.kbrow{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width:100%;
    height:33%;
    margin:0.5%;
}

.keycap{
    font-size: 40px;
    /* font-weight: bold; */
    width: 85px;
    height: 70px;
    /* margin:0.5%; */
    margin: 0% 0.7%;
    border-radius:10px;
    background-color: white;
    border: none;
}

.keycap:hover {
    background-color: #95989c;
    color: white;
}

.keycap:active {
    transform: scale(0.95); /* 버튼 축소 효과 */
}

.spacekey{
    width: 480px;
}

.back {
    margin-left: 30px;
    width: 110px;
    background-color: #AAB0BB;
    padding-bottom: 10px;
}

.shift {
    margin-right: 30px;
    width: 110px;
    background-color: #AAB0BB;
    padding-bottom: 10px;
}

.keypad {
    width: 235px;
}

.search {
    width: 235px;
    background-color: #6fd7f7;
}
.btnRange{
    position: absolute;
    right: 0px;
    top: 5px;
    /* margin:1% 0%; */
    width:5%;
    height:5%;
    /* display: flex; */
    /* flex-direction: row; */
    /* align-items: center; */
    /* justify-content: center; */
    font-size: 32px;
}

.SearchBtn{
    margin-left: 5px;
    width: 100%;
    height:85%;
    background-color:#80E1FF ;
    border-radius: 10px;
    border: 2px solid #FFFFFF;
    color: #FFFFFF;
    font-weight: bold;
    font-size: 25px;
}

.content {
    margin-top: 2%;
    width: 100%;
    height: 83%;
}
.BackBtn{
    width:100%;
    height:100%;
    background-color: white;
    border: none;
    color:red;
    font-weight: bold;
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

.blinking-cursor {
    font-weight: 100;
    font-size: 44px;
    /* color: rgb(173, 169, 169); */
    -webkit-animation: 1s blink step-end infinite;
    -moz-animation: 1s blink step-end infinite;
    -ms-animation: 1s blink step-end infinite;
    -o-animation: 1s blink step-end infinite;
    animation: 1s blink step-end infinite;
}

@keyframes blink {
    from, to { color: transparent }
    50% { color: rgb(154, 150, 150) }
}

</style>
