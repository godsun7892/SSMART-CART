import axios from "axios";

// 구현한 spring boot 서버에 맞게 수정합니다
// AWS 서버 : i10a203.p.ssafy.io
export default axios.create({
  baseURL: "https://i10a203.p.ssafy.io/api/",
  //baseURL: "http://localhost:8080/",
  headers: {
    "Content-type": "application/json",
  },
});
