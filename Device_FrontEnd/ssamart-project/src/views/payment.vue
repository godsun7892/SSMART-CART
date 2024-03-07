<template>
   <div class="MainPage">
        <div class="MainStage">
            <div class="MainStageHeader">
                <div class="MainLogo"><img src="/image/logo.png" alt="" width="70%"></div>
            </div>
            <div class="payContainer">
                <div class="payList">
                    <div class="total">
                        <div class="title">
                            총금액
                        </div>
                        <div class="price">
                            ￦ {{ (Math.floor(totalPrice / 10) * 10).toLocaleString('ko-KR') }}
                        </div>
                    </div>
                    
                    <div class="totalList">
                        <div class="deal-card" v-for="product in products">
                            <!-- 상품 정보 -->
                            <div class="deal-info">
                                <div class="productName">{{ product.product.name.length > 21 ? product.product.name.slice(0,20) + '...' : product.product.name }}</div>
                            </div>

                            <!-- 기존 가격, 할인 가격 등 -->
                            <div class="deal-pricing">
                                <div class="original-price">{{ (product.product.price * product.quantity).toLocaleString('ko-KR') }}원</div>
                                <div class="discounted-price">{{ (Math.floor((product.product.price * product.quantity * (1 - product.product.discountRate)) / 10) * 10).toLocaleString()}}원</div>
                            </div>
                        </div>
                    </div>

                    <div class="decision">
                        <button class="btn cancel" @click="$router.push('/shoppingBasket')">취소</button>
                        <!-- <button class="btn pay" @click="$router.push('/paymentProgress')">확인</button> -->
                        <!-- 실제 사용 시 아래 코드 -->
                        <button class="btn pay" @click="cartStore.paymentNow()">확인</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import {storeToRefs} from "pinia";
import { useCartStore } from '@/stores/cart';


const cartStore = useCartStore();
const { products, totalPrice } = storeToRefs(cartStore);

// 가격에서 '원' 제거하고 숫자로 변환하는 함수
const parsePrice = (price) => {
    if (typeof price !== 'string') {
        price = price.toString();
    }
    
    return Number(price.replace(/[^0-9.-]+/g, ""));
};




</script>

<style scoped>
.MainPage{
    /* background-color: antiquewhite; */
    width: 100%;
    height: 100%;
}

.MainStage{
    display: flex;
    flex-direction: column;
    /* background-color:aquamarine; */
    width:100%;
    height: 100%;
    justify-content: center;
}
.MainStageHeader{
    display: flex;
    flex-direction: row;
    /* background-color:darkcyan; */
    width:100%;
    height: 20%;
    align-items: center;
    justify-content: center;
    padding-top:0.47%;
}
.MainLogo {
    text-align: center;
    margin: 0% 1%;
    padding-right: 3%;
}
.statusIconCon{
    display: flex;
    flex-direction: row;
    width:50%;
    height: 100%;
    justify-content: right;
    align-items:end;
}

.StatusIcon{
    width:25%;
    height: 40%;
    margin:0% 2%;
    background:#80E1FF;
    color:#FFF;
    border:0;
    font-size: 24px;
    font-weight: bold;
    border-radius: 10px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.payContainer {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 86%;
}
.payList {
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    align-items: center;
    width: 65%;
    height: 90%;
}

.total {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.title {
    font-size: 30px;
    font-weight: bold;
}

.price {
    font-size: 80px;
    color: rgb(103, 146, 252);
    font-weight: bold;
}

.totalList {
    display: flex;
    width: 75%;
    height: 50%;
    flex-direction: column;
    align-items: center;
    margin-top: 3%;
    overflow: auto;
    padding-right: 10px;
}

.decision {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 10%;
    margin-top: auto;
    margin-bottom: 15px;
}

.btn {
    width: 25%;
    font-size: 30px;
    border-radius: 10px;
    margin-right: 10px;
}

.cancel {
    background-color: #CECECE;
}

.pay {
    background-color: rgb(52, 108, 230);
    color: white;
}

.deal-card {
    display: flex;
    /* justify-content: center; */
    align-items: center;
    border: 1px solid #ccc; /* 상품 카드 테두리 */
    margin-bottom: 10px; /* 카드 간 간격 */
    background-color: #F5F5F5;
    border-radius: 10px;
    height: 15%;
    margin-right: 5px;
    width: 690px;
}

.deal-info {
    padding: 10px;
    /* flex-grow: 1; 나머지 공간을 채움 */
    width: 60%;
}

.productName {
    display: flex;
    align-items: center;
    margin-left: 10px;
    /* width: 40%; */
    font-size: large;
    font-weight: 600;
}

.deal-pricing {
    display: flex;
    flex-direction: row;
    justify-content: space-between; /* 가격과 할인률을 양 끝으로 정렬 */
    width: 40%;
}

.original-price {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    text-decoration: line-through; /* 원래 가격에 줄 긋기 */
    color: #7A7A7A;
    font-size: 18px;
    width: 40%;
    font-weight: 600;
}

.discounted-price {
    display:flex;
    justify-content: flex-end;
    align-items: center;
    margin-right: 5%;
    color: black; /* 할인된 가격 강조 */
    font-size: 26px;
    font-weight: 600;
    width: 60%;
    
}

 .totalList::-webkit-scrollbar {
    width: 6px;
    display: block;
    /* opacity: 1; */
}
.totalList::-webkit-scrollbar-thumb {
    /* background-color: #bbbbbd; */
    /* background-color: #80E1FF; */
    background-color: rgb(170, 168, 168);
    border-radius: 10px;
}
.totalList::-webkit-scrollbar-track {
    background-color: rgb(208, 209, 210);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
</style>