<template>
    <div class="product-location-modal" @click="closeModal" >
        <div class="modal-content" @click="closeModal">
            <!-- 본문 부분 -->
            <!-- <div class="searchScreenChange"> -->
                    <div class="header">
                        <div class="title">
                            레시피 추천
                        </div>
                        <button class="BackBtn" @click="closeModal">
                            X
                        </button>
                    </div>

                    <div class="recipe">
                        <div class="recipeExp">
                            <!-- <img v-bind:src="`${recipeInfo.cuisineImage}`"  height="90%" width="100%" class="img"> -->
                            <!-- <img src="/food/kimchi_jjikae.jpg" height="90%" width="100%" class="img"> -->
                        </div>
                        <div class="recipeExp">
                            <div class="foodTitle">
                                <div class="recipeName">{{ recipeInfo.cuisineName }}</div>
                                <div class="serving">
                                    <img src="/food/serving.png" height="40px"> <div class="font">4인분</div>
                                </div>
                            </div>
                            <div class="explanation">{{ recipeInfo.explanation }}</div>
                        </div>
                    </div>
            <!-- </div> -->
        </div>
    </div>

</template>
  
<script setup>

import {ref, computed} from 'vue';
import {storeToRefs} from "pinia";
import { useFlagStore } from "@/stores/flag.js";
import { useSearchListStore } from "@/stores/searchList";

const flagSwitch = useFlagStore();
const searchStore = useSearchListStore();
//const { recipeInfo } = storeToRefs(searchStore);

var listChar = [];

const maxIngredientsInView = 6;

const closeModal = () =>{
    console.log('hi');
    flagSwitch.currentModalProductSeq = null;
}

// ingredients 배열을 두 개의 서브 배열로 나누는 계산된 속성
const splitIngredients = computed(() => {
    const halfLength = Math.ceil(recipeInfo.value.productList.length / 2);
    return {
        firstColumn: recipeInfo.value.productList.slice(0, halfLength),
        secondColumn: recipeInfo.value.productList.slice(halfLength)
    };
});

const recipeInfo = ref(
    {
        cuisineName : '돼지고기 김치찌개',
        explanation :`
            깔끔한 맛을 원한다면
            적당히 익은 김치를 볶지 않고 넣어보자.
            
            깊은 맛을 원한다면 잘 익은 김치를
            1~2분간 볶은 후에 물을 부으면 된다.

            매콤 칼칼한 김치찌개를 추천!`,
        productList : [
            {category: "김치",quantity: "1송이",location: "B-1층 A-13"},
            {category: "양파",quantity: "1/2 개",location: "B-1층 A-12"},
            {category: "다진마늘",quantity: "1/2 개",location: "B-1층 A-12"},
            {category: "돼지고기",quantity: "1/2 개",location: "B-1층 A-12"},
            {category: "애호박",quantity: "1/2 개",location: "B-1층 A-12"},
        ]
    }
)

</script>

<style scoped>



.product-location-modal {
    /* 모달 오버레이의 스타일 */
    position: fixed;
    top: 0;
    left: 0;
    width: 1600px;
    height: 900px;
    /* background-color: rgba(0, 0, 0, 0.5); */
    display: flex;
    flex-direction: column;;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

.modal-content {
    /* 모달 내용의 스타일 */
    top: 10.5%;
    left: 13.5%;
    width: 42%;
    height: 59%;
    background-color: #e1e4e8;
    padding: 10px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}


.modal-buttons {
    display: flex;
    height: 9%;
    justify-content: center;
    /* margin-bottom: 20px; 버튼과 내용 사이의 간격 */
}

.modal-buttons button {
    width: 31%;
    margin: 0px 5px;
    /* border: 1px solid #ccc; 경계선 */
    border-top-left-radius: 10px; /* 둥근 모서리 */
    border-top-right-radius: 10px; /* 둥근 모서리 */
    font-size: 25px;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.3s; /* 애니메이션 효과 */
}

.modal-buttons button:hover {
    background-color: lightgray; /* 호버 배경색 */
    transform: translateY(-2px); /* 호버 시 버튼이 약간 위로 움직임 */
}

.modal-buttons button:active, .modal-buttons button.active {
    background-color: #ddd; /* 활성화된 버튼의 배경색 */
    border-color: #aaa; /* 활성화된 버튼의 경계선 색상 */
}

.hotDealBtn {
    background-color: #7A7A7A;
    border-width: 0;
}

.categoryBtn {
    background-color: #FFF;
    border-width: 0;
}

.mapBtn {
    background-color: #BCB5B5;
    border-width: 0;
}

.searchScreenChange{
    width: 100%;
    height: 200px;
    overflow-y: auto;
    background-color: #EEEEEE;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
}

.background {
    display: flex;
    flex-direction: column;
    /* flex-direction: column; */
    /* justify-content: center; */
    /* align-items: center; */
    /* align-items: center; */
    height: 100%;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    /* background-color: #EEEEEE; */
    width: 100%;
}

.header {
    display: flex;
    width: 100%;
    height: 8%;
    align-items: center;
    background-color: #393939;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
}

.title {
    margin-left: 20px;
    color: white;
    width: 30%;
    margin-right: 65%;
    font-size: x-large;
    font-weight: bold;
}
.searchResultScreen{
    height:85%;
}

.BackBtn{
    width:5%;
    height:100%;
    background-color:#393939 ;
    color:red;
    font-weight: bold;
    border: 0;
    border-top-right-radius: 10px;
    font-size: x-large;
}

.recipe {
    display: flex;
    height: 45%;
    flex-direction: row;
}
.recipeExp {
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    margin: 20px;
    width: 50%;
    height: 100%;
    font-weight: 600;
}

.img {
    border-radius: 20px;
}
.recipeTitle {
    align-items: center;
}

.foodTitle {
    display: flex;
    flex-direction: column;
}

.recipeName {
    font-size: 35px;
    font-weight: 700;
    font-family: inter;
    margin-bottom: 10px;
}
.serving {
    display: flex;
    flex-direction: row;
    margin-bottom: 20px;
}

.font {
    font-size: 20px;
    margin-left: 10px;
    margin-top: 4px;
}

.explanation {
    color: #777777;
    font-size: 19px;
    font-weight: 600 ;
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
}

.recipeDetail{
    width: 98%;
    /* height: 47%; */
    display: flex;
    flex-direction: row;
    margin-bottom: 20px;
    border-radius: 20px;
}

.ingredientHeader {
    height: 5%;
    margin-left: 2%;
    font-size: 25px;
    font-weight: bolder;
    margin-bottom: 10px;
}

.ingredient {
    display: flex;
    flex-direction: column;
    margin-left: 1%;
    margin-right: 1%;
    height: 90%;
    width: 90%;
}
.ingredient-info {
  display: flex; /* Arrange name, quantity, and location horizontally */
  flex-direction: row;
  margin-bottom: 10px; /* Add spacing between ingredients */
  border-bottom: 1px solid #ccc; /* Add an underline */
  padding-bottom: 5px; /* Create some space below the underline */
  justify-content: space-between;
}

.ingredient-name {
    width: 33%;
}
.ingredient-quantity {
    width: 33%
}
.btnRange{
    margin:1% 0%;
    width:50%;
    height:10%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    font-size: 32px;
}

.searchScreenChange::-webkit-scrollbar {
    width: 10px;
    display: none;
    /* opacity: 1; */
}

.searchScreenChange:hover::-webkit-scrollbar {
    display: block;
    width: 10px;
}

.searchScreenChange::-webkit-scrollbar-thumb {
    background-color: #3d77f4;
    border-radius: 10px;
}
.searchScreenChange::-webkit-scrollbar-track {
    background-color: rgb(186, 215, 248);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
</style>
