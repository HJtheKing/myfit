<template>
    <div class="text-center">
        <div class="row align-items-center wrapper">
            <div class="col-2">
                <select class="form-select" v-model="searchInfo.key">
                    <option value="none">-검색어-</option>
                    <option value="title">제목</option>
                    <option value="exercise_area">운동부위</option>
                    <option value="channel_name">채널명</option>
                </select>
            </div>
            <div class="col-5">
                <input type="text" class="form-control" v-model="searchInfo.word" :placeholder="placeholder" @keydown.enter="searchVideoList" :disabled="isDisable">
            </div>
            <div class="col-4">
                <div class="form-check form-check-inline">
                    <input v-model="searchInfo.orderBy" class="form-check-input" type="radio" id="title" value="title"/>
                    <label class="form-check-label" for="title">제목</label>
                </div>
                <div class="form-check form-check-inline">
                    <input v-model="searchInfo.orderBy" class="form-check-input" type="radio" id="likeCnt" value="like_cnt" />
                    <label class="form-check-label" for="likeCnt">좋아요</label>
                </div>
                <div class="form-check form-check-inline">
                    <input v-model="searchInfo.orderBy" class="form-check-input" type="radio" id="viewCnt"
                        value="view_cnt" />
                    <label class="form-check-label" for="viewCnt">조회수</label>
                </div>
                <div @click="sortChange">
                    <span class="btn btn-outline-info" v-if="searchInfo.orderByDir === 'asc'">
                        오름차순
                    </span>
                    <span class="btn btn-outline-info" v-else>
                        내림차순
                    </span>
                </div>
            </div>
            <div class="col-1">
                <button class="btn btn-search" @click="searchVideoList">검색</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useVideoStore } from '@/stores/video';
import axios from 'axios';
import { computed, ref } from 'vue';


const searchInfo = ref({
    key: 'none',
    word: '',
    orderBy: 'none',
    orderByDir: 'asc'
})

const store = useVideoStore()
const placeholder = ref("")
const isDisable = computed(()=>{
    if(searchInfo.value.key==='none'){
        placeholder.value = '좌측에서 검색어를 선택해주세요.'
        return true
    }else{
        placeholder.value = '검색어를 입력하세요'
        return false
    }
})

const searchVideoList = function () {
    store.searchVideoList(searchInfo.value)
}
const sortChange = function () {
    if (searchInfo.value.orderByDir === "asc")
        searchInfo.value.orderByDir = "desc";
    else searchInfo.value.orderByDir = "asc";
};
</script>

<style scoped>

*{
    font: var(--base-pre-font);
}
.wrapper{
    width: 100%;
    padding-bottom: 16px;
}
.col-4{
    display: flex;
}
.col-4 > div{
    display: flex;
    align-items: center;
}
label{
    margin: 0 0 0 8px;
}
.btn{
    color: black;
}
.btn-search {
    background-color: var(--action-color);
    color: white;
}
</style>