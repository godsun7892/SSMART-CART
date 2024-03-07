<template>
    <div class="background">
        <div class="left">
            <div class="txt">
                화면을<br>
                누르면<br>
                시작합니다<br>
            </div>

            <div class="startBtn">
                <button class="btn" @click="goToMain">시작하기
                    <img class="img" src="/image/startIcon.png">
                </button>
            </div>
        </div>

        <div class="right">
            <div class="rightInside">
                <div class="rightInsideInside">
                     <div class="iconBackground iconBackground1">
                        <img class="icon" src="/image/firstScreen/productSearch.png">
                    </div>
                    <div class="explanation explanation1"><span class="hot-deal">상품 검색</span>을 통해 원하는 상품을<br> 찾아보세요! 위치도 알 수 있어요.</div>
                </div>
               
                <div class="rightInsideInside">
                     <div class="iconBackground iconBackground2">
                        <img class="icon" src="/image/firstScreen/marketMap.png">
                    </div>
                    <div class="explanation explanation2">언제든 <span class="hot-deal">매장 지도</span>를 확인하세요.<br> 어디에 어떤 상품이 있을까요?</div>
                </div>
            </div>


            <div class="rightInside">
                <div class="rightInsideInside">
                     <div class="iconBackground iconBackground3">
                        <img class="icon" src="/image/firstScreen/hotDealIcon.png">
                    </div>
                    <div class="explanation explanation3">할인 중인 상품을 한눈에 확인<br>하세요. <span class="hot-deal">오늘의 핫딜</span> 추천!!</div>
                </div>
               
                <div class="rightInsideInside">
                     <div class="iconBackground iconBackground4">
                        <img class="icon" src="/image/firstScreen/menuRecommend.png">
                    </div>
                    <div class="explanation explanation4">오늘의 <span class="hot-deal">메뉴를 추천</span>합니다.  필요<br>한 재료의 위치도 보여드려요.</div>
                </div>
            </div>

        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
const router = useRouter();

const goToMain = () =>{
    router.push('/home');
}




</script>

<style scoped>
.background {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
}

.left {
    display: flex;
    flex-direction: column;
    width: 45%;
    height: 100%;
    background-color: #4892f3;
    padding-top : 10%;
}

.txt {
    height: 60%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 50px;
    color: white;
    font-weight: 600;
}

.startBtn {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}

.btn {
    width: 240px;
    height: 60px;
    background-color: white;
    font-size: 25px;
    font-weight: 600;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    border-radius: 20px;
}

.img {
    margin-left: 10px;
    width: 50px;
}

.right {
    display: flex;
    flex-direction: column;
    width: 55%;
    height: 100%;
    background-color: rgb(235, 244, 246);
}

.rightInside {
    height: 50%;
    display: flex;
    flex-direction: row;
}

.rightInsideInside {
    display: flex;
    flex-direction: column;
    width: 50%;
}

.iconBackground {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 250px;
    height: 250px;
    border-radius: 50%;
    background-color: white;
}

.iconBackground1 {
    margin-left : 140px;
    margin-top : 55px;
}

.iconBackground2 {
    margin-left : 70px;
    margin-top : 55px;
}

.iconBackground3 {
    margin-left : 140px;
    margin-top : 30px;
}

.iconBackground4 {
    margin-left : 70px;
    margin-top : 30px;
}

.icon {
    width: 150px;
    height: 150px;
}

.hot-deal {
    color: red; /* 빨간색 텍스트 */
}

.explanation {
    font-weight: bold;
    font-size: 20px;
}

.explanation1 {
    margin-left: 110px;
    margin-top: 15px;
}

.explanation2 {
    margin-left: 60px;
    margin-top: 15px;
}

.explanation3 {
    margin-left: 140px;
    margin-top: 15px;
}

.explanation4 {
    margin-left: 50px;
    margin-top: 15px;
}

.rightBottom {
    height: 50%;
    display: flex;
    flex-direction: row;
}

</style>