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

    <h2>게시글 작성</h2>
    <div class="menu-box">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="email" class="form-control" id="title" placeholder="제목을 입력하세요" v-model="title">
        </div>
        <div class="mb-3">
            <label for="formFileMultiple" class="form-label">Picture</label>
            <br>
            <div v-if="imgList.length" style="display: inline-block;">
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
                v-model="content"></textarea>
        </div>
        <button class="btn" @click="createPost">작성</button>
    </div>

    
</template>

<script setup>
import { ref } from "vue";
import axios from 'axios';
import { useRoute } from "vue-router";
import router from "@/router";

const imgList = ref([]);
const imagePreviews = ref([]);
const title = ref('');
const content = ref('');
const selectedImg = ref('');
const route = useRoute();

const handleFileUpload = (event) => {
    const inputImg = ref([]);
    inputImg.value = Array.from(event.target.files);

    //forEach()로 순회하면서 push 하기
    inputImg.value.forEach((item)=>{
        imgList.value.push(item)
    })
    // 파일은 최대 3개까지 업로드 가능하므로, 길이는 3으로 제한
    if(imgList.value.length>3){
        alert("이미지는 3개까지 등록 가능합니다.")
        imgList.value.length=3;
    }
    imagePreviews.value = imgList.value.map(file => URL.createObjectURL(file));
};

const zoom = (imgSrc) => {
    selectedImg.value = imgSrc;
}

const deleteFile = (imageIdx) => {
    imgList.value.splice(imageIdx, 1);
    imagePreviews.value.splice(imageIdx, 1);
}

const createPost = () => {
    const formData = new FormData();
    formData.append('post', new Blob([JSON.stringify({
        title: title.value,
        content: content.value,
        memberId: sessionStorage.getItem("memberId"),
        videoId: route.params.videoId
    })], { type: 'application/json' }));
    
    for (let i = 0; i < imgList.value.length; i++) {
        formData.append("imgList", imgList.value[i]);
    }

    console.log(imgList.value)

    axios({
        method: 'post',
        url: 'http://localhost:8080/api-post/post',
        headers: { 'Content-Type': 'multipart/form-data' },
        data: formData
    }).then((response) => {
        // console.log(response);
        // 작성 되었다면 다시 videodetail로 돌아가자
        router.push(`/video/${route.params.videoId}`)
    })
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