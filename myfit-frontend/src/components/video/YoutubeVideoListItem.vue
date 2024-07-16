<template>
    <div class="col">
    <li class="card my-2">
        <img :src="video.snippet.thumbnails.high.url" class="card-img-top" alt="thumbnail">
        <div class="card-body">
            <h5 class="card-title" v-html="video.snippet.title"></h5>
            <p class="card-text">{{ video.snippet.channelTitle }}</p>
            <button @click="clickVideo(video)" class="btn btn-primary">상세보기</button>
        </div>
    </li>
    </div>
</template>

<script setup>
    import { useVideoStore } from '@/stores/video';
    import { defineProps } from 'vue';

    const store = useVideoStore();

    const props = defineProps({
        video: {
            type: Object,
            required: true
        }
    })

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
    }
    .btn{
        display: block;
        margin: 0 auto;
        background-color: var(--action-color);
        border: none;
        color: black
    }
</style>