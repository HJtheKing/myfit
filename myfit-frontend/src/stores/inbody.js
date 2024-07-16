// src/stores/inbody.js

import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

export const useInbodyStore = defineStore('inbody', () => {
  const inbodyData = ref([]);
  // const inbodySolution = ref('');

  const getInbodyData = (memberId) => {
    axios.get(`http://localhost:8080/api-inbody/inbody/${memberId}/list`)
      .then((response) => {
        inbodyData.value = response.data; // 받아온 데이터를 inbodyData에 저장
        // console.log(response.data);
      })
      .catch((error) => {
        console.error("Failed to fetch inbody data:", error);
        inbodyData.value = []; // 에러 발생 시 빈 배열로 초기화
      });
  };


  const readFile = (file) => {
    const formData = new FormData();
    formData.append("file", file);

    return axios.post(
      "http://localhost:8080/api-inbody/read",
      formData,
      { headers: {
        "Content-Type": "multipart/form-data",
        "Access-Control-Allow-Origin": "*",
       },
     }
    )
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error("Failed to read file:", error);
      throw error;
    });
  };

  const formattedInbodyData = computed(() => {
    return inbodyData.value.map(data => ({
      inbodyId: data.inbodyId,
      measurementDate: new Date(data.measurementDate).toLocaleDateString(),
    }));
  });

  return { 
    inbodyData, 
    getInbodyData, 
    formattedInbodyData,
    readFile
  };
});
