<template>
    <div>
        <h2>댓글 {{ store.postCnt }}개</h2>
        <ul>
            <VideoBoardItem v-for="post in store.postList" :key="post.postId" :post="post"/>
        </ul>
        <RouterLink :to="{name: 'write', params: {videoId : route.params.id}}" class="btn">작성하기</RouterLink>
    </div>
</template>

<script setup>
    import { usePostStore } from '@/stores/post';
    import VideoBoardItem from './VideoBoardItem.vue';
    import { useRoute } from 'vue-router';
    import { onMounted } from 'vue';
    
    const route = useRoute();
    const store = usePostStore();

    onMounted(()=>{
        store.getPostListByVideoId(route.params.id)
    })
</script>

<style scoped>
    *{
        font: var(--base-pre-font)
    }
    div{
        box-sizing: border-box;
        padding: 16px 8px;
        overflow-y: scroll;
    }
    h2{
        font: var(--xl-pre-font);
    }
    ul{
        padding: 0;
    }
    .btn{
        display: block;
        margin: 0 auto;
        width: 100%;
        background-color: var(--action-color);
    }
</style>