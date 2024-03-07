import axios from "axios";

// 구현한 spring boot 서버에 맞게 수정합니다
const axiosInstance = axios.create({
  // baseURL: "https://i10a203.p.ssafy.io/api/",
  baseURL: "http://localhost:8080/",
  headers: {
    "Content-type": "application/json"
  },
});

// 세션 스토리지에서 토큰을 가져옵니다.
const token = sessionStorage.getItem('user-token');
if(token) {
  // 인스턴스의 기본 헤더에 토큰을 추가합니다.
  axiosInstance.defaults.headers.common['Authorization'] = `Bearer ${token}`;
}

export default axiosInstance;
