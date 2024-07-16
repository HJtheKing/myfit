<script setup>
  import UserProfile from '../components/mypage/UserProfile.vue'
  import MypageNav from '../components/mypage/MypageNav.vue'
  import { useMemberStore } from '@/stores/member'
  import { onMounted, onUnmounted } from 'vue';
  import router from '@/router';
  
  const store = useMemberStore();

  onMounted(()=>{ 
    router.push({name: 'profile'})
   })
  // onUnmounted(()=>{ store.invalidate() })
</script>

<template>
  <main>
    <aside>
      <UserProfile />
      <MypageNav />
    </aside>
    <article>
      <RouterView/>
    </article>
  </main>
</template>

<style scoped>
  main{
    max-width: 1200px;
    display: flex;
    flex-direction: row;
    gap: 20px;
    box-sizing: border-box;
    padding: 48px;
  }
  aside{
    flex-basis: calc( 100vw / 1200 * 320 );
  }
  article{
    flex-basis: calc( 100vw / 1200 * 880 );
  }
</style>