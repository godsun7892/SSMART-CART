<template>
  <div v-if="isVisible" class="modal-overlay" @keyup.esc="closeModal">
    <div class="modal" @click.stop>
      <slot></slot>
      <button class="close-btn" @click="closeModal">&times;</button>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
name: 'ModalComponent',
setup() {
  const isVisible = ref(false);

  const showModal = () => {
    isVisible.value = true;
  };

  const closeModal = () => {
    isVisible.value = false;
  };

  return {
    isVisible,
    showModal,
    closeModal
  };
}
};
</script>

<style scoped>
.modal-overlay {
position: fixed;
top: 0;
left: 0;
width: 100%;
height: 100%;
background-color: rgba(0, 0, 0, 0.6);
display: flex;
justify-content: center;
align-items: center;
z-index: 5000;
opacity: 0;
animation: fadeIn 0.3s forwards;
}

@keyframes fadeIn {
to {
  opacity: 1;
}
}

.modal {
position: relative;
background-color: white;
padding: 40px;
border-radius: 12px;
width: 90%;
max-width: 500px;
box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
transform: scale(0.9);
animation: scaleIn 0.2s forwards;
}

@keyframes scaleIn {
to {
  transform: scale(1);
}
}

.close-btn {
position: absolute;
top: 10px;
right: 15px;
font-size: 1.5rem;
background: none;
border: none;
cursor: pointer;
color: #666;
}

.close-btn:hover {
color: #222;
}
</style>
