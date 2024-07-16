<template>
    <div>
        <h1> 영상 검색하기 </h1>
        <input type="text" v-model="store.keyword" @keypress.enter="search" placeholder="검색어를 입력하세요">
        <button class="btn" @click="search">검색</button>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import { useVideoStore } from '@/stores/video'

    const store = useVideoStore();

    const search = function() {
        store.youtubeSearch()
    }
</script>

<style scoped>
    *{
        font: var(--base-pre-font);
        margin: 0 auto;
        text-align: center;
    }
    div{
        margin: 0 auto;
        padding-top: 16px;
    }
    h1{
        font: var(--lg-pre-font);
        height: 0;
        margin: 0;
        text-align: left;
    }
    input[type="text"]{
        text-align: left;
        padding: 0 8px;
    }
</style>