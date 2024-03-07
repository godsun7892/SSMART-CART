<template>
  <div class="product-regist-container">
    <h1>상품 등록</h1>
    <form @submit.prevent="submitProduct">
      <!-- 바코드 -->
      <div class="form-group">
        <label for="barcode">바코드</label>
        <input type="text" id="barcode" v-model="productData.barcode" required>
      </div>

      <!-- 상품명 -->
      <div class="form-group">
        <label for="name">상품명</label>
        <input type="text" id="name" v-model="productData.name" required>
      </div>

      <!-- 내용 -->
      <div class="form-group">
        <label for="content">내용</label>
        <textarea id="content" v-model="productData.content"></textarea>
      </div>

      <!-- 재고 -->
      <div class="form-group">
        <label for="stock">재고</label>
        <input type="number" id="stock" v-model="productData.stock" min="1" value="1" required>
      </div>

      <!-- 가격 -->
      <div class="form-group">
        <label for="price">가격</label>
        <input type="number" id="price" v-model="productData.price" required>
      </div>

      <!-- 평점 -->
      <div class="form-group">
        <label for="avgScore">평점</label>
        <input type="number" id="avgScore" v-model="productData.avgScore" min="0" max="5" step="0.1">
      </div>

      <!-- 할인율 -->
      <div class="form-group">
        <label for="discountRate">할인율</label>
        <input type="number" step="0.1" id="discountRate" v-model="productData.discountRate">
      </div>

      <!-- 할인 여부 -->
      <div class="form-group">
        <label for="discount">할인 여부</label>
        <select id="discount" v-model="productData.discount">
          <option value="true">예</option>
          <option value="false">아니오</option>
        </select>
      </div>

      <!-- 판매 상태 -->
      <div class="form-group">
        <label for="productSellStatus">판매 상태</label>
        <select id="productSellStatus" v-model="productData.productSellStatus">
          <option value="SELL">판매 중</option>
          <option value="SOLD_OUT">품절</option>
        </select>
      </div>

      <!-- 카테고리 -->
      <div class="form-group">
        <label for="category">카테고리</label>
        <!-- 사용자가 카테고리 ID를 직접 입력할 수 있는 input 요소로 변경 -->
        <input type="number" id="category" v-model="productData.categorySeq" required>
      </div>

      <!-- 이미지 파일 업로드 -->
      <div class="form-group">
        <label for="image">이미지</label>
        <input type="file" id="image" @change="handleFileUpload">
        <div v-if="previewImage" class="image-preview">
          <img :src="previewImage" alt="Image preview" style="max-width: 200px; max-height: 200px;">
        </div>
      </div>

      <button type="submit">등록하기</button>
    </form>
  </div>
</template>
  
<script>
import { ref } from 'vue';
import { useProductStore } from '@/stores/productStore';

export default {
  setup() {
    const productStore = useProductStore();
    const previewImage = ref('');
    const fileToUpload = ref(null); // 업로드할 파일 저장

    // 입력된 상품 정보, categoryId 필드 추가
    const productData = ref({
      barcode: '',
      name: '',
      content: '',
      stock: 1,
      price: '',
      discountRate: 0.2,
      discount: true,
      productSellStatus: 'SELL',
      avgScore: 4.5,
      categorySeq: '', // 사용자가 입력하는 카테고리 ID
    });

    const handleFileUpload = (event) => {
      const file = event.target.files[0];
      if (file) {
        previewImage.value = URL.createObjectURL(file);
        fileToUpload.value = file;
      }
    };

    const submitProduct = async () => {
      const formData = new FormData();
      formData.append('productForm', new Blob([JSON.stringify(productData.value)], { type: 'application/json' }));
      if (fileToUpload.value) {
        formData.append('image', fileToUpload.value);
      }

      try {
        await productStore.addProduct(formData);
        console.log("상품 등록 성공");
        // 상품 목록 페이지로 리다이렉션 등의 추가 로직
      } catch (error) {
        console.error("상품 등록 실패", error);
      }
    };

    return {
      productData,
      handleFileUpload,
      submitProduct
    };
  }
};
</script>
  
<style>
.product-regist-container {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input[type="text"],
.form-group input[type="number"],
.form-group textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-group textarea {
  resize: vertical;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.image-preview img {
  margin-top: 10px;
}
</style>
