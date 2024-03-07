<template>
    <div class="product-location-modal" @click="closeModal" >
        <div class="modal-content" @click.stop="">
            <div class="btnRange">
                <button class="BackBtn" @click="closeModal">
                    <!-- <svg xmlns="http://www.w3.org/2000/svg" width="35px" height="35px" fill="none" stroke="currentColor" stroke-width="1" class="bi bi-arrow-left" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                            </svg> -->
                    X
                </button>
            </div>
            <div class="header">
                <b>현재 계신 위치</b>를 클릭해주세요
            </div>
            <ProductLocation />
        </div>
        
    </div>
</template>

<script setup>

import {storeToRefs} from "pinia";
import { useFlagStore } from "@/stores/flag.js";
import { useSearchListStore } from "@/stores/searchList";
import searchScreen from "@/components/searchScreen.vue";
// import searchResult from "@/components/searchResultScreen.vue";
import ProductLocation from "@/components/ProductLocationModalSelect.vue";
import HotDealScreen from "@/components/HotDeal2.vue";

const flagSwitch = useFlagStore();
const {screenIndex} = storeToRefs(flagSwitch);

const searchListStore = useSearchListStore();

const closeModal = () =>{
    flagSwitch.selectlocationmodalflagChange();
    // screenIndex.value = 0;
}

</script>

<style scoped>

.product-location-modal {
    /* 모달 오버레이의 스타일 */
    position: fixed;
    top: 0;
    left: 0;
    width: 1600px;
    height: 860px;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    flex-direction: column;;
    justify-content: center;
    align-items: center;
    z-index: 3;
}

.modal-content {
    /* 모달 내용의 스타일 */
    position:relative;
    width: 87%;
    height: 90%;
    background-color: white;
    padding: 10px;
    border-radius: 10px;
    border : 5px solid black;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}


.modal-buttons {
    margin-left: auto;
    margin-right: auto;
    display: flex;
    width: 78%;
    height: 9%;
    justify-content: center;
    border-bottom: 3px solid #D9D9D9;
    /* margin-bottom: 20px; 버튼과 내용 사이의 간격 */
}

.modal-buttons button {
    width: 350px;
    margin-right: 5px;
    padding: 0;
    /* border: 1px solid #ccc; 경계선 */
    /* border-top-left-radius: 10px;  */
    /* border-top-right-radius: 10px; */
    font-size: 25px;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.3s; /* 애니메이션 효과 */
}

.modal-buttons button:last-child {
    margin: 0px;
}

.modal-buttons button:hover {
    background-color: rgb(241, 239, 239); /* 호버 배경색 */
    transform: translateY(-2px); /* 호버 시 버튼이 약간 위로 움직임 */
    transform: translateY(2px); /* 호버 시 버튼이 약간 위로 움직임 */
}

.modal-buttons button:active {
    background-color: #ddd; /* 활성화된 버튼의 배경색 */
    border-color: #aaa; /* 활성화된 버튼의 경계선 색상 */
}

.selectBtn{
    background-color: white;
    border-width: 0;
    /* border-bottom: 5px solid #DADADA; */
}

.whenButtonClicked {
    border : none;
    background-color: white;
    border-bottom: 5px solid #80E1FF;
    font-weight: 40px;
    color: #80E1FF;
}
.mapBtn {
    background-color: #BCB5B5;
    border-width: 0;
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
    z-index: 1000;
}

.BackBtn{
    width:100%;
    height:100%;
    background-color: white;
    border: none;
    color:red;
    font-weight: bold;
}

.header {
    margin-top: 20px;
    margin-bottom: 10px;
    font-size: 35px;
}
</style>