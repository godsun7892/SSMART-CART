<template>
  <div class="product-detail">
    <div class="left" @mousemove="handleMouseMove" @mouseleave="handleMouseLeave" ref="imageContainer">
      <div class="zoom-lens" v-if="showLens" :style="lensStyle"></div>
      <img :src="productDetails.image" alt="Product Image" class="product-image" ref="productImage"/>
    </div>
    <div class="right">
      <div class="prod-name">{{ productDetails.name }}</div>
      <div class="prod-second">
        <div class="prod-content">{{ productDetails.content }}</div>
        <div class="prod-stock">잔여수량 : {{productDetails.stock}} 개</div>
      </div>
      <div class="prod-grp">
        <div class="starContainer">  
          <div class="stars">
            ★{{ (Math.round(productDetails.avgScore * 10) / 10).toFixed(1) }}
          </div>
          <!-- <div class="review">리뷰 ({{ productDetails.sales }})</div> -->
        </div>
        <div class="prod-pricegrp">
          <div class="prod-discountgrp">
            <img src="/main/logo/dc_icon.png" style="width:40px">
            <div class="prod-discount">{{ productDetails.discountRate * 100 }}% 할인 중</div>
          </div>
          <div class="prod-prevprice">{{Math.round(productDetails.price).toLocaleString()}} 원</div>
          <div class="prod-price">{{ Math.round(productDetails.price*(1-productDetails.discountRate)).toLocaleString('ko-KR') }} 원</div>
        </div>
      </div>
      
      <img src="/main/logo/banner.png" style="width:100%; margin-bottom:3%;">
      <!-- Select Dropdown for Wish Lists -->
      <div class="wishlist">
        <select id="wishlistSelectBox" v-model.number="selectedWishListSeq">
          <option disabled value="">찜 목록을 선택해주세요</option>
          <option v-for="wishList in wishLists" :key="wishList.seq" :value="wishList.seq">
            {{ wishList.name }}
          </option>
        </select>
        <!-- Add to Wish List Button -->
        <button class="wishlistbtn" @click="addProductToWishListHandler">상품 찜하기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineComponent, computed, onMounted, ref, reactive, watch } from 'vue';
import { useProductStore } from '@/stores/productStore';
import { useWishStore } from '@/stores/wishListStore';
import { useRoute } from 'vue-router';

const productStore = useProductStore();
const wishListStore = useWishStore();
const selectedWishListSeq = ref('');
const route = useRoute();

watch(selectedWishListSeq, (newValue, oldValue) => {
  console.log(`selectedWishListSeq changed from ${oldValue} to ${newValue}`)
})

// 상품 상세 정보와 찜 목록을 가져오기
const productDetails = computed(() => productStore.productDetails);
const wishLists = computed(() => wishListStore.wishLists);

const showLens = ref(false); // 확대 렌즈 표시 여부
const lensStyle = reactive({ // 확대 렌즈 스타일
  position: 'absolute',
  border: '1px solid #d4d4d4',
  width: '100px',
  height: '100px',
  opacity: '0.5',
  backgroundRepeat: 'no-repeat',
  display: 'none',
});

onMounted(() => {
  const seq = route.params.seq;
  productStore.fetchProductDetail(seq).then((data) => {
    productDetails.value = data;
  });
});


// 찜하기 버튼 클릭 이벤트 핸들러
const addProductToWishListHandler = () => {
  if(!selectedWishListSeq.value) {
    console.log(selectedWishListSeq.value)
    alert("찜 목록을 선택해주세요.")
  } else {
    // productStore에서 제공하는 addProductToWishList 함수를 사용하여 상품을 찜 목록에 추가
    wishListStore.addProductToWishList(productDetails.value.seq, selectedWishListSeq.value)
  }
}

// selectedWishListSeq의 변화를 감시하고 콘솔에 출력
watch(selectedWishListSeq, (newValue, oldValue) => {
  console.log(`찜 목록 선택 변경됨: ${oldValue} -> ${newValue}`);
});



</script>

<style scoped>
.product-detail {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 500;
  font-style: normal;
  display: flex;
  flex-direction: row;
  padding: 20px;
}

/* 상품 사진 파트 */
.left {
  width : 45%;
}
.product-image {
  width: 400px;
  height: 400px;
  margin-left: 100px;
  margin-top: 20px;
}

/* 상품 상세 내용 파트 */
.right {
  width : 55%;
  margin-right: 80px;
  margin-top: 20px;
}
.prod-name { 
  font-size : 25px;
  font-weight: bold;
  margin-bottom : 4%;
}
.prod-content {
  font-size: 20px;
  color: rgb(87, 87, 87);
  margin-bottom : 2%;
  padding-bottom : 3%;
}

.prod-second {
  border-bottom : 1px solid rgb(222, 222, 222);
  margin-bottom: 3%;
}

/* 리뷰, 가격 */
.prod-grp {
  display: flex;
  justify-content: space-between;
  padding-bottom : 5%;
}
.starContainer {
  height: 5%;
  display: flex;
  align-items:center;
  margin-bottom : 5%;
}
.stars {
  display:flex;
  flex-direction: row;
  color: #f4d211; /* 별 색상을 금색으로 설정 */
  font-size: 1.7em; /* 별 크기를 조정 */
  align-items: center;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.15));
}
.review {
  color: gray;
  font-size: small;
  margin-left : 10%;
  white-space: nowrap;
}
.prod-stock {
  margin-bottom : 5%;
}
.prod-pricegrp {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items:flex-end;
}
.prod-discountgrp {
  display: flex;
  justify-content: center;
  align-items: center;
}
.prod-discount {
  margin-left : 3%;
  margin-bottom : 1%;
  font-size: 20px;
  color : rgb(215, 0, 0);
  font-weight: bold;
  white-space: nowrap;
}
.prod-prevprice {
  font-size:18px;
  margin-bottom : 5%;
  text-decoration:line-through;
  color : gray;
}
.prod-price {
  font-size:33px;
  margin-bottom : 1%;
  font-weight: bold;
}

/* 위시리스트 */
.wishlist {
  display: flex;
  justify-content: flex-end;
  padding-top : 5%;
  border-top : 1px solid rgb(222, 222, 222);
}

select {
  width : 60%;
  padding:5px;
  margin-right: 3%;
  border : 1px solid #999;
  border-radius: 15px;
}

.wishlistbtn {
  background-color: white;
  border: 1px solid #036b40;
  border-radius: 15px;
  color: #036b40;
  transition: 0.3s
}

.wishlistbtn:hover {
  background-color: #036b40;
  color: white;
  transition: 0.3s
}
.zoom-lens {
  cursor: crosshair;
  position: absolute;
  border: 2px solid #000;
  width: 100px; /* 렌즈의 너비 */
  height: 100px; /* 렌즈의 높이 */
  opacity: 0.5; /* 렌즈의 투명도 */
  background-color: white; /* 렌즈 배경색, 필요에 따라 조정 가능 */
}

.wishlistbtn:hover {
  background-color: white; /* 호버 상태의 배경색 */
  color: #78EAEF;
  border: none;
  transform: scale(1.05); /* 호버 시 약간 확대 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 호버 시 그림자 효과 */
}

</style>
