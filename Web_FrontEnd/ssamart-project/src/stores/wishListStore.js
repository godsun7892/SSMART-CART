import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "@/util/http-common.js";
import { useRouter } from "vue-router";
import { useUserStore } from "./userStore";

export const useWishStore = defineStore("wish", () => {
  const wishLists = ref([]);
  const wishProductDetails = ref([]);
  const router = useRouter();
  const componentKey = ref(0);

  const refreshComponent = () => {
    componentKey.value++; // 컴포넌트 키 값을 증가시켜 재렌더링 유발
  };

  // 사용자의 찜 목록 가져오기
  const fetchWishLists = () => {
    console.log(sessionStorage.getItem('userSeq'))
    const userSeq = sessionStorage.getItem("userSeq");
    if (!userSeq) {
      console.error("로그인 정보가 없습니다.");
      return;
    }
    axios
      .get(`/wish/list/${userSeq}`)
      .then((response) => {
        wishLists.value = response.data;
        console.log("fetchWishLists 성공", wishLists.value)
      })
      .catch((error) => {
        console.error("fetchWishLists 실패", error);
      });
  };

  // 새 찜 목록 추가하기
  const addWishList = async (wishListName) => {
    const userSeq = sessionStorage.getItem("userSeq"); // 현재 로그인한 사용자의 userSeq 가져오기
    console.log({ wishListName, userSeq }); // 값 확인
    if (!userSeq) return; // userSeq가 없으면 함수 종료

    // 중복 이름 검사
    const isDuplicate = wishLists.value.some(
      (wishList) => wishList.name === wishListName
    );
    if (isDuplicate) {
      alert("중복된 찜 목록 이름입니다.");
      return;
    }

    try {
      const wishListFormDto = { name: wishListName }; 
      axios
        .post(`/wish/listSave/${userSeq}`, wishListFormDto)
        .then((response) => {
          console.log(response);
          // pinia 저장소에 데이터를 바로 저장하는 것
          wishLists.value.push(response.data);
        });
      console.log("addWishList 성공");

    } catch (error) {
      console.error("addWishList 실패", error);
    }
  };


  // 찜 목록 이름 변경하기
  const updateWishListName = async (wishListSeq) => {
    const newName = prompt("새 찜 이름을 입력해주세요.");
    if (!newName || newName.trim() === "") {
      alert("찜 목록 이름을 올바르게 입력해주세요.");
      return;
    }

    try {
      await axios.patch(`/wish/listUpdate/${wishListSeq}`, { name: newName });
      // 찜 목록의 이름을 직접 업데이트
      const wishList = wishLists.value.find(wish => wish.seq === wishListSeq);
      if (wishList) {
        wishList.name = newName;
      }
      // fetchWishLists 호출 대신 직접 업데이트
    } catch (error) {
      console.error("updateWishListName 실패", error);
    }
  };

  // 찜 목록 삭제하기
  const removeWishList = async (wishListSeq) => {
    console.log(wishLists.value);
    const isConfirmed = confirm("이 찜 목록을 삭제하시겠습니까?");
    if (!isConfirmed) return;

    try {
      axios.delete(`/wish/listDelete/${wishListSeq}`);
      for(let i = 0;i<wishLists.value.length;i++){
        if(wishLists.value[i].seq === wishListSeq){
          console.log(wishListSeq)
          wishLists.value.splice(i,1);
        }
      }
      alert("찜 목록이 삭제되었습니다.");
    } catch (error) {
      console.error("removeWishList 실패", error);
    }
  };


  // 찜 목록에서 상품 제거하기
  const removeProductFromWishList = async (wishProductSeq) => {
    try {
      await axios.delete(`/wish/productDelete/${wishProductSeq}`);
      console.log("상품 삭제 성공", wishProductSeq);
      alert("선택하신 상품이 찜 목록에서 삭제되었습니다.")
      window.location.reload();

      // 상품을 찜 목록에서 삭제 후 wishProductDetails 상태 업데이트
      wishProductDetails.value = wishProductDetails.value.filter(
        (product) => product.wishProductSeq !== wishProductSeq
      );
    } catch (error) {
      console.error("상품 삭제 실패", error);
    }
  };


  // 찜 목록 상품 추가하기
  const addProductToWishList = (productSeq, wishListSeq) => {
    const wishProductFormDto = { quantity: 1, productSeq };
    axios
      .post(`/wish/productSave/${wishListSeq}`, wishProductFormDto)
      .then(() => {
        fetchWishLists(); // 찜 목록 다시 불러오기
        alert("찜 목록에 상품이 추가되었습니다.");
        console.log("addProductToWishList 성공", wishProductFormDto);
      })
      .catch((error) => {
        // console.log(wishListSeq)
        console.error("addProductToWishList 실패", error);
      });
  };

  const fetchWishListProducts = async (seq) => {
    try {
      const response = await axios.get(`/wish/products/${seq}`);
      wishProductDetails.value = response.data;
      console.log("fetchWishListProducts 성공", wishProductDetails.value);
    } catch (error) {
      console.error("fetchWishListProducts 실패", error);
    };
  };

  

  
  return {
    wishLists,
    wishProductDetails,
    fetchWishLists,
    fetchWishListProducts,
    addWishList,
    addProductToWishList,
    updateWishListName,
    removeWishList,
    removeProductFromWishList,
    refreshComponent,
  };
},
  { persist: true }
);
