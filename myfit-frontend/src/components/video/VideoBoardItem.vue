<template>
    <RouterLink :to="`/post/${post.postId}`">
    <article>
        <div class="wrapper">
            <img v-if="post.imgSrc1" :src="imgURL(post.imgSrc1)">
            <img v-if="post.imgSrc2" :src="imgURL(post.imgSrc2)">
            <img v-if="post.imgSrc3" :src="imgURL(post.imgSrc3)">
        </div>
        <h3>{{ post.title }}</h3>
        <h4>{{ post.writer }} <span>{{ post.regidate }}</span></h4>
        <h5>{{ post.content }}</h5>
    </article>
    </RouterLink>
</template>

<script setup>
import { ref } from 'vue';


    const props = defineProps({
        post: {
            type: Object,
            required: true
        }
    })
    // console.log(props.post)
    const imgURL = (imgSrc)=>(`http://localhost:8080/img/${imgSrc}`)
</script>

<style scoped>

    *{
        font: var(--base-pre-font);
    }
    a{
        text-decoration: none;
        color: black;
    }
    article{
        border-bottom: 1px var(--surface-color) solid;
        box-sizing: border-box;
        padding: 16px;
    }

    article:hover{
        background-color: #ececec;
    }
    span{
        font: var(--sm-pre-font);
        font-weight: 400;
        color: var(--surface-color);
    }
    h3{
        font: var(--lg-pre-font);
    }
    h5{
        font: var(--sm-pre-font);
    }
    img {
        max-width: 80px;
        aspect-ratio: 1;
        border: 2px solid var(--surface-color);
        border-radius: 5px;
        margin-right: 8px;
    }
    .wrapper{
        display: flex;
        justify-content: start;
        padding-bottom: 8px;
    }
</style>