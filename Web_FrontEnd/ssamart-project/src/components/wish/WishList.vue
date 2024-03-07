<template>
  <div class="wish-lists-container" :key="componentKey">
    <h1>내 찜 목록</h1>
    <div class="add-wish-list">
      <input type="text" v-model="newWishListName" placeholder="새 찜 목록 이름"  @keyup.enter="addWishList(newWishListName)"/>
      <button @click="addWishList(newWishListName)">추가</button>
    </div>
    <div v-if="wishLists.length > 0" class="wish-lists-grid">
      <div v-for="wishList in wishLists" :key="wishList.seq" class="wish-list-card" @click="navigateToWishListDetail(wishList.seq)">
        <div class="wish-list-info">
          <h3>{{ wishList.name }}</h3>
        </div>
        <div class="wish-list-actions">
          <button @click.stop="updateWishListName(wishList.seq)">수정</button>
          <button @click.stop="removeWishList(wishList.seq)">삭제</button>
        </div>
      </div>
    </div>
    <div v-else>
      찜 목록이 없습니다.
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { useWishStore } from '@/stores/wishListStore';
import { useRouter } from 'vue-router';

const { wishLists, fetchWishLists, addWishList, removeWishList, updateWishListName } = useWishStore();
const newWishListName = ref('')
const router = useRouter();
// 컴포넌트 키를 관리하는 상태


const navigateToWishListDetail = (seq) => {
  router.push({ name: 'WishListDetail', params: { seq }});
}

// onMounted(() => {
//   fetchWishLists();
// });

</script>

<style scoped>
.wish-lists-container {
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
  max-width: 1200px;
  margin: auto;
  padding: 20px;
}

.wish-lists-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 20px;
  
}

.wish-list-card {
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 30px;
  box-shadow: 0 1px 2px rgba(0,0,0,0.2);
  transition: box-shadow 0.5s;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
}

.wish-list-card:hover {
  box-shadow: 0 2px 8px rgba(0,0,0,0.5);
}

.wish-list-info h3 {
  margin: 0;
  cursor: pointer;
}

.wish-list-info h3:hover {
  transform: scale(1.2);
  transition-duration: 0.3s;
}

.wish-list-actions button {
  margin-left: 15px;
  padding: 10px 18px;
  border-radius: 20px;
  cursor: pointer;
}
.wish-list-actions button:first-child {
  background-color: white; /* 수정 버튼 색상 */
  color: rgb(6, 92, 34);
  border: 1px solid #4CAF50;
  transition-duration: 0.5s;
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
}

.wish-list-actions button:first-child:hover {
  background-color: #4CAF50;
  color: white;
  transition-duration: 0.5s;

}

.wish-list-actions button:last-child {
  background-color: white; /* 삭제 버튼 색상 */
  color: rgb(121, 12, 12);
  border: 1px solid #f44336;
  transition-duration: 0.5s;
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 600;
  font-style: normal;
}

.wish-list-actions button:last-child:hover {
  background-color: #f44336;
  color: white;
  transition-duration: 0.5s;
}

.add-wish-list {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 30px;
}

.add-wish-list input {
  padding: 20px 100px;
  margin-right: 20px;
  border: 1px solid #aaa;
  border-radius: 20px;
}

.add-wish-list input::placeholder {
  color: #bbb;
}

.add-wish-list button {
  font-family: "Gowun Dodum", sans-serif;
  font-style: normal;
  padding: 15px 30px;
  background-color: #78EAEF;
  font-weight: 700;
  font-size: 15px;
  color: white;
  border : none;
  border-radius: 20px;
  cursor: pointer;
  transition-duration: 0.3s;
}

.add-wish-list button:hover {
  font-weight: 800;
  background: white;
  color: #78EAEF;
  border: 1px solid #78EAEF;
  transition-duration: 0.3s;
}
</style>
