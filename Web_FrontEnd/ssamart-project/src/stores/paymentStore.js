import { ref } from "vue"
import { defineStore } from 'pinia';
import axios from '@/util/http-common.js';

export const usePaymentStore = defineStore('pay', () => {
  const customerKey = ref('Y291c3Rlc3QxMjM0NTYwMQ');
  const amount = ref(10000); // 예시 금액
  const payMethodFlag = ref(false)
  const paymentHistory = ref([]) // 결제 내역을 저장할 배열

  function processPayment(authKey, customerKey) {
    // 서버로 결제 정보를 전송하는 로직을 구현합니다.
    console.log(typeof(sessionStorage.getItem('userId')), sessionStorage.getItem('userId'));
    console.log(typeof(authKey),authKey);
    console.log(typeof(customerKey), customerKey)
    axios
      .post('/pay/regist',{
        phoneNumber : sessionStorage.getItem('userId'),
        authKey: authKey,
        customerKey: customerKey,
      })
      .then((response)=>{
        console.log(response);
      })
      .catch((error)=>{
        console.log(error);
      })
  }

  // 결제 내역을 가져오는 메소드
  async function fetchPaymentHistory() {
    const userSeq = sessionStorage.getItem('userSeq')
    try {
      const response = await axios.get(`/pay/confirm/${userSeq}`)
      paymentHistory.value = response.data.filter(history => history.isValid);
      paymentHistory.value = response.data; // 서버로부터 받은 결제 내역을 저장
      console.log('결제 내역을 가져오는데 성공했습니다.', response.data)
    } catch (error) {
      console.error('결제 내역을 가져오는데 실패했습니다.', paymentHistory.value);
    } 
  }


  function payMethodModalSwitch(){
    payMethodFlag.value = !payMethodFlag.value;
  }

  return {
    customerKey,
    amount,
    payMethodFlag,
    paymentHistory,
    processPayment,
    payMethodModalSwitch,
    fetchPaymentHistory,  
  };
}, {persist: true});
