<template>
    <!-- <LoginHead /> -->
    <div class="phoneGround" @click="keyRangeShow('off')">
        <!-- <div class="updown"> -->
            <!-- <div class="inside left"> -->
                <div class="pw pw1">
                    <div class="imgContainer">
                        <img class="img" src="/image/loginSelect/passwordIcon.png">
                    </div>
                    <button class="btn">비밀번호로 로그인</button>
                </div>
            <!-- </div> -->
                <div class="pw pw2">
                    <div class="imgContainer">
                        <img class="img2" src="/image/loginSelect/fingerprintIcon.png">
                    </div>
                    <button class="btn">지문으로 로그인</button>
                </div>
        
        <button class="backBtn" @click="$router.push('/home')">
            <img class="back" src="/image/loginSelect/back.png">
        </button>
    </div>
</template>

<script setup>

import { useRouter } from 'vue-router';
const router = useRouter();

</script>

<style scoped>
.phoneGround{
position: relative;
display: flex;
flex-direction: row;
align-items: center;
justify-content: center;
width: 100%;
height: 100%;
flex-shrink: 0;
background: rgb(235, 244, 246);
}

.leftSide {
    width:45%;
    height: 100%;
    background-color: #4892f3;
}

.rightSide {
    width:55%;
    height: 100%;
    background-color:rgb(235, 244, 246);
}

.inside {
    width: 50%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    /* align-items: center; */
}

.pw {
    position: absolute;
    width: 45%;
    height: 90%;
    background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 20px;
    border: 3px solid rgb(195, 193, 193);
    box-shadow: 1px 1px gray;
}

.pw1 {
    left: 4%;
}

.pw2 {
    left: 51%;
}

.imgContainer {
    width: 250px;    
    height: 250px;
    border-radius: 50%;
    border: 2px solid rgb(172, 170, 170);
    display: flex;
    justify-content: center;
    align-items: center;
}

.img {
    width: 120%;
    height: 100%;
    margin-left: 16px;
    margin-top : 15px;
}

.img2 {
    width: 120%;
    height: 120%;
}

.btn {
    width: 45%;
    height: 15%;
    border-radius: 10px;
    border: none;
    background: #78EAEF;
    font-weight: 600;
    margin-top: 9%;
    font-size: 19px;
    color: rgb(99, 99, 99);
}

.btn:active {
      transform: scale(0.95); /* 버튼 축소 효과 */
}

.backBtn{
    /* margin-left: auto; */
    /* margin-right:3%; */
    /* height:80%; */
    position: absolute;
    top: 2%;
    left: 89%;
    width:10%;
    height: 10%;
    font-size: 30px;
    border-radius: 10px;
    border: none;
    background-color:rgb(235, 244, 246);
}

.back {
    width: 100%;
    height: 100%;
    background-color: rgb(235, 244, 246);
}
</style>