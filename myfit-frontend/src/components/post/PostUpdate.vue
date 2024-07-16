<template>
    <!-- 모달 달기 -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title fs-5" id="exampleModalLabel">미리보기</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <img :src="selectedImg" style="max-width: 100%; height: auto;">
                </div>
            </div>
        </div>
    </div>

    <h2>게시글 수정</h2>
    <div class="menu-box">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요" v-model="store.post.title">
        </div>
        <div class="mb-3">
            <label for="formFileMultiple" class="form-label">Picture (이미지는 최대 3장까지 게시 가능합니다.)</label>
            <br>
            <img v-if="store.post.imgSrc1" :src="imgURL(store.post.imgSrc1)" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="zoom(`http://localhost:8080/img/${store.post.imgSrc1}`)" class="prev-img">
            <button v-if="store.post.imgSrc1" class="delete-btn" @click="deleteSavedFile(1)"><i class="bi bi-trash"></i></button>
            <img v-if="store.post.imgSrc2" :src="imgURL(store.post.imgSrc2)" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="zoom(store.post.imgSrc2)" class="prev-img">
            <button v-if="store.post.imgSrc2" class="delete-btn" @click="deleteSavedFile(2)"><i class="bi bi-trash"></i></button>
            <img v-if="store.post.imgSrc3" :src="imgURL(store.post.imgSrc3)" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="zoom(store.post.imgSrc3)" class="prev-img">
            <button v-if="store.post.imgSrc3" class="delete-btn" @click="deleteSavedFile(3)"><i class="bi bi-trash"></i></button>
            <div v-if="imagePreviews.length" style="display: inline-block;">
                <!-- <h3>업로드할 이미지</h3> -->
                <div v-for="(image, index) in imagePreviews" :key="index" class="prev-wrapper">
                    <img :src="image" alt="Uploaded Image" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="zoom(image)" class="prev-img">
                    <button class="delete-btn" @click="deleteFile(index)"><i class="bi bi-trash"></i></button>
                </div>
            </div>
            <div class="prev-wrapper">
                <label for="formFileMultiple" class="form-label btn-add">+</label>
            </div>
            <input type="file" id="formFileMultiple" @change="handleFileUpload" multiple>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">Content</label>
            <textarea class="form-control" id="content" placeholder="내용을 입력하세요" style="height: 200px"
                v-model="store.post.content"></textarea>
        </div>
        <button class="btn" @click="updatePost">수정하기</button>
    </div>
</template>

<script setup>
import { usePostStore } from "@/stores/post";
import { ref, onBeforeMount } from "vue";
import axios from "axios";
import { useRouter } from 'vue-router';

const router = useRouter();
const imgURL = (imgSrc) => `http://localhost:8080/img/${imgSrc}`;

const store = usePostStore();

const imgList = ref([]);
const imagePreviews = ref([]);
const selectedImg = ref('');

const handleFileUpload = (event) => {
    const inputFiles = Array.from(event.target.files);

    imgList.value.push(...inputFiles);
    
    // 파일은 최대 3개까지 업로드 가능하므로, 길이는 3으로 제한
    if (imgList.value.length > 3) {
        imgList.value.length = 3;
    }
    imagePreviews.value = imgList.value.map(file => URL.createObjectURL(file));
};

const zoom = (imgSrc) => {
    selectedImg.value = imgSrc;
};

const deleteFile = (imageIdx) => {
    imgList.value.splice(imageIdx, 1);
    imagePreviews.value.splice(imageIdx, 1);
};

const deleteSavedFile = (imgNum) => {
    switch(imgNum) {
        case 1: store.post.imgSrc1 = null; break;
        case 2: store.post.imgSrc2 = null; break;
        case 3: store.post.imgSrc3 = null; break;
    }
};

const updatePost = () => {
    const formData = new FormData();
    if(store.post.title.length > 30) { 
        store.post.title = store.post.title.substr(0, 30)
    }
    formData.append('post', new Blob([JSON.stringify({
        postId: store.post.postId,
        title: store.post.title,
        content: store.post.content,
        memberId: sessionStorage.getItem("memberId"),
        videoId: store.post.videoId,
        imgSrc1: store.post.imgSrc1,
        imgSrc2: store.post.imgSrc2,
        imgSrc3: store.post.imgSrc3
    })], { type: 'application/json' }));
    
    for (let i = 0; i < imgList.value.length; i++) {
        formData.append("imgList", imgList.value[i]);
    }

    axios({
        method: 'put', // PUT 메서드를 사용합니다.
        url: `http://localhost:8080/api-post/post/${store.post.postId}`,
        headers: { 'Content-Type': 'multipart/form-data' },
        data: formData
    }).then((response) => {
        router.push(`/video/${store.post.videoId}`);
    }).catch((error) => {
        console.error("Failed to update post:", error);
    });
};
</script>

<style scoped>
* {
    font: var(--base-pre-font);
}

h2 {
    font: var(--xl-pre-font);
    padding-top: 16px;
}

.menu-box {
    border: 2px #7CBBF3 solid;
    background-color: #fff;
    padding: 16px;
    border-radius: 10px;
    margin-bottom: 24px;
}

button {
    border: none;
    border-radius: 8px;
    background-color: #7CBBF3;
    font: var(--sm-pre-font);
    margin-right: 0;
}

.prev-wrapper {
    display: inline-block;
}
.btn-add {
    display: inline-block;
    text-align: center;
    width: 80px;
    height: 80px;
    line-height: 80px;
    padding: 0;
    border: 2px solid var(--surface-color);
    border-radius: 5px;
    cursor: pointer;
    margin: 0;
}

#formFileMultiple {
    display: none;
}

.prev-img {
    width: 80px;
    height: 80px;
    border: 2px solid var(--surface-color);
    border-radius: 5px;
}

.delete-btn {
    border-radius: 100%;
    background-color: var(--surface-color);

    position: relative;
    top: -35px;
    left: -15px;
}
</style>
