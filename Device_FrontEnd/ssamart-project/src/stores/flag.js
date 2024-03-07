import {defineStore} from 'pinia';
import {ref, computed} from 'vue';

export const useFlagStore = defineStore('useFlagStore', ()=>{
    
    
    const modalflag = ref(false)
    const callmodalflag = ref(false)
    const selectLocationModalflag = ref(false);
    const recommendmodalflag = ref(false)
    const plmodalflag = ref(false)
    const detailmodalflag = ref(false)
    const logoutmodalflag = ref(false)
    const screenIndex = ref(0);
    const FPloginFlag = ref(false);
    const productLocation = ref('box1');
    const currentModalProductSeq = ref(0);
    const activeButtonIndex = ref(0);
    const enrollflag = ref(0);
    const locationModalflag = ref(false);
    const menuPickflag = ref(false);
    const loadingFlag = ref(false);

    function modalflagChange(num){
        if(modalflag.value) modalflag.value = false;
        else modalflag.value = true;
    }

    function callmodalflagChange(){
        if(callmodalflag.value) callmodalflag.value = false;
        else callmodalflag.value = true;
    }

    function selectlocationmodalflagChange(){
        console.log('clicked');
        if(selectLocationModalflag.value) selectLocationModalflag.value = false;
        else selectLocationModalflag.value = true;
    }

    function logoutmodalflagChange(){
        if(logoutmodalflag.value) logoutmodalflag.value = false;
        else logoutmodalflag.value = true;
    }

    function recommendmodalflagChange(){
        if(recommendmodalflag.value) recommendmodalflag.value = false;
        else recommendmodalflag.value = true;
    }

    function plmodalflagChange(){
        if(plmodalflag.value) plmodalflag.value = false;
        else plmodalflag.value = true;
    }

    function detailmodalflagChange(){
        if(detailmodalflag.value) detailmodalflag.value = false;
        else detailmodalflag.value = true;
    }

    function menuPickflagChange(){
        if(menuPickflag.value) menuPickflag.value = false;
        else menuPickflag.value = true;
    }

    function viewLocation(num){
        productLocation.value = 'box' + num;
        console.log(productLocation.value);
        locationModalflag.value = true;
    }

    function activeButtonIndexChange(num){
        activeButtonIndex.value = num;
    }

    return {modalflag, callmodalflag, selectLocationModalflag, recommendmodalflag, plmodalflag, detailmodalflag, enrollflag, loadingFlag,
         screenIndex, FPloginFlag, productLocation, logoutmodalflag, currentModalProductSeq, activeButtonIndex, locationModalflag, menuPickflag,
         viewLocation, logoutmodalflagChange,
         modalflagChange, callmodalflagChange, selectlocationmodalflagChange, recommendmodalflagChange, plmodalflagChange, detailmodalflagChange, activeButtonIndexChange, menuPickflagChange}

})