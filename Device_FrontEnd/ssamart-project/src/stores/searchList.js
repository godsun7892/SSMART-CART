import { ref, computed } from 'vue'
import { defineStore, storeToRefs } from 'pinia'
import axios from "@/util/http-common";
import { useFlagStore } from "@/stores/flag.js";
import { useLoginStore } from "./login";

export const useSearchListStore = defineStore('useSearchListStore', () => {

  const wishlist = ref([])
  const wishGroupList = ref([])
  // const wishlist = ref([
  //   {seq:0, product:{image:'/image/image 40.png',name:'가지',price:4800},quantity:10},
  //   {seq:1, product:{image:'/image/image 40.png',name:'가지',price:4800},quantity:10},
  //   {seq:2, product:{image:'/image/image 40.png',name:'가지',price:4800},quantity:10},
  //   {seq:3, product:{image:'/image/image 40.png',name:'가지',price:4800},quantity:10},
  //   {seq:4, product:{image:'/image/image 40.png',name:'가지',price:4800},quantity:10},
  // ]) // 찜 목록 그룹 전체 저장소

  // const wishGroupList = ref([
  //   {seq:0, name:'찜목록1',pName:'가지가지', image:'/image/onion/onion1.png', quantity:7},
  //   {seq:1, name:'찜목록2',pName:'가지가지', image:'/image/squash/squash1.png', quantity:7},
  //   {seq:2, name:'찜목록3',pName:'가지가지', image:'/image/kimchi/kimchi1.png', quantity:7},
  //   {seq:3, name:'찜목록4',pName:'가지가지', image:'/image/snack/chocoPie.png', quantity:7},
  //   {seq:4, name:'찜목록5',pName:'가지가지', image:'/image/snack/diget.png', quantity:7},
  // ])

  // const searchList = ref([
  //   {seq:0, product:{image:'/image/image 71.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:1, product:{image:'/image/image 121.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:2, product:{image:'/image/image 122.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:3, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:4, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:5, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:6, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:7, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:8, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:9, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:10, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:11, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:12, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:13, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  //   {seq:14, product:{image:'/image/kimchi.png',name:'가지(3개입, 100g) 우리나라 국내산 가지가지 정말로 가지가지',price:4800,discountRate:0.26,avgScore:4.17},quantity:10, sales:154},
  // ]) // 찜 목록 그룹 전체 저장소

  const keyWord = ref('') // 검색어 저장소
  const flagSwitch = useFlagStore();
  const { screenIndex } = storeToRefs(flagSwitch); // 모달창 on/off에 필요한 값 받기

  const loginStore = useLoginStore();
  const { userSeq } = storeToRefs(loginStore); // userSeq 값 받기

  const recipeInfo =ref([]) // 오늘의 메뉴 추천 정보 저장소

  const searchList = ref([]); // 검색 결과 정보 저장소

  const HotDealList = ref([]); // 오늘의 핫딜 정보 저장소

  const currentDetail = ref([]);

  const nowList = ref('');

  const firstColumn=ref([]);
  const secondColumn=ref([]);

  // 오늘의 핫딜 할인 상품 불러오기
  const discountListSearch = (() => {
    axios
      .get(`/products/discount`)
      .then((response) => {
        console.log("할인 정보 조회 성공");
        HotDealList.value = response.data;
        //console.log(HotDealList.value);
        screenIndex.value=3; // 할인 정보 저장 후 모달 창 열기
      })
      .catch((error) => {
        console.log("할인 정보 조회 실패");
      })
  })

  const wishPlus = ((target)=>{
    console.log(userSeq.value);
    axios
      .post(`/wish/save/${userSeq.value}`,{
        quantity : 1,
        productSeq : target.seq
      })
      .then((response) => {
        console.log("wishlist 등록 성공")
        console.log(response);
        target.productLiked = !target.productLiked;
      })
      .catch((error)=>{
        console.log("wishlist 등록 실패")
      })

  })

  const wishMinus = ((target)=>{
    console.log(target);
    var wishSeq = 0;
    var wishIndex = -1;
    console.log('hoii');
    for(let i = 0; i<wishlist.value.length;i++){
      if(wishlist.value[i].name === target.name){
        wishSeq = target.wishProductSeq;
        wishIndex = i;
      }
    }
    console.log('hoiioo');
    console.log(wishSeq);
    axios
      .delete(`/wish/delete/${wishSeq}`)
      .then((response) => {
        console.log(response);
        console.log("wishlist 삭제 성공")
        console.log(target);
        target.productLiked = !target.productLiked;
        wishlist.value.splice(wishIndex,1);
        console.log(wishlist);
      })
      .catch((error)=>{
        console.log("wishlist 삭제 실패")
      })
  })

  // userSeq번 유저의 찜 목록 wishlistSeq를 불러옴 
  const wishlistSearch = (()=>{
    console.log(userSeq.value)
    axios
      .get(`/wish/list/${userSeq.value}`)
      .then((response) => {
        console.log("wishGroup 조회 성공")
        console.log(response);
        wishGroupList.value = response.data;
        if(wishGroupList.value.length>0){
            wishSearch(wishGroupList.value[0].seq);
            nowList.value = wishGroupList.value[0].name;
        }
      })
      .catch((error)=>{
        console.log("wishGroup 조회 실패")
      })
  })

  // wishListSeq에 해당하는 상품들 불러옴
  const wishSearch = ((wishListSeq)=>{
    axios
      .get(`/wish/products/${wishListSeq}`,{
      })
      .then((response) => {
        console.log("wishlist 조회 성공")
        console.log(response.data)
        wishlist.value = response.data
        
      })
      .catch((error)=>{
        console.log("wishlist 조회 실패")
      })
  })

  // 오늘의 메뉴 추천 정보 불러옴
  const randomMenu = (()=>{
    axios
      .get('/etc/randomCuisine')
      .then((response) => {
        console.log('레시피 조회 성공');
        console.log(response);
        recipeInfo.value = response.data;
        const halfLength = Math.ceil(recipeInfo.value.productList.length / 2);
        firstColumn.value = recipeInfo.value.productList.slice(0, halfLength),
        secondColumn.value =  recipeInfo.value.productList.slice(halfLength)
      })
      .catch((error) =>{
        console.log('레시피 조회 실패');
      })
  })

  const productSearch = (()=>{
    axios
      .get(`/products/search/${keyWord.value}`)
      .then((response)=>{
        searchList.value = response.data;
        console.log(searchList.value);
      })
      .catch((error)=>{
        console.log(error);
      })
  })


  // return { wishlist, searchList, HotDealList, recipeInfo, toggleHeart, discountListSearch, wishlistSearch, randomMenu, wishMinus }
  return { wishlist, keyWord, searchList, HotDealList, recipeInfo, currentDetail, wishGroupList,nowList, firstColumn, secondColumn,
    discountListSearch, wishlistSearch, randomMenu, wishMinus, productSearch, wishSearch }

})


