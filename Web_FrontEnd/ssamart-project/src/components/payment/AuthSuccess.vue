<template>
  <div class="methodSuccess">
    <div class=""><h1>결제수단 등록이 완료되었습니다.</h1></div>
    <button @click="goToPayment">이전 창으로</button>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import {usePaymentStore} from '@/stores/paymentStore';
import { useRouter } from 'vue-router'
const router = useRouter();

const paymentStore = usePaymentStore();

// URL에서 바로 authKey를 추출
const extractAuthKeyFromUrl = () => {
  const regex = /authKey=([^&]+)/;
  const matches = window.location.href.match(regex);
  return matches && matches[1] ? matches[1] : null;
};

const extractCustomerKeyFromUrl = () => {
  const regex = /customerKey=([^&]+)/;
  const matches = window.location.href.match(regex);
  return matches && matches[1] ? matches[1] : null;
};

onMounted(()=>{
  const authKey = extractAuthKeyFromUrl();
  const customerKey = extractCustomerKeyFromUrl();
  const userSeq = sessionStorage.getItem('userSeq');
  // authKey 값이 있는 경우, 추가 처리를 수행
  if (authKey && customerKey) {
    console.log('Extracted authKey:', authKey);
    console.log('customerKey :', customerKey);
    // 여기에서 authKey를 사용한 로직을 추가하세요.

    paymentStore.processPayment(authKey, customerKey);

  }
})


const goToPayment = () => {
      const userSeq = sessionStorage.getItem('userSeq');
      if(userSeq) {
        router.push({ name: 'Payment', params: { userSeq: userSeq }})
      }
    };

</script>

<style scoped>

.methodSuccess{
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  display: flex;
  flex-direction: column;
}

</style>