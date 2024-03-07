<!-- src/components/ProductLocationModal.vue -->

<template>
    <div class="background discount-map-modal">
        <!-- 할인 지도 내용 -->
        <div class="floorModal">
            <div class="headPart">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="defaultDropdown" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false">
                        층별 안내도
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="defaultDropdown">
                        <li>
                            <button class="dropdown-item fourth" type="button" @click="switchFloor('4F')">
                                <div class="floorName">4F</div> 
                                <div class="floorInfo"> 남성패션 | 아웃도어 </div>
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" type="button"  @click="switchFloor('3F')">
                                <div class="floorName">3F</div> 
                                <div class="floorInfo"> 스포츠 | 유니섹스 캐주얼 </div>
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" type="button" @click="switchFloor('2F')">
                                <div class="floorName">2F</div> 
                                <div class="floorInfo"> 여성패션 | 영캐주얼 </div>
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item" type="button" @click="switchFloor('1F')">
                                <div class=floorName>1F</div> 
                                <div class="floorInfo"> 패션잡화 | 슈즈 | 핸드백 </div>
                            </button>
                        </li>
                        <li>
                            <button class="dropdown-item ssafy" type="button" @click="switchFloor('SF')">
                                <div class="floorName">SSAFY F</div> 
                                <div class="floorInfo"> 식품 | 생필품 </div>
                            </button>
                        </li>
                    </ul>
                </div>
                <!-- <button class="sidebar-toggle" @click="toggleSidebar">층별 안내도</button> -->
            </div>


            <div class="bodyPart">
                <ForthFloor v-if="floorComponent==='4F'"/>
                <ThirdFloor v-if="floorComponent==='3F'"/>
                <SecondFloor v-if="floorComponent==='2F'"/>
                <FirstFloor v-if="floorComponent==='1F'"/>
                <SSAFYFloor v-if="floorComponent==='SF'"/>
                <!-- <component :is="floorComponent"></component> -->
            </div>

            <div class="tailPart">

            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
const floorComponent = ref('SF');
import ForthFloor from "@/views/floor/FourthFloor.vue";
import ThirdFloor from "@/views/floor/ThirdFloor.vue";
import SecondFloor from "@/views/floor/SecondFloor.vue";
import FirstFloor from "@/views/floor/FirstFloor.vue";
import SSAFYFloor from "@/views/floor/SSAFYFloor.vue";


const switchFloor = (str) => {
    floorComponent.value = str;
    closeDropdown();
}

const closeDropdown = () => {
    const dropdownToggle = document.getElementById('defaultDropdown');
    if (dropdownToggle) {
        dropdownToggle.click(); // 드롭다운 토글 버튼 클릭하여 닫기
    }
};


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
    justify-content: center;
    align-items: center;
}

    /* 모달 스타일을 여기에 추가하세요 */
.background {
    display: flex;
    justify-content: center;
    /* align-items: center; */
    height: 100%;
    padding: 15px 15px 15px 15px;
    background-color: white;
}

.discount-map-modal {
    align-items: center;
    position: relative;
}

.floorModal {
    display: flex;
    flex-direction: column;
    width: 95%;
    height: 98%;
    /* border: 10px solid gray; */
    border-radius: 20px;
    /* background: #F0F0F0; */
}

.headPart {
    position: absolute;
    width: 100%;
    height: 7%;
    display: flex;
    flex-direction: row;
    justify-content: right;
    /* background: rgba(0, 0, 0, 0.07); */
    border-radius: 10px 10px 0px 0px;
    z-index : 1;
}

.bodyPart {
    display: flex;
    height: 86%;
    justify-content: center;
}

.tailPart {
    height: 7%;
    /* background: rgba(0, 0, 0, 0.07); */
}

.dropdown{
    width: 30%;
}

.btn {
    width: 50%;
    height: 95%;
    background: #979580;
    font-size: 20px;
    font-weight: 600;
    position: absolute;
    top: 10%;
    right: 3%;
    box-shadow: 4px 4px 0px 0px rgb(200, 199, 199);
}
.dropdown-menu {
    width: 100%;
    background: rgba(89, 89, 89, 0.80);
    
}

.dropdown-item {
    border-bottom: 1px solid black;
    border-right: 1px solid black;
    height: 106px;
}

.fourth {
    border-top-right-radius: 10px;
    border-top: 1px solid black;
}
.dropdown-item:hover {
    background : rgba(89, 89, 89, 0.80);
}
.dropdown-item:focus {
    outline : 3px solid yellow;
    background : rgba(89, 89, 89, 0.80);
}

.ssafy {
    border-bottom-right-radius: 10px;
}

.floorName {
    color : white;
    font-size: xx-large;
    font-weight: bold;
}

.floorInfo {
    color : white;
    font-size: large;
}

</style>
