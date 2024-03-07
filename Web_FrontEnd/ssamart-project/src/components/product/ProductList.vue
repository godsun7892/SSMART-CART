<template>
  <div class="product-cards-container">
    <div class="product-card-container" v-for="product in categoryProducts" :key="product.seq"
      @click="goToProductDetail(product.seq)">
      <div class="product-one-card">
        <img :src="product.image" alt="Product Image" class="product-image">
        <div class="product-info">
          <h3 class="product-name">{{ product.name }}</h3>
          <p class="product-price">{{ Math.round(product.price - (product.price) * (product.discountRate)).toLocaleString() }}원</p>
        </div>
      </div>
    </div>
  </div>
</template>



<script setup>
import { computed, onMounted } from 'vue';
import { useProductStore } from '@/stores/productStore';
import { useRoute, useRouter } from 'vue-router';

const productStore = useProductStore();
const route = useRoute();
const router = useRouter();

onMounted(async () => {
  await productStore.fetchProductList(); // 상품 목록을 먼저 불러옵니다.
  // 특정 카테고리에 해당하는 상품 목록을 계산된 속성으로 만듭니다.

});

  const categoryProducts = computed(() => {
    const categorySeq = parseInt(route.query.categorySeq);
    return productStore.fetchProductsByCategory(categorySeq);
});


// 상품 상세 페이지로 이동
const goToProductDetail = (seq) => {
  if (seq) {
    router.push({ name: 'ProductDetail', params: { seq } });
  } else {
    console.error("제품 ID가 제공되지 않았습니다.")
  }
};
</script>

<style scoped>
/* 상품 리스트 */
.product-cards-container {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  margin: 5% 10%;
  /* 상품 카드 간격을 균등하게 조절 */

}

.product-card-container {
  /* 한 줄에 3개씩 표시 */
  flex: calc(33% - 40px);
  width: 80%;
  height: 80%;
  margin: 10px 20px;
  /* 카드 간 마진 */
  display: flex;
  /* Flexbox를 사용하여 내부 요소 정렬 */
  flex-direction: column;
  /* 요소들을 세로로 쌓음 */
  justify-content: space-between;
  /* 내부 요소 사이의 공간을 균등 분배 */
}

.product-one-card {
  border: 1px solid #ddd;
  border-radius: 10px;
  overflow: hidden;
  text-align: center;
  padding-bottom: 10px;
  margin-bottom: 10px;
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  /* 호버 효과를 위한 전환 추가 */
  height: 400px;
  
}

/* 호버 효과 추가 */
.product-one-card:hover {
  transform: scale(1.05);
  /* 카드가 약간 확대되는 효과 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 그림자 효과를 추가하여 입체감을 줌 */
}

.product-image {
  width: 100%;
  /* 이미지 너비를 카드에 맞춤 */
  aspect-ratio: 2/3;
  /* 이미지의 종횡비를 설정, 필요에 따라 조절 */
  border-top-left-radius: 8px;
  /* 이미지 상단 모서리 둥글게 처리 */
  border-top-right-radius: 8px;
  object-fit: cover; /* 이미지가 카드 크기에 맞게 조절되도록 설정 */
  height: 60%;
}

.product-info {
  padding: 15px;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* 텍스트를 중앙 정렬 */
}

.product-name {
  font-size: 18px;
  margin: 10px 0;
}

.product-price {
  color: #666;
  font-size: 20px;
}

.product-name,
.product-price {
  margin: 3% 20%;
  /* 이름과 가격 사이의 마진 */
}

</style>
