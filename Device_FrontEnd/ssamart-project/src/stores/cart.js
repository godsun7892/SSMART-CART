import { ref, computed } from 'vue';
import { defineStore, storeToRefs } from 'pinia';
import { useSearchListStore } from './searchList';
import axios from "@/util/http-common";
import { useLoginStore } from "./login";
import { useRouter } from 'vue-router';

const searchListStore = useSearchListStore();
const { wishlist } = storeToRefs(searchListStore);

export const useCartStore = defineStore('useCartStore', () => {
    
    const payMethod = ref([]);
    const cartSeq = ref(0);
    const loginStore = useLoginStore();
    const { userSeq } = storeToRefs(loginStore);
    const router = useRouter();

    //const purchasePrice = ref(0);
    //const discountPrice = ref(0); // 할인 가격 초기값을 0으로 설정
    //const totalPrice2 = computed(() => purchasePrice.value - discountPrice.value); // 총 결제 가격은 purchasePrice - discountPrice

    const products = ref([]); // 장바구니 상품 정보 저장소

    const totalPrice = computed(() => { // 할인된 상품 총 금액 computed 연산 값 저장
        return products.value.reduce((total, item) => total + (item.product.price * item.quantity * (1 - item.product.discountRate)), 0)
    });

    const totalOriginPrice = computed(() => { // 원래의 상품 총 금액 computed 연산 값 저장
        return products.value.reduce((total, item) => total + (item.product.price * item.quantity), 0)
    });

    const totalDiscountedPrice2 = computed(() => { // 총 할인된 금액 computed 연산 값 저장
        return products.value.reduce((total, item) => total + (item.product.price * item.quantity * item.product.discountRate), 0)
    });

    const totalDiscountedPrice = computed(() => { // 총 할인된 금액 computed 연산 값 저장
        return totalOriginPrice - totalPrice;
    });

    const totalQuantity = computed(() => { // 상품 총 금액 computed 연산 값 저장
        return products.value.reduce((total, item) => total + item.quantity, 0)
    });

    const addProduct = ((barcode) => {
        // 바코드에 찍힌 상품을 장바구니에 추가
        // 이미 있는 상품인지에 대한 판단은 shop_ver2 바코드 스캔 관련 스크립트에서 수행
        axios
            .post(`/cart/save/${userSeq.value}`, {
                barcode: barcode
            })
            .then((response) => {
                if (!response.data.product) { // 반환값이 Null 이면 상품을 추가하지 않음
                    alert("상품을 찾을 수 없습니다.")
                    axios.delete(`/cart/delete/${response.data.cartProductSeq}`) // Null 데이터 DB에도 삭제
                }
                else {
                    console.log("상품 등록 성공");
                    console.log(response);
                    response.data.quantity = 1; // 수량 속성이 따로 없으므로 1개라고 추가
                    products.value.push(response.data); //proudcts 저장소에 반환값 추가
                    // 추가한 상품이 찜 목록에 있는 상품인지 확인
                    for (let i = 0; i < wishlist.value.length; i++) {
                        if (wishlist.value[i].name === response.data.product.name) {
                            //찜 목록에 있는 상품이라면 해당 찜 상품의 productOn 속성 true로 변경
                            // productOn : 해당 찜 상품이 장바구니에 담겨있는지를 나타내는 속성 (true일 경우 화면에 담았다고 표시됨)
                            wishlist.value[i].productOn = true;
                            break;
                        }
                    }
                }
            })
            .catch((error) => {
                console.log(barcode);
                console.log("상품 등록 실패");
            })
    })

    const searchCart = (()=>{
        axios
            .get(`/cart/list/${userSeq.value}`)
            .then((response)=>{
                console.log('장바구니 불러오기 성공')
            })
            .catch((error)=>{
                console.log('장바구니 불러오기 실패')
            })
    })

    const plusProduct = ((product) => {
        // 바코드에 찍힌 상품이 이미 담겨있거나,
        // 장바구니 카드에 수량 추가 버튼을 클릭 했을 때 동작
        console.log(product);
        axios
            .patch(`/cart/plus/${product.cartProductSeq}`)
            .then((response) => {
                console.log(`${product.cartProductSeq} 수량 증가`);
                product.quantity++; // DB와 동기화 시키기 위해 따로 추가시켜줌
                //purchasePrice.value += product.product.price;
            })
            .catch((error) => {
                console.log("수량 증가 실패")
            });
    })

    const minusProduct = ((product) => {
        // 장바구니 카드에 수량 감소 버튼 클릭 했을 때 동작
        // 상품 수량을 1개 미만으로 감소 시킬 수 없도록 방지
        if (product.quantity > 1) {
            axios
                .patch(`/cart/minus/${product.cartProductSeq}`)
                .then((response) => {
                    console.log(`${product.cartProductSeq} 수량 감소`);
                    product.quantity--; // DB와 동기화 시키기 위해 따로 감소시켜줌
                    //purchasePrice.value -= product.price;
                })
                .catch((error) => {
                    console.log("수량 감소 실패")
                });
        }
        else {
            alert('상품이 최소 수량입니다. 삭제를 원하실 경우 우측 버튼을 클릭해주세요')
        }

    })

    const deleteProduct = ((product) => {
        // 장바구니에 담긴 상품을 삭제
        console.log(product);
        console.log(products.value);
        axios
            .delete(`/cart/delete/${product.cartProductSeq}`)
            .then((response) => {
                console.log(`${product.cartProductSeq} 삭제`);
                // DB와 동기화 시키기 위해 따로 제거
                for (let i = 0; i < products.value.length; i++) {
                    if (products.value[i].cartProductSeq === product.cartProductSeq) {
                        products.value.splice(i, 1);
                    }
                }
                // 삭제한 상품이 찜 목록에 있던 상품인지 체크
                for (let i = 0; i < wishlist.value.length; i++) {
                    if (wishlist.value[i].name === product.product.name) {
                        wishlist.value[i].productOn = false; // 있던 상품이면 productOn 속성 false로
                    }
                }
            })
            .catch((error) => {
                console.log("삭제 실패")
            });
    })

    const deleteAllProduct = (() => {
        // 로그아웃, 결제 완료 후 장바구니를 비움
        axios
            .delete(`/cart/deleteAll/${userSeq.value}`)
            .then((response) => {
                console.log('장바구니를 비웠습니다.')
                products.value = [];
                wishlist.value = [];
            })
            .catch((error) => {
                console.log('장바구니 비우기에 실패하였습니다.')
            })
    })

    const paymentNow = (() => {
        // UWB 모듈간 거리가 일정 이하 일 때 동작
        // 결제 로직을 수행하라는 메세지를 서버에 전송
        console.log(userSeq.value);
        console.log(totalPrice.value);
        axios
            .post('/pay/process', {
                memSeq: userSeq.value,
                amount : totalPrice.value
            })
            .then((response) => {
                console.log('결제 요청 성공');
                router.push('/paymentProgress');
            })
            .catch((error) => {
                alert('결제 요청에 실패하였습니다.')
                console.log("결제 요청 실패")
            })
    })


    const cartClean = () => {
        products.value = [];
        wishlist.value = [];
    }

    return { products, totalPrice, totalOriginPrice, totalDiscountedPrice, totalDiscountedPrice2, totalQuantity, searchCart,
         addProduct, plusProduct, minusProduct, deleteProduct, deleteAllProduct, paymentNow, cartClean }
})