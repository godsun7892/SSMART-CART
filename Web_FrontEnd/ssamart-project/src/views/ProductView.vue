<template>
    <div>
      <RouterView />
    </div>
  </template>
  
  <script setup></script>
  
  <style scoped>
  body{
    background-color: #fff;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: inherit; /* 기본 글꼴을 상속하지 않음 */
    font-size: 1rem; /* 원하는 글꼴 크기로 설정 */
    line-height: 1.5; /* 원하는 줄 높이로 설정 */
    color: inherit; /* 기본 글꼴 색을 상속하지 않음 */
    background-color: transparent; /* 배경색을 투명으로 설정 */
  }
  
  
  
  .product-link {
    color: black;
  }
  
  .view {
    width: 80%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    color: #787878;
    font-size: medium;
  }
  
  label {
    display: inline-block;
    width: 130px;
  }
  
  .btn {
    width: 8%;
    background-color: #d0d3d0;
    color: rgb(80, 82, 79);
    padding: 14px 20px;
    margin: 8px;
    border: 1px solid #787878;
    border-radius: 4px;
    font-size: large;
    cursor: pointer;
  }
  
  /* 테이블 CSS */
  .product-list {
    border-collapse: collapse;
    width: 100%;
    text-align: center;
    margin: auto;
  }
  
  .product-list td,
  .shoes-list th {
    border: 1px solid black;
  }
  </style>
  