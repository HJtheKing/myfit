// 추후 video DB 구축되면, Video로 변경하여 사용할 것!

import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_POST_API = `http://localhost:8080/api-post/post`

export const usePostStore = defineStore('post', () => {
    const postList = ref([]);
    const post = ref({})
    // 전체 게시글 리스트
    const getPostList = function () {
        axios.get(REST_POST_API)
        .then((response) => {
          // console.log('실행은 됨?')
          postList.value = response.data
        })
    }

    // 특정한 videoId를 보냈을 때, postList를 갱신할 수 있는 메서드
    const getPostListByVideoId = (videoId)=>{
      axios.get(`http://localhost:8080/api-post/video/${videoId}`)
      .then((response) => {
        // console.log('실행은 됨?')
        postList.value = response.data
      })
  }


    // 전체 개수
    const postCnt = computed(()=>{
            return postList.value.length;
        }
    )

    // memberId 가져다 쓸 때 사용 (return 필요 X)
    const memberId = sessionStorage.getItem("memberId")

    // 게시글 상세 조회
    const getDetail = function (id) {
      axios.get(`${REST_POST_API}/${id}`)
        .then((response) => {
        post.value = response.data
      })
    }

    // 게시글 수정
    const updatePost = function () {
        axios.put(`REST_POST_API/${post.value.postId}`, post.value)
          .then(() => {
          router.push(`post/${post.value.postId}`)
        })
    }
    
    // 게시글 삭제
    const deletePost = function (id, videoId) {
      if (post.value.memberId !== parseInt(sessionStorage.getItem('memberId'))) {
        alert('삭제는 작성자만 할 수 있습니다.')
        router.push({name: 'home'});
        return;
      }
        axios.delete(`${REST_POST_API}/${id}`, post.value)
          .then(() => {
          router.push(`/video/${videoId}`)
        })
    }

    // 게시글 검색
    const searchPostList = function (searchCondition) {
        axios.get(REST_POST_API, {
          params: searchCondition
        })
          .then((res) => {
          postList.value = res.data
        })
      }
    return { postList, post, getPostList, getPostListByVideoId, postCnt, getDetail, updatePost, deletePost, searchPostList }
})