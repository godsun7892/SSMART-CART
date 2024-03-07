<template>
    <div class="MainStageHeader">
        <img src="/image/logo.png" class="MainLogo" @click="loginStore.logout()">
        <div class="adBanner">
            <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                    <img src="/image/image 63.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                    <img src="/image/image 63.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                    <img src="/image/image 63.png" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useLoginStore } from '@/stores/login';
const loginStore = useLoginStore();

</script>

<style scoped>
.MainStageHeader{
    display: flex;
    flex-direction: row;
    /* background-color:darkcyan; */
    width:100%;
    height: 15%;
    align-items: center;
}

.MainLogo{
    width:30%;
    margin: 0% 0%;
}

.adBanner{
    width:70%;
    margin:1% 0% 1% 0%;
}

</style>