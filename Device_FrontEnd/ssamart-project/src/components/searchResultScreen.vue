<template>
    <div class="hot-deals">
        <!-- <div class="keySearchBtnContainer"> -->
            <!-- <div class="deals-container"> -->
                <div class="EmptyMsgContainer" v-if="!searchList">
                    <div class="EmptyMessage">검색된 상품이 없습니다.</div>
                </div>
                
                <div class="deal-card" v-for="(slist, i) in searchList" :key="slist.seq" :class="{'skyblue-background': flagSwitch.currentModalProductSeq === slist.seq, 'margin-right-zero': i % 4 === 3}">
                    <!-- <button class="heartBtn" @click="searchListStore.toggleHeart(slist)">
                        <svg v-if="slist.productLiked" xmlns="http://www.w3.org/2000/svg" fill="#f00" class="bi bi-heart-fill" viewBox="0 0 16 16" style="background: #F5F5F5;">
                            <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314"/>
                        </svg>
                        <svg v-else xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" style="background: #F5F5F5;">
                            <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
                        </svg>
                    </button> -->
                    <!-- 상품 이미지 -->
                    <img :src="slist.image" :alt="slist.name" class="deal-image">
                    <!-- 상품 정보 -->
                    <div class="deal-info">
                        <div>{{ slist.name }}</div>
                    </div>
                    <!-- 리뷰 및 할인 정보 -->
                    <div class="detailInfo">
                        <div>
                            <div class="redFont">
                                <!-- <img src="/image/dc_icon.png" height="25px"> -->
                                <p class="fontMargin">
                                    {{ Math.round(((parsePrice(slist.discountRate) * 100))) }}%
                                </p>
                            </div>
                        </div>
                        <div class="deal-pricing">
                            <!-- <span class="original-price">{{ slist.productPrice }}</span>
                            <span class="discounted-price">{{ Math.round(((parsePrice(slist.productPrice) * (1 - parsePrice(slist.productDiscountRate))))) }}</span> -->
                            <!-- <span class="original-price">{{ slist.product.price.toLocaleString('ko-KR') }}원</span> -->
                            <span class="discounted-price">{{ (Math.round((parsePrice(slist.price) * (1 - parsePrice(slist.discountRate))) / 10) * 10).toLocaleString('ko-KR') }}원</span>
                            <!-- <span class="discounted-price">{{ (Math.round(((deal.price) * (1 - deal.discountRate)) / 10) * 10).toLocaleString('ko-KR') }}원</span> -->
                        </div>
                    </div>

                    <!-- 기존 가격, 할인 가격 등 -->
                    

                    <div class="stars">  
                        <div class="star">★</div>
                        <div class="starFont">{{ slist.avgScore.toFixed(1) }}</div>
                        <div class="review">리뷰 ({{ slist.sales }})</div>
                        <!-- {{ getStars( deal.rate ) }} -->
                    </div>
                    <button class="btn" @click="viewCategory(slist.category.location)">
                        <img class="btnImg" src="/image/location.png">
                    </button>
                </div>

                <!-- <div class="btnRange">
                    <button @click="searchBack">뒤로가기</button>
                </div> -->
            <!-- </div> -->
        <!-- </div> -->

        <ProductDetailform v-if="flagSwitch.currentModalProductSeq" />
        <ssafyFloor2 v-if="flagSwitch.locationModalflag"/>
    </div>

</template>

<script setup>
import {storeToRefs} from "pinia";
import { useFlagStore } from "@/stores/flag.js";
import { useSearchListStore  } from "@/stores/searchList";
import ProductDetailform from '@/components/productDetailModal.vue';
import ssafyFloor2 from '@/views/floor/SSAFYFloor2.vue';

const flagSwitch = useFlagStore();
const searchListStore = useSearchListStore();
const { searchList, currentDetail } = storeToRefs(searchListStore);

// 가격에서 '원' 제거하고 숫자로 변환하는 함수
const parsePrice = (price) => {
    if (typeof price !== 'string') {
        price = price.toString();
    }
    
    return Number(price.replace(/[^0-9.-]+/g, ""));
};

// 상품의 위치를 나타내는 함수
const viewCategory = (category) => {
    // 채소, 과일
    if(category === 'A5' || category === 'P1' || category === 'P2' || category === 'P3' || category === 'P4' || category === 'P5' || category === 'P6' || category === 'P7' || category === 'P8' || category === 'P9'){
        console.log('채소/과일');
        flagSwitch.viewLocation(2);
    }
    // 김치
    else if(category === 'A9' || category === 'M1'){
        console.log('김치/반찬');
        flagSwitch.viewLocation(1);
    }
    // 정육
    else if(category === 'A1' || category === 'N1' || category === 'N2' || category === 'N3' || category === 'Q3'){
        console.log('정육/계란');
        flagSwitch.viewLocation(3);
    }
    // 과자, 간식
    else if(category === 'A13'){
        console.log('과자/간식');
        flagSwitch.viewLocation(4);
    }
    // 위생, 건강
    else if(category === 'D' || category === 'D1' || category === 'D2' || category === 'D3'){
        console.log('위생/건강');
        flagSwitch.viewLocation(5);
    }
    // 주방용품
    else if(category === 'B1' || category === 'B2' || category === 'B3' || category === 'B4' || category === 'B5' || category === 'B6' || category === 'B7' ){
        console.log('주방용품');
        flagSwitch.viewLocation(6);
    }
    // 우유, 유제품, 치즈
    else if(category === 'Q2' || category === 'Q4' || category === 'Q5'){
        console.log('우유/유제품/치즈');
        flagSwitch.viewLocation(8);
    }
    // 면류, 통조림
    else if(category === 'Q1'){
        console.log('면류/통조림');
        flagSwitch.viewLocation(7);
    }
}

</script>

<style scoped>

.hot-deals {
    display: flex;
    /* justify-content: center; */
    /* width: 100%; */
    /* height: 100%; */
    /* padding: 15px 15px 15px 15px; */
    /* background-color: #EEEEEE; */
    flex-wrap: wrap;
    width: 1020px;
    height: 570px;
    overflow-y: auto;
}

.keySearchBtnContainer {
    display: flex;
    flex-direction: column;
    width: 80%;
    height: 100%;
}
.deals-container {
    width: 100%; /* 모달 가로의 80%를 차지 */
    height:100%;
    display: flex;
    flex-direction: column;
    /* overflow: auto; 내용이 많을 경우 스크롤바 표시 */
    padding-right: 10px;
    overflow-y: scroll; /* 항상 스크롤 바 표시 */
}

.EmptyMsgContainer {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.EmptyMessage {
    text-align: center;
    /* margin-top: 3%; */
    /* height: 85%; */
    font-size: 32px;
    color: #939393;
}

.deal-card {
    position: relative;
    width: calc(25% - 20px); 
    margin-right: 50px; 
    margin-bottom: 2%; 
    padding-bottom: 5px;
    /* background-color: #F5F5F5; */
    /* border-radius: 10px; */
    height: 52%;
    width: 21%;
}


@media (max-width: 992px) {
  .product-card {
    width: calc(33.333% - 20px); /* 화면 크기가 작을 때는 한 줄에 3개의 카드를 배치합니다. */
  }
}

@media (max-width: 768px) {
  .product-card {
    width: calc(50% - 20px); /* 화면 크기가 더 작을 때는 한 줄에 2개의 카드를 배치합니다. */
  }
}

@media (max-width: 576px) {
  .product-card {
    width: 100%; /* 화면 크기가 가장 작을 때는 한 줄에 1개의 카드만 배치합니다. */
    margin-right: 0;
  }
}

.skyblue-background {
    background-color: #edeaea;
}

.margin-right-zero {
    margin-right: 0 !important;
}

.heartBtn {
    margin-left: 3%;
    margin-right: 3%;
    width: 5%;
    height: 40%;
    border: none;
    background-color: #F5F5F5;
}

.deal-image {
    width: 100%; /* 상품 이미지 크기 */
    height: 70%; /* 상품 이미지 크기 */
    object-fit: cover; /* 이미지 비율 유지 */
    margin-bottom: 5px;
}

.deal-info {
    /* flex-grow: 1; 나머지 공간을 채움 */
    display: flex;
    align-items: center;
    width: 100%;
    font-size: 12px;
    font-weight: 600;
    height: 13%;
}

/* .productName {
    display: flex;
    align-items: center;
    margin-left: 10px;
    margin-right: 50px;
    width: 40%;
    font-size: large;
    font-weight: 600;
} */

.detailInfo {
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 10%;
}

.redFont {
    display: flex;
    flex-direction: row;
    color: red;
    font-weight: bold;
}

.fontMargin {
    font-size: 22px;
}

.stars {
    display:flex;
    flex-direction: row;
    color: #f5dd59; /* 별 색상을 금색으로 설정 */
    font-size: 1.4em; /* 별 크기를 조정 */
    align-items: center;
    margin-top: -5px;
    /* filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25)); */

}

.star {
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.2));
}

.starFont {
    margin-top: 7px;
    margin-left: 5px;
    color: black;
    font-size: 17px;
    /* font-weight: 550; */
}

.review {
    margin-left: 10px;
    margin-top: 9px;
    color: gray;
    font-size: 12px;
}

.btn {
    position: absolute;
    bottom: 2px;
    right: 2px;
    /* background-color: aqua; */
    width: 29%;
    height: 18%;
}

.btnImg {
    width: 100%;
    height: 100%;
}

.deal-pricing {
    display: flex;
    /* flex-direction: column; */
    /* justify-content: space-between; 가격과 할인률을 양 끝으로 정렬 */
    /* margin-right: 30px; */
    margin-left: 10px;
    align-items: center;
}

.original-price {
    text-decoration: line-through; /* 원래 가격에 줄 긋기 */
    color: #7A7A7A;
    font-size: 20px;
    font-weight: 600;
}

.discounted-price {
    color: black; /* 할인된 가격 강조 */
    font-size: 20px;
    font-weight: bold;
}

.ad-section img {
    width: 100%;
    height: 100%;
    /* object-fit: cover; 이미지가 비율을 유지하면서 영역 채우도록 */
    /* .ad-section 내부에서 절대 위치 */
    position: absolute;
}

.hot-deals::-webkit-scrollbar {
    width: 6px;
    display: block;
    opacity: 1;
}

/* .hot-deals:hover::-webkit-scrollbar {
    display: block;
    width: 10px;
} */

.hot-deals::-webkit-scrollbar-thumb {
    /* background-color: #80E1FF; */
    background-color: rgb(170, 168, 168);
    border-radius: 10px;
}
.hot-deals::-webkit-scrollbar-track {
    /* background-color: rgb(186, 215, 248); */
    background-color: rgb(208, 209, 210);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
</style>
