<template>
    <div class="user-profile">
      <img src="../../assets/img/logo.png" alt="User Image" class="profile-image">
      <h1>{{ store.member.name }}</h1>
      <p>{{ store.member.email }}</p>
    </div>
  </template>
  
  <script setup>
    import { useMemberStore } from '@/stores/member';
    import { onMounted, ref } from 'vue'
    
    const store = useMemberStore();
    onMounted(()=>{
      store.getMemberInfo();
    })
  </script>
  
<style scoped>
    *{
    font: var(--base-pre-font);
    text-align: center;
  }
  h1{
    font: var(--xl-pre-font);
  }
  .user-profile {
    background-color: #f0f0f0;
    padding: 20px;
    border-radius: 10px;
  }
  .profile-image {
    width: 100px;
    height: 100px;
    border-radius: 50%;
  }
</style>
  