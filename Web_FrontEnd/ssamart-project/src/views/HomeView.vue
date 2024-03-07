<template>
  <div class="carousel-big-container">
    <Carousel :items-to-show="1.4" :wrap-around="true" :autoplay="3000" :autoplay-speed="1000">
      <Slide v-for="(image, index) in bigImages" :key="index">
        <div class="carousel__item">
          <img :src="image" alt="Carousel image" style="width: 100%;  height: auto; border-radius: 8px;">
        </div>
      </Slide>

      <template #addons>
        <Navigation>
          <template #prev>
            <div class="nav-button prev"></div>
          </template>
          <template #next>
            <div class="nav-button next"></div>
          </template>
        </Navigation>
        <Pagination />
      </template>
    </Carousel>
  </div>
  <div class="divider"></div>

  <div class="centered-image">
    <img src="/main/bigAd/ggok_saja.png" alt="Centered Image">
  </div>
  <div class="carousel-small-container">
    <Carousel :items-to-show="1.4" :wrap-around="true" :autoplay="2000" :autoplay-speed="4000">
      <Slide v-for="(image, index) in smallImages" :key="index">
        <div class="carousel__item">
          <img :src="image" alt="Carousel image" style="width: 100%;  height: auto; border-radius: 8px;">
        </div>
      </Slide>
      <template #addons>
        <Navigation>
          <template #prev>
            <div class="nav-button prev"></div>
          </template>
          <template #next>
            <div class="nav-button next"></div>
          </template>
        </Navigation>
      </template>
    </Carousel>
  </div>
  <div class="divider"></div>
  <!-- 상품 카드 컨테이너 -->
  <div class="product-cards-container" v-if="productList && productList.length > 0">
    <div v-for="product in productList" :key="product.seq" @click="goToProductDetail(product.seq)"
      class="product-card-container">
      <div class="product-one-card">
        <img :src="product.image" alt="Product Image" class="product-image">
        <div class="product-info">
          <h3 class="product-name">{{ product.name }}</h3>
          <p class="product-price">{{ Math.round(product.price - (product.price * product.discountRate)).toLocaleString() }}원</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent, ref, onMounted, computed } from 'vue'
import { Carousel, Navigation, Pagination, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import { useRouter } from 'vue-router'
import { useProductStore } from '@/stores/productStore'

export default defineComponent({
  name: 'WrapAround',
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
  },

  setup() {
    const productStore = useProductStore();
    const router = useRouter();

    // 상품 데이터를 불러오는 로직
    onMounted(async () => {
      await productStore.fetchProductList();
    });


    const productList = computed(() => productStore.productList.slice(0,9))

    // 상품 상세 페이지로 이동
    const goToProductDetail = (seq) => {
      // 'productSeq' 를 상품 ID 로 사용하여 URL에 전달합니다.
      if (seq) {
        router.push({ name: 'ProductDetail', params: { seq } });
      } else {
        console.error("제품 ID가 제공되지 않았습니다.")
      }
    };

    const bigImages = ref([
      '/main/bigAd/ad_1.png',
      '/main/bigAd/ad_2.png',
      '/main/bigAd/ad_3.png',
      '/main/bigAd/ad_4.png',
      '/main/bigAd/ad_5.png',
      '/main/bigAd/ad_6.png',
      '/main/bigAd/ad_7.png',
    ])

    const smallImages = ref([
      '/main/smallAd/ad_1.png',
      '/main/smallAd/ad_2.png',
      '/main/smallAd/ad_3.png',
      '/main/smallAd/ad_4.png',
      '/main/smallAd/ad_5.png',
      '/main/smallAd/ad_6.png',
    ])


    return { bigImages, smallImages, productList, goToProductDetail }
  }
})
</script>

<style>
.carousel-big-container {
  padding-bottom: 30px;
  /* 구분선 아래 공간 확보 */
}

.carousel-small-container {
  padding-bottom: 30px;
  /* 구분선 아래 공간 확보 */
}

.divider {
  border-bottom: 1px solid #cccccc77;
  /* 연한 회색의 1px 두께 구분선 */
  width: 100%;
  /* 너비는 부모 요소에 맞춤 */
  margin-top: 20px;
  margin-bottom: 50px;
  /* 구분선 아래 여백 추가 */
}

.centered-image {
  display: flex;
  justify-content: center;
  /* 가로 중앙 정렬 */
  margin: 30px 0;
  /* 위아래 여백 추가 */
}

.centered-image img {
  max-width: 100%;
  /* 이미지가 부모 요소 너비를 초과하지 않도록 함 */
  height: auto;
  /* 이미지 높이는 자동 조절 */
}

.carousel__item {
  /* small Banner 높낮이 */
  min-height: 80px;

  width: 100%;
  background-color: var(--vc-clr-primary);
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
  /* 헤더와 배너의 간격 */
}

.carousel__slide {
  padding: 10px;
}

.nav-button::before {
  content: '';
  display: inline-block;
  border: solid white;
  /* 화살표 색상 */
  border-width: 0 4px 4px 0;
  /* 화살표 두께 */
  padding: 20px;
  /* 화살표 크기 */
  transition: transform 0.2s;
  /* 부드러운 전환 효과 */
}

.next::before,
.prev::before {
  position: absolute;
  top: 0%;
  border-color: rgba(208, 205, 205, 0.716)
}

.next::before {
  right: calc(700% - 0px);
  /* 오른쪽 화살표 위치 조정 */
  transform: translateY(-10%) rotate(-45deg);
}

.prev::before {
  left: calc(700% - 0px);
  /* translateY : 화살표 높낮이 결정 */
  transform: translateY(-10%) rotate(135deg);
  /* 왼쪽 화살표 방향 */
}

.nav-button:hover::before {
  border-color: rgba(100, 100, 100, 0.472);
  /* hover 시 화살표 색상 변경 */
  transform: translateY(-10%) rotate(-45deg) scale(1.2);
  /* 오른쪽 화살표 확대 */
}

.prev:hover::before {
  /* translateY : 화살표 높낮이 결정 */
  transform: translateY(-10%) rotate(135deg) scale(1.2);
  /* 왼쪽 화살표 확대 */
}

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
  flex: calc(33% - 40px);
  /* 한 줄에 3개씩 표시 */
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
  /* 이미지와 설명 사이의 간격 */
}

/* 호버 효과 추가 */
.product-one-card:hover {
  transform: scale(1.05);
  /* 카드가 약간 확대되는 효과 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  /* 그림자 효과를 추가하여 입체감을 줌 */
}

.product-image {
  width: 70px;
  /* 이미지 너비를 카드에 맞춤 */
  aspect-ratio: 2/3;
  /* 이미지의 종횡비를 설정, 필요에 따라 조절 */
  border-top-left-radius: 8px;
  /* 이미지 상단 모서리 둥글게 처리 */
  border-top-right-radius: 8px;
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
