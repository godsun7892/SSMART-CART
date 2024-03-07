<template>
  <div class="container">
    <img src="/image/SSAMART CART.png" alt="logo">
  </div>
  <div class="container">
    <div>
      <h3>로그인</h3>
        <input type="text" v-model.trim="id" placeholder="id"/>
        <input type="password" v-model.trim="password" placeholder="password"/>
        <button @click="login">로그인</button>
        asdfasd
        sibal
    </div>
  </div>
</template>

<script setup>
import axios from "@/util/http-common.js"
import {ref} from "vue"

const id = ref("")  
const password = ref("")

const login = function() {
  axios.post(`http://localhost:8080/api-user/test`, {
    id: id.value, 
    password: password.value})
  .then((response) => {
    
  })
}

</script>

<style scoped>

</style>