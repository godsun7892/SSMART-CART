<template>
    <div class="call-modal" @click="closeCallModal">
        <div class="callModalScreen" @click.stop="">
            <div class="callImgRange">
                <svg xmlns="http://www.w3.org/2000/svg" class="bi bi-telephone-fill callImage" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M1.885.511a1.745 1.745 0 0 1 2.61.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
                </svg>
            </div>
            <div class="callModalHeader">직원을 호출 하였습니다!</div>
            <div class="callModalBody">잠시만 기다려주세요</div>
            <div class="buttonGrp">
                <button class="BackBtn1">다시호출</button>
                <button class="BackBtn2" @click="closeCallModal">창 닫기</button>
            </div>
        </div>
    </div>
</template>

<script setup>

import { useFlagStore } from "@/stores/flag.js";

const flagSwitch = useFlagStore();

const closeCallModal = () =>{
    flagSwitch.callmodalflagChange();
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
    justify-content:end;
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
    animation-name : shake;
    animation-iteration-count:infinite;
    animation-duration : 1s;
}
.callModalHeader{
    height: 15%;
    position: relative;
    font-size: 30px;
    font-weight: bold;
    padding-top : 20px;
}

.callModalBody{
    height: 30%;
    position: relative;
    font-size: 24px;
    padding-top : 20px;
}

.buttonGrp {
    width: 80%;
    height: 20%;
    display : flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom : 10px;
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

@keyframes shake {

    /* @keyframes를 이용해 shake의 각도를 좌우 15deg로 조정 */
    10% {
        transform: rotate(15deg);
    }

    20% {
        transform: rotate(-15deg);
    }

    90% {
        transform: rotate(15deg);
    }

    100% {
        transform: rotate(-15deg);
    }
}

</style>