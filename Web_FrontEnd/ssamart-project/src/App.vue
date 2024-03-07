<template>
  <div>
    <HeaderNav v-if="shouldShowHeader"/>
    <RouterView />
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import HeaderNav from '@/components/common/HeaderNav.vue'

const route = useRoute()

const shouldShowHeader = computed(() => {
  return !route.meta.hideHeader;
})



</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  font-family: Arial, Helvetica, sans-serif;
}
.text-center {
  text-align: center;
}
.container {
  margin: 0px 30px;
}
</style>