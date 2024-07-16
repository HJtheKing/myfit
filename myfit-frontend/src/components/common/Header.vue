<template>
  <header>
    <div class="upper">
      <RouterLink :to="{ name: 'home' }"><img src="@/assets/img/logo.png" alt="MYFIT Logo" class="logo" /></RouterLink>
      <nav>
        <template v-if="!memberStore.isLoggedIn">
          <!-- 회원가입, 로그인 버튼 -->
          <RouterLink :to="{ name: 'signup' }">
            <i class="bi bi-person-add"></i>&nbsp;&nbsp;회원가입
          </RouterLink>
          <RouterLink :to="{ name: 'login' }"><i class="bi bi-box-arrow-in-right"></i>&nbsp;&nbsp;로그인</RouterLink>
        </template>
        <template v-else>
          <RouterLink :to="{ name: 'mypage' }"> <i class="bi bi-person-gear"></i>&nbsp;&nbsp;마이페이지</RouterLink>
          <a href="#" @click.prevent="logout"><i class="bi bi-box-arrow-left"></i>&nbsp;&nbsp;로그아웃</a>
        </template>
        <i class="bi bi-list" @click="viewMenu()"></i>
      </nav>
    </div>
    <ul class="nav justify-content-start lower">
      <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'about' }">About <i class="bi bi-door-open"></i></RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'youtube' }">영상 추가 <i class="bi bi-play-circle"></i></RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'videoList' }">운동 추천 <i class="bi bi-play-circle"></i></RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'inbody' }">체성분 분석 <i class="bi bi-clipboard-data"></i
        ></RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" :to="{ name: 'form' }">식단 추천 <i class="bi bi-egg-fried"></i></RouterLink>
      </li>
    </ul>
  </header>
</template>

<script setup>
  import { computed } from "vue";
  import { useRouter } from "vue-router";
  import { useMemberStore } from "@/stores/member";

  const memberStore = useMemberStore();
  const isLoggedIn = computed(() => memberStore.isLoggedIn);
  const router = useRouter();

  const logout = () => {
    memberStore.logout();
    alert("로그아웃 되었습니다.");
    router.push({ name: "home" });
  };
</script>

<style scoped>
header {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background: linear-gradient(to right, #4285F0, #7CBBF3);
  box-sizing: border-box;
  padding: 24px 48px 8px;
}
.upper {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}
.logo {
  width: 160px;
}
nav {
  display: flex;
}
nav > a {
  font: var(--lg-pre-font);
  text-decoration: none;
  margin-left: 20px;
}
.lower {
  margin-top: 16px;
  padding-left: 32px;
}
a {
  color: white;
  text-decoration: none;
}
a > svg:hover {
  color: var(--action-color);
}
a:hover {
  color: #fdd430;
  font-weight: 800;
}
a:focus {
  color: #fdd430;
  font-weight: 800;
}
.bi-list {
  display: none;
}
@media (max-width: 620px) {
  .lower {
    display: none;
  }
  .bi-list {
    display: block;
    color: white;
    font-size: larger;
    margin-left: 20px;
  }
  header {
    padding: 24px 48px;
  }
}
</style>
