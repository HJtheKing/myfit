<script setup>
import ViewCntList from "@/components/common/ViewCntList.vue";
import RecommandList from "@/components/common/RecommandList.vue";
import { useVideoStore } from "@/stores/video";
import { onMounted } from "vue";

const store = useVideoStore();
const memberId = sessionStorage.getItem("memberId");

onMounted(() => {
  if (memberId) {
    store.getExerciseAreaList(memberId);
  }
  store.getVideo();
});
</script>

<template>
  <main>
    
    <br />
    <ViewCntList></ViewCntList>
    <br />
    <RecommandList></RecommandList>
  </main>
</template>

<style scoped>
  *{
    /* margin: 0 auto; */
    font: var(--sm-pre-font);
    color: black;
  }
  main {
    overflow-y: scroll;
    width: 100%;
    padding-bottom: 86px;
    display: flex;
    flex-direction: column;
  }
  .carousel-indicators{
    margin-bottom: 0;
  }
  .carousel-caption{
    padding: 0;
  }
  .carousel img{
    aspect-ratio: 6;
    margin: 0 auto;
    max-height: 130px;
    height: 100%;
  }
  h2{
    font: var(--xl-pre-font);
    max-width: 1200px;
    padding: 8px 20px 0;
    text-align: left;
  }
  h4{
    font: var(--xl-base-font);
    max-width: 1200px;
    padding: 0 48px 0;
    text-align: left;
  }
  strong{
    font-size: 1.2em;
  }
  h5{
    width: fit-content;
    margin: 0 auto;
    padding: 8px 16px;
    font: var(--lg-pre-font);
    background-color: rgba(255, 255, 255, 0.7);
    border-radius: 8px;
  }
</style>