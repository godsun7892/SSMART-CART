<template>
  <div class="wish-list-detail-container">
    <div class="list-header">
      <h1>{{ wishListName }} 리스트 <span class="total-count">(총 {{ wishListStore.wishProductDetails.length }}개)</span></h1>
      <p class="total-price">총 가격: {{ totalProductPrice }}원</p>
    </div>
    <ul v-if="wishListStore.wishProductDetails && wishListStore.wishProductDetails.length > 0" class="product-list">
      <li v-for="product in wishListStore.wishProductDetails" :key="product.wishProductSeq" class="product-item">
        <img :src="product.image" alt="Product Image" class="product-image" />
        <h3 class="product-name">{{ product.name }}</h3>
        <p class="product-price">{{ Math.round(product.price).toLocaleString() }}원</p>
        <button @click="removeProductFromWishList(product.wishProductSeq)" class="remove-btn">삭제</button>
      </li>
    </ul>
    <div v-else class="no-products">
      이 찜 목록에는 상품이 없습니다.
    </div>
    <button @click="goBack" class="go-back-btn">뒤로 가기</button>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useWishStore } from '@/stores/wishListStore';
import { useRouter, useRoute } from 'vue-router';

const wishListStore = useWishStore();
const router = useRouter();
const route = useRoute();

const wishListSeq = route.params.seq; // URL에서 가져온 찜 목록의 seq입니다.
const wishListName = ref('');

onMounted(async () => {
  await wishListStore.fetchWishListProducts(wishListSeq);
  // 반응성을 유지하기 위해 필요할 때마다 wishProductDetails에 직접 접근합니다.
  const wishList = wishListStore.wishLists.find(list => list.seq === wishListSeq*1);
  wishListName.value = wishList ? wishList.name : '알 수 없는 목록';
});

// 총 가격
const totalProductPrice = computed(() => {
  return wishListStore.wishProductDetails.reduce((total, product) => {
    return total + product.price;
  }, 0).toLocaleString();
});

// 뒤로 가기
const goBack = () => {
  router.back();
};

// 찜 목록에서 상품 제거 이벤트 핸들러
const removeProductFromWishList = (wishProductSeq) => {
  wishListStore.removeProductFromWishList(wishProductSeq);
}


</script>

<style scoped>
.wish-list-detail-container {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  max-width: 1000px;
  margin: auto;
  padding: 20px;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}

.total-price {
  font-weight: 700;
  font-size: 1.4em;
  color: #333
}

.product-list {
  list-style: none;
  padding: 0;
  margin: 20px 0px; /* 상하좌우에 여백 추가 */
}

.product-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #ccc;
  padding: 10px 0;
}

.product-image {
  max-width: 150px; /* 이미지 크기 */
  height: 100px;
  flex-shrink: 0; /* 이미지 크기가 줄어들지 않도록 설정 */
  margin-right: 0px; /* 이미지와 상품명 사이 간격 유지 */
}

.product-name {
  white-space: nowrap; /* 텍스트가 한 줄로 표시 */
  overflow: hidden; /* 오버플로는 숨김 처리 */
  text-overflow: ellipsis; /* 텍스트가 오버플로우 될 경우 말줄임표로 표시 */
  flex-grow: 1;
  margin-right: 0px; /* 상품명과 가격 사이 간격 */
  margin-left: 5%;
  font-weight: 500;
}

.product-info {
  flex-grow: 1; /* 상품 정보가 남은 공간을 차지하도록 설정 */
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  overflow: hidden; /* 오버플로우는 숨김 처리 */
}

.product-price {
  white-space: nowrap; /* 텍스트가 한 줄로 표시 */
  flex-shrink: 0; /* 가격 크기가 줄어들지 않도록 설정 */
  margin-right: 20px; /* 가격과 삭제 버튼 사이 간격 */
  margin-left: 5%;
  font-weight: 500;
  color: black;
}

.remove-btn {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  flex-shrink: 0; /* 버튼 크기가 줄어들지 않도록 설정 */
  padding: 10px 30px;
  margin-left: 30px;
  background-color: inherit; /* 뒤에 배경이랑 똑같은 색 */
  color: #78EAEF;
  font-weight: 900;
  border: 2px solid #78EAEF;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.remove-btn:hover {
  background-color: #78EAEF; /* 버튼 호버 배경색 */
  border-color: #78EAEF;
  color: #ffffff; /* 텍스트 색상 */
}

.no-products {
  text-align: center;
  padding: 20px;
  font-size: 20px;
}

.go-back-btn {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  margin-top: 30px;
  margin-left: 20px;
  padding: 10px 20px;
  background-color: inherit;
  color: #78EAEF;
  font-weight: 900;
  border: 2px solid #78EAEF;
  cursor: pointer;
  transition: background-color 0.3s;
  border-radius: 5px;
}

.go-back-btn:hover {
  background-color: #78EAEF;
  border-color: #78EAEF;
  color: #ffffff;
}

.total-count {
  font-size: 0.5em;
  font-weight: 400;
}
</style>


