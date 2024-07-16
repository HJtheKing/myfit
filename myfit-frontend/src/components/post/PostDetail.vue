<template>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title fs-5" id="exampleModalLabel">미리보기</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <img :src="selectedImg" style="width: 100%; height: 100%; display:block; margin: 0 auto;">
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <h2>게시글 상세</h2>
        <hr />
        <div class="d-flex justify-content-center">
            <div class="card" style="width: 30rem">
                <h2 class="card-title">{{ store.post.title }}</h2>
                <div class="wrapper">
                    <img v-if="store.post.imgSrc1" :src="imgURL(store.post.imgSrc1)" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="zoom(store.post.imgSrc1)" class="prev-img">
                    <img v-if="store.post.imgSrc2" :src="imgURL(store.post.imgSrc2)" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="zoom(store.post.imgSrc2)" class="prev-img">
                    <img v-if="store.post.imgSrc3" :src="imgURL(store.post.imgSrc3)" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="zoom(store.post.imgSrc3)" class="prev-img">
                </div>
                <div class="card-body">
                    <div class="mb-3 d-flex justify-content-between align-items-center">
                        <div class="d-flex justify-content-end">
                            <h4 class="card-subtitle mx-3 text-body-secondary">{{ store.post.writer }}</h4>
                            <h4 class="card-subtitle text-body-secondary">{{ store.post.regDate }}</h4>
                        </div>
                    </div>
                    <h5 class="card-text ">
                        {{ store.post.content }}
                    </h5>
                    <div class="d-flex justify-content-center" v-if="test">
                        <button class="mx-3 btn btn-outline-success" @click="moveUpdate">수정</button>
                        <button class="mx-3 btn btn-outline-danger" @click="deletePost">삭제</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, computed, onMounted } from "vue";
import { usePostStore } from "@/stores/post";
import axios from "axios";
const route = useRoute();
const router = useRouter();
const store = usePostStore();

onMounted(() => {
    store.getDetail(route.params.id);
});

const moveUpdate = function () {
    router.push({ name: "update" });
};

const test = computed(()=>{
    const writerId = store.post.memberId;
    const memberId = parseInt(sessionStorage.getItem("memberId"))
    return writerId === memberId
})

const deletePost = function () {
    store.deletePost(route.params.id, store.post.videoId)
};

const imgURL = (imgSrc)=>(`http://localhost:8080/img/${imgSrc}`)

const selectedImg = ref("")
const zoom = (imgSrc) => {
    selectedImg.value = `http://localhost:8080/img/${imgSrc}`;
}
</script>
  
<style scoped>
*{
    font: var(--base-pre-font);
}
h2{
    font: var(--xl-pre-font);
    padding: 16px;
}
h4{
    color: gray;
}
.prev-img {
        max-width: 140px;
        aspect-ratio: 1;
        border: 2px solid var(--surface-color);
        border-radius: 5px;
        margin: 0 8px;
    }
    .wrapper{
        display: flex;
        justify-content: center;
        padding: 16px;
    }
</style>
  