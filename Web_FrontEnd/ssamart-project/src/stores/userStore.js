import { ref, onMounted } from "vue"
import { defineStore } from "pinia"
import router from "@/router"
import axios from "@/util/http-common.js"
import { useWishStore } from "./wishListStore"
import { usePaymentStore } from "./paymentStore"

export const useUserStore = defineStore("user", () => {
    const users = ref([]);
    const user = ref(null);
    const loginUser = ref(null);
    const wishListStore = useWishStore();
    const paymentStore = usePaymentStore();

    // 회원가입
    const registUser = ((inputUser) => {
        axios
            .post("auth/regist", inputUser)
            .then(() => {
                console.log("registUser 성공")
                alert("회원가입에 성공하였습니다.")
                router.push({ name: "Login" })
            })
            .catch((error) => {
                console.error("registUser 실패:", error)
                alert("회원가입에 실패하였습니다.")
            })
    })

    
    // 로그인
    const setLoginUser = (inputUser) => {
        axios
            .post("auth/login", inputUser)
            .then((response) => {
                if (response.status === 200) {
                    const { token, seq } = response.data;
                    // 토큰을 세션 스토리지에 저장합니다.
                    sessionStorage.setItem('user-token', token);
                    sessionStorage.setItem('userSeq', seq);+
                    sessionStorage.setItem('userId',inputUser.userId);
                    // 이후의 모든 요청에 토큰을 헤더에 추가합니다.
                    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
                    loginUser.value = response.data;
                    wishListStore.fetchWishLists();
                    paymentStore.fetchPaymentHistory();
                    console.log("setLoginUser 성공:", loginUser.value);
                    router.push({ name: "Home" })
                }
            })
            .catch((error) => {
                console.error('setLoginUser 실패:', error);
                alert('로그인에 실패하였습니다.')
            });
    };


    // 로그아웃
    const setLogout = (() => {
        loginUser.value = null
        sessionStorage.removeItem('userSeq')
        sessionStorage.removeItem('user-token')
        // axios header에서 Authorization을 삭제합니다.
        delete axios.defaults.headers.common['Authorization'];
        router.push({ name: "Home" })
    })

    return {
        users,
        user,
        loginUser,
        registUser,
        setLoginUser,
        setLogout
    }
}, { persist: true })