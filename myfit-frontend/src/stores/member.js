import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

const REST_MEMBER_API = `http://localhost:8080/api-member`

export const useMemberStore = defineStore('member', () => {
  const member = ref({})
  const memberId = sessionStorage.getItem("memberId");
  const isLoggedIn = ref(false);

  if (sessionStorage.getItem("access-token")) {
    isLoggedIn.value = true;
  }

  const getMemberInfo = function () {
    axios.get(`${REST_MEMBER_API}/member/${memberId}`)
      .then((response) => {
      member.value = response.data
      // console.log(member.value)
    })
  }

  const login = (email, password) => {
    return axios.post(`${REST_MEMBER_API}/login`, {
      email,
      password
    })
    .then((response) => {
      // console.log(response);
      const accessToken = response.data['access-token'];
      sessionStorage.setItem("access-token", response.data['access-token'])
      sessionStorage.setItem("memberId", response.data['id']);
      isLoggedIn.value = true;
      // member.value = response.data; // assuming the response contains user info
      // isLoggedIn.value = true;
      return true;
    })
    .catch((error) => {
      // console.error('Login failed:', error);
      return false;
    });
  };

  const invalidate = function() {
    member.value=null;
  }

  const logout = () => {
    sessionStorage.removeItem("access-token");
    sessionStorage.removeItem("memberId");
    member.value = '';
    // memberId.value = '';
    isLoggedIn.value = false;
    // return axios.post(`${REST_MEMBER_API}/logout`)  // Assuming you have a logout endpoint
    //   .then(() => {
    //     member.value = null;
    //     isLoggedIn.value = false;
    //   })
    //   .catch((error) => {
    //     console.error('Logout failed:', error);
    //   });
  };


  return { member, getMemberInfo, login, invalidate, logout, isLoggedIn }
})
