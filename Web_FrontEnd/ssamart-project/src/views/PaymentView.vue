<template>
    <div class="wish-list-view">
      <div class="wish-list-header">
        <h1>결제 내역</h1>
      </div>
      <div class="wish-list-content">
        <RouterView />
      </div>
    </div>
  </template>
  
  <script setup>

  </script>
  
  <style scoped>
  .wish-list-view {
    font-family: "Gowun Dodum", sans-serif;
    font-weight: 700;
    font-style: normal;
    max-width: 1000px;
    margin: auto;
    padding: 20px;
    margin-top: 30px;
  }
  
  .wish-list-header {
    margin-bottom: 50px;
    text-align: center;
  }
  
  .wish-list-content {
    width: 100%;
    max-width: 1200px; /* 콘텐츠 영역의 최대 너비를 제한합니다. */
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  </style>
  