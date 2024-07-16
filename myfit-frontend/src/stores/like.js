// 추후 video DB 구축되면, Video로 변경하여 사용할 것!

import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_DIET_API = `http://localhost:8080/api-like/post`

export const useDietStore = defineStore('like', () => {
  const dietList = ref([]);

  // 추천했던 식단 전체 조회 메서드
  const getDiets = function(){
    axios.get(REST_DIET_API)
    .then((response)=>{
      diets.value = response.data
    })
  }

  return{ dietList, getDiets }
})