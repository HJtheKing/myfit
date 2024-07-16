<template>
  <main class="login-wrapper">
    <h2>Login</h2>
    <form @submit.prevent="login" id="login-form">
      <input type="text" v-model="email" name="email" placeholder="Email" />
      <input type="password" v-model="password" name="password" placeholder="Password" />
      <div class="menus">
        <div><input type="checkbox" id="remember-check" /> <label for="remember-check">아이디 저장하기</label></div>
        <div>
          <a href="#">아이디 찾기</a>
          &nbsp;
          <a href="#">비밀번호 찾기</a>
        </div>
      </div>
      <input type="submit" value="로 그 인" />
      <button class="naver">
        <div class="img-frame naver-frame"><img src="../assets/img/icon-naver.png" /></div>
        <p>네이버 로그인</p>
      </button>
      <button class="kakao">
        <div class="img-frame kakao-frame"><img src="../assets/img/icon-kakao.png" /></div>
        <p>카카오 로그인</p>
      </button>
    </form>
    <div class="move-to-signup">
      <h2>아직 회원가입 안하셨나요?</h2>
      <p>
        지금 회원가입하시면, 별도 비용 없이, <br />
        나의 건강 상태에 맞는 운동을 추천받을 수 있습니다.
      </p>
      <RouterLink :to="{ name: 'signup' }" class="btn">회원 가입 하기</RouterLink>
    </div>
  </main>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";

const email = ref("");
const password = ref("");
const router = useRouter();
const memberStore = useMemberStore();
    
const login = () => {
  memberStore.login(email.value, password.value).then((success) => {
    // console.log(success);
    if (success) {
      router.push({ name: "home" });
    } else {
      alert("Login failed");
    }
  });
};
</script>

<style scoped>
* {
  font: var(--base-pre-font);
}
main {
  display: block;
  margin: 0 auto;
  padding: 0 0 70px;
}
h2 {
  font: var(--xl-pre-font);
  margin: 0 auto;
  padding-top: 40px;
  width: 420px;
}
form {
  margin: 0 auto;
}
.menus {
  display: flex;
  justify-content: space-between;
  width: 420px;
  margin: 8px auto;
}
.menus a {
  text-decoration: none;
  color: black;
}
input[type="text"],
input[type="password"],
input[type="submit"] {
  display: block;
  margin: 16px auto;
  border: 1px black solid;
  border-radius: 8px;
  width: 420px;
  height: 48px;
  padding: 0 20px;
  font: var(--lg-pre-font);
}
input[type="submit"] {
  background-color: var(--action-color);
  border: 0;
  margin-bottom: 32px;
}
button {
  display: flex;
  border-radius: 8px;
  align-items: center;
  margin: 8px auto;
  width: 420px;
  height: 48px;
  font: var(--base-pre-font);
  background-color: white;
  padding: 0;
}
.img-frame {
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  text-align: center;
  width: 64px;
  height: 48px;
}
.naver-frame {
  background-color: #23ba09;
}
.kakao-frame {
  background-color: #ffd800;
}
img {
  width: 25px;
  height: 25px;
  display: block;
  margin: 11.5px auto;
}
p {
  margin: 0 auto;
}
.naver {
  border: 1px #23ba09 solid;
}
.kakao {
  font: var(--base-pre-font);
  background-color: white;
  border: 1px #ffd800 solid;
}
.move-to-signup {
  margin-top: 40px;
  box-sizing: content-box;
  padding: 24px;
  background-color: #ececec;
  text-align: center;
}
.move-to-signup > p {
  width: 420px;
  padding: 8px;
}
.move-to-signup > h2 {
  margin: 0 auto;
  padding: 8px;
}
.btn {
  display: inline-block;
  background-color: var(--main-color);
  color: white;
}
</style>
