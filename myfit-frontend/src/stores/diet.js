// 추후 video DB 구축되면, Video로 변경하여 사용할 것!

import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_DIET_API = `http://localhost:8080/api-diet/diet`

export const useDietStore = defineStore('diet', () => {
  const dietList = ref([]);
  const result = ref([{},{},{}])

  // 추천했던 식단 전체 조회 메서드
  const getDiets =  async ()=>{
    await  axios.get(REST_DIET_API)
    .then((response)=>{
      dietList.value = response.data
      for(let i=0;i<dietList.value.length;i++) {
        if(dietList.value[i].memberId == sessionStorage.getItem("memberId")){
          if(dietList.value[i].mealTime === "Breakfast"){
            result.value[0] = dietList.value[i];
          }else if(dietList.value[i].mealTime === "Lunch"){
            result.value[1] = dietList.value[i];
          }else{
            result.value[2] = dietList.value[i];
          }
        }
      }
      // console.log(result.value)
    })
    .catch(()=>{

    })
  }

  return{ dietList, result, getDiets }
})