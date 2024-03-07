<template>
    <div class="call-modal">
        <div class="callModalScreen">
            <div class="logoImg">
                <img src="/image/logo.png" class="MainLogo"/>
            </div>
            <!-- 일반적인 로그인 성공 시 메시지 -->
            <div class="callModalHeader" v-if="loginModalNum===1">
                <b>환영합니다 {{username}}님</b>
                <div style="font-size:24px">즐거운 쇼핑 되십시오.</div>
            </div>
            <!-- 지문 등록을 완료한 후 메시지 -->
            <div class="callModalHeader" v-if="loginModalNum===2">
                <b>환영합니다 {{username}}님</b>
                <div style="font-size:24px">지문 등록이 완료되었습니다.</div>
            </div>
            <!-- 지문 등록 페이지에 접속했으나, 지문 등록을 취소할 경우 메시지 -->
            <div class="callModalHeader" v-if="loginModalNum===3">
                <b>환영합니다 {{username}}님</b>
                <div style="font-size:24px">지문 등록이 취소되었습니다.</div>
            </div>
            <!-- 비회원으로 로그인 시 메시지 -->
            <div class="callModalHeader" v-if="loginModalNum===4">
                <b>비회원으로 이용하시겠습니까?</b>
                <div style="font-size:24px">일부 기능이 제한될 수 있습니다.</div>
            </div>
            <div class="btnCon">
                <!-- 버튼 클릭 시 장바구니 페이지로 이동 -->
                <button class="BackBtn" @click="shoppingStart(loginModalNum)">확인</button>
            </div>
        </div>
    </div>
</template>

<script setup>

import { useRouter } from 'vue-router';
const router = useRouter();
import { useLoginStore } from '@/stores/login';
import { storeToRefs } from 'pinia';
import { useSearchListStore } from '@/stores/searchList';
import {useCartStore} from '@/stores/cart';

const loginStore = useLoginStore();
const {loginModalNum, username, loginMessageOn } = storeToRefs(loginStore);

const searchListStore =useSearchListStore();
const cartStore = useCartStore();

const shoppingStart = (num) =>{
    loginMessageOn.value = false; // 로그인 메시지 모달창 off
    searchListStore.wishlistSearch(); // 찜 목록 그룹 전체 불러오기
    cartStore.searchCart();
    // 유저의 결제수단 정보 받아오기
    router.push('/shoppingBasket'); // 장바구니 페이지로 이동
}


</script>

<style scoped>

.call-modal {
    /* 모달 오버레이의 스타일 */
    position: fixed;
    top: 0;
    left: 0;
    width: 1530px;
    height: 860px;
    background:none;
    display: flex;
    background-color: rgba(0, 0, 0, 0.15);
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.callModalScreen{
    width:35%;
    height:45%;
    border-radius: 10px;
    background: #FFF;
    padding:1% 1%;
    flex-shrink: 0;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    align-items: center;
}

.logoImg{
    width:100%;
    height:50%;
}

.MainLogo{
    width:100%;
    height: 100%;
}

.callModalHeader{
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    height: 30%;
    position: relative;
    font-size: 28px;
    padding-bottom: 4%;
    /* font-weight: bold; */
}


.btnCon{
    width:100%;
    height: 20%;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}

.BackBtn{
    width:30%;
    height:80%;
    background-color:#80E1FF ;
    border-radius: 30px;
    border: 2px solid #FFFFFF;
    color: #FFFFFF;
    font-size: 32px;
    /* font-weight: bold; */
}

</style>