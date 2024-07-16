<template>
    <div class="scroll-content">
        <ul class="row row-cols-1 row-cols-md-2 g-4">
            <YoutubeVideoListItem v-for="video in store.youtubeVideos" :key="video.id.videoId" :video="video"/>
        </ul>
    </div>
    <div class="blank"></div>
</template>

<script setup>
    import { onMounted } from 'vue';
    import { useVideoStore } from '@/stores/video';
    import YoutubeVideoListItem from './YoutubeVideoListItem.vue';

    const store = useVideoStore();
</script>

<style scoped>
    .scroll-content{
        max-width: 1200px;
        width: 95%;
        height: 80%;
        margin: 0 auto 100px;
        overflow-y: scroll;
        padding: 0 10px 70px 0;
        box-sizing: border-box;
        position: absolute;
    }
    ul{
        padding: 0;
        width: 90%;
        margin: 0 auto;
    }
    .blank{
        height: 70px;
    }

    .scroll-content::-webkit-scrollbar {
        position: absolute;
        left: -20px;
        width: 15px;
    }

    .scroll-content::-webkit-scrollbar-track {
        background: #ffffff;
    }

    .scroll-content::-webkit-scrollbar-thumb {
        background: #ececec;
        border-radius: 10px;
    }

    .scroll-content::-webkit-scrollbar-thumb:hover {
        background: #555;
    }
</style>