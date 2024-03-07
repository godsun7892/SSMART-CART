<template>
  <!-- 전체 -->
  <div class="login-wrap">
    <!-- 로고 -->
    <div class="login-logo">
      <a href="/">
        <img src="/main/logo/SSAMART_CART_logo.png" alt="logo">
      </a>
    </div>

    <!-- 폼 -->
    <div class="login-grid">
        <!-- ID 입력 필드에서 formatId 함수를 호출합니다 -->
      <input type="text" placeholder="휴대전화번호 11자리 입력" v-model="formattedId" @input="formatId">
      <input type="password" placeholder="Password" v-model="password" @keyup.enter="login">
      <button class="login" @click="login">LOGIN</button>
      <button class="sign-up" @click="goToSignUp">SIGN UP</button>
    </div> 
  </div>
</template>

<script setup>
import { ref } from "vue"
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router"

const router = useRouter()
const userStore = useUserStore()

const id = ref("") // 서버로 전송될 실제 아이디 값 (하이픈 제거)
const formattedId = ref("") // 사용자에게 보이는 형식화된 아이디 값
const password = ref("")

// 입력값을 형식에 맞게 가공하는 함수
const formatId = (event) => {
  let value = event.target.value.replace(/\D/g, '')
  let formatted = ''

  if(value.length <= 3) {
    formatted = value
  } else if (value.length <= 7) {
    formatted = `${value.slice(0,3)}-${value.slice(3)}`
  } else if (value.length > 7) {
    formatted = `${value.slice(0,3)}-${value.slice(3,7)}-${value.slice(7,11)}`
  }

  formattedId.value = formatted
  id.value = value // 하이픈이 제거된 순수 숫자만 저장
}


const login = () => {
    const user = {
      // userId 변수명은 변경하면 안됨 -> spring이랑 똑같아야함
      userId : id.value, // 하이픈이 제거된 아이디 사용
      password : password.value,
    }
  userStore.setLoginUser(user)
}

const goToSignUp = () => {
  router.push({name: 'UserRegist'})
};
</script>

<style scoped>

  body {
    background-color: linear-gradient(to right, #fc5c7d, #6a82fb);
  }
  .login-wrap {
    width: 40%;
    height: 500px;
    margin: 50px auto;
    /* background-color: #006B3F; */
    padding-top: 60px;
    border-radius: 20px;
    background-color: linear-gradient(to right, #fc5c7d, #6a82fb);
  }

  .login-logo {
      width: 400px;
      height: 200px;
      margin: 0 auto;
      text-align: center;
      padding-top: 100px;
  }

  .login-logo img {
      width: 70%;
  }

  .login-grid {
      display: grid;
      grid-template-columns: 1fr;
      grid-gap: 10px;
      max-width: 350px;
      margin: 0px auto;
      /* background-color: #f1c5c5; */
  }

  .login-grid input {
      width: 100%;
      height: 50px;
      box-sizing: border-box;
      border: 1px solid #78EAFF;
      border-radius: 30px;
      padding: 0 40px;
  }

  .login-grid input::placeholder {
      padding: 0 30px;
      color: #d9d9d9;
  }

  .login-grid button {
      width: 100%;
      height: 50px;
      box-sizing: border-box;
      background-color: #323232;
      color: #fff;
      border: none;
      border-radius: 30px;
      cursor: pointer; /* 클릭 가능한 버튼임을 표시 */
  }
  .login-grid button:last-child {
      background-color :#78EAFF;
      cursor: pointer; /* 클릭 가능한 버튼임을 표시 */
  }

  .login-grid button:hover{
    /* background-color: #006B3F;   */
    border: 2px solid #78EAFF;
    background-color: #fff;
    color: #000;
    transition-duration:1s;
    cursor: pointer; /* 클릭 가능한 버튼임을 표시 */
  }

</style>
