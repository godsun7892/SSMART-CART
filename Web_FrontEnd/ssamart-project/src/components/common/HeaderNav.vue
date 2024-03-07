<template>
  <div class="header-nav">
    <div class="top-row">
      <div class="logo">
        <RouterLink to="/">
          <img src="/main/logo/SSAMART_CART_logo.png" />
        </RouterLink>
      </div>
      <div class="search-and-controls">
        <div class="search-bar">
          <input type="text" placeholder="검색어를 입력하세요" id="search-input" v-model="searchTerm"
            @keyup.enter="searchForProduct" />
          <button @click="searchForProduct" class="search-button">
            <i class="fas fa-search"></i>
          </button>
        </div>
        <div class="user-controls">
          <button v-if="!loginUser" @click="goToLogin" class="header-button">로그인</button>
          <button v-if="!loginUser" @click="goToRegist" class="header-button">회원가입</button>
          <button v-if="loginUser" @click="logout" class="header-button">로그아웃</button>
          <button v-if="loginUser" @click="goToWishList" class="header-button">My 찜</button>
          <button v-if="loginUser" @click="goToPayment" class="header-button">My 결제</button>
        </div>
      </div>
    </div>
    <div class="navigation">
      <ul>
        <li ref="categoryButton" @click="toggleCategories">카테고리</li>
        <li>
          <RouterLink to="/products/list?categorySeq=105">베스트</RouterLink>
        </li>
        <li>
          <RouterLink to="/products/list?categorySeq=120">신상품</RouterLink>
        </li>
        <li>
          <RouterLink to="/products/list?categorySeq=121">설날</RouterLink>
        </li>
        <li>
          <RouterLink to="/products/list?categorySeq=114">단독특가</RouterLink>
        </li>
        <!-- 여기에 추가 카테고리 -->
      </ul>
    </div>
    <!-- 드롭다운 메뉴 -->
    <div class="category-dropdown" v-if="showDropdown" :style="dropdownStyles">
      <ul>
        <li>
          <RouterLink to="/products/list?categorySeq=2">정육/계란</RouterLink>
        </li>
        <li>
          <RouterLink to="/products/list?categorySeq=6">채소/과일</RouterLink>
        </li>
        <li>
          <RouterLink to="/products/list?categorySeq=14">과자/간식</RouterLink>
        </li>
        <li>
          <RouterLink to="/products/list?categorySeq=10">김치/반찬</RouterLink>
        </li>
        <!-- 추가 카테고리 -->
      </ul>
    </div>
  </div>
  <ModalComponent ref="logoutModal">
    <p>로그아웃 되었습니다.</p>
  </ModalComponent>
</template>

<script>
import { ref, computed, nextTick, reactive } from 'vue';
import { useUserStore } from "@/stores/userStore";
import { useRoute, useRouter, RouterLink } from 'vue-router';
import { useProductStore } from '@/stores/productStore';
import ModalComponent from './ModalComponent.vue';

export default {
  name: 'HeaderNav',
  components: {
    RouterLink,
    ModalComponent,
  },

  setup() {
    const userStore = useUserStore();
    const productStore = useProductStore();
    const searchTerm = ref('');
    const loginUser = computed(() => userStore.loginUser);
    const route = useRoute();
    const router = useRouter();
    const products = ref([]);
    const logoutModal = ref('');
    const showDropdown = ref(false);
    const categoryButton = ref(null);
    const dropdownStyles = reactive({
      top: '0px',
      left: '0px',
    });

    const goToLogin = () => {
      router.push('/auth/login');
    };

    const goToRegist = () => {
      router.push('/auth/regist');
    };

    const goToWishList = () => {
      const userSeq = sessionStorage.getItem('userSeq');
      if (userSeq) {
        router.push({ name: 'WishList', params: { userSeq: userSeq } })
      }
    }

    const goToPayment = () => {
      const userSeq = sessionStorage.getItem('userSeq');
      if (userSeq) {
        router.push({ name: 'Payment', params: { userSeq: userSeq } })
      }
    };

    const logout = () => {
      userStore.setLogout();
    }

    const searchForProduct = () => {
      productStore.searchProduct(searchTerm.value);
      // 검색 후 ProductSearch 페이지로 이동
      router.push({ name: 'ProductSearch', query: { searchTerm: searchTerm.value } });
    }

    const toggleCategories = () => {
      showDropdown.value = !showDropdown.value;
      if (showDropdown.value) {
        nextTick(() => {
          const rect = categoryButton.value.getBoundingClientRect();
          dropdownStyles.top = `${rect.bottom + window.scrollY}px`;
          dropdownStyles.left = `${rect.left + window.scrollX}px`;
        });
      }
    };



    // 외부 클릭 감지를 위한 로직 (옵션)
    document.addEventListener('click', (e) => {
      if (!e.target.matches('.navigation li')) {
        showDropdown.value = false;
      }
    });


    return {
      loginUser,
      logout,
      logoutModal,
      goToLogin,
      goToRegist,
      goToWishList,
      goToPayment,
      searchForProduct,
      searchTerm,
      showDropdown,
      toggleCategories,
      categoryButton,
      dropdownStyles,
      products,
    }
  }
}
</script>

<style scoped>
.header-nav {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  background-color: white;
  /* 변경 가능 */
  border-bottom: 2px solid #78EAEF;
  /* 변경 가능 */
  padding: 0.5rem 0rem;
}

.logo img {
  margin-top: 30px;
  margin-left: 50px;
  /* 로고의 왼쪽 마진 추가 */
  height: 85px;
  /* 로고 크기 조정 가능 */
}


.top-row {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
}

.search-and-controls {
  display: flex;
  align-items: center;
  width: 65%;
  margin-right: 20px;
}

.search-bar {
  display: flex;
  align-items: center;
  position: relative;
  /* 검색창에 상대적인 위치 설정 */
  gap: 0.5rem;
  width: 63%;
  border-radius: 30px;
  /* 검색창 모서리 둥글게 */
  background-color: #FFFFFF;
  /* 배경색 설정 */
}

.search-bar input {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 400;
  font-style: normal;
  flex-grow: 1;
  /* 입력 필드가 검색창 너비를 채우도록 함 */
  border: 1px solid #bbb;
  height: 25px;
  padding: 1rem 2rem;
  /* 적절한 여백 */
  border-radius: 30px;
  /* 입력 필드 모서리 둥글게 */
  position: relative;
  /* 상대 위치 설정 */
}

.search-bar input::placeholder {
  color: #ccc;
}

.search-bar button {
  position: absolute;
  right: 30px;
  /* 오른쪽 정렬 */
  top: 50%;
  /* 상단에서 50% 위치 */
  transform: translateY(-50%);
  /* Y축으로 -50% 이동하여 세로 중앙 정렬 */
  background-color: transparent;
  border: none;
  cursor: pointer;
}

.search-bar button i {
  font-size: 1.5rem;
  /* 아이콘 크기 증가 */
  color: #0a11137e;
  /* 아이콘 색상 */
}

.user-controls {
  
  display: flex;
  margin-left: auto;
  
  /* 왼쪽의 모든 여분 공간을 사용하여 오른쪽으로 정렬 */
}

.header-button {
  background-color: #78EAEF;
  /* 버튼 배경색 */
  color: #0a1113a1;
  /* 버튼 텍스트 색상 */
  font-size: 1.0rem;
  padding: 12px 30px;
  /* 버튼 내부 여백 */
  border-radius: 30px;
  /* 버튼 모서리 둥글기 */
  border: none;
  /* 버튼 테두리 제거 */
  cursor: pointer;
  /* 마우스 커서를 포인터로 변경 */
  margin-left: 20px;
  /* 버튼 사이 간격 */
  transition: background-color 0.2s;
  /* 호버 효과 시간 */
  text-align: center;
}

.header-button:hover {
  background-color: #78EAEF;
  /* 버튼 호버 배경색 */
  border-color: #78EAEF;
  color: #ffffff;
  /* 텍스트 색상 */
}

.header-link {
  text-decoration: none;
  /* 링크 밑줄 제거 */
  color: inherit;
  /* 부모 요소에서 색상 상속 */
  display: block;
}


.user-controls button {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  background: none;
  border: #78EAEF;
  padding: 0.8rem 1.4rem;
  margin: 0 10px;
}

.header-box {
  padding-top: 30px;
}

.header-a {
  background-color: #fff;
  padding: 8px 20px;
  border-radius: 20px;
  margin-left: 10px;
  box-sizing: border-box;
  color: #686868;
}

/* 카테고리 간의 간격 */
.navigation ul {
  list-style: none;
  display: flex;
  justify-content: center;
  padding: 0;
  margin: 0;
  gap: 7rem;
}

.navigation li {
  cursor: pointer;
  margin: 0.5rem 0;
  transition: 0.2s;
}

.navigation li:hover {
  color: #78EAEF;
}

.navigation a {
  text-decoration: none;
  color: black;
  /* 링크 색상 조정 가능 */
  transition: color 0.2s;
}

.navigation a:hover {
  color: #78EAEF;
  /* 호버 상태에서의 링크 색상 */
}

/* Styles for the dropdown menu */
.category-dropdown {
  display: block;
  position: absolute;
  background-color: #eeeeee;
  /* 카테고리 한 칸 가로길이 */
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
  /* 위치 설정은 스크립트에서 처리 */
}

.category-dropdown ul {
  list-style-type: none;
  padding: 0;
}

.category-dropdown li a {
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  color: black;
}

.category-dropdown li a:hover {
  background-color: white;
}


/* 더 많은 스타일링이 필요할 수 있습니다. */</style>
