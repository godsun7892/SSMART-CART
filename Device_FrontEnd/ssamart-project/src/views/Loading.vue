<template>
    <div class="loading-container">
        <div class="logo">
            <img src="/image/logo.png">
        </div>
        <div class="gif">
            <img src="/image/loadingGif.gif">
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
.loading-container {
    width: 100%;
    height: 100%;
    background:none;
    display: flex;
    background-color: rgba(0, 0, 0, 0.08);
    flex-direction: column;
    justify-content: center;
    align-items: center;
    z-index: 3;
    margin : 0;
}

.logo {
    margin-left: -100px;
    margin-bottom: 50px;
}
</style>