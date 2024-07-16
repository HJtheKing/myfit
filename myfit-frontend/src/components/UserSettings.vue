<template>
    <div class="user-settings">
      <h3>설정</h3>
      <label>
        <input type="checkbox" v-model="notifications">
        알림 받기
      </label>
    </div>
  </template>
  
  <script>
    import { ref } from 'vue'
    const notifications = ref(false)
  </script>
  
  <style scoped>
  .user-settings {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
  }
  </style>
  