<template>
    <div class="call-modal" @click="closeCallModal">
        <div class="callModalScreen" @click.stop="">
            <div class="callModalText">구매를 종료하면<br>장바구니 목록이 초기화됩니다</div>
            <div class="callModalHeader">쇼핑을 종료하시겠습니까?</div>
            <div class="btnCon">
                <button class="BackBtn1" @click="logout">종료 하기</button>
                <button class="BackBtn2" @click="closeCallModal">뒤로 가기</button>
            </div>
        </div>
    </div>
</template>

<script setup>

import { useRouter } from 'vue-router';
const router = useRouter();
import { useFlagStore } from "@/stores/flag.js";
import { useCartStore } from '@/stores/cart';
import { useLoginStore } from '@/stores/login';
const flagSwitch = useFlagStore();
const cartStore = useCartStore();
const loginStore = useLoginStore();

const closeCallModal = () =>{
    flagSwitch.logoutmodalflagChange();
}

const logout = () =>{
    cartStore.deleteAllProduct();
    loginStore.logout();
    closeCallModal();
    router.push('/');
}

</script>

<style scoped>

.call-modal {
    /* 모달 오버레이의 스타일 */
    position: fixed;
    top: 0;
    left: 0;
    width: 1600px;
    height: 900px;
    background:none;
    display: flex;
    background-color: rgba(0, 0, 0, 0.08);
    flex-direction: column;
    justify-content: center;
    align-items: center;
    z-index: 3;
}

.callModalScreen{
    width:30%;
    height:40%;
    border-radius: 10px;
    background: #FFF;
    padding:1% 1%;
    flex-shrink: 0;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content:center;
}

.callImgRange{
    position:absolute;
    width:150px;
    height:150px;
    background-color: #80E1FF;
    border-radius: 50%;
    top:25%;
    display: flex;
    align-items: center;
    justify-content: center;
}

.callImage{
    fill:#FFF;
    width:65%;
    height:65%;
}
.callModalText {
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    padding-bottom : 10%; 
}
.callModalHeader{
    height: 25%;
    position: relative;
    font-size: 30px;
    font-weight: bold;
}

.callModalBody{
    height: 30%;
    position: relative;
    font-size: 24px;
}

.btnCon{
    width:80%;
    height: 20%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.BackBtn1{
    width:45%;
    height:100%;
    background-color:#FFFFFF ;
    border-radius: 10px;
    border: 2px solid #9d9d9d;
    color: #000000;
    font-size: 32px;

}
.BackBtn2{
    width:45%;
    height:100%;
    background-color:#80E1FF ;
    border-radius: 10px;
    border: 2px solid #FFFFFF;
    color: #FFFFFF;
    font-size: 32px;
}

</style>