// 추후 video DB 구축되면, Video로 변경하여 사용할 것!

import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const API_VIDEO_URL = "http://localhost:8080/api-video/video"

export const useVideoStore = defineStore('video', () => {
    const videoList = ref([]);
    const youtubeVideos = ref([]);
    const videoListOrderByViewCnt = ref([]);
    const videoListByPart = ref([]);
    const videoTags = ref([]);
    const selectedVideo = ref(null);
    const selectedDBVideo = ref(null);
    const exerciseAreaList = ref('전신');
    const keyword = ref('')

    // Youtube API 활용하기
    const youtubeSearch = function(){
        // 잘 찍히는지 확인하기
        const URL = 'https://www.googleapis.com/youtube/v3/search'
        const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY
        axios({
            url: URL,
            method: 'GET',
            params:{
                key: API_KEY,
                part: 'snippet',
                maxResults: 100,
                q: `${keyword.value}운동`,
                type: 'video'
            }
        })
        .then((response)=>{
            youtubeVideos.value = response.data.items
        })
        .catch(()=>{})
    }

    // DB에 있는 Video 가져오기
    const getVideo = function(){
        axios.get(API_VIDEO_URL)
        .then((response)=>{
            // 깊은 복사 하기
            const tmp = JSON.parse(JSON.stringify(response.data));
            videoList.value = tmp;
            const tmp2 = JSON.parse(JSON.stringify(response.data));
            videoListOrderByViewCnt.value = tmp2
            videoListOrderByViewCnt.value.sort((a, b) => b.viewCnt - a.viewCnt);
            videoListOrderByViewCnt.value.length=3;
            videoListByPart.value = response.data.filter((item)=>{
                return item.exerciseArea === exerciseAreaList.value
            })
            if(videoListByPart.value.length>3){
                videoListByPart.value.length=3;
            }
        })
    }

    // DB에서 영상 상세 조회
    const getDetail = function (id) {
        return axios.get("http://localhost:8080/api-video/detail", {
            params: {
                videoId : id,
                memberId: sessionStorage.getItem("memberId")
            }
        })
          .then((response) => {
            selectedDBVideo.value = response.data
        })
    }

    const getVideoTags = ()=>{
        videoList.value.forEach((items)=>{
            videoTags.value.push(items.part)
        })
    }
    // youtube에서 영상 상세 조회
    const clickVideo = (video) => {
        selectedVideo.value = video;
    }

    const unvalidate = function() {
        selectedVideo.value=null;
        selectedDBVideo.value=null;
    }

    const createVideo = ()=>{
        const video = ref({
            "title": selectedVideo.value.snippet.title,
            "channelName": selectedVideo.value.snippet.channelTitle,
            "exerciseArea": keyword.value,
            "youtubeId": selectedVideo.value.id.videoId,
        });
        axios.post(API_VIDEO_URL, video.value)
        .then(
            ()=>{
                unvalidate()
                router.push({name:"youtube"})
            }
        )
        .catch(
            ()=>{
                router.push({name:"youtube"})
            }
        )
    }

    const getExerciseAreaList = function(memberId) {
        axios.get(`http://localhost:8080/api-inbody/pref-ex-area/${memberId}`)
        .then((response) => {
            if(response.data[0] != undefined) {
                const randomVal = Math.floor(Math.random() * response.data.length)
                exerciseAreaList.value = response.data[randomVal];
            }else{
                exerciseAreaList.value = '전신'
            }
            router.push({name: 'home'})
        })
        .catch((error) => {
        });
    }

    const getRecommandVideo = (purpose)=>{
        if(purpose === 'Bulkup'){
            exerciseAreaList.value = "하체";
        }
        else if(purpose === 'Diet'){
            exerciseAreaList.value = "유산소";
        }
        else{
            exerciseAreaList.value = "전신";
        }
        // deep copy
        getVideo();
    }

    const searchVideoList = function (searchCondition) {
        axios.get(`${API_VIDEO_URL}/search`, {
          params: searchCondition
        })
          .then((res) => {
          videoList.value = res.data
        }).catch((error)=>{

        })
      }

    return { 
        videoList, 
        youtubeVideos, 
        selectedVideo, 
        selectedDBVideo, 
        videoListByPart, 
        videoTags,
        keyword, 
        youtubeSearch,
        getVideo, 
        getDetail,
        getVideoTags,
        clickVideo, 
        unvalidate, 
        createVideo, 
        videoListOrderByViewCnt,
        getExerciseAreaList,
        exerciseAreaList,
        getRecommandVideo,
        searchVideoList
    }
});