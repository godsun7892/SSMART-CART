import { defineStore, storeToRefs } from 'pinia';
import { useCartStore } from './cart';
import { ref, watch } from 'vue';

export const useCopyStore = defineStore('copy', () => {
  const CartStore = useCartStore();
  const { products } = storeToRefs(CartStore); // cart.js의 products 참조
  const productsCopy = ref([]);

  // products 배열이 변할 때마다 실행될 watch 함수
  watch(products, (newValue) => {
    productsCopy.value = [...newValue]; // products 배열의 변경을 productsCopy에 반영
  }, { deep: true });

  return { productsCopy };
});