<template>
    <div>
        <div v-if="paymentHistory.length > 0" class="receipts">
            <div v-for="(history, index) in paymentHistory" :key="index" class="receipt-container" :class="{'open': receiptDetailsVisibility[history.receiptSeq]}">
                <div class="title">영수증 번호: {{ history.receiptSeq }}</div>
                <p>결제 일자: {{ formatDate(history.date) }}</p>
                <table class="product-table">
                    <thead>
                        <tr>
                            <th>상품명</th>
                            <th>가격</th>
                            <th>할인가</th>
                            <th>수량</th>
                            <th>상품별 총 금액</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(product, index) in history.payHistoryProductDtoList" :key="`product-${index}`" v-show="index < 2 || receiptDetailsVisibility[history.receiptSeq]">
                            <td>{{ product.name }}</td>
                            <td>{{ Math.round(product.price).toLocaleString() }}</td>
                            <td v-if="product.discountPrice">{{ Math.round(product.price - product.discountPrice).toLocaleString() }}</td>
                            <td v-else>-</td>
                            <td>{{ product.quantity }}</td>
                            <td>{{ Math.round((product.price - product.discountPrice || product.price) * product.quantity).toLocaleString() }}원</td>
                        </tr>
                    </tbody>
                </table>
                <!-- 화살표 아이콘 버튼 -->
                <div class="arrow-icon" @click="toggleReceiptDetails(history.receiptSeq)">
                    <i :class="{'down-arrow': !receiptDetailsVisibility[history.receiptSeq], 'up-arrow': receiptDetailsVisibility[history.receiptSeq]}"></i>
                </div>
                <!-- 결제 총 금액 -->
                <div class="total-price">
                    결제 총 금액: {{ calculateTotal(history.payHistoryProductDtoList).toLocaleString() }}원
                </div>
            </div>
        </div>
        <div v-else>
            <p>결제 내역이 없습니다.</p>
        </div>
    </div>
</template>

  
  
<script setup>
import { onMounted, reactive } from 'vue';
import { usePaymentStore } from '@/stores/paymentStore';

const { paymentHistory, fetchPaymentHistory } = usePaymentStore();
const receiptDetailsVisibility = reactive({}) // 각 영수증의 상세 정보 표시 여부

// 상세 정보 표시 여부를 토글하는 메서드
const toggleReceiptDetails = (receiptSeq) => {
    receiptDetailsVisibility[receiptSeq] = !receiptDetailsVisibility[receiptSeq];
}

const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
};

const calculateTotal = (products) => {
  return products.reduce((total, product) => {
    const price = (product.price - product.discountPrice) || product.price;
    return Math.round(total + (price * product.quantity));
  }, 0);
};


// 영수증 로딩 시 기본적으로 상세 정보가 숨겨져 있도록 설정
onMounted(async () => {
  await fetchPaymentHistory();

  // paymentHistory가 ref로 선언되었을 경우 .value를 사용해야 합니다.
  if (paymentHistory.value && paymentHistory.value.length > 0) {
    // receiptSeq를 기준으로 내림차순 정렬
    paymentHistory.value.sort((a, b) => b.receiptSeq - a.receiptSeq);

    // 각 영수증의 상세 정보 표시 여부를 초기화합니다.
    paymentHistory.value.forEach((history) => {
      receiptDetailsVisibility[history.receiptSeq] = false;
    });
  }
});


</script>
  
<style scoped>
/* 영수증들을 감싸는 컨테이너에 대한 스타일 */
.receipts {
    margin-top: 50px;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 50px; /* 영수증 간 간격 */
}

/* 영수증 컨테이너 */
.receipt-container {
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
    padding: 80px; /* 패딩 조정 */
    border-radius: 10px;
    width: calc(50% - 40px); /* 두 줄 배치를 위한 너비 조정 */
    margin-bottom: 20px;
    overflow-x: auto;
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 600;
    font-style: normal;
}

.receipt-container p {
    font-weight: 700;
}

/* 표 스타일 */
.product-table {
    width: 100%;
    border-collapse: separate; /* 세로줄만 표시를 위해 변경 */
    margin-top: 20px;
    border-spacing: 0 8px; /* 세로줄 간격 조정 */
}

.product-table th, .product-table td {
    border-right: 1px solid #ddd; /* 세로줄 스타일 */
    text-align: center;
    padding: 8px;
}

.product-table th:last-child, .product-table td:last-child {
    border-right: none; /* 마지막 세로줄 제거 */
}

.product-table th {
    background-color: #f2f2f2;
    border-bottom: none; /* 헤더 가로줄 제거 */
}

.product-table tr {
    border-bottom: none; /* 데이터 행 가로줄 제거 */
}


/* 결제 총 금액 스타일 */
.total-price {
    font-weight: bold;
    margin: 50px 50px;
    text-align: right;
    font-size: 20px;
}

/* 화살표 아이콘 스타일 */
.arrow-icon {
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    width: 30px;
    height: 30px;
    margin: 20px auto;
    background-color: white; /* 배경색 하얀색 */
    transition: transform 0.3s ease; /* 화살표 회전에 부드러운 효과 적용 */
}

.down-arrow, .up-arrow {
    border: solid black; /* 화살표 색 검은색 */
    border-width: 0 2px 2px 0;
    display: inline-block;
    padding: 5px;
    transition: transform 0.3s ease; /* 화살표 회전에 부드러운 효과 적용 */
}

/* 화살표 확대/축소 효과 */
.arrow-icon:hover {
    transform: scale(1.2); /* 마우스 오버 시 화살표 확대 */
}

/* 영수증 상세 정보 표시/숨김 애니메이션 */
.product-table, .total-price {
    transition: max-height 0.5s ease-out, opacity 0.5s ease;
    overflow: hidden;
    max-height: 0; /* 초기 상태: 숨김 */
    opacity: 0; /* 기본 상태에서 내용이 보이도록 변경 */
}

.receipt-container:not(.open) .product-table, 
.receipt-container:not(.open) .total-price {
    max-height: 1000px; /* 충분히 큰 값으로 설정 */
    opacity: 1;
}

.receipt-container.open .product-table,
.receipt-container.open .total-price {
    max-height: 1000px; /* 충분히 큰 값으로 설정하여 내용이 보이도록 함 */
    opacity: 1; /* 내용이 완전히 보이도록 함 */
}

.down-arrow {
    transform: rotate(45deg); /* 아래 화살표 */
    -webkit-transform: rotate(45deg);
}

.up-arrow {
    transform: rotate(-135deg); /* 위 화살표 */
    -webkit-transform: rotate(-135deg);
}

</style>
