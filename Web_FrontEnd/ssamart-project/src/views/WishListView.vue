<template>
    <div class="wish-list-view">
      <div class="wish-list-header">
        <h1>찜 리스트</h1>
      </div>
      <div class="wish-list-content">
        <RouterView />
      </div>
    </div>
  </template>
  
  <script setup>
  // 여기서는 특별한 스크립트 로직이 필요하지 않을 수 있습니다.
  // 필요한 경우 여기에 Vue Composition API 로직을 추가하세요.
  </script>
  
  <style scoped>
  .wish-list-view {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    background-color: #f5f5f5;
  }
  
  .wish-list-header {
    margin-bottom: 20px;
  }
  
  .wish-list-content {
    width: 100%;
    max-width: 800px; /* 콘텐츠 영역의 최대 너비를 제한합니다. */
  }
  </style>
  