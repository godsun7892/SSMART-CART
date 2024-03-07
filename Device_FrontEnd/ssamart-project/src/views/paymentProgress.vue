<template>
    <div class="MainPage">
        <div class="MainStage">
            <div class="MainStageHeader">
                <div class="MainLogo"><img src="/image/logo.png" alt="" width="70%"></div>
            </div>
            <div class="MainStageBody">
                <div class="progress-circle-container">
                    <div class="progress-circle"></div>
                    <div class="circle">
                        <img class="Lockimg" src="/image/lock.svg" alt="" width="50%">
                    </div>

                </div>
                <div class="processText1">
                    잠시만 기다려주세요
                </div>
                <div class="processText2">
                    안전하게 결제 진행 중입니다.
                </div>
            </div>
        </div>
    </div>
</template>
 
<!-- <script>
export default {
    name: "ProgressCircle",
};

</script> -->
 
<script setup>

import { useRouter } from 'vue-router';
const router = useRouter();

const name = "ProgressCircle";

setTimeout(function(){
    router.push('/paymentSuccess')
}, 3000)

</script>

<style scoped>
.MainPage {
    /* background-color: antiquewhite; */
    width: 100%;
    height: 100%;
}

.MainStage {
    display: flex;
    flex-direction: column;
    /* background-color:aquamarine; */
    width: 100%;
    height: 100%;
    align-items: center;
    justify-content: center;
}

.MainStageHeader {
    display: flex;
    flex-direction: row;
    /* background-color:darkcyan; */
    width: 100%;
    height: 20%;
    align-items: center;
    justify-content: center;
}

.MainLogo {
    text-align: center;
    margin: 0% 1%;
    padding-right: 3%;
}

.MainStageBody {
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
    justify-content: flex-start;
    width: 100%;
    height: 80%;
    margin-top: 1%;
}

.circle {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 50%;
    height: 50%;
    border-radius: 50%;
    background-color: #78EAEF;
    margin-left: 25%;
    margin-top: 25%;
    box-shadow: 0 5px 5px gray;
}

.progress-circle-container {
    position: relative;
    width: 500px;
    height: 500px;
}

.progress-circle {
    position: absolute;
    width: 100%;
    height: 100%;
    border-radius: 50%;
    border: 7px solid #ccc;
    /* 프로그레스 서클의 테두리 스타일 및 색상 설정 */
    border-top-color: #0cd4df;
    /* 프로그레스 서클의 채우는 부분 색상 설정 */
    animation: spin 1s linear infinite;
    /* 회전 애니메이션 설정 */
}

.processText1 {
    padding-top: 2%;
    text-align: center;
    font-size: 32px;
    font-weight: 900;
}

.processText2 {
    text-align: center;
    font-size: 24px;
    font-weight: 400;
    color: #808080;
}

.Lockimg {
    animation: shake 1s ease infinite alternate;
}

@keyframes spin {
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
}

@keyframes shake {

    /* @keyframes를 이용해 shake의 각도를 좌우 15deg로 조정 */
    10% {
        transform: rotate(15deg);
    }

    20% {
        transform: rotate(-15deg);
    }

    30% {
        transform: rotate(15deg);
    }

    40% {
        transform: rotate(-15deg);
    }
}</style>