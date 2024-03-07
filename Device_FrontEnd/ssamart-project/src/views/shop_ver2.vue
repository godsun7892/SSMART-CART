<template>
	<div class="MainPage">
		<div class="MainStage">
			<div class="MainContainer">
				<div class="MainLeft">
					<div class="sideBar">
						<!-- <div class="sideMenuCon"> -->
						<button class="sideBtn sideBtn2" @click="switchModal(0)">
							<img src="/image/searchProduct.png" style="width: 35%; height: 35%;" alt="">
							<br>
							<div class=sideTxt>상품 검색</div>
						</button>
						<img src="/image/line.png" style="" alt="">
						<button class="sideBtn" @click="switchModal(2)">
							<img src="/image/martMap.png" style="width: 30%; height: 30%;" alt="">
							<br>
							<div class=sideTxt>매장 지도</div>
						</button>
						<img src="/image/line.png" style="" alt="">
						<button class="sideBtn" @click="switchModal(3)">
							<img src="/image/discountInfo.png" style="width: 35%; height: 35%;" alt="">
							<br>
							<div class=sideTxt>할인 정보</div>
						</button>
						<img src="/image/line.png" style="" alt="">
						<button class="sideBtn" @click="openRecommendModal">
							<img src="/image/todayMenu.png" style="width: 35%; height: 35%;" alt="">
							<br>
							<div class=sideTxt>오늘의<br> 메뉴 추천</div>
						</button>
						<img src="/image/line.png" style="" alt="">	
						<button class="sideBtn" @click="flagSwitch.callmodalflagChange()">
							<img src="/image/callEmployee.png" style="width: 35%; height: 35%;" alt="">
							<br>
							<div class=sideTxt>직원 호출</div>
						</button>
						<img src="/image/line.png" style="" alt="">
						<button class="sideBtn" @click="flagSwitch.callmodalflagChange()">
							<img src="/image/payment.png" style="width: 35%; height: 35%;" alt="">
							<br>
							<div class=sideTxt>수동 결제</div>
						</button>
						<img src="/image/line.png" style="" alt="">
						<button class="sideBtn sideBtn3" @click="flagSwitch.logoutmodalflagChange()">
							<img src="/image/logout.png" style="width: 35%; height: 35%;" alt="">
							<br>
							<div class=sideTxt>쇼핑 종료</div>
						</button>

						<!-- <div class="SideMenuFooter">
								<button class="payBtn fontSize24" @click="$router.push('/payMent')">수동 결제</button>
							</div> -->
						<!-- </div> -->
					</div>
				</div>

				<div class="MainRight">
					<div class="MainStageBody">
						<div class="bodyContainer">
							<div class="adBanner">
								<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
									<div class="">
										<div class="carousel-item active">
											<img src="/image/image 63.png" class="d-block w-100 h-60" alt="...">
										</div>
										<div class="carousel-item">
											<img src="/image/image 63.png" class="d-block w-100 h-60" alt="...">
										</div>
										<div class="carousel-item">
											<img src="/image/image 63.png" class="d-block w-100 h-60" alt="...">
										</div>
									</div>
									<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying"
										data-bs-slide="prev">
										<span class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="visually-hidden">Previous</span>
									</button>
									<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying"
										data-bs-slide="next">
										<span class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="visually-hidden">Next</span>
									</button>
								</div>
							</div>

							<div class="bodyContent">
								<div class="BodyScreen1">
									<div class="listContainer">
										<!-- <div class="BodyScreen2Menu">
										<div class="wishListBtn">장바구니</div>
										</div> -->
										<div class="Basket" v-if="ShowList">
											<div class="EmptyMessage" v-if="products.length === 0">담은 상품이 없습니다.</div>
											<div class="Basket_list" v-else>
												<li v-for="product in products" class="buy_list">
													<!-- <img class="heartImg" src="/image/favorite.png" alt=""> -->
													<div class="productImage">
														<img v-bind:src="`${product.product.image}`" alt="" class="pImage">
													</div>
													<div class="mainFont product_description"> {{ product.product.name }} </div>
													<button class="mainFont quantityBtn plusBtn" @click="CartStore.plusProduct(product)">+</button>
													<div class="mainFont productQuantity"> {{ product.quantity }} </div>
													<button class="mainFont quantityBtn minusBtn"
														@click="CartStore.minusProduct(product)">-</button>
													<div class="mainFont product_price"> {{ (Math.floor((product.product.price * product.quantity * (1 -
														product.product.discountRate)) / 10) * 10).toLocaleString('ko-KR') }}원 </div>
													<button class="trashBtn" @click="CartStore.deleteProduct(product)">
														<img src="/image/trashcan50.svg" alt="">
													</button>
												</li>
												<div class="emptyRange"> hi </div>
											</div>
											<!-- <hr/>
											<div class="MainStageFooter">
												<button class="payBtn fontSize24">결제하기</button>
												<div class="AllPriceText fontSize24">금액 : </div>
												<div class="AllPrice fontSize24">{{ totalPrice.toLocaleString() }}</div>
											</div> -->
										</div>
									</div>

								</div>

								<div class="BodyScreen2">
									<div class="userStatusCon">
										<img src="/image/user_photo_test1.png" alt="" class="userImg">
										<div class="userStatus">
											<div class="mainFont main_user">{{ username }}님 로그인 중입니다</div>
										</div>
									</div>
									<div class="point">
										<img class="pointImg" src="/image/point2.png">
										{{ username }}
									</div>
									<div class="whiteLine"></div>
									<div class="jjimList" v-if="!jjimflag">
										<div class="">목록을 선택해주세요</div>
										
										<div class="deal-card" v-for="(item, index) in wishGroupList" :key="index" @click="selectwishlist(item.seq, item.name)">
											<!-- 상품 정보 -->
											<img v-bind:src="`${item.image}`" alt="" class="jjimImage">
											<div class="deal-info">
												
												<div class="productName">{{ item.name }}</div>
												<div class="jjimExplanation">{{item.pName}} 외 {{item.quantity}}건</div>
											</div>
										</div>
									</div>
									<div class="jjimList" v-else>
										<div class="wishheader">
											<div class="">{{ wishGroupName }}</div>
											<button class="" @click="wishlistclose">뒤로가기</button>
										</div>
										
										<div class="deal-card" v-for="(item, index) in wishlist" :key="index">
											<!-- 상품 정보 -->
											<img v-bind:src="`${item.product.image}`" alt="" class="jjimImage">
											<div class="deal-info">
												<div class="productName">{{ item.product.name }}</div>
												<span>{{ item.product.price }}원</span>
											</div>
										</div>
									</div>
									<!-- <div class="sideMenuCon">
											<div class="sideMenuCon1">
												<button class="sideBtn" @click="switchModal(0)">
													<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor"
														class="bi bi-search" viewBox="0 0 16 16">
														<path
															d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
													</svg>
													<br>상품 검색
												</button>
												<button class="sideBtn otherColor" @click="switchModal(2)">
													<img src="/image/martMap.png" style="width: 60%; height: 60%;" alt="">
													<br>매장 지도
												</button>
											</div>
											<div class="sideMenuCon1">
												<button class="sideBtn otherColor" @click="switchModal(3)">
													<img src="/image/discountIcon.png" style="width: 60%; height: 60%;" alt="">
													<br>할인 정보
												</button>
												<button class="sideBtn" @click="openRecommendModal">
													<img src="/image/todayMenu.png" style="width: 60%; height: 60%;" alt="">
													<br>오늘의 메뉴 추천
												</button>
											</div>
											<div class="sideMenuCon1">
												<button class="sideBtn" @click="flagSwitch.callmodalflagChange()">
													<svg xmlns="http://www.w3.org/2000/svg" width="105" height="94" class="bi bi-telephone-fill callImage" viewBox="0 0 16 16">
														<path fill-rule="evenodd" d="M1.885.511a1.745 1.745 0 0 1 2.61.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
													</svg>
													<br>직원 호출
												</button>
												<button class="sideBtn otherColor" @click="flagSwitch.logoutmodalflagChange()">
													<img src="/image/logout.png" style="width: 60%; height: 60%;" alt="">
													<br>쇼핑 종료
												</button>
											</div>
											<div class="SideMenuFooter">
												<button class="payBtn fontSize24" @click="$router.push('/payMent')">수동 결제</button>
											</div>
										</div> -->
									<!-- <div class="whiteLine"></div>
										<div class="wishListCon">
											<div class="ListHeader">
												{{username}}님의 위시리스트
												<button class="cardExpandBtn">+</button>
											</div>
											<div class="ListMain" v-if="wishListextendFlag">
												<li v-for="product in wishlist" class="wishlist">
													<div class="productImage"><img src="/image/image 40.png" alt=""></div>
													<div class="wishlist_description">{{ product.name }}</div>
													<div class="trashBtn" @click="wishDelete(product)"><img src="/image/trashcan50.svg" alt=""></div>
													<div class="wishOn" v-if="product.productOn">
														<div class="wishOnMessage">상품을 장바구니에 담았습니다</div>
													</div>
												</li>
												<div class="EmptyMessage" v-if="wishlist.length===0">위시리스트가 없습니다.</div>
											</div>
											<div class="ListMain" v-else>
												<li v-for="product in wishlist" class="wishlist">
													<div class="wishlist_description">{{ product.name }}</div>
													<div class="wishOn2" v-if="product.productOn"></div>
												</li>
											</div>
										</div> -->
								</div>
							</div>

							<!-- <hr/>
							<div class="MainStageFooter">
								<button class="payBtn fontSize24">결제하기</button>
								<div class="AllPriceText fontSize24">금액 : </div>
								<div class="AllPrice fontSize24">{{ totalPrice.toLocaleString() }}</div>
								<div class="main_con_2">
									<div class="main_con_2_photo">
										<img src="/image/user_photo_test1.png" alt="">
									</div>
									<div class="mainFont main_user">
										{{ username }}님 로그인 중입니다.
									</div>
								</div>
							</div> -->
						</div>
					</div>


					<Modalform v-if="modalflag" />
					<callModalform v-if="callmodalflag" />
					<recommendModalform v-if="recommendmodalflag" />
					<LogoutModalform v-if="logoutmodalflag" />
				</div>
				<div class="MainStageFooter">
					<div class="quanRange">
						<div>수량:</div>
						<div>{{ totalQuantity }}개</div>
					</div>
					<div class="priceRange">
						<div>총액:</div>
						<div>{{ (Math.floor(totalPrice / 10) * 10).toLocaleString('ko-KR') }}원</div>
					</div>
					<div class="btnRange">
						<button class="payBtn" @click="bongtu">봉투 구매</button>
						<button class="payBtn" @click="$router.push('/payMent')">수동 결제</button>
					</div>

				</div>
			</div>
		</div>
		<!-- <div class="SideBarStage">
			<button class="sideBtn" @click="switchModal(0)">상품 검색</button>
			<button class="sideBtn" @click="switchModal(2)">매장 지도</button>
			<button class="sideBtn" @click="switchModal(3)">할인 정보</button>
			<button class="sideBtn" @click="openRecommendModal">오늘의 메뉴 추천</button>
			<button class="sideBtn" @click="openCallModal">직원 호출</button>
			<button class="sideBtn" @click="$router.push('/')">종료</button>
		</div> -->
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
import { useCartStore } from "@/stores/cart";
import { useSearchListStore } from "@/stores/searchList";
import { useLoginStore } from "@/stores/login";


const ShowList = ref(true);
const wishListextendFlag = ref(false);

const flagSwitch = useFlagStore();
const { modalflag, callmodalflag, logoutmodalflag, recommendmodalflag, screenIndex } = storeToRefs(flagSwitch);

const CartStore = useCartStore();
const { products, totalPrice, totalQuantity } = storeToRefs(CartStore);

const searchListStore = useSearchListStore();
const { wishlist, wishGroupList } = storeToRefs(searchListStore);

const loginStore = useLoginStore();
const { username } = storeToRefs(loginStore);

const jjimflag = ref(false);
const wishGroupName = ref('');

const selectwishlist = (num, name) =>{
	//테스트는 더미 값

	// 실제
	searchListStore.wishSearch(num);
	wishGroupName.value = name;
	jjimflag.value = true;
}

const wishlistclose = () =>{
	jjimflag.value = false;
}

const openRecommendModal = () => {
	searchListStore.randomMenu();
	flagSwitch.recommendmodalflagChange();
}

const switchModal = (num) => {
	flagSwitch.activeButtonIndexChange(num);
    console.log(flagSwitch.activeButtonIndex);
	
	if (num === 3) searchListStore.discountListSearch()
	else if (num === 2) flagSwitch.viewLocation(1);
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
	//loginStore.payment();

}

// 바코드 인식

let barcodeData = ''; // 입력된 바코드 데이터를 저장할 변수

onUpdated(() => {
	barcodeData = '';
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

document.addEventListener('keydown', keyListen);

onUnmounted(() => {
	document.removeEventListener('keydown', keyListen);
})

onMounted(() => {
	searchListStore.wishlistSearch();
})

</script>

<style scoped>
.MainPage {
	display: flex;
	flex-direction: row;
	/* background-color: antiquewhite; */
	width: 100%;
	height: 100%;
}

.SideBarStage {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-around;
	/* background-color:aqua; */
	width: 118.4px;
	height: 100%;
}

.Topbar {
	width: 80%;
	margin-left: 5%;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: right;
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

.MainStage {
	display: flex;
	flex-direction: column;
	background-color: #F2F2F2;
	width: 100%;
	height: 100%;
}

.MainContainer {
	position: relative;
	display: flex;
	flex-direction: row;
	height: 100%;
	width: 1536px;
	/* padding: 1% 1%; */
}

.MainLeft {
	width: 8.4%;
	display: flex;
	align-items: center;
}

.sideBar {
	display: flex;
	width: 100%;
	height : 100%;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	background-color: white;
	box-shadow: 1px 0px #939393;
	z-index: 1;
}

.MainRight {
	width : 100%;
}

.MainStageHeader {
	display: flex;
	flex-direction: row;
	/* background-color:darkcyan; */
	width: 100%;
	height: 10%;
	align-items: center;
}

.MainLogo {
	margin: 0% 1%;
	height: 100%;
}

.basketTxt {
	width: 30%;
	margin-left: 10px;
	margin-top: 20px;
	font-size: 30px;
	font-weight: 600;
}

.logo {
	margin-left: 860px;
	height: 80px;
}

.listChangeBtnCon {
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	width: 30%;
}

.listChangeBtn {
	background: none;
	border: 0;
	font-size: 32px;
	width: 40%;
	font-weight: bold;
	box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.adBanner {
	margin: 0% 0% 1% 0%;
	width: 100%;
	height: 20%;
	z-index: 0;
}

.bodyContent {
	display: flex;
	height: 71%;
	flex-direction: row;
}

.MainStageBody {
	display: flex;
	flex-direction: row;
	/* background-color: forestgreen; */
	width: 100%;
	height: 90%;
}

.bodyContainer {
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;
	background-color: white;
}

.ad {
	height: 10%;
}

.BodyScreen1 {
	display: flex;
	flex-direction: column;
	/* background-color: navajowhite; */
	width: 70%;
	height: 100%;
	align-items: center;
	/* margin: 0% 0% 0% 0%; */
}

.listContainer {
	width: 97%;
	height: 100%;
	border-radius: 20px;
	background-color: #fcfcfc;
}
.Basket {
	/* width: 100%; */
	/* height: 100%; */
	margin: 1% 0% 1% 0%;

}

.BodyScreen2 {
	background-color: #DCDCDC;
	display: flex;
	flex-direction: column;
	width: 29%;
	height: 100%;
	align-items: center;
	border-radius: 30px;
	margin: 0% 0% 0% 1%;
}

.userStatusCon {
	width: 100%;
	height: 10%;
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-top: 3%;
}

.userImg {
	margin-left: 5%;
	height: 100%;
}

.statusIconCon {
	display: flex;
	flex-direction: row;
	width: 50%;
	height: 100%;
	justify-content: right;
	align-items: end;
}

.StatusIcon {
	width: 25%;
	height: 40%;
	margin: 0% 2%;
	background: #80E1FF;
	color: #FFF;
	border: 0;
	font-size: 24px;
	font-weight: bold;
	border-radius: 10px;
	box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.sideMenuCon {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	/* width: 90%; */
	/* height: 80%; */
}

.sideMenuCon1 {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	width: 100%;
	margin: 2% 0%;
	height: 28%;
}

.SideMenuFooter {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100%;
	height: 10%;
}

.callImage {
	fill: #000;
}

.point {
	margin-right: 120px;
	margin-top: -6px;
}

.pointImg {
	width: 30px;
	height: 25px;
	margin-top: -6px;
}

.whiteLine {
	width: 90%;
	height: 0.3%;
	background-color: #FFF;
	margin: 3% 0%;
}

.jjimList {
	display: flex;
	flex-direction: column;
	width: 90%;
	height: 78%;
	overflow-y: auto;
}

.wishheader{
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.jjimImage{
	width:25%;
}

.wishListCon {
	display: flex;
	flex-direction: column;
	width: 95%;
	height: 62%;
	border: 0px solid #000;
	border-radius: 0px 0px 30px 30px;
}

.ListHeader {
	display: flex;
	flex-direction: row;
	width: 100%;
	height: 7%;
	font-size: 24px;
	font-weight: bold;
	color: #FFF;
	background: none;
	justify-content: space-between;
	align-items: center;
}

.cardExpandBtn {
	background: none;
	border: 0;
	font-size: 36px;
	font-weight: bold;
	color: #FFF;
}

.ListMain {
	height: 90%;
	display: flex;
	flex-direction: column;
	align-items: center;
	overflow-y: scroll;
}

.ListMain::-webkit-scrollbar {
	width: 0px;
	height: 0px;
}

.ListMain::-webkit-scrollbar-thumb {
	height: 1%;
	/* 스크롤바의 길이 */
	background: #E1E1E1;
	/* 스크롤바의 색상 */

	border-radius: 10px;
}

.ListMain::-webkit-scrollbar-track {
	background: rgba(33, 122, 244, .1);
	/*스크롤바 뒷 배경 색상*/
}

.BodyScreen2Menu {
	display: flex;
	flex-direction: row;
	font-size: 20px;
	height: 3%;
	align-items: center;
	width: 100%;
	justify-content: space-around;
	font-weight: bold;
}



.wishListBtn {
	background: none;
}

.wishlistClearBtn {
	width: 30%;
	font-size: 22px;
	font-weight: bold;
	border-radius: 10px;
	border: 0px;
	background-color: #E8828D;

}

.wishOn {
	position: absolute;
	display: flex;
	justify-content: center;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	border-radius: 10px;
}

.wishOn2 {
	position: absolute;
	display: flex;
	justify-content: center;
	width: 100%;
	height: 100%;
	background-color: rgba(8, 241, 245, 0.5);
	border-radius: 10px;
}

.wishOnMessage {
	color: #FFFFFF;
	font-size: 24px;
	font-weight: bold;
}

.EmptyMessage {
	text-align: center;
	margin-top: 3%;
	/* height: 85%; */
	font-size: 24px;
	color: #939393;
}

.BodyScreen2main {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100%;
	height: 90%;
	transition: transform 0.5s;
	overflow-y: scroll;
}

.BodyScreen2main::-webkit-scrollbar {
	width: 0px;
}

.BodyScreen2main::-webkit-scrollbar-thumb {
	height: 5%;
	/* 스크롤바의 길이 */
	background: #000000;
	/* 스크롤바의 색상 */

	border-radius: 10px;
}

.BodyScreen2main::-webkit-scrollbar-track {
	background: rgba(33, 122, 244, .1);
	/*스크롤바 뒷 배경 색상*/
}

.MainStageFooter {
	position: absolute;
	bottom: 0%;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	background-color: #F2F2F2;
	width: 100%;
	height: 7%;
	align-items: center;
	font-size: 24px;
	font-weight: bold;
	z-index: 0;
}

.quanRange {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	margin-left: 10%;
	width: 20%;
}

.priceRange {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	margin-left: 18%;
	width: 20%;
}

.btnRange {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	margin: 0% 2%;
	width: 25%;
}


.payBtn {
	width: 50%;
	height: 80%;
	background-color: #80E1FF;
	margin: 0% 3%;
	border: 0;
	border-radius: 10px;
	box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.footerText1 {
	width: 10%;
	height: 100%;
	background-color: #CECECE;
}



.bottomBtn {
	width: 100px;
	height: 100px;
	border-radius: 10px;
	background: #80E1FF;
	box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
	margin: 0% 1%;
	border: 0;
}

.Basket_list {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100%;
	height: 98%;
	transition: transform 0.5s;
	overflow-y: scroll;
}

.Basket_list::-webkit-scrollbar {
	width: 0px;
	height: 0px;
}

.Basket_list::-webkit-scrollbar-thumb {
	height: 1%;
	/* 스크롤바의 길이 */
	background: #E1E1E1;
	/* 스크롤바의 색상 */

	border-radius: 10px;
}

.Basket_list::-webkit-scrollbar-track {
	background: rgba(33, 122, 244, .1);
	/*스크롤바 뒷 배경 색상*/
}

.buy_list {
	display: flex;
	flex-direction: row;
	align-items: center;
	/* justify-content: space-around; */
	width: 98%;
	height: 18%;
	border-radius: 10px;
	background: #FFF;
	margin: 0% 0% 1% 0%;
	flex-shrink: 0;
	box-shadow: 0px 4px 4px 2px rgba(0, 0, 0, 0.25);

}

.emptyRange {
	width: 100%;
	height: 1%;
	background-color: #FFFFFF;
	font-size: 1px;
}

.heartImg {
	margin-left: 2%;
}

.productImage {
	width: 8%;
	display: flex;
	margin: 0.5% 2%;
}

.pImage {
	width: 100%;
	height: 100%;
}

.product_description {
	width: 45%;
	font-size: 22px;
	font-weight: 600;
	text-align: left;
}

.wishlist_description {
	width: 60%;
	font-size: 22px;
	font-weight: 600;
	text-align: left;
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
	background-color: #80E1FF;
	filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
	border-radius: 50%;
	border: 0px;
	padding-bottom: 7px;
	margin: 0% 1%;
}

.plusBtn {
	background-color: #80E1FF;
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

.product_price {
	width: 15%;
	font-size: 24px;
	font-weight: bold;
	text-align: right;
	margin-right: 3%;
}

.trashBtn {
	border: 0;
	background-color: transparent;
}



.AllPriceText {
	margin-left: 40%;
	width: 10%;
	font-size: 30px;
}

.AllPrice {

	width: 45%;
	text-align: right;
	font-size: 30px;
}

.main_con_2 {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 390px;
	height: 98px;
	flex-shrink: 0;
	border-radius: 50px 0px 0px 50px;
	background: #D9D9D9;
	box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}

.main_user {
	margin-left: 20px;
	font-size: 20px;
	font-weight: 700;
}

/* * {
	border: 1px solid red;
} */

.hot-deals {
	display: flex;
	justify-content: center;
	width: 100%;
	height: 100%;
	padding: 15px 15px 15px 15px;
	background-color: #EEEEEE;
}

.keySearchBtnContainer {
	display: flex;
	flex-direction: column;
	width: 70%;
	height: 100%;
}

.deals-container {
	width: 100%;
	/* 모달 가로의 80%를 차지 */
	height: 100%;
	display: flex;
	flex-direction: column;
	/* overflow: auto; 내용이 많을 경우 스크롤바 표시 */
	padding-right: 10px;
	overflow-y: scroll;
	/* 항상 스크롤 바 표시 */
}

.deal-card {
	display: flex;
	/* justify-content: center; */
	/* align-items: center; */
	border: 1px solid #ccc;
	/* 상품 카드 테두리 */
	margin-bottom: 10px;
	/* 카드 간 간격 */
	background-color: #F5F5F5;
	border-radius: 10px;
	height: 20%;
	width: 100%;
}

.skyblue-background {
	background-color: #78EAEF;
}

.heartBtn {
	margin-left: 3%;
	margin-right: 3%;
	width: 5%;
	height: 40%;
	border: none;
	background-color: #F5F5F5;
}

.deal-image {
	margin-left: 10px;
	margin-top: 5px;
	width: 24%;
	/* 상품 이미지 크기 */
	height: 93%;
	/* 상품 이미지 크기 */
	object-fit: cover;
	/* 이미지 비율 유지 */
	border-radius: 50%;
}

.deal-info {
	padding: 10px;
	display: flex;
	flex-direction: column;
}

.productName {
	display: flex;
	align-items: center;
	font-size: 22px;
	font-weight: 600;
}

.jjimExplanation {
	margin-top: 5px;
	font-weight: 100;
	color: #939393;
	font-size: 15px;
}

.detailInfo {
	display: flex;
	align-items: center;
	flex-direction: column;
	width: 25%;
}

.redFont {
	display: flex;
	flex-direction: row;
	color: red;
	font-weight: 600;
	margin-top: 3%;
	font-size: 18px;

}

.fontMargin {
	margin-left: 5px;
	margin-bottom: 5px;
}

.stars {
	display: flex;
	flex-direction: row;
	color: #f4d211;
	/* 별 색상을 금색으로 설정 */
	font-size: 1.7em;
	/* 별 크기를 조정 */
	align-items: center;
	margin-top: -5px;
	/* filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25)); */

}

.starFont {
	margin-top: 7px;
	margin-left: 5px;
	color: black;
	font-size: large;
	font-weight: 550;
}

.review {
	margin-left: 10px;
	margin-top: 7px;
	color: gray;
	font-size: small;
}

.deal-pricing {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	/* 가격과 할인률을 양 끝으로 정렬 */
	margin-right: 30px;
	margin-left: 30px;
}

.original-price {
	text-decoration: line-through;
	/* 원래 가격에 줄 긋기 */
	color: #7A7A7A;
	font-size: 20px;
	font-weight: 600;
}

.discounted-price {
	color: black;
	/* 할인된 가격 강조 */
	font-size: 30px;
	font-weight: 600;
}</style>