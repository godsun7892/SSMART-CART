<template>
    <div class="MainPage" @click="manuallyLogout">
        <div class="MainStage">
            <div class="MainStageHeader">
                <div class="MainLogo"><img src="/image/logo.png" alt="" width="70%"></div>
            </div>
            <div class="MainStageBody">
                <div class="circle">
                    <img class="Lockimg" src="/image/check.svg" alt="" width="80%">
                </div>
                <div class="processText1">
                    {{(Math.floor(totalPrice / 10) * 10).toLocaleString('ko-KR')}}원 결제 완료.
                </div>
                <div class="processText2">
                    설정하신 결제 수단으로 결제 완료하였습니다. 감사합니다.
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
import { ref, onUpdated, onUnmounted, onMounted } from 'vue';
import { useLoginStore } from '@/stores/login';
import { useRouter } from 'vue-router';
import { useCartStore } from '@/stores/cart';
import { storeToRefs } from 'pinia';
const router = useRouter();

const cartStore = useCartStore();
const { totalPrice } = storeToRefs(cartStore);

const loginStore = useLoginStore();
const name = "ProgressCircle";

const autologout = setTimeout(()=>{
    cartStore.deleteAllProduct();
    loginStore.logout();
    
}, 20000);

const manuallyLogout = () =>{
    cartStore.deleteAllProduct();
    loginStore.logout();
}

onUnmounted(() => {
	clearTimeout(autologout);
})

</script>

<style scoped>
.MainPage {
    display: flex;
    flex-direction: row;
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
    width: 400px;
    height: 400px;
    border-radius: 50%;
    background-color: #78EAEF;
    box-shadow: 0 5px 5px gray;
    margin-top: 3%;
}

.processText1 {
    padding-top: 2%;
    text-align: center;
    font-size: 32px;
    font-weight: 900;
}

.processText2 {
    padding-top: 1%;
    text-align: center;
    font-size: 24px;
    font-weight: 400;
    color: #808080;
}

.Lockimg {
    opacity: 0;
    animation: dropIn 8s ease forwards infinite;
}

@keyframes dropIn {
    20% {
        opacity: 0;
        transform: translateY(-100px);
    }

    30%,
    100% {
        opacity: 1;
        transform: translateY(0%);
    }

    47% {
        transform: rotate(-15deg);
        ;
    }

    56% {
        transform: rotate(0deg);
    }

    65% {
        transform: rotate(15deg);
    }

    73% {
        transform: rotate(0deg);
    }

    82% {
        transform: rotate(-15deg);
    }

    90% {
        transform: rotate(0deg);
    }
}</style>