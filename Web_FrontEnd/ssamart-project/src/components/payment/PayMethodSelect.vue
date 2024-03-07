<template>
    <div class="call-modal">
        
        <div class="callModalScreen" @click.stop="">
            <div class="card-list-header">
                <h1>SSAMART</h1>
                <div>결제수단 관리</div>
                <button @click="closeCallModal" class="closeBtn">창닫기</button>
            </div>
            
            <div class="pay-method-list">
                <div class="pay-method-length">  등록 계좌 {{ MethodList.length }}개</div>
                <li v-for="card in MethodList" class="pay-method-card">
                    <div class="imgRange"><img v-bind:src="`${card.image}`" alt="" class="pImage"></div>
                    <div class="cardDetail">
                        <div>{{ card.bank }}</div>
                        <div class="CardNum">{{ card.cardNum }}</div>
                    </div>
                    <button class="deleteBtn">삭제</button>
                </li>
            </div>
            <div class="btn_Con">
                <button @click="requestPayment" class="submitBtn"> + 결제수단 등록</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { usePaymentStore } from '@/stores/paymentStore';


const customerKey = ref('thkim_Key_240213') // 고객의 고유 키
const clientKey = 'test_ck_4yKeq5bgrpBMv2ezLyMA3GX0lzW6'
const tossPayments = TossPayments(clientKey)
const MethodList = ref([
    {image:"/main/cardImage/card1.png", name:"card1",bank:"국민은행",cardNum:"9490-9402-2352-3245"},
    {image:"/main/cardImage/card2.png", name:"card2",bank:"신한은행",cardNum:"102-45-345934-389"},
])

const paymentStore = usePaymentStore();

const closeCallModal = ()=> {
    paymentStore.payMethodModalSwitch()
}

function requestPayment() {
    // TossPayments 객체 초기화
    paymentStore.payMethodModalSwitch()
    // 결제 요청
  tossPayments.requestBillingAuth('카드', {
    customerKey: customerKey.value,
    successUrl: window.location.origin + '/pay/success',
    failUrl: window.location.origin + '/pay/fail',
  }).then((result) => {
    // 결제창 호출 결과 처리
    paymentStore.processPayment(customerKey.value, { /* 주문 정보 */ });
  }).catch((error) => {
    console.error('결제창 호출 실패:', error);
  });
}



</script>

<style scoped>

.call-modal {
    /* 모달 오버레이의 스타일 */
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background:none;
    display: flex;
    background-color: rgba(0, 0, 0, 0.08);
    flex-direction: column;
    justify-content: center;
    align-items: center;
    z-index: 3;
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 600;
    font-style: normal;
}

.callModalScreen{
    width:500px;
    height:640px;
    border-radius: 10px;
    background: #FFF;
    flex-shrink: 0;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content:center;
    margin: 10px 10px;
}

.closeBtn{
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 600;
    font-style: normal;
    position:absolute;
    top: 20px;
    right:20px; 
    margin-right: 10px;
    background-color: white;
    color: rgba(167, 10, 10, 0.691);
    border: 1px solid rgba(167, 10, 10, 0.691);
}

.closeBtn:hover {
    color: white;
    background-color: rgba(167, 10, 10, 0.691);
    transition: 0.3s
}

.card-list-header{
    display: flex;
    flex-direction: column;
    align-items: center;
    width:100%;
    position:relative;
    margin: 10px 10px;
}

.pay-method-length{
    height: 5%;
    width:100%;
    padding-left: 20px;
    margin: 10px;
}

.pay-method-list{
    background-color: #F5F5F5;
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    height:90%;
    overflow: hidden;
}

.pay-method-card{
    background-color: #FFF;
    display: flex;
    flex-direction: row;
    align-items: center;
    width:100%;
    height: 30%;
    border: 1px solid #ccc;

}

.imgRange{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width:30%;
}

.cardDetail{
    margin-left:5%;
    width:45%;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.deleteBtn{
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 600;
    font-style: normal;
    margin-right: 8%;
    width:16%;
    height:24%;
    padding:0px;
    color: rgba(167, 10, 10, 0.691);
    background-color: white;
    border: 1px solid rgba(167, 10, 10, 0.691);
    transition: 0.3s;
}

.deleteBtn:hover {
    color: white;
    background-color: rgba(167, 10, 10, 0.691);
    transition: 0.3s
}

.CardNum{
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 500;
    font-style: normal;
    margin-top:5%;
    font-size: 15px;
}

.pImage{
    top:20px;
    left:10px;
    width:120px;
    height:75px;
    margin-left: 15px;
}

.btn_Con{
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 500;
    font-style: normal;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 8%;
}

.submitBtn {
  position: relative; /* 위치 기준점 설정 */
  overflow: hidden; /* 내부 요소가 박스를 넘어갈 경우 숨김 처리 */
  border: none; /* 테두리 제거 */
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  width: 100%;
  font-size: 20px;
  z-index: 1;
  background-color: #78EAEF;
  color: #FFFFFF;
  transition: color 0.4s; /* 텍스트 색상 전환 효과 */
}

.submitBtn::before {

    content: '';
    position: absolute;
    left: -100%; /* Start from the left */
    top: 0;
    width: 100%;
    height: 100%;
    color: #78EAEF;
    background-color: white; /* The color fill effect */
    z-index: -1;
    transition: all 0.4s cubic-bezier(.5, .24, 0, 1); /* Animation timing */
    font-weight: 600;

}

.submitBtn:hover::before {
    left: 0; /* End position - fill the button */
}

.submitBtn:hover {
    color: #78EAEF; /* Optional: Change text color on hover */

}


</style>