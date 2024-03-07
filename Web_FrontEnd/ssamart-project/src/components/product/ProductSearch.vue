<template>
  <div class="product-search-container">
    <h2>검색 결과</h2>
    <div v-if="searchResults.length" class="product-grid">
      <div class="product-card" v-for="product in searchResults" :key="product.seq" @click= "goToProductDetail(product.seq)">
        <img :src="product.image" alt="Product image" class="product-image" />
        <div class="product-info">
          <h3>{{ product.name }}</h3>
          <p>{{ Math.round(product.discountPrice).toLocaleString() }}원</p>
        </div>
      </div>
    </div>
    <div v-else>
      검색 결과가 없습니다.
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { useProductStore } from '@/stores/productStore';
import { useRouter } from 'vue-router';

const productStore = useProductStore();
const router = useRouter();
const searchResults = computed(() => productStore.searchResults);

function goToProductDetail(seq) {
  console.log("Navigating to product detail with productSeq:", seq); // 로그 출력

  if (!seq) {
    console.error("제품 ID가 제공되지 않았습니다.");
    return;
  }

  router.push({ name: 'ProductDetail', params: { seq } }); // 올바른 params 전달
}

// 상품 데이터를 불러오는 로직
onMounted(() => {
  productStore.fetchProductList();
});
</script>

<style>
.product-search-container {
  padding: 20px;
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
}

.product-search-container h2 {
  margin: 3% 3%;
}

.product-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 40px;
  margin: 0 30px;
  justify-content: space-around;
}

.product-card {
  width: calc(33% - 50px);
  border: 1px solid #eee;
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  transition: box-shadow 0.3s;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
}

.product-card:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.product-info {
  padding: 10px;
  text-align: center;
}

h3 {
  margin: 10px 0;
}

p {
  color: #666;
}
</style>
