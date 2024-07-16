<template>
    <div class="detail">
        <div>
            <button @click="unvalidate" class="btn btn-light"><i class="bi bi-arrow-left"></i>&nbsp; 뒤로 가기</button>
        </div>
        <iframe :src="videoURL" title="YouTube video player" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerpolicy="strict-origin-when-cross-origin" allowfullscreen>
        </iframe>
        <h1 v-html="store.selectedVideo.snippet.title"></h1>
        <p>{{ store.selectedVideo.snippet.channelTitle }}</p>
        <button class="btn btn-success" @click="createVideo">Video 추가하기</button>
    </div>
</template>

<script setup>
import { useVideoStore } from '@/stores/video';
import { ref, computed } from 'vue'

const store = useVideoStore();

const unvalidate = function () {
    store.unvalidate();
}

const videoURL = computed(() => {
    const videoId = store.selectedVideo.id.videoId
    return `https://www.youtube.com/embed/${videoId}`
})

const createVideo = () => {
    store.createVideo()
}
</script>

<style scoped>
* {
    font: var(--base-pre-font);
}

.detail {
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    padding: 24px 48px;
    margin: 0;
}

.btn {
    display: block;
    margin-bottom: 10px;
}

iframe {
    display: block;
    margin: 0 auto;
    width: 100%;
    aspect-ratio: 4/3;
    border-radius: 10px;
}

h1 {
    padding-top: 16px;
    font: var(--xl-pre-font);
}

p {
    font: var(--large-pre-font);
}
</style>
