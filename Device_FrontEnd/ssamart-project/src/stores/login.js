import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { useRouter } from 'vue-router';
import axios from "@/util/http-common.js"


export const useLoginStore = defineStore('useLoginStore', () => {

	const inputId = ref('');
	const inputPw = ref('');
	const router = useRouter();
	const loginMessageOn = ref(false);
	const loginModalNum = ref(0);
	const username = ref('');
	const userSeq = ref();
	const loginUser = ref(null);
	const matchFlag = ref(true);

	const loginRequest = (num) => {
		axios
			.post("/auth/login", {
				userId: inputId.value,
				password: inputPw.value,
			})
			.then((response) => {
				console.log(response);
				//라우팅 -> 지문 등록 선택 화면 (로그인 성공 시)
				username.value = response.data.name;
				userSeq.value = response.data.seq;
				loginUser.value = response.data;
				sessionStorage.setItem('userSeq', userSeq.value)
				sessionStorage.setItem('user-token', response.data.token)
				sessionStorage.setItem('username', username.value)
				axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
				console.log(num);
				// 지문 등록 창에서 로그인 할 경우 num = 2 => 메시지 창 안띄움
				if (num === 1) loginMessageModal(num);
				else router.push('/fingerEnroll');
				//inputId.value = '';
				inputPw.value = '';
			})
			.catch((error) => {
				alert("비밀 번호를 다시 확인해주십시오")
				console.log(inputId.value);
				console.log(inputPw.value);
				console.log("로그인 실패");
			})
	}

	const loginMessageModal = (num) => {
		loginModalNum.value = num;
		loginMessageOn.value = true;
	}

	const logout = () => {
		username.value = '';
		userSeq.value = '';
		sessionStorage.removeItem('userSeq')
		sessionStorage.removeItem('user-token')
		router.push('/');
	}

	

	// 사용 안하고 있음, matchFunc는 fingerLogin.vue에서 정의하고 사용 중
	

	return {
		inputId, inputPw, username, userSeq, loginMessageOn, loginModalNum, matchFlag, logout,
		loginRequest, loginMessageModal
	}
}, {persist: true})