<template>
    <LoginHead />
    <div class="HomeBackGround">
        <button class='homeBtn User' @click="$router.push('/fingerPrintRequest')">지문<br> 등록하기</button>
        <button class='homeBtn notUser' @click="$router.push('/shoppingBasket')">바로<br> 쇼핑하기</button>
    </div>
</template>
  
  <script setup>
  
  import { useRouter } from 'vue-router';
  const router = useRouter();
  import LoginHead from '@/components/LoginHeader.vue';
  
  </script>
  
  <style scoped>
  
  
  </style>