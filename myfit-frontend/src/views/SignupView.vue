<template>
  <main>
    <div class="container">
      <h4>회원가입</h4>
      <div class="form-floating mb-3 position-relative" :class="{ 'has-error': emailError }">
        <input type="email" class="form-control" id="email" v-model="email" placeholder="이메일" />
        <label for="email">Email</label>
        <button class="btn btn-outline-secondary position-absolute top-0 end-0 mt-2 me-2" @click="checkEmail">
          이메일 중복검사
        </button>
        <div v-if="emailError" class="text-danger">{{ emailError }}</div>
        <div v-if="emailSuccess" class="text-success">{{ emailSuccess }}</div>
      </div>
      <div class="form-floating mb-3" :class="{ 'has-error': passwordError }">
        <input type="password" class="form-control" id="password" v-model="password" placeholder="비밀번호" />
        <label for="password">Password</label>
        <div v-if="passwordError" class="text-danger">{{ passwordError }}</div>
      </div>
      <div class="form-floating mb-3" :class="{ 'has-error': confirmPasswordError }">
        <input
          type="password"
          class="form-control"
          id="confirmPassword"
          v-model="confirmPassword"
          placeholder="비밀번호 확인"
        />
        <label for="confirmPassword">Confirm Password</label>
        <div v-if="confirmPasswordError" class="text-danger">{{ confirmPasswordError }}</div>
      </div>
      <div class="form-floating mb-3" :class="{ 'has-error': alternateEmailError }">
        <input
          type="email"
          class="form-control"
          id="alternateEmail"
          v-model="alternateEmail"
          placeholder="보조 이메일"
        />
        <label for="alternateEmail">Alternate Email</label>
        <div v-if="alternateEmailError" class="text-danger">{{ alternateEmailError }}</div>
      </div>
      <div class="form-floating mb-3" :class="{ 'has-error': nameError }">
        <input type="text" class="form-control" id="name" v-model="name" placeholder="이름" />
        <label for="name">Name</label>
        <div v-if="nameError" class="text-danger">{{ nameError }}</div>
      </div>
      <div class="form-floating mb-3" :class="{ 'has-error': birthDateError }">
        <input type="date" class="form-control" id="birthDate" v-model="birthDate" placeholder="생년월일" />
        <label for="birthDate">Birth Date</label>
        <div v-if="birthDateError" class="text-danger">{{ birthDateError }}</div>
      </div>
      <div class="form-floating mb-3" :class="{ 'has-error': phoneError }">
        <input type="text" class="form-control" id="phone" v-model="phone" placeholder="전화번호" @input="autoHyphen()"/>
        <label for="phone">Phone</label>
        <div v-if="phoneError" class="text-danger">{{ phoneError }}</div>
      </div>
      <div class="d-flex justify-content-end">
        <button class="btn btn-outline-primary" @click="validateForm">등록</button>
      </div>
    </div>
  </main>
</template>

<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import router from '@/router';

const email = ref("");
const password = ref("");
const confirmPassword = ref("");
const alternateEmail = ref("");
const name = ref("");
const birthDate = ref("");
const phone = ref("");

const emailError = ref("");
const passwordError = ref("");
const confirmPasswordError = ref("");
const alternateEmailError = ref("");
const nameError = ref("");
const birthDateError = ref("");
const phoneError = ref("");
const emailSuccess = ref("");

const validateEmail = (email) => {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return re.test(email);
};

const validatePhone = (phone) => {
  const re = /^\d{2,3}-\d{3,4}-\d{4}$/;
  return re.test(phone);
};

const autoHyphen = () => {
 phone.value = phone.value
   .replace(/[^0-9]/g, '')
   .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

watch(alternateEmail, (newValue) => {
  if (newValue && !validateEmail(newValue)) {
    alternateEmailError.value = "유효한 이메일 형식을 입력하세요.";
  } else {
    alternateEmailError.value = "";
  }
});

watch(phone, (newValue) => {
  if (newValue && !validatePhone(newValue)) {
    phoneError.value = "유효한 전화번호 형식을 입력하세요.";
  } else {
    phoneError.value = "";
  }
});

const validateForm = () => {
  let isValid = true;

  if (!email.value) {
    emailError.value = "이메일을 입력하세요.";
    isValid = false;
  } else if (!validateEmail(email.value)) {
    emailError.value = "유효한 이메일 주소를 입력하세요.";
    isValid = false;
  } else {
    emailError.value = "";
  }

  if (!password.value) {
    passwordError.value = "비밀번호를 입력하세요.";
    isValid = false;
  } else {
    passwordError.value = "";
  }

  if (!confirmPassword.value) {
    confirmPasswordError.value = "비밀번호 확인을 입력하세요.";
    isValid = false;
  } else if (password.value !== confirmPassword.value) {
    confirmPasswordError.value = "비밀번호가 일치하지 않습니다.";
    isValid = false;
  } else {
    confirmPasswordError.value = "";
  }

  if (!alternateEmail.value) {
    alternateEmailError.value = "보조 이메일을 입력하세요.";
    isValid = false;
  } else if (!validateEmail(alternateEmail.value)) {
    alternateEmailError.value = "유효한 이메일 형식을 입력하세요.";
    isValid = false;
  } else {
    alternateEmailError.value = "";
  }

  if (!name.value) {
    nameError.value = "이름을 입력하세요.";
    isValid = false;
  } else {
    nameError.value = "";
  }

  if (!birthDate.value) {
    birthDateError.value = "생년월일을 입력하세요.";
    isValid = false;
  } else {
    birthDateError.value = "";
  }

  if (!phone.value) {
    phoneError.value = "전화번호를 입력하세요.";
    isValid = false;
  } else if (!validatePhone(phone.value)) {
    phoneError.value = "유효한 전화번호 형식을 입력하세요.";
    isValid = false;
  } else {
    phoneError.value = "";
  }

  if (isValid) {
    createAccount();
    router.push({name: 'login'})
  }
};

const checkEmail = () => {
  if (!validateEmail(email.value)) {
    emailError.value = "유효한 이메일 주소를 입력하세요.";
    emailSuccess.value = "";
    return;
  }

  axios
    .get(`http://localhost:8080/api-member/check/${email.value}`)
    .then((response) => {
      if (response.status === 200) {
        emailError.value = "";
        emailSuccess.value = "사용 가능한 이메일입니다.";
      }
    })
    .catch((error) => {
      if (error.response && error.response.status === 409) {
        emailError.value = "이미 사용 중인 이메일입니다.";
        emailSuccess.value = "";
      } else {
        emailError.value = "이메일 중복 검사 중 오류가 발생했습니다.";
        emailSuccess.value = "";
      }
    });
};

const createAccount = () => {
  const accountData = {
    email: email.value,
    password: password.value,
    alternateEmail: alternateEmail.value,
    name: name.value,
    birthDate: birthDate.value,
    phone: phone.value,
  };

  // 회원가입 API 요청
  axios
    .post("http://localhost:8080/api-member/signup", accountData)
    .then((response) => {
      alert("회원가입이 성공적으로 완료되었습니다.");
    })
    .catch((error) => {
      alert("회원가입 중 오류가 발생했습니다.");
    });
};
</script>

<style scoped>
* {
  font: var(--base-pre-font);
}
.text-danger {
  color: red;
  font-size: 0.875em;
}
.text-success {
  color: green;
  font-size: 0.875em;
}
.position-relative {
  position: relative;
}
.position-absolute {
  position: absolute;
}
.top-0 {
  top: 0;
}
.end-0 {
  right: 0;
}
.mt-2 {
  margin-top: 0.5rem;
}
.me-2 {
  margin-right: 0.5rem;
}
.has-error input {
  border-color: red;
}
</style>
