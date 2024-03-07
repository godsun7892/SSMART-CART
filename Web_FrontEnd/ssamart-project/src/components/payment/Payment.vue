<template>
    <div class="btnContainer">
        <div class="btnWrapper"><button class="actionButton" @click="MethodModalopen">결제수단 관리</button></div>
        <div class="btnWrapper"><button class="actionButton" @click="goToPaymentDetail">결제내역 조회</button></div>
    </div>
    <PayMethod v-if="payMethodFlag"/>
</template>


<script setup>
import { useRouter } from 'vue-router'
import { storeToRefs } from 'pinia';
import { usePaymentStore } from '@/stores/paymentStore'
import PayMethod from '@/components/payment/PayMethodSelect.vue';

const router = useRouter()
const paymentStore = usePaymentStore()
const { payMethodFlag } = storeToRefs(paymentStore);


const MethodModalopen = () =>{
    payMethodFlag.value = true;
}

const goToPaymentDetail = () => {
    router.push({ name: 'PaymentDetail' })
}


</script>

<style scoped>
body {
    background-image: url('https://static.toss.im/ml-illust/img-back_005.jpg');
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 500;
    font-style: normal;
  }

  .btnContainer {
    display: flex;
    flex-direction: column; /* Changed from row to column */
    justify-content: space-around;
    align-items: center; /* Center the buttons */
    width: 100%; /* Use full container width */
    height: 100%;
}

.btnWrapper {
    width: 100%; /* Ensure wrapper uses full width */
    display: flex;
    justify-content: center; /* Center the button inside the wrapper */
    margin-bottom: 10px; /* Add some space between the buttons */
}

.actionButton {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 700;
  font-style: normal;
  padding: 20px 120px;
  margin: 20px;
  border: 2px solid #78EAEF; /* 테두리 제거 */
  font-size: 16px;
  cursor: pointer;
  border-radius: 20px;
  background-color: white; /* 초기 배경색 */
  color: #1cacb6bf; /* 초기 텍스트 색상 */
  position: relative; /* 위치 기준점 설정 */
  overflow: hidden; /* 내부 요소가 박스를 넘어갈 경우 숨김 처리 */
  transition: color 0.4s, transform 0.5s ease; /* 텍스트 색상 및 변형 전환 효과 */
}

.actionButton::before {
  content: '';
  position: absolute;
  left: -100%; /* 시작 위치를 왼쪽으로 조정 */
  top: 0;
  width: 100%;
  height: 100%;
  background-color: #78EAEF; /* 호버 효과 배경색 */
  z-index: -1;
  transition: all 0.4s cubic-bezier(.5, .24, 0, 1); /* 전환 효과 적용 */
}

.actionButton:hover::before {
  left: 0; /* 호버 시 배경이 버튼을 가득 채우도록 설정 */
}

.actionButton:hover {
  color: white; /* 호버 시 텍스트 색상을 흰색으로 변경 */
  transform: translateX(0px); /* 버튼이 오른쪽으로 슬라이드되는 효과 제거 */
}


.p {
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 500;
    font-style: normal;
  padding: 0;
  margin: 0;
  font-family: Toss Product Sans, -apple-system, BlinkMacSystemFont,
    Bazier Square, Noto Sans KR, Segoe UI, Apple SD Gothic Neo, Roboto,
    Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji,
    Segoe UI Symbol, Noto Color Emoji;
  color: #4e5968;
  word-break: keep-all;
  word-wrap: break-word;
}
.h4 {
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 500;
    font-style: normal;
  font-size: 20px;
  font-weight: 700;
  color: #333D4B;
}
.wrapper {
  max-width: 800px;
  margin: 0 auto;
}
.button {
  color: black;
  background: transparent;
  background-color: white;
  position: absolute;
  z-index: -1;
  font-size: 15px;
  font-weight: 400;
  line-height: 18px;
  white-space: nowrap;
  text-align: center;
  /* vertical-align: middle; */
  cursor: pointer;
  border: 1px solid #ccc;
  user-select: none;
  text-decoration: none;
  border-radius: 20px;
  padding: 11px 16px;
  box-shadow: inset 0px 0px 0px #78EAEF;
  display: block;
  -webkit-transition: all 0.4s cubic-bezier(.5, .24, 0, 1);
  transition: all 0.4s cubic-bezier(.5, .24, 0, 1)
}

.button:hover {
  color: #fff;
  box-shadow: inset 300px 0px 0px #78EAEF;
}

.title {
  margin: 0 0 4px;
  font-size: 24px;
  font-weight: 600;
  color: #4e5968;
}
.result {
  flex-direction: column;
  align-items: center;
  text-align: center;
}
.box_section {
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 10px 20px rgb(0 0 0 / 1%), 0 6px 6px rgb(0 0 0 / 6%);
    padding: 40px 30px 50px 30px; 
    margin-top:30px; 
    margin-bottom:50px; 
    color: #333D4B
}
:root {
    --checkable-size: 20px;
    --checkable-input-top: 3px;
    --checkable-input-left: 5px;
    --checkable-input-width: 14px;
    --checkable-input-height: 10px;
    --checkable-input-svg: url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M1.343 4.574l4.243 4.243 7.07-7.071' fill='transparent' stroke-width='2' stroke='%23FFF' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E");
    --checkable-label-text-padding: 8px;
    --indeterminate-checkable-input-top: 7px;
    --indeterminate-checkable-input-left: 5px;
    --indeterminate-checkable-input-width: 14px
}

:root .checkable--small {
    --checkable-size: 20px;
    --checkable-input-top: 2px;
    --checkable-input-left: 4px;
    --checkable-input-width: 12px;
    --checkable-input-height: 9px;
    --checkable-input-svg: url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M1.286 3.645l3.536 3.536 5.892-5.893' fill='transparent' stroke-width='2' stroke='%23FFF' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E");
    --indeterminate-checkable-input-top: 5px;
    --indeterminate-checkable-input-left: 4px;
    --indeterminate-checkable-input-width: 12px
}

.checkable {
    position: relative;
    display: flex
}

.checkable+.checkable {
    margin-top: 12px
}

.checkable--inline {
    display: inline-block
}

.checkable--inline+.checkable--inline {
    margin-top: 0;
    margin-left: 18px
}

.checkable__label {
    display: inline-block;
    max-width: 100%;
    min-height: 20px;
    min-height: var(--checkable-size);
    line-height: 1.6;
    padding-left: 20px;
    padding-left: var(--checkable-size);
    margin-bottom: 0;
    padding-top: 0;
    padding-bottom: 0;
    color: #4e5968;
    color: var(--grey700);
    cursor: pointer
}

.checkable__input {
    position: absolute;
    margin: 0 0 0 -20px;
    margin: 0 0 0 calc(var(--checkable-size)*-1);
    top: 4px;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border: none;
    cursor: pointer
}

.checkable__input:after,.checkable__input:before {
    content: "";
    position: absolute
}

.checkable__input:before {
    top: -4px;
    left: 0;
    width: 20px;
    width: var(--checkable-size);
    height: 20px;
    height: var(--checkable-size);
    border: 2px solid #d1d6db;
    border: 2px solid #d1d6db;
    background-color: #fff;
    background-color: white;
    transition: border-color .1s ease,background-color .1s ease
}

.checkable__input:after {
    opacity: 0;
    transition: opacity .1s ease;
    top: 3px;
    top: var(--checkable-input-top);
    left: 5px;
    left: var(--checkable-input-left);
    width: 14px;
    width: var(--checkable-input-width);
    height: 10px;
    height: var(--checkable-input-height);
    background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M1.343 4.574l4.243 4.243 7.07-7.071' fill='transparent' stroke-width='2' stroke='%23FFF' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E");
    background-image: var(--checkable-input-svg);
    background-repeat: no-repeat
}

.checkable__input[type=checkbox]:indeterminate:after {
    top: 7px;
    top: var(--indeterminate-checkable-input-top);
    left: 5px;
    left: var(--indeterminate-checkable-input-left);
    width: 14px;
    width: var(--indeterminate-checkable-input-width);
    height: 0;
    border: 1px solid #fff;
    border: 1px solid var(--white);
    border-radius: 1px;
    transform: rotate(0)
}

.checkable__input:focus {
    outline: 0
}

.checkable__input:focus:before,.checkable__input:hover:before {
    background-color: #e8f3ff;
    background-color: #e8f3ff;
    border-color: #3182f6;
    border-color: #3182f6
}

.checkable__input:checked:before,.checkable__input[type=checkbox]:indeterminate:before {
    border-color: #3182f6;
    border-color: #3182f6;
    background-color: #3182f6;
    background-color: #3182f6
}

.checkable__input:checked:after,.checkable__input[type=checkbox]:indeterminate:after {
    opacity: 1
}

.checkable__input:disabled:before {
    background-color: #f2f4f6;
    background-color: var(--grey100);
    border-color: rgba(0,23,51,.02);
    border-color: var(--greyOpacity50)
}

.checkable__input:disabled:checked:before,.checkable__input:disabled[type=checkbox]:indeterminate:before {
    background-color: #e5e8eb;
    background-color: var(--grey200);
    border-color: #e5e8eb;
    border-color: var(--grey200)
}

.checkable__input[type=checkbox]:before {
    border-radius: 6px
}

.checkable__input[type=radio]:before {
    border-radius: 12px
}

.checkable__label-text {
    display: inline-block;
    padding-left: 13px;
    color: #4e5968;

    /* padding-left: var(--checkable-label-text-padding) */
}

.checkable--disabled>.checkable__input {
    cursor: not-allowed
}

.checkable--disabled>.checkable__label {
    color: #b0b8c1;
    color: var(--grey400);
    cursor: not-allowed
}

.checkable--read-only {
    pointer-events: none
}
</style>