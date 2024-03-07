<template>
    <LoginHead />
    <div class="HomeBackGround">
        <button class='homeBtn User' @click="$router.push('/phone')">회원</button>
        <button class='homeBtn notUser' @click="$router.push('/shoppingBasket')">비회원</button>
    </div>
</template>

<script setup>

import { useRouter } from 'vue-router';
const router = useRouter();
import LoginHead from '@/components/LoginHeader.vue';

</script>

<style scoped>


</style>