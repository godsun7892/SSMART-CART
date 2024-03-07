<template>
    <div class="hot-deals">
      <div class="left">
          <div class="timerContainer">
              <div class="alarmImgContainer">
                  <img src="/image/alarm.gif" class="alarmImg">
              </div>
              <div class="hotDealImg">
                <img src="/image/hotdealTXT.png" class="hotDeal">
              </div>
              <div class="txt">
                  핫딜 종료 시간 
              </div>  
              <div class="calanderTimer">
                <div v-for="(digit, index) in timeDigits" :key="index" class="box">
                    <div class="boxCover">
                        <div class="timeNumber">{{ digit }}</div>
                    </div>
                    <div class="boxBottom"></div>
                    <div class="colon" v-if="index === 1 || index === 3">
                        <img src="/image/colon.png" class="colonImg">
                    </div>
                </div>
              </div>
          </div>
            
          <div class="deals-container">
               <div class="deal-card" v-for="(deal, i) in HotDealList" :class="{'margin-right-zero': i % 2 === 1}" :key="deal.productSeq">
                    <!-- 상품 이미지 -->
                    <div class="discountImg">
                        <img class="img" src="/image/dc_icon.png">
                    </div>
                    <div class="imgContainer">
                        <img :src="deal.image" :alt="deal.name" class="deal-image">
                    </div>
                    <div class="expContainer">
                        <div class="productInfo">
                            <div class="productName">{{ deal.name }}</div>
                        </div>
                        <div class="priceInfo">
                            <span class="originPrice">
                                {{ deal.price.toLocaleString('ko-KR') }}원 
                            </span>
                            <div class="redFont">
                                <p class="fontMargin">
                                    {{ Math.round(deal.discountRate * 100) }}%
                                    <span class="discounted-price">{{ (Math.round(((deal.price) * (1 - deal.discountRate)) / 10) * 10).toLocaleString('ko-KR') }}원</span>
                                </p>
                            </div>
                            <div class="starContainer">  
                                <div class="stars">
                                    ★
                                    <div class="starFont">{{ (Math.round(deal.avgScore * 10) / 10).toFixed(1) }}</div>
                                </div>
                                <div class="review">리뷰 ({{ deal.sales }})</div>
                            </div>
                        </div>
                    </div>
                    <button class="posBtn" @click.stop="" @click="viewCategory(deal.category.location)">
                        <img src="/image/location.png" class="btn">
                    </button>
                  <!-- 상품 정보 -->
                  
              </div>
          </div>
          <!-- 상품 목록 -->
         
      </div>
  
      <div class="right">
          <!-- 광고 섹션 -->
          <div class="ad-section">
              <!-- 광고 이미지 또는 컨텐츠 -->
              <img src="/image/discount.png" class="img">
          </div> 
      </div>
  
      <ProductDetailform v-if="flagSwitch.currentModalProductSeq" />
      <ssafyFloor2 v-if="flagSwitch.locationModalflag"/>
    </div>
  </template>
    
  <script setup>
  import { ref, computed, onMounted } from 'vue';
  import { useFlagStore } from "@/stores/flag.js";
  import { useSearchListStore } from '@/stores/searchList';
  import { storeToRefs } from 'pinia';
  import ProductDetailform from '@/components/productDetailModal.vue';
  import ssafyFloor2 from '@/views/floor/SSAFYFloor2.vue';
  
  const flagSwitch = useFlagStore();
  const SearchListStore = useSearchListStore();
  const { HotDealList, currentDetail } = storeToRefs(SearchListStore);

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
    else if(category === 'D1' || category === 'D2' || category === 'D3'){
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


  // 시간을 저장할 반응형 데이터
const timeUnits = ref([]);
// 남은 시간을 각 자리수 별로 분리하여 저장할 반응형 데이터
const timeDigits = ref([]);

// 남은 시간을 계산하고 포맷하는 메서드
const calculateTimeLeft = () => {
  const now = new Date();
  const end = new Date();
  end.setHours(22, 0, 0, 0); // 오후 10시까지의 목표 시간 설정
  
  // 남은 시간 계산
  let delta = end - now;
  // 시, 분, 초 단위로 변환
  let hours = Math.floor(delta / 3600000);
  delta -= hours * 3600000;
  let minutes = Math.floor(delta / 60000);
  delta -= minutes * 60000;
  let seconds = Math.floor(delta / 1000);
  
  // 남은 시간을 시간단위 배열로 변환
  timeUnits.value = [hours, minutes, seconds].map(unit => String(unit).padStart(2, '0'));

  // 각 시간단위를 개별 숫자로 분리하여 저장
  timeDigits.value = timeUnits.value.flatMap(unit => unit.split(''));
};

// 컴포넌트가 마운트될 때 타이머 시작
onMounted(() => {
  calculateTimeLeft(); // 초기 시간 설정
  setInterval(calculateTimeLeft, 1000); // 매초마다 시간 업데이트
});
  </script>
    
  <style scoped>
  .hot-deals {
      display: flex;
      /* position: relative; */
      flex-direction: row;
      justify-content: center;
      align-items: center;
      /* align-items: center; */
      height: 92%;
      background-color: white;
  }
  
  .left {
      width: 77%; /* 모달 가로의 80%를 차지 */
      height: 96%;
      display: flex;
      flex-direction: column;
      overflow-y: auto; /* 내용이 많을 경우 스크롤바 표시 */
      /* justify-content: center; */
      align-items: center;
  }
  
  
  .timerContainer {
      width: 100%;
      height: 19%;
      display: flex;
      align-items: center;
      justify-content: center;
      position: relative;
  }
  
  .alarmImgContainer {
      margin-left: 4%;
      width: 8%;
      height: 60%;
  }
  
  .alarmImg {
      width: 70%;
      height: 90%;
  }
  
  .hotDealImg {
    position: absolute;
    left: 21%;
    top: 12%;
    width: 12%;
  }

  .hotDeal {
    width: 100%;
  }
  .txt {
      margin-left: 1%;
      margin-top: 3%;
      width: 23%;
      font-size: 32px;
      font-weight: 600;
  }
  
  .calanderTimer {
      width : 57%;
      height: 90%;
      /* border: 1px solid red; */
      display: flex;
      justify-content: center;
      align-items: center;
  }
  .box {
    position: relative;
      display: flex;
      width: 13%;
      height: 90%;
      /* border: 3px solid blue; */
      margin-right: 2%;
      background-color: #1D1D1B;
      /* background-color: white; */
      align-items: center;
      border-radius: 5px;
      box-shadow: 2px 2px 5px darkgray;
      justify-content: center;
      align-items: center;
  }

  .box:nth-child(2) {
    margin-right: 4%;
  }
  .box:nth-child(4) {
    margin-right: 4%;
  }
  
  .boxBottom {
      width: 100%;
      height: 50%;
      margin-top: auto;
      /* border: 2px solid white; */
      background: linear-gradient(to bottom, #ffffff 10%, #f0f0f0 15%, #2F2F2F 50%, #1D1D1B 100%);
      /* background: white; */
      opacity: 20%;
  }

  .boxCover {
    position: absolute;
      display: flex;
      width: 100%;
      height: 90%;
      /* border: 3px solid blue; */
      /* margin-right: 2%; */
      background-color: transparent;
      /* background-color: white; */
      align-items: center;
      border-radius: 5px;
      /* box-shadow: 2px 2px 5px darkgray; */
      justify-content: center;
      align-items: center;
  }
  
  /* .num {
      color: #ECECEC;
      font-size: 50px;
      font-weight: bold;
      background-color: rgba( 0, 0, 0, 0.01 );
  } */

  .box:last-child {
      margin-right: 0%;
  }
  
  .colon {
    position: absolute;
    left: 86px;
      width:2%;
      height: 65%;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 10px;
      margin-left: -2px;
  }
  
  .colonImg {
      height: 50%;
      /* width: 80%; */
  }
  
  .deals-container {
    display: flex;
    flex-wrap: wrap;
      margin-top: 4%;
      margin-left: 2%;
      width: 98%;
      height: 93%;
      overflow-y: auto;

  }
  
  .deal-card {
    position: relative;
      border: none; /* 상품 카드 테두리 */
      width: 45%;
      height: 40%;
      margin-right: 8%; 
      margin-bottom: 2%; 
      padding-bottom: 5px;
      display: flex;
      /* border-bottom-right-radius: 5px; */
      /* border-bottom-left-radius: 5px; */
      box-shadow: 0px 3px 0px 0px rgb(209, 207, 207);
  }
  
  .margin-right-zero {
    position: relative;
    border: none; /* 상품 카드 테두리 */
    width: 45%;
    margin-bottom: 2%; 
    padding-bottom: 5px;
    /* background-color: #F5F5F5; */
    /* border-radius: 10px; */
    height: 40%;
    margin-right: 0%;
  }

  .heartBtn {
      margin-left: 3%;
      margin-right: 3%;
      width: 5%;
      height: 40%;
      border: none;
      background-color: #F5F5F5;
  }
  
  .imgContainer {
    width: 45%;
    height: 90%;
  }

  .deal-image {
      width: 100%; /* 상품 이미지 크기 */
      height: 100%; /* 상품 이미지 크기 */
      object-fit: cover; /* 이미지 비율 유지 */
      /* margin-left: 20px; */
      /* border-radius: 10px; */
  }
  
  .discountImg {
    position: absolute;
    margin-top: 5px;
    margin-left: 5px;
    height: 10%;
    width: 5%;
  }

  .expContainer {
    background-color: white;
    left: 0;
    top: 0;
    width: 55%;
    height: 100%;
    display: flex;
    flex-direction: column;
    /* z-index: 100; */
  }

  .productInfo {
    height: 35%;
    display: flex;
    align-items: center;
  }

  .priceInfo {
    height: 50%;
    margin-left: 20px;
    display: flex;
    flex-direction: column;
  }

  .originPrice {
    font-size: 16px;
    color: #656464;
    text-decoration: line-through;
  }

  .posBtn {
    position: absolute;
    bottom: 55px;
    right: 5px;
    border-radius: 7px;
    width: 18%;
    height: 29%;
    color: white;
    border: none;
    background-color: white;
    /* filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.15)); */
  }

  .btn {
    width: 100%;
    height: 100%;
  }
  .deal-info {
      padding: 10px;
      flex-grow: 1; /* 나머지 공간을 채움 */
  }
  
  .productName {
      margin-left: 20px;
      font-size: large;
      font-weight: 600;
      color: black;
      font-size: 16px;
  }
  
  .detailInfo {
      display: flex;
      align-items: center;
      flex-direction: column;
      width: 25%;
  }
  
  .redFont {
      display: flex;
      flex-direction: row;
      color: red;
      font-weight: 600;
      font-size: 22px;
  
  }
  
  .fontMargin {
      margin-bottom: 5px;
  }
  
  .starContainer {
    display: flex;
  }

  .stars {
      display:flex;
      flex-direction: row;
      color: #f4d211; /* 별 색상을 금색으로 설정 */
      font-size: 1.7em; /* 별 크기를 조정 */
      align-items: center;
      margin-top: -10px;
      filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.15));
  
  }

  .timeNumber {
  font-size: 50px;
  font-weight: bold;
  color: #ECECEC;
  /* margin-left: 25px; */
}
  
  .starFont {
      margin-top: 7px;
      margin-left: 5px;
      color: black;
      font-size: large;
      font-weight: 550;
  }
  
  .review {
      margin-left: 10px;
      margin-top: 8px;
      color: gray;
      font-size: small;
  }
  .deal-pricing {
      display: flex;
      flex-direction: column;
      justify-content: space-between; /* 가격과 할인률을 양 끝으로 정렬 */
      margin-right: 30px;
      margin-left: 30px;    
  }
  
  .original-price {
      text-decoration: line-through; /* 원래 가격에 줄 긋기 */
      color: #7A7A7A;
      font-size: 20px;
      font-weight: 600;
  }
  
  .discounted-price {
      color: black; /* 할인된 가격 강조 */
      font-size: 19px;
      /* font-weight: 600; */
  }
  
  
  .right {
      width: 19%;
      height: 96%;
      /* margin-right: auto; */
  }
  
  .ad-section {
      margin-left: 14%;
      height: 102%;
      width: 92%;
      display: flex;
      justify-content: center;
  }
  
  .img {
      width: 100%;
      height: 100%;
      border-radius: 20px;
  }

  .deals-container::-webkit-scrollbar {
    width: 6px;
    display: block;
    /* opacity: 1; */
}

/* .deals-container:hover::-webkit-scrollbar {
    display: block;
    width: 10px;
} */

.deals-container::-webkit-scrollbar-thumb {
    /* background-color: #bbbbbd; */
    /* background-color: #80E1FF; */
    background-color: rgb(170, 168, 168);
    border-radius: 10px;
}
.deals-container::-webkit-scrollbar-track {
    background-color: rgb(208, 209, 210);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
  </style>