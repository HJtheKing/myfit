<template>
    <article>
        <h2><strong>⭐</strong> MYFIT 추천 운동영상 <strong>⭐</strong></h2>
          <h4>내 몸에 맞는 이 운동은 어때요?</h4>
        <div class="row">
            <VideoInfo v-for="video in store.videoListByPart" :key="video.videoId" :video-detail="video"></VideoInfo>
        </div>
    </article>
</template>

<script setup>
    import { ref, onMounted, onBeforeMount, onUpdated, watchEffect } from 'vue'
    import { useVideoStore } from '@/stores/video';
    import VideoInfo from './VideoInfo.vue'

    const store = useVideoStore();
    const part = store.exerciseAreaList
    // watchEffect(()=>{
    //     // console.log(part)
    // })
</script>

<style scoped>
    *{
    /* margin: 0 auto; */
    font: var(--sm-pre-font);
    color: black;
    }
    article {
        padding: 0 24px;
    }
    div{
        padding: 0 16px;
    }
    .carousel img{
    aspect-ratio: 3;
    margin: 0 auto;
    max-height: 200px;
    }
    h2{
    font: var(--xl-pre-font);
    max-width: 1200px;
    padding: 8px 0 0;
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
    }
</style>