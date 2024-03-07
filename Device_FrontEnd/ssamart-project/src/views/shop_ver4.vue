<template>
	<div class="MainPage">
		<div class="sideBar">
			<button class="sideBtn sideBtn2" @click="switchModal(0)">
				<img src="/image/searchProduct.png" style="width: 35%; height: 35%;" alt="">
				<br>
				<div class="sideTxt">상품 검색</div>
			</button>
			<img src="/image/line.png" style="" alt="">
			<button class="sideBtn" @click="switchModal(2)">
				<img src="/image/martMap.png" style="width: 30%; height: 30%;" alt="">
				<br>
				<div class="sideTxt">매장 지도</div>
			</button>
			<img src="/image/line.png" style="" alt="">
			<button class="sideBtn" @click="switchModal(3)">
				<img src="/image/discountInfo.png" style="width: 35%; height: 35%;" alt="">
				<br>
				<div class="sideTxt">할인 정보</div>
			</button>
			<img src="/image/line.png" style="" alt="">
			<button class="sideBtn" @click="openRecommendModal">
				<img src="/image/todayMenu.png" style="width: 35%; height: 35%;" alt="">
				<br>
				<div class="sideTxt">오늘의<br> 메뉴 추천</div>
			</button>
			<img src="/image/line.png" style="" alt="">	
			<!-- <button class="sideBtn" @click="flagSwitch.selectlocationmodalflagChange()"> -->
			<button class="sideBtn" @click="flagSwitch.callmodalflagChange()">
				<img src="/image/callEmployee.png" style="width: 35%; height: 35%;" alt="">
				<br>
				<div class="sideTxt">직원 호출</div>
			</button>
			<img src="/image/line.png" style="" alt="">
			<button class="sideBtn" @click="$router.push('/payMent')">
				<img src="/image/payment.png" style="width: 35%; height: 35%;" alt="">
				<br>
				<div class="sideTxt">수동 결제</div>
			</button>
			<img src="/image/line.png" style="" alt="">
			<button class="sideBtn sideBtn3" @click="flagSwitch.logoutmodalflagChange()">
				<img src="/image/logout.png" style="width: 35%; height: 35%;" alt="">
				<br>
				<div class="sideTxt">쇼핑 종료</div>
			</button>
		</div>
		<div class="MainContainer">
			<div class="adBanner">
				<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
					<div class="">
						<div class="carousel-item active">
							<img src="/image/banner1.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="/image/banner2.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="/image/banner3.png" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying"
						data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<!-- <span class="visually-hidden">Previous</span> -->
					</button>
					<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying"
						data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<!-- <span class="visually-hidden">Next</span> -->
					</button>
				</div>
			</div>
			<div class="MainStage">
				<div class="BascketContainer">
					<!-- <div class="Bascketlist">
						<img src="/image/image 71.png" style="width:10%" alt="...">
						<div class="mainFont product_description">가지 3개입(300g)</div>
						<button class="mainFont quantityBtn plusBtn">+</button>
						<div class="mainFont productQuantity">3</div>
						<button class="mainFont quantityBtn minusBtn">-</button>
						<div class="mainFont product_price"> 32,400원 </div>
						<button class="trashBtn">
							<img src="/image/trashcan50.svg" alt="">
						</button>
					</div> -->
					<div class="EmptyMessage" v-if="products.length === 0">담은 상품이 없습니다.</div>
					<div class="Bascketlist" v-for="product in products">
						<!-- <img class="heartImg" src="/image/favorite.png" alt=""> -->
						<div class="productImage">
							<img class="pimg" v-bind:src="`${product.product.image}`" alt="">
						</div>
						<div class="mainFont product_description"> {{ product.product.name }} </div>
						
						<div class="quantityContainer">
							<button class="mainFont quantityBtn minusBtn"
								@click="CartStore.minusProduct(product)">-</button>
							<div class="mainFont productQuantity"> {{ product.quantity }} </div>
							<button class="mainFont quantityBtn plusBtn" @click="CartStore.plusProduct(product)">+</button>
						</div>
					
						<div class="priceContainer">
							<div class="originPrice" v-if="product.product.discountRate !== 0">
								{{ (Math.floor((product.product.price * product.quantity) / 10) * 10).toLocaleString('ko-KR') }}원 
							</div>
							<div class="mainFont product_price"> {{ (Math.floor((product.product.price * product.quantity * (1 -
								product.product.discountRate)) / 10) * 10).toLocaleString('ko-KR') }}원 
							</div>
						</div>
						
						
						<button class="trashBtn" @click="CartStore.deleteProduct(product)">
							<img src="/image/trashcan50.svg" alt="">
						</button>
						<!-- <img src="/image/Line2.png" style="margin-left:3%; margin-top:2%; width:100%;" alt="..."> -->
					</div>
				</div>
				<div class="WishlistContainer">
					<div class="UserStatus">
						<img src="/image/user.png" alt="">
						<div class="UserInfo">
							<div class="UserText">{{ username }}님 로그인 중입니다</div>
						</div>
					</div>
					<!-- <div class="WishCard">
						<div class='Cardslider'>
							<div class='Card-slide-track'>
								<div class="Cardslide" v-for="list in wishGroupList">
									<div class = "train-card">
										<img v-bind:src="`${list.image}`" class="train-card-img" alt="...">
									</div>
									<div class="train-card-text">
										{{ list.name }}
									</div>
								</div>
							</div>
						</div>
					</div>	 -->
					<section id="slider">
						<!-- <input type="radio" name="slider" id="s1" checked>
						<input type="radio" name="slider" id="s2">
						<input type="radio" name="slider" id="s3">
						<input type="radio" name="slider" id="s4">
						<input type="radio" name="slider" id="s5">

						<label for="s1" id="slide1">1</label>
						<label for="s2" id="slide2">2</label>
						<label for="s3" id="slide3">3</label>
						<label for="s4" id="slide4">4</label>
						<label for="s5" id="slide5">5</label> -->
						<input type="radio" name="slider" v-for="(list, index) in wishGroupList" :id="`s${index+1}`" :checked="index === 0" :style="`visibility:hidden;`">
						<label v-for="(list, index) in wishGroupList" :for="`s${index+1}`" :id="`slide${index+1}`" @click="selectWishList(list)">
							<div class="imgContainer">
								<template v-if="list.img===null">
									<img class="imgWrapper" src="/image/noimage.png">
								</template>
								<template v-else>
									<img class="imgWrapper" :src="list.img">
								</template>
								<div class="nameWrapper">
									<!-- {{ list.name }} -->
								</div>
							</div>

						</label>
	
					</section>
					<div class="nameContainer">{{ nowList }}</div>
					<div class="wishListProduct">
						<div class="wishProduct" v-for="product in wishlist" :class="{'background-blue': product.productOn}">
							<img v-bind:src="`${product.image}`" class="pImage" alt="...">
							<div class="pName" @click="viewCategory(product.locate)">{{ product.name.length > 12 ? product.name.slice(0,12) + '...' : product.name }}</div>
							<div class="pPrice">{{ product.price.toLocaleString('ko-KR') }}원</div>
							<!-- <div v-if="!product.productOn">hohuioi</div> -->
						</div>
					</div>
				</div>
			</div>
			<Modalform v-if="modalflag" />
			<callModalform v-if="callmodalflag" />
			<recommendModalform v-if="recommendmodalflag" />
			<LogoutModalform v-if="logoutmodalflag" />
			<!-- <selectLocationModalform v-if="selectLocationModalflag" /> -->

			<div class="MainFooter">
				<div class="FooterText">수량 :&emsp;&emsp;&emsp;<b style="font-size:24px">{{ totalQuantity }} 개</b></div>
				<div class="FooterText">전체 금액 :&emsp;&emsp;&emsp;<b style="font-size:24px">{{ totalOriginPrice.toLocaleString('ko-KR') }} 원</b></div>
				<div class="FooterText">할인 금액 :&emsp;&emsp;&emsp;<b style="font-size:24px">{{ (Math.floor(totalDiscountedPrice2 / 10) * 10).toLocaleString('ko-KR') }} 원</b></div>
				<div class="FooterText">총 액 :&emsp;&emsp;&emsp;<b style="font-size:24px">{{ (Math.floor(totalPrice / 10 ) * 10).toLocaleString('ko-KR') }} 원</b></div>
				
			</div>
		</div>
		<ssafyFloor2 v-if="flagSwitch.locationModalflag"/>
	</div>
</template>

<script setup>
import { ref, onUpdated, onUnmounted, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import { storeToRefs } from "pinia";
import { useFlagStore } from "@/stores/flag.js";
import Modalform from '@/components/ModalForm.vue';
import callModalform from '@/components/employeeCallModal.vue';
import LogoutModalform from '@/components/LogoutModal.vue';
import recommendModalform from '@/components/menuRecommendModal.vue';
import selectLocationModalform from '@/components/selectLocationModal.vue';
import { useCartStore } from "@/stores/cart";
import { useSearchListStore } from "@/stores/searchList";
import { useLoginStore } from "@/stores/login";
import ssafyFloor2 from '@/views/floor/SSAFYFloor2.vue';
import { useCopyStore } from "@/stores/copy";

const ShowList = ref(true);
const wishListextendFlag = ref(false);

const flagSwitch = useFlagStore();
const { modalflag, callmodalflag, logoutmodalflag, recommendmodalflag, selectLocationModalflag, screenIndex, menuPickflag } = storeToRefs(flagSwitch);

const CartStore = useCartStore();
const { products, totalPrice, totalQuantity, totalOriginPrice, totalDiscountedPrice, totalDiscountedPrice2 } = storeToRefs(CartStore);

const searchListStore = useSearchListStore();
const { wishlist, wishGroupList, nowList } = storeToRefs(searchListStore);

const loginStore = useLoginStore();
const { username } = storeToRefs(loginStore);

const jjimflag = ref(false);
const wishGroupName = ref('');
const radioButtons = document.querySelectorAll('input[type=radio]');

const copyStore = useCopyStore();
const { productsCopy } = storeToRefs(copyStore);

const slideShow = () => {
 for(let i =0; i<radioButtons.length; i++){
   setInterval(() => {
     console.log(i)
   }, 3000)
 }
}

const selectwishlist = (num, name) =>{
	//테스트는 더미 값

	// 실제
	searchListStore.wishSearch(num);
	wishGroupName.value = name;
	jjimflag.value = true;
}

const selectWishList = (list) =>{
	searchListStore.wishSearch(list.seq);
	nowList.value = list.name;
}


const wishlistclose = () =>{
	jjimflag.value = false;
}

const openRecommendModal = () => {
	console.log("copyProducts");
	productsCopy.value.forEach(product => {
        console.log(product);
    });
	if(!menuPickflag.value){
		searchListStore.randomMenu();
	}
	flagSwitch.recommendmodalflagChange();
}

const switchModal = (num) => {
	flagSwitch.activeButtonIndexChange(num);
    console.log(flagSwitch.activeButtonIndex);
	
	if (num === 3) searchListStore.discountListSearch()
	else screenIndex.value = num;
	flagSwitch.modalflagChange();

}



const bongtu = () =>{
	alert('봉투 구매');
}

// MQTT UWB 신호 받으면 동작하는 함수

$(document).ready(function () {
	fncStartMqtt();
});


// MQTT client
var mqttClient = null;

//MQTT info
// 각자 상황에 맞는 host, port, topic 을 사용합니다.
var mqtt_host = "127.0.0.1";
var mqtt_port = "9001";
var mqtt_clientId = "clientID-" + parseInt(Math.random() * 100);        // 랜덤 클라이언트 ID 
var mqtt_topic = "testTopic";

// MQTT 클라이언트 연결
function fncStartMqtt() {
	mqttClient = new Paho.MQTT.Client(mqtt_host, Number(mqtt_port), mqtt_clientId);

	mqttClient.onConnectionLost = onConnectionLost;
	mqttClient.onMessageArrived = onMessageArrived;

	mqttClient.connect({
		onSuccess: onConnect
		, onFailure: onFailure
	});
}

// 연결 성공 시 실행되는 function
function onConnect() {
	console.log("connet : onConnect..");

	mqttClient.subscribe(mqtt_topic);
}

// 연결 실패 시 실행되는 function
function onFailure() {
	console.log("connet : onFailure..");

}



function onConnectionLost(responseObject) {
	if (responseObject.errorCode !== 0) {
		console.log("onConnectionLost : " + responseObject.errorMessage);

		// 연결 재시도
		fncConnMqtt();
	}
}

// 메시지 받는 부분
function onMessageArrived(message) {
	console.log("onMessageArrived : " + message.payloadString);

	// mqtt 받은 메시지 
	// 받은 메시지를 각 화면에서 사용하려면 각 화면에서 아래 function 선언하여 사용

	if(message.payloadString === 'autoPaymentOn') fncMqttPayment();

}



// 메시지 보내기
// 각 화면에서 메시지를 보내려면 각 화면에서 아래 function 선언하여 사용
function fncMqttDoSend(sendMsg) {
	mqttClient.send(mqtt_topic, sendMsg);
}

function fncMqttPayment() {
	console.log("결제 요청");
	//테스트
	router.push('/paymentProgress')

	//실제
	//CartStore.paymentNow();

}

// 바코드 인식

let barcodeData = ''; // 입력된 바코드 데이터를 저장할 변수

onUpdated(() => {
	barcodeData = '';
	console.log(products.value)
	sessionStorage.setItem('product',products.value);
	for(let i = 0; i<wishlist.value.length; i++){
		var flag = false;
		for(let j = 0; j<products.value.length; j++){
			if(wishlist.value[i].name === products.value[j].product.name){
				wishlist.value[i].productOn = true;
				console.log(i);
				flag = true;
				break;
			}
		}
		if(!flag) wishlist.value[i].productOn = false;
	}
	console.log(wishlist.value);
})



function keyListen(event) {
	// 숫자 키 또는 Enter 키가 눌렸을 때만 데이터 처리
	if ((event.keyCode >= 48 && event.keyCode <= 57) || // 숫자 키
		(event.keyCode >= 65 && event.keyCode <= 90) || // 대문자 알파벳 키
		(event.keyCode >= 97 && event.keyCode <= 122)) {
		barcodeData += String.fromCharCode(event.keyCode);
	}
	else if (event.keyCode === 13 && barcodeData !== '') {
		// Enter 키가 눌렸을 때는 바코드 데이터 처리
		console.log("Scanned barcode: " + barcodeData);

		//스캔한 상품이 이미 장바구니에 있는지 확인
		if (products.value.length !== 0) {
			var flag = 0;
			for (let i = 0; i < products.value.length; i++) {
				if (products.value[i].product.barcode === barcodeData) {
					console.log('이미 담긴 상품입니다. 수량을 추가합니다.');
					console.log(products.value[i].cartProductSeq);
					CartStore.plusProduct(products.value[i]);
					flag = 1;
					break;
				}
			}
			if(flag===0){
				console.log('상품을 담습니다.');
				CartStore.addProduct(barcodeData);
			}

		}
		else CartStore.addProduct(barcodeData); // 장바구니가 비었으면 바로 상품 추가

		// 바코드 데이터 처리 후 초기화
		barcodeData = '';
	}
}

// 상품의 위치를 나타내는 함수
const viewCategory = (category) => {
	console.log('hello');
    // 채소, 과일
    if(category === 'A5'){
        console.log('채소/과일');
		// flagSwitch.modalflagChange(1);
        flagSwitch.viewLocation(2);
    }
    // 김치
    else if(category === 'A9'){
        console.log('김치/반찬');
        flagSwitch.viewLocation(1);
    }
    // 정육
    else if(category === 'A1'){
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
}

document.addEventListener('keydown', keyListen);

onUnmounted(() => {
	document.removeEventListener('keydown', keyListen);
})



onMounted(() => {
	slideShow();
	//tmpList = searchListStore.wishlistSearch();
})

</script>


<style scoped>
#slider {
  position: relative;
  width: 40%;
  height: 30%;
  margin: 30px auto;
  perspective: 1400px;
  transform-style: preserve-3d;
}

input[type=radio] {
  position: relative;
  top: 108%;
  left: 50%;
  width: 18px;
  height: 18px;
  margin: 0 15px 0 0;
  opacity: 0.4;
  transform: translateX(-83px);
  cursor: pointer;
  /* visibility: hidden; */
}

input[type=radio]:checked {
  opacity: 1;
}

#slider label {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  color: white;
  font-size: 70px;
  font-weight: bold;
  border-radius: 5px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: transform 400ms ease;
}
.imgContainer {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.imgWrapper {
    height: 150px; /* 전체 높이의 70%를 차지 */
    width: 150px; /* 이미지 컨테이너의 너비에 맞춰 조정 */
    display: flex;
    justify-content: center;
    align-items: center;
	border : 3px solid rgb(187, 184, 184);
	border-radius: 10%;
	background-color: white;
	opacity: 100%;
}
.nameWrapper {
	height: 30%; /* 나머지 30%를 차지 */
    width: 100%; /* 이름 컨테이너의 너비에 맞춰 조정 */
    display: flex;
    justify-content: center;
    align-items: center;
    color: black;
    font-size: 16px;
	font-weight: bold;
}

/* .name {
	max-height: 100%;
	max-width: 100%;
	background-color: #F2F2F2;
	color: black;
	font-size: 20px;
} */

.nameContainer {
	display: flex;
	align-items: center;
	justify-content: center;
	margin-top: -45px;
	font-size: 20px;
	font-weight: 600;
}

.wishListProduct {
	margin-top: 2%;
	height: 48%;
	/* background-color: rgb(58, 172, 71); */
	display: flex;
	flex-direction: column;
	/* justify-content: center; */
	align-items: center;
	overflow-y: auto;
}

.wishProduct {
	margin-top: 0.5%;
	/* margin-bottom: 1%; */
	width: 95%;
	height: 22%;
	background-color: #f0efef7a;
	display: flex;
	flex-direction: row;	
	align-items: center;
}

.background-blue {
	background-color: aqua;
}

.pImage {
	margin-left:1%;
	padding : 1%;
	width: 12%;
	height: 55px;
	border-radius: 10px;
}

.pName {
	font-size: 20px;
	width: 55%;
	padding-left: 5%;
	font-weight: 600;
}

.pPrice {
	width: 30%;
	text-align: right;
	font-size: 20px;
	font-weight: 600;
	color: #4c4b4b;
	padding-right: 5%;
}
/* Slider Functionality */

/* Active Slide */
#s1:checked ~ #slide1, #s2:checked ~ #slide2, #s3:checked ~ #slide3, #s4:checked ~ #slide4, #s5:checked ~ #slide5 {
  /* box-shadow: 0 13px 26px rgba(0,0,0, 0.3), 0 12px 6px rgba(0,0,0, 0.2); */
  transform: translate3d(0%, 0, 0px);
}

/* Next Slide */
#s1:checked ~ #slide2, #s2:checked ~ #slide3, #s3:checked ~ #slide4, #s4:checked ~ #slide5, #s5:checked ~ #slide1 {
  /* box-shadow: 0 6px 10px rgba(0,0,0, 0.3), 0 2px 2px rgba(0,0,0, 0.2); */
  transform: translate3d(50%, 0, -200px);
}


/* Next to Next Slide */
#s1:checked ~ #slide3, #s2:checked ~ #slide4, #s3:checked ~ #slide5, #s4:checked ~ #slide1, #s5:checked ~ #slide2 {
  /* box-shadow: 0 1px 4px rgba(0,0,0, 0.4); */
  transform: translate3d(95%, 0, -400px);
}

/* Previous to Previous Slide */
#s1:checked ~ #slide4, #s2:checked ~ #slide5, #s3:checked ~ #slide1, #s4:checked ~ #slide2, #s5:checked ~ #slide3 {
  /* box-shadow: 0 1px 4px rgba(0,0,0, 0.4); */
  transform: translate3d(-95%, 0, -400px);
}

/* Previous Slide */
#s1:checked ~ #slide5, #s2:checked ~ #slide1, #s3:checked ~ #slide2, #s4:checked ~ #slide3, #s5:checked ~ #slide4 {
  /* box-shadow: 0 6px 10px rgba(0,0,0, 0.3), 0 2px 2px rgba(0,0,0, 0.2); */
  transform: translate3d(-50%, 0, -200px);
}




.MainPage {
	display: flex;
	flex-direction: row;
	/* background-color: antiquewhite; */
	width: 100%;
	height: 100%;
}

.sideBar {
	display: flex;
	width: 8%;
	height : 100%;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	background-color: white;
	box-shadow: 1px 0px #939393;
	z-index: 1;
}
.sideBtn {
	width: 100%;
	padding: 0;
	background-color: white;
	border: 0;
	height: 122.5px;
}

.sideBtn2 {
	border-top-right-radius: 20px;
}

.sideBtn3 {
	border: none;
	border-bottom-right-radius: 20px;
}

.sideTxt {
	margin-top: 2px;
	font-weight: 600;
}

.MainContainer {
	width : 100%;
}
.adBanner {
	width: 100%;
	height: 12%;
	z-index: 0;
}

.MainStage {
	width:100%;
	height:81%;
	display: flex;
    flex-direction: row;

}
.product_description {
	width: 43%;
	font-size: 21px;
	font-weight: 600;
	text-align: left;
	/* margin-left : 3%; */
	font-family: "Gowun Dodum", sans-serif;
}

.quantityContainer {
	margin-left: 3%;
	width: 15%;
	display: flex;
	justify-content: center;
	align-items: center;
}

.plusBtn {
	background-color: #80E1FF;
}

.priceContainer {
	width: 15%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-end;
}

.originPrice {
	text-decoration: line-through;
	font-size: 16px;
	font-weight: 600;
	color: gray;
	margin-right :3%;
}
.productQuantity {
	font-size: 32px;
	font-weight: 600;
	margin: 0px 15px;
}

.minusBtn {
	background-color: #DADADA;
	margin-right: 2%;
}
.quantityBtn {
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 32px;
	font-weight: 600;
	width: 30px;
	height: 30px;
	flex-shrink: 0;
	/* background-color: #80E1FF; */
	filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
	border-radius: 50%;
	border: 0px;
	padding-bottom: 7px;
	margin: 0% 1%;
}

.BascketContainer{
	display: flex;
	flex-direction: column;
	align-items: center;
	width : 65%;
	/* padding-left : 1%; */
	/* padding-right : 5%; */
	margin-top : 8px;
	overflow-y: auto;
}
.EmptyMessage{
	margin-top: 35%;
	font-size: 30px;
	color: darkgray;
}

.Bascketlist {
	display: flex;
	flex-direction: row;
	align-items: center;
	width: 95%;
	height: 15%;
	padding-top : 1%;
	margin-left : 2%;
	padding-bottom: 1%;
	border-bottom: 2px solid rgb(226, 223, 223);
}

.productImage {
	width: 10%;
	height: 95%;
	display: flex;
	margin: 0.5% 2%;
}

.pimg {
	border-radius: 10px;
	height: 100%;
	width: 90%;
}

.product_price {
	width: 100%;
	font-size: 20px;
	font-weight: bold;
	text-align: right;
	/* padding-left: 3%; */
	font-family: "Gowun Dodum", sans-serif;
}
.trashBtn {
	margin-left: 2%;
	border: 0;
	background-color: transparent;
}

.WishlistContainer {
	width : 35%;
	border-left : 1px solid #DADADA;
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
}

.MainFooter {
	height : 7%;
	background-color : #F2F2F2; 
	display : flex;
	align-items: center;
	justify-content: space-between;
}
.FooterText {
	font-size : 20px;
	margin-left : 3%;
	margin-right : 3%;
}

/* 유저 정보 */
.UserStatus {
	display : flex;
	align-items: center;
	justify-content: flex-start;
	margin-top: 5%;
	margin-left: 4%;
}
.UserPoint {
	display : flex;
	align-items: center;
	justify-content: flex-start;
}
.UserInfo {
	margin-left : 3%;
}
.UserText {
	font-size: 24px;
	font-weight: bold;
}
.PointInfo {
	margin-left: 2%;
	font-weight: 400;

}
/* 카드슬라이더 */

.WishCard {
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
}
.Cardslider{
  height: 80%;
  margin: auto;
  position:relative;
  width: 80%;
  display:flex;
  place-items:center;
  overflow:hidden;
}

.Card-slide-track{
  display:flex;
  width: calc(300px * 12);
  animation: scroll 20s linear infinite; /* 한바퀴도는데 20초로 설정 여기를 위시리스트 목록 수에 따라 어떻게 조절할지 고민 */
}

.Card-slide-track:hover{
  animation-play-state:paused;
}

@keyframes scroll{
  0% {
    transform: translateX(0);
  }
  100%{
    transform: translateX(calc(-100px * 6)); /* 옆으로 넘어가는 속도 여기를 위시리스트 목록 수에 따라 어떻게 조절할지 고민 */
  }
}

.Cardslide{
  height: 250px;
  width: 200px;
  display: flex;
  align-items:center;
  padding: 15px;
  perspective:100px;
  justify-content: center;
}
.train-card {
	display: flex;
	justify-content: center;
	align-items: center;
}
.train-card-img {
	width:150px; 
	height:150px; 
	border:3px solid black; 
	border-radius:10%;
	position: relative;
	z-index: 1;
	opacity: 50%;
}
.train-card-text{
  width:60%;
  transition:transform 1s;
  overflow:hidden;
  position: absolute;
  z-index:2;
  /* top:40%; */
  text-align: center;
  font-size : 20px;
  font-weight: bold;
  word-break: keep-all;
}

.train-card:hover{
  transform:translateZ(18px);
}

/* 슬라이더 그라데이션 그림자 */

.Cardslider::before,
.Cardslider::after {
  background:linear-gradient(to right, #ffffff 0%,
  rgba(255, 255, 255, 0) 100%);
  content:'';
  height:100%;
  position:absolute;
  width:15%;
  z-index:2;
}

.Cardslider::before{
  left:0;
  top:0;
}

.Cardslider::after{
  right:0;
  top:0;
  transform: rotateZ(180deg);
}

.BascketContainer::-webkit-scrollbar {
    width: 6px;
    display: block;
    opacity: 1;
	
}

/* .hot-deals:hover::-webkit-scrollbar {
    display: block;
    width: 10px;
} */

.BascketContainer::-webkit-scrollbar-thumb {
    /* background-color: #80E1FF; */
    background-color: rgb(170, 168, 168);
    border-radius: 10px;
}
.BascketContainer::-webkit-scrollbar-track {
    /* background-color: rgb(186, 215, 248); */
    background-color: rgb(208, 209, 210);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}

.wishListProduct::-webkit-scrollbar {
    width: 6px;
    display: block;
    opacity: 1;
}

/* .hot-deals:hover::-webkit-scrollbar {
    display: block;
    width: 10px;
} */

.wishListProduct::-webkit-scrollbar-thumb {
    /* background-color: #80E1FF; */
    background-color: rgb(170, 168, 168);
    border-radius: 10px;
}
.wishListProduct::-webkit-scrollbar-track {
    /* background-color: rgb(186, 215, 248); */
    background-color: rgb(208, 209, 210);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
</style>