<template>
    <main>
        <YoutubeVideoDetail v-if="store.selectedVideo"/>
        <div v-else="store.selectedVideo">
            <YoutubeVideoSearch/>
            <hr>
            <YoutubeVideoList/>
        </div>
    </main>
</template>

<script setup>
    import { useVideoStore } from '@/stores/video';
    import YoutubeVideoSearch from '@/components/video/YoutubeVideoSearch.vue';
    import YoutubeVideoList from '@/components/video/YoutubeVideoList.vue';
    import YoutubeVideoDetail from '@/components/video/YoutubeVideoDetail.vue';
    
    const store = useVideoStore();

</script>

<style scoped>
    main{
        display: block;
        padding-bottom: 70px;
    }
</style>