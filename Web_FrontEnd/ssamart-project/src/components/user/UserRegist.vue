<template>
    <!-- 전체 -->
    <div class="sign-wrap">
      <!-- 로고 -->
      <div class="sign-logo">
        <a href="/">
            <img src="/main/logo/SSAMART_CART_logo.png" alt="logo">
        </a>
      </div>
  
      <!-- 회원가입 폼 -->
      <div class="sign-grid">
        <div class="sign-item">
          <label for="userId"><span>*</span> 휴대전화 번호 (ID)</label>
          <input type="tel" id="userId" v-model="userId" @input="formatUserId" placeholder="010-0000-0000">
        </div>
        <div class="sign-item">
          <label for="password"><span>*</span> 비밀번호</label>
          <input type="password" id="password" v-model="password" placeholder="8자리 이상 특수문자 1자 이상 포함">
        </div>
        <div class="sign-item">
          <label for="password2"><span>*</span> 비밀번호 확인</label>
          <input type="password" id="password2" v-model="password2">
        </div>
        <div class="sign-item">
          <label for="name"><span>*</span> 이름</label>
          <input type="text" id="name" v-model="name">
        </div>
        <div class="sign-item">
          <label for="email"><span>*</span> 이메일</label>
          <input type="email" id="email" v-model="email">
        </div>
        <div class="sign-item">
          <button @click="regist">가입하기</button>
        </div>
      </div>
    </div>
  </template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from "@/stores/userStore"

const userStore = useUserStore();

const userId = ref("") // 실제 데이터를 저장하는 변수
const formattedUserId = ref("") // 사용자에게 보여지는 형식화된 데이터
const password = ref("")
const password2 = ref("")
const name = ref("")
const email = ref("")

// userId를 전화번호 형식에 맞게 변환하는 함수
const formatUserId = (event) => {
  let value = event.target.value.replace(/\D/g, '')
  let formatted = ''

   // 전화번호 형식에 맞게 숫자를 그룹화합니다.
   if (value.length <= 3) {
    formatted = value;
  } else if (value.length <= 7) {
    formatted = `${value.slice(0, 3)}-${value.slice(3)}`;
  } else if (value.length > 7) {
    formatted = `${value.slice(0, 3)}-${value.slice(3, 7)}-${value.slice(7, 11)}`;
  }

  // 입력 필드의 값을 갱신합니다.
  userId.value = formatted;
}

const isEmailValid = (email) => {
    const re =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase())
}

const isPasswordValid = (pwd) => {
    return pwd.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/g.test(pwd)
}

const regist = () => {
    if(
        userId.value === "" ||
        password.value === "" ||
        password2.value === "" ||
        name.value === "" ||
        email.value === "" 
    ) {
        alert("모든 내용을 입력해주세요")
        return
    }

    if(password.value !== password2.value) {
        alert("비밀번호가 일치하지 않습니다.")
        return
    }

    if(!isPasswordValid(password.value)) {
        alert("비밀번호는 8자리 이상, 특수문자 1자 이상 포함해야 합니다.")
        return
    }

    if(!isEmailValid(email.value)) {
        alert("올바른 이메일을 입력해주세요.")
        return
    }


    // 숫자만 추출하여 서버로 전송
    const formattedUserId = userId.value.replace(/-/g, '');

    if(formattedUserId.length !== 11) {
        alert("전화번호는 11자리 숫자여야 합니다.");
        return;
    }

    const user = {
        userId: formattedUserId,
        password: password.value,
        name: name.value,
        email: email.value,
    };
    
    userStore.registUser(user)
}


</script>

<style>
.sign-wrap {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 500;
  font-style: normal;
    width: 40%;
    margin: 50px auto;
    /* background-color: #006B3F; */
    padding-top: 40px;
    border-radius: 20px;
}

.sign-logo {
    width: 400px;
    height: 150px;
    margin: 0 auto;
    text-align: center;
}

.sign-logo img {
    width: 90%;
}

.sign-grid {
    display: grid;
    grid-template-columns: 1fr;
    grid-gap: 10px; /* 열 간의 간격 조절 */
    /* max-width: 350px; */
    /* margin: 0 auto; */
    /* background-color: #f1c5c5; */
}

.sign-item {
    width: 50%; 
    box-sizing: border-box;
    margin: 12px auto;
    position: relative;
}

.sign-item label {
    display: inline-block;
    position: absolute;
    top: -5px;
    left: 14px;
    padding: 10px;
    background: white;
    font-size: 14px;
    color: #888;
    font-weight: bold;
}

.sign-item label span{
    color: #78EAFF;
    vertical-align: -2px;
}

.sign-item input {
    width: 100%;
    border: 0.5px solid #888 !important;
    font-size: 1rem;
    line-height: 2.2;
    letter-spacing: -0.04rem;
    border-radius: 8px;
    padding: 16px;
    margin-top: 15px;
}

.sign-item input::placeholder {
    padding: 0 12px;
    color: #c2c2c2;
    font-size: 12px;
}

.sign-item button {
    width: 100%;
    height: 50px;
    box-sizing: border-box;
    background-color :#78EAFF;
    color: #fff;
    border: none;
    font-size: 1rem;
    line-height: 1.45;
    letter-spacing: -0.04rem;
    border-radius: 30px;
    padding: 13px;
    margin-top: 12px;
    cursor: pointer; /* 클릭 가능한 버튼임을 표시 */
}

.sign-item button:hover{
    /* background-color: #006B3F;   */
    border: 2px solid #78EAFF;
    background-color: #fff;
    color: #000;
    transition-duration:1s;
    cursor: pointer; /* 클릭 가능한 버튼임을 표시 */
  }


</style>