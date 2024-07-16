<template>
    <div class="col">
    <li class="card my-2">
        <RouterLink :to="`/video/${video.videoId}`">
        <img :src="url" class="card-img-top" alt="thumbnail">
        <div class="card-body">
            <h5 class="card-title" v-html="video.title"></h5>
            <p class="card-text">{{ video.channelName }}&nbsp;&nbsp;|&nbsp;&nbsp;<i class="bi bi-eye"></i>&nbsp;{{ video.viewCnt }}&nbsp;&nbsp;|&nbsp;&nbsp;<span class="card-text">{{ video.exerciseArea }}</span></p>
        </div>
        </RouterLink>
    </li>
    </div>
</template>

<script setup>
    import { ref } from 'vue'
    import { useVideoStore } from '@/stores/video';

    const store = useVideoStore();
    const props = defineProps({
        video: {
            type: Object,
            required: true
        }
    })
    let url = `https://i.ytimg.com/vi/${props.video.youtubeId}/hqdefault.jpg`
    const clickVideo = function(video){
        store.clickVideo(video)
    }
</script>

<style scoped>
    *{
        font: var(--base-pre-font);
    }
    h5{
        font: var(--lg-pre-font);
    }
    a{
        margin: 0 auto;
        text-decoration: none;
        color: black;
    }
    .btn{
        display: block;
        margin: 0 auto;
        background-color: var(--action-color);
        border: none;
        color: black
    }
</style>