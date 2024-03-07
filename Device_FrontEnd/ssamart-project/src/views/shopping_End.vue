<template>
    <div class="MainPage">
         <div class="MainStage">
             <div class="MainStageHeader">
                 <div class="MainLogo"><img src="/image/logo.png" alt=""></div>
                 <button class="gohomeBtn" @click="logout">
                    <span class="homeBtnText">Home Screen</span>
                </button>
             </div>
             <div class="MainStageBody">

             </div>
         </div>
     </div>
 </template>
 
 <script setup>
import { useLoginStore } from '@/stores/login';
import { useCartStore } from '@/stores/cart';
const loginStore = useLoginStore();
const cartStore = useCartStore();
const logout = () => {
    cartStore.deleteAllProduct();
    loginStore.logout();
    cartStore.cartClean();
}

 </script>
 
 <style scoped>
 .MainPage{
     display: flex;
     flex-direction: row;
     /* background-color: antiquewhite; */
     width: 100%;
     height: 100%;
 }
 
 .MainStage{
     display: flex;
     flex-direction: column;
     /* background-color:aquamarine; */
     width:100%;
     height: 100%;
     justify-content: center;
 }
 .MainStageHeader{
     display: flex;
     flex-direction: row;
     /* background-color:darkcyan; */
     width:100%;
     height: 15%;
     align-items: center;
     justify-content: space-between;
 }
 
 .MainLogo{
     margin: 0% 1%;
 }

.MainStageBody{
    width:100%;
    height: 85%;
}


.gohomeBtn{
    width:30%;
    height:100%;
    padding:0% 1%;
    border-radius: 20px;
}

.homeBtnText{
    width: 100%;
    font-size: 30px;
}

 </style>