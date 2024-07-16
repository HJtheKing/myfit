<template>
  <nav class="navigation-menu">
    <ul>
      <li>
        <RouterLink :to="{ name: 'profile' }"><span>내 프로필</span><i class="bi bi-chevron-right"></i></RouterLink>
        
      </li>
      <li><RouterLink :to="{ name: 'activity' }">이력 관리<i class="bi bi-chevron-right"></i></RouterLink></li>
      <li><RouterLink :to="{ name: 'help' }">고객센터<i class="bi bi-chevron-right"></i></RouterLink></li>
      <li><a class="logout" @click="deleteMember()">회원 탈퇴</a></li>
    </ul>
  </nav>
</template>

<script setup>
import router from "@/router";
import { useMemberStore } from "@/stores/member";
import axios from "axios";

const store = useMemberStore();
const deleteMember = () => {
  if (confirm("정말 탈퇴하시겠습니까")) {
    axios
      .delete(`http://localhost:8080/api-member/delete/${sessionStorage.getItem("memberId")}`)
      .then(() => {
        store.isLoggedIn = false;
        router.push({ name: "home" });
      })
      .catch(() => {
        alert("탈퇴에 실패했습니다. 다시 시도해 주세요.");
      });
  }
};
axios;
</script>

<style scoped>
* {
  font: var(--base-pre-font);
}
.navigation-menu ul {
  margin: 0 auto;
  list-style: none;
  padding: 0;
}
.navigation-menu li {
  margin: 8px auto;
  padding: 8px 16px;
  
}
a {
  color: black;
  display: flex;
  justify-content: space-between;
  text-decoration: none;
}
.logout {
  color: red;
}
</style>
