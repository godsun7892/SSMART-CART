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
               <div class="deal-card" v-for="(deal, i) in HotDealList" :class="{'margin-right-zero': i % 2 === 1}" :key="deal.productSeq" @click="openDetailModal(deal)">
                  <!-- 상품 이미지 -->
                  <div class="imgContainer">
                    <img :src="deal.image" :alt="deal.name" class="deal-image">
                    <div class="cover">
                        <div class="productInfo">
                            <div class="productName">{{ deal.name }}</div>
                        </div>
                        <div class="priceInfo">
                            <div class="stars">  
                              <div>★</div>
                              <div class="starFont">{{ deal.avgScore }}</div>
                              <div class="review">리뷰 ({{ deal.sales }})</div>
                              <!-- {{ getStars( deal.rate ) }} -->
                          </div>
                            <div class="redFont">
                                <img src="/image/dc_icon.png" height="25px">
                                <p class="fontMargin">
                                    {{ Math.round(deal.discountRate * 100) }}% 할인 중
                                </p>
                            </div>
                            <span class="discounted-price">{{ (Math.round(((deal.price) * (1 - deal.discountRate)) / 10) * 10).toLocaleString('ko-KR') }}원</span>
                        </div>
                    </div>
                    <button class="posBtn" @click.stop="" @click="viewCategory(deal.category.location)">위치 찾기</button>
                  </div>
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
  console.log('clicked');
    // 채소, 과일
    if(category === 'A5'){
        console.log('채소/과일');
        flagSwitch.viewLocation(2);
    }
    // 김치
    else if(category === 'A9'){
        console.log('김치/반찬');
        flagSwitch.viewLocation(1);
    }
    // 정육
    else if(category === 'A1'){
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


  const openDetailModal = (product) => {
      // 현재 모달이 열린 상품의 productSeq 업데이트
      console.log(product);
      if (flagSwitch.currentModalProductSeq === product.seq) {
          currentDetail.value = [];
          flagSwitch.currentModalProductSeq = null; // 이미 열린 모달을 닫음
      } else {
          flagSwitch.currentModalProductSeq = product.seq; // 새 모달 열기
          currentDetail.value = product;
      }
  }
  // 가격에서 '원' 제거하고 숫자로 변환하는 함수
  // const parsePrice = (price) => {
  //     if (typeof price !== 'string') {
  //         price = price.toString();
  //     }
      
  //     return Number(price.replace(/[^0-9.-]+/g, ""));
  // };
  
  
  
  
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
      width: 98%;
      height: 93%;
      overflow-y: auto;

  }
  
  .deal-card {
      border: none; /* 상품 카드 테두리 */
      width: 48%;
      margin-right: 3%; 
    margin-bottom: 2%; 
    padding-bottom: 5px;
    height: 44%;
  }
  
  .margin-right-zero {
    border: none; /* 상품 카드 테두리 */
    border-radius: 10px;
    width: 48%;
    margin-bottom: 2%; 
    padding-bottom: 5px;
    /* background-color: #F5F5F5; */
    /* border-radius: 10px; */
    height: 44%;
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
    position: relative;
    width: 100%;
    height: 100%;
  }

  .deal-image {
      width: 100%; /* 상품 이미지 크기 */
      height: 100%; /* 상품 이미지 크기 */
      object-fit: cover; /* 이미지 비율 유지 */
      /* margin-left: 20px; */
      border-radius: 10px;
  }
  
  .cover {
    position: absolute;
    background-color: white;
    left: 0;
    top: 0;
    width: 63%;
    height: 100%;
    opacity: 50%;
    display: flex;
    flex-direction: column;
    /* z-index: 100; */
  }

  .productInfo {
    height: 50%;
  }

  .priceInfo {
    height: 50%;
    margin-bottom: 15px;
    margin-left: auto;
    display: flex;
    flex-direction: column;
  }

  .posBtn {
    position: absolute;
    bottom: 5px;
    right: 5px;
    border-radius: 7px;
    width: 28%;
    height: 18%;
    font-size: 20px;
    font-weight: 500;
    color: white;
    background-color: #595959;
  }

  .deal-info {
      padding: 10px;
      flex-grow: 1; /* 나머지 공간을 채움 */
  }
  
  .productName {
      display: flex;
      align-items: center;
      margin-left: 10px;
      margin-right: 50px;
      width: 100%;
      font-size: large;
      font-weight: 600;
      color: black;
      font-size: 28px;
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
      margin-top:3%;
  
  }
  
  .fontMargin {
      margin-left: 5px;
      margin-bottom: 5px;
  }
  
  .stars {
      display:flex;
      flex-direction: row;
      color: #f4d211; /* 별 색상을 금색으로 설정 */
      font-size: 1.7em; /* 별 크기를 조정 */
      align-items: center;
      /* filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25)); */
  
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
      margin-top: 7px;
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
      font-size: 30px;
      font-weight: 600;
  }
  
  
  .right {
      width: 19%;
      height: 96%;
      /* margin-right: auto; */
  }
  
  .ad-section {
      margin-left: 10%;
      height: 102%;
      width: 100%;
      display: flex;
      justify-content: center;
  }
  
  .img {
      border-radius: 20px;
  }
  </style>