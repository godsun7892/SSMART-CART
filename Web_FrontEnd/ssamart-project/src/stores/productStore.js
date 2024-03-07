import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/util/http-common.js";
import { useRouter } from "vue-router";

export const useProductStore = defineStore("product",() => {
    const productList = ref([]);
    const searchResults = ref([]);
    const productDetails = ref([]);
    const router = useRouter();

    // 상품 목록 불러오기
    const fetchProductList = () => {
      axios
        .get("/products/list?size=200")
        .then((response) => {
          productList.value = response.data;
          console.log("fetchProductList 성공", productList.value);
        })
        .catch((error) => {
          console.error("fetchProductList 실패", error);
        });
    };

    // 특정 카테고리에 해당하는 상품만 필터링하여 불러오기
    const fetchProductsByCategory = (categorySeq) => {
        // 이미 fetchProductList를 호출하여 상품 목록을 불러왔다고 가정합니다.
        // 필터링 조건에 맞는 상품만 반환합니다.
        return productList.value.filter(product => product.category && product.category.seq === categorySeq);
    };

    // 상품 등록 (이미지 포함)
    const addProduct = (formData) => {
      return axios
        .post("/products/save", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          console.log("addProduct 성공");
          router.push({ name: "ProductList" });
        })
        .catch((error) => {
          console.error("addProduct 실패", error);
        });
    };

    // 상품 검색
    const searchProduct = (searchTerm) => {
      axios
        .get(`/products/search/${encodeURIComponent(searchTerm)}`)
        .then((response) => {
          searchResults.value = response.data;
          console.log("searchProduct 성공", searchResults.value);
        })
        .catch((error) => {
          console.error("searchProduct 실패", error);
        });
    };

    // 상품 상세 정보 가져오기
    const fetchProductDetail = (seq) => {
        return axios.get(`/products/detail/${seq}`)
            .then((response) => {
                productDetails.value = response.data;
                console.log("fetchProductDetail 성공", productDetails.value);
            })
            .catch((error) => {
                console.log(productDetails.value)
                console.error("fetchProductDetail 실패", error);
            });
    };

    return {
        productList,
        searchResults,
        searchProduct,
        fetchProductList,
        fetchProductDetail,
        fetchProductsByCategory,
        addProduct,
        productDetails,
        get productCount() {
            return productList.value.length;
        },
        get searchProductCount() {
            return searchResults.value.length;
        }
    }
}, { persist: true });
