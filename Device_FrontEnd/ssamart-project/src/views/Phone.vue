<template>
    <LoginHead />
    <div class="phoneGround">
        <div class="updown">
            <div>
                <div class="phoneInfo">
                    <div><b>휴대폰 번호</b>를</div>
                    입력하세요.
                </div>

                <input type="text" class="phoneInput" v-model="phoneNumber" placeholder="010 - " maxlength="17" />
                <div class="underline"></div>
                <div class="invalidPhone" v-if="invalidPhone">{{ errorNumMessage }}</div>
            </div>

            <div class="numberSection">
                <button class="numBtn" @click="appendNumber(1)">1</button>
                <button class="numBtn" @click="appendNumber(2)">2</button>
                <button class="numBtn" @click="appendNumber(3)">3</button>
                <button class="numBtn" @click="appendNumber(4)">4</button>
                <button class="numBtn" @click="appendNumber(5)">5</button>
                <button class="numBtn" @click="appendNumber(6)">6</button>
                <button class="numBtn" @click="appendNumber(7)">7</button>
                <button class="numBtn" @click="appendNumber(8)">8</button>
                <button class="numBtn" @click="appendNumber(9)">9</button>
                <button class="numBtn" @click="backInput">
                    <svg xmlns="http://www.w3.org/2000/svg" width="35px" height="35px" fill="none" stroke="currentColor"
                        stroke-width="1" class="bi bi-arrow-left" viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                            d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z" />
                    </svg>

                </button>
                <button class="numBtn" @click="appendNumber(0)">0</button>
                <button class="numBtn" @click="clearInput">
                    <svg xmlns="http://www.w3.org/2000/svg" width="50px" height="50px" fill="currentColor" class="bi bi-x"
                        viewBox="0 0 16 16">
                        <path
                            d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                    </svg>
                </button>
            </div>
        </div>
        <!-- <button class="nextBtn" @click="$router.push('/fingerPrint')">확인</button> -->
        <button class="nextBtn" @click="NumCheck">확인</button>
    </div>
</template>

<script setup>

import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import LoginHead from '@/components/LoginHeader.vue';

const phoneNumber = ref('');
const dataNum = ref('');
const invalidPhone = ref(false);
const errorNumMessage = ref('');
var flag = 0;

const appendNumber = (number) => {
    if (flag == 0) {
        phoneNumber.value = "010 - ";
        dataNum.value = '010';
        errorNumMessage.value = '';
        flag = 1;
    }


    if (phoneNumber.value.length < 17) {
        phoneNumber.value += number;
        dataNum.value += number;
    }

    if (phoneNumber.value.length === 10) {
        phoneNumber.value += ' - ';
    }

};

const clearInput = () => {
    phoneNumber.value = '';
    flag = 0;
}

const backInput = () => {

    var sliceCnt = 0;
    const tL = phoneNumber.value.length;
    if (tL > 7) {
        if (tL === 14 || tL === 13) sliceCnt = -4;
        else sliceCnt = -1;

        phoneNumber.value = phoneNumber.value.slice(0, sliceCnt);
        dataNum.value = dataNum.value.slice(0, -1);
    } else {
        phoneNumber.value = '';
        flag = 0;
    }
    console.log(phoneNumber.value);
    console.log(dataNum.value);
}

const NumCheck = () => {
    if (dataNum.value.length < 11) {
        invalidPhone.value = true;
        errorNumMessage.value = '형식이 올바르지 않습니다.';
    }
    else {
        invalidPhone.value = false;
        console.log(dataNum.value)
        phoneNumber.value = '';
        flag = 0;
        // login();
        // LoadingWithMask();
        router.push('/fingerPrint');
    }
}

function LoadingWithMask() {
    //화면의 높이와 너비를 구합니다.    
    var maskHeight = $(document).height();
    var maskWidth = window.document.body.clientWidth;
    //화면에 출력할 마스크를 설정해줍니다.
    var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg = '';

    loadingImg += "<div id='loadingImg' style='position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);'>";
    loadingImg += "<img src='../../public/image/LoadingImg.gif' style='display: block; margin: 0px auto;'/>";
    loadingImg += "</div>";

    //화면에 레이어 추가    
    $('body')
        .append(mask)
        .append(loadingImg)

    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.    
    $('#mask').css({
        'width': maskWidth,
        'height': maskHeight,
        'opacity': '0.3'
    });

    //마스크 표시    
    $('#mask').show();
    //로딩중 이미지 표시
    $('#loadingImg').show();
}
// const login = function() {
//     axios.post(`http://i10a203.p.ssafy.io:8080/auth/regist`, {
//         userId: 'testid004',
//         name: 'test_user',
//         password : 'testpw001',
//         phoneNumber: `${dataNum.value}`,
//         email : 'testmail@gmail.ssafy',
//         fingerPrint:'testimg',
//     })
//     .then((response) => {
//         console.log(response);
//     })
// }

</script>

<style scoped>
/* 여기서 부터 phone 페이지 */
.phoneMargin {
    margin: 5% 5%;
}

.phoneInfo {
    font-size: 55px;
    width: 450px;
    margin-left: 10%;
    margin-bottom: 30%;
}

.phoneInput {
    font-weight: bold;
    font-size: 30px;
    width: 70%;
    height: 60px;
    border: none;
    outline: none;
    background-color: transparent;
    margin-left: 10%;
}

.underline {
    width: 80%;
    border-bottom: 1px solid black;
    /* 가로선 스타일 및 색상 설정 */
    margin-top: 1%;
    /* 가로선과 입력란 사이의 간격 조절 */
    margin-left: 10%;
}

.invalidPhone {
    margin-top: 1%;
    font-size: 20px;
    color: red;
    margin-left: 10%;
}

.numberSection {
    overflow: hidden;
    width: 30%;
    height: 80%;
    padding: 0 4%;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;

    .numBtn {
        font-size: 38px;
        width: 80px;
        /* 원하는 버튼의 너비 */
        height: 80px;
        /* 버튼의 높이를 너비와 동일하게 설정하여 정사각형 모양 유지 */
        border-radius: 40px;
        /* 버튼의 width 또는 height의 절반으로 설정하여 원 모양 만들기 */
        background-color: #FFF;
        /* 버튼의 배경색을 하얀색으로 설정 */
        border: 1px solid rgba(0, 0, 0, 0.1);
        /* 원형 버튼에 경계선을 추가하려면 이 줄을 유지 */
        margin: 10px;
        /* 필요에 따라 여백 조정 */
        color: #000;
        /* 글자 색상을 검정색으로 설정 */
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;

    }

    .numBtn:hover {
        background-color: darkgray;
        color: gray;
    }

    .numBtn:active {
        transform: scale(0.95);
        /* 버튼 축소 효과 */
    }
}

.nextBtn {
    width: 55%;
    height: 10%;
    margin-bottom: 5%;
    font-size: 30px;
    background-color: #80E1FF;
    border-radius: 30px;
    border: 0;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);

}

#nextBtn:active {
    transform: scale(0.95);
    /* 버튼 축소 효과 */
}

.phoneGround {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 85%;
    flex-shrink: 0;
    background: rgba(217, 217, 217, 0.32);
}

.updown {
    margin: 0% 5%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 80%;
    flex-shrink: 0;
}</style>