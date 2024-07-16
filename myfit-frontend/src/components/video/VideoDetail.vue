<template>
    <div class="wrapper">
        <div class="detail">
            <iframe :src="videoURL" title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                referrerpolicy="strict-origin-when-cross-origin" allowfullscreen>
            </iframe>
            <div class="infos" v-if="store.selectedDBVideo">
                <div>
                    <h1>{{store.selectedDBVideo.title}}</h1>
                    <p>{{ store.selectedDBVideo.channelName }}&nbsp;&nbsp;|&nbsp;&nbsp;<i class="bi bi-eye"></i>&nbsp;{{ store.selectedDBVideo.viewCnt }}</p>
                </div>
                <div class="like">
                    <div class="heart">
                        <i v-if="store.selectedDBVideo.like" class="bi bi-heart-fill" @click="unlike()"></i>
                        <i v-else class="bi bi-heart" @click="like()"></i>
                    </div>
                    <p>{{ store.selectedDBVideo.likeCnt }}</p>
                </div>
            </div>
        </div>
        <div class="posts">
            <VideoBoard></VideoBoard>
        </div>
    </div>
</template>

<script setup>
    import { useRoute } from "vue-router";
    import { useVideoStore } from '@/stores/video';
    import { ref, computed, onMounted } from 'vue'
    import VideoBoard from '@/components/video/VideoBoard.vue'

    import router from "@/router";
    import axios from "axios";
    
    const route = useRoute();
    const store = useVideoStore();
    const videoURL = ref('');

    onMounted(async () => {
        await store.getDetail(route.params.id);
        videoURL.value = `https://www.youtube.com/embed/${store.selectedDBVideo.youtubeId}`
    });



    const like = ()=>{
        axios.post('http://localhost:8080/api-video/video/like', {
            memberId: sessionStorage.getItem('memberId'),
            videoId: route.params.id
        })
        .then(()=>{
            store.selectedDBVideo.like = true;
            store.selectedDBVideo.likeCnt++;
        })
        .catch(()=>{

        })
    }

    const unlike = ()=>{
        axios.delete('http://localhost:8080/api-video/video/like', {
            params: {
                videoId : route.params.id,
                memberId: sessionStorage.getItem("memberId")
            }
        })
        .then(()=>{
            store.selectedDBVideo.like = false;
            store.selectedDBVideo.likeCnt--;
        })
        .catch(()=>{

        })
    }
</script>

<style scoped>
    *{
        font: var(--base-pre-font);
    }
    .wrapper{
        display: flex;
        box-sizing: border-box;
        padding: 24px 48px;
        margin: 0;
    }
    .detail{
        display: flex;
        flex-direction: column;
        flex-basis: 800px;
        padding: 16px;
    }
    .infos{
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .like{
        padding: 8px;
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: center;
    }
    .posts{
        flex-basis: 400px;
    }
    .btn{
        display: block;
        margin-bottom: 10px;
    }
    iframe{
        display: block;
        margin: 0 auto;
        width: 100%;
        aspect-ratio: 16/9;
        border-radius: 10px;
    }
    h1{
        font: var(--xl-pre-font);
        margin: 16px 0;
    }
    p{
        font: var(--lg-pre-font);
        margin: 0;
    }
    .like{
        margin-left: 16px;
    }
    .like > p {
        width: 50px;
        text-align: center;
    }
    .bi{
        scale: 180%;
        font-size: x-large;
    }
    .bi-heart-fill{
        color: red;
    }
</style>

