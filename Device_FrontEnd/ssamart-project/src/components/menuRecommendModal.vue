<template>
    <div class="product-location-modal" @click="closeModal" >
        <div class="modal-content" @click.stop="">
            <!-- 본문 부분 -->
            <div class="searchScreenChange">
                <div class="background">
                    <div class="header">
                        <div class="title">
                            레시피 추천
                        </div>
                        <button @click="menuPick" class="titleBtn">
                            <div v-if="!menuPickflag">
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="red" class="bi bi-pin" viewBox="0 0 16 16" >
                                    <path d="M4.146.146A.5.5 0 0 1 4.5 0h7a.5.5 0 0 1 .5.5c0 .68-.342 1.174-.646 1.479-.126.125-.25.224-.354.298v4.431l.078.048c.203.127.476.314.751.555C12.36 7.775 13 8.527 13 9.5a.5.5 0 0 1-.5.5h-4v4.5c0 .276-.224 1.5-.5 1.5s-.5-1.224-.5-1.5V10h-4a.5.5 0 0 1-.5-.5c0-.973.64-1.725 1.17-2.189A5.921 5.921 0 0 1 5 6.708V2.277a2.77 2.77 0 0 1-.354-.298C4.342 1.674 4 1.179 4 .5a.5.5 0 0 1 .146-.354zm1.58 1.408-.002-.001.002.001zm-.002-.001.002.001A.5.5 0 0 1 6 2v5a.5.5 0 0 1-.276.447h-.002l-.012.007-.054.03a4.922 4.922 0 0 0-.827.58c-.318.278-.585.596-.725.936h7.792c-.14-.34-.407-.658-.725-.936a4.915 4.915 0 0 0-.881-.61l-.012-.006h-.002A.5.5 0 0 1 10 7V2a.5.5 0 0 1 .295-.458 1.775 1.775 0 0 0 .351-.271c.08-.08.155-.17.214-.271H5.14c.06.1.133.191.214.271a1.78 1.78 0 0 0 .37.282z"/>
                                </svg>
                            </div>
                            <div v-else>
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="red" class="bi bi-pin-fill" viewBox="0 0 16 16">
                                    <path d="M4.146.146A.5.5 0 0 1 4.5 0h7a.5.5 0 0 1 .5.5c0 .68-.342 1.174-.646 1.479-.126.125-.25.224-.354.298v4.431l.078.048c.203.127.476.314.751.555C12.36 7.775 13 8.527 13 9.5a.5.5 0 0 1-.5.5h-4v4.5c0 .276-.224 1.5-.5 1.5s-.5-1.224-.5-1.5V10h-4a.5.5 0 0 1-.5-.5c0-.973.64-1.725 1.17-2.189A6 6 0 0 1 5 6.708V2.277a3 3 0 0 1-.354-.298C4.342 1.674 4 1.179 4 .5a.5.5 0 0 1 .146-.354"/>
                                </svg>
                            </div>
                        </button>
                        <button class="BackBtn" @click="closeModal">
                            X
                        </button>
                    </div>

                    <div class="recipe">
                        <div class="recipeExp">
                            <img v-bind:src="`${recipeInfo.cuisineImage}`"  height="90%" width="100%" class="img">
                            <!-- <img src="/food/kimchi_jjikae.jpg" height="90%" width="100%" class="img"> -->
                        </div>
                        <div class="recipeExp">
                            <!-- <div class="foodTitle">
                                <div class="recipeName">{{ recipeInfo.cuisineName }}</div>
                                <div class="serving">
                                    <img src="/food/serving.png" height="40px"> <div class="font">4인분</div>
                                </div>
                            </div>
                            <div class="explanation">{{ recipeInfo.explanation }}</div> -->
                            <img v-bind:src="`${recipeInfo.cuisineName}`" class="expImg">
                        </div>
                    </div>
                    
                    <div class="hrContainer">
                        <hr>
                    </div>
                    
                    <div class="ingredientHeader">재료</div>
                    <div class="container">
                        <div class="recipeDetail">
                            <div class="ingredient">
                                <div class="ingredient-info" v-for="ingredient in firstColumn" :key="ingredient.name">
                                    <span class="ingredient-name">{{ ingredient.category.name }}
                                        <svg v-if="ingredient.isInCart" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#44BEF7" class="bi bi-cart-check-fill" viewBox="0 0 16 16">
                                            <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0m7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0m-1.646-7.646-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L8 8.293l2.646-2.647a.5.5 0 0 1 .708.708"/>
                                        </svg>
                                    </span>
                                    <span class="ingredient-quantity">{{ ingredient.content }}</span>
                                    <!-- <button @click="viewCategory(ingredient.location)">위치</button> -->
                                    <button class="btn" @click="viewCategory(ingredient.category.location)">
                                        <img src="/image/location.png" class="btnImg">
                                    </button>
                                </div>
                            </div>

                            <div class="ingredient">
                                <div class="ingredient-info" v-for="ingredient in secondColumn" :key="ingredient.name">
                                    <span class="ingredient-name">{{ ingredient.category.name}}
                                        <svg v-if="ingredient.isInCart" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#44BEF7" class="bi bi-cart-check-fill" viewBox="0 0 16 16">
                                            <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0m7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0m-1.646-7.646-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L8 8.293l2.646-2.647a.5.5 0 0 1 .708.708"/>
                                        </svg>
                                    </span>
                                    <span class="ingredient-quantity">{{ ingredient.content }}</span>
                                    <button class="btn" @click="viewCategory(ingredient.category.location)">
                                        <img src="/image/location.png" class="btnImg">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <br>
                    <div class="ingredientHeader">조리순서</div>
                    <div class="container">
                        <div class="recipeDetail">
                           <img v-bind:src="`${recipeInfo.cuisineStepImage}`" width="100%" class="img">
                           <!-- <img src="/food/jjigae_recipe.png" width="100%" class="img"> -->
                        </div>
                    </div>
                </div>
            </div>
            <ssafyFloor2 v-if="flagSwitch.locationModalflag"/>
        </div>
        
    </div>

</template>
  
<script setup>

import {ref, computed, onMounted} from 'vue';
import {storeToRefs} from "pinia";
import { useFlagStore } from "@/stores/flag.js";
import { useSearchListStore } from "@/stores/searchList";
import ssafyFloor2 from '@/views/floor/SSAFYFloor2.vue';
import { useCopyStore } from "@/stores/copy";

const flagSwitch = useFlagStore();
const searchStore = useSearchListStore();
const { recipeInfo, firstColumn, secondColumn } = storeToRefs(searchStore);
const { menuPickflag } = storeToRefs(flagSwitch);

const copyStore = useCopyStore();
const { productsCopy } = storeToRefs(copyStore);

var listChar = [];

const closeModal = () =>{
    console.log('hi');
    flagSwitch.recommendmodalflagChange();
}

const menuPick = () =>{
    console.log(menuPickflag.value);
    flagSwitch.menuPickflagChange();
}

// ingredients 배열을 두 개의 서브 배열로 나누는 계산된 속성
// const splitIngredients = computed(() => {
//     console.log(recipeInfo.value.productList.length);
//     const halfLength = Math.ceil(recipeInfo.value.productList.length / 2);
//     return {
//         firstColumn: recipeInfo.value.productList.slice(0, halfLength),
//         secondColumn: recipeInfo.value.productList.slice(halfLength)
//     };
// });

const viewCategory = (category) => {
    // 채소, 과일
    if(category === 'A5' || category === 'P1' || category === 'P2' || category === 'P3' || category === 'P4' || category === 'P5' || category === 'P6' || category === 'P7' || category === 'P8' || category === 'P9'){
        console.log('채소/과일');
        flagSwitch.viewLocation(2);
    }
    // 김치
    else if(category === 'A9' || category === 'M1'){
        console.log('김치/반찬');
        flagSwitch.viewLocation(1);
    }
    // 정육
    else if(category === 'A1' || category === 'N1' || category === 'N2' || category === 'N3' || category === 'Q3'){
        console.log('정육/계란');
        flagSwitch.viewLocation(3);
    }
    // 과자, 간식
    else if(category === 'A13'){
        console.log('과자/간식');
        flagSwitch.viewLocation(4);
    }
    // 위생, 건강
    else if(category === 'D1' || category === 'D2' || category === 'D3'){
        console.log('위생/건강');
        flagSwitch.viewLocation(5);
    }
    // 주방용품
    else if(category === 'B1' || category === 'B2' || category === 'B3' || category === 'B4' || category === 'B5' || category === 'B6' || category === 'B7' ){
        console.log('주방용품');
        flagSwitch.viewLocation(6);
    }
    // 우유, 유제품, 치즈
    else if(category === 'Q2' || category === 'Q4' || category === 'Q5'){
        console.log('우유/유제품/치즈');
        flagSwitch.viewLocation(8);
    }
    // 면류, 통조림
    else if(category === 'Q1'){
        console.log('면류/통조림');
        flagSwitch.viewLocation(7);
    }
}

onMounted(() => {
  setTimeout(() => {
    // firstColumn과 secondColumn의 모든 재료를 확인
    const allIngredients = [...firstColumn.value, ...secondColumn.value];
    allIngredients.forEach(ingredient => {
      ingredient.isInCart = productsCopy.value.some(product => product.product.category.seq === ingredient.category.seq);
      //console.log(`재료 ${ingredient.name}은/는 카트에 있습니까?`, isInCart);
    });
  
  }, 1200); // 2초 대기 후 실행
});
</script>

<style scoped>



.product-location-modal {
    /* 모달 오버레이의 스타일 */
    position: fixed;
    top: 0;
    left: 0;
    width: 1600px;
    height: 900px;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    flex-direction: column;;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

.modal-content {
    /* 모달 내용의 스타일 */
    width: 80%;
    height: 90%;
    background-color: #5F5F5F;
    padding: 5px;
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
    height: 100%;
    overflow-y: auto;
    background-color: white;
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
}

.header {
    display: flex;
    width: 100%;
    height: 8%;
    align-items: center;
    background-color: #393939;
}

.title {
    margin-left: 30px;
    color: white;
    width: 30%;
    margin-right: 65%;
    font-size: 20px;
    font-weight: bold;
}
.titleBtn {
    background-color: transparent;
    border: 0;
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
    margin-right: 0px;
    width: 48%;
    height: 100%;
    font-weight: 600;
    /* align-items: center; */
}

.expImg {
    width: 100%;
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
    font-size: 20px;
    font-weight: 600 ;
}

.hrContainer {
    margin-left: 2%;
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

.btn {
    width: 5%;
    height: 5%;
    margin: 0px;
    padding: 0px;
}
.btnImg {
    width:100%;
    height: 100%;
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
    width: 6px;
    display: block;
    /* opacity: 1; */
}

/* .searchScreenChange:hover::-webkit-scrollbar {
    display: block;
    width: 10px;
} */

.searchScreenChange::-webkit-scrollbar-thumb {
    /* background-color: #3d77f4; */
    background-color: rgb(170, 168, 168);
    border-radius: 10px;
}
.searchScreenChange::-webkit-scrollbar-track {
    /* background-color: rgb(186, 215, 248); */
    background-color: rgb(208, 209, 210);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
</style>
