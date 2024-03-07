<template>
  <div class="locationBox" @click="closeModal">
    <div class="background">
      <!-- <div class="bodyPart" @click.stop=""> -->
        <SSAFYFloor />
      <!-- </div> -->
    </div>
    
  </div>
</template>

<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useFlagStore } from "@/stores/flag.js";
import SSAFYFloor from "./SSAFYFloorCopy.vue";
const flagSwitch = useFlagStore();
const {locationModalflag} = storeToRefs(flagSwitch);

const closeModal = ()=>{
  locationModalflag.value = false;
}

</script>

<style scoped>

.locationBox{
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  top:0;
  left:0;
  width: 1530px;
  height: 860px;
  background-color: rgba(0, 0, 0, 0.5);
}

.background {
  width: 75%;
  height: 75%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  border-radius: 15px;
}

.bodyPart {
    display: flex;
    height: 65%;
    justify-content: center;
}

</style>