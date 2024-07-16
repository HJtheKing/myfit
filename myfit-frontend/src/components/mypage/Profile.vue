<template>
  <div class="menu-box">
    <h2>기본 정보</h2>
    <div class="contact-info">
      <p><span><i class="bi bi-person"></i>&nbsp;{{ store.member.name }}</span><button @click="openModal('name')">수정</button></p>
      <p><span><i class="bi bi-key"></i> 비밀번호 변경</span><button @click="openModalPassword('password')">수정</button></p>
      <p><span><i class="bi bi-phone"></i>&nbsp;{{ store.member.phone }}</span><button @click="openModal('phone')">수정</button></p>
      <p><span><i class="bi bi-envelope"></i>&nbsp;{{ store.member.alternateEmail }}</span><button @click="openModal('alternateEmail')">수정</button></p>
    </div>
  </div>
  <div class="menu-box">
    <h2>알림 설정</h2>
    <div class="contact-info">
      <p>게시물 조치 알림 수신 동의
        <i v-if="store.member.postAlert" class="bi bi-toggle-on" @click="store.member.postAlert = false"></i>
        <i v-else class="bi bi-toggle-off" @click="store.member.postAlert = true"></i>
      </p>
      <p>프로모션 정보 수신 동의
        <i v-if="store.member.promotionAlert" class="bi bi-toggle-on" @click="store.member.promotionAlert = false"></i>
        <i v-else class="bi bi-toggle-off" @click="store.member.promotionAlert = true"></i>
      </p>
    </div>
  </div>

  <div v-if="showModal" class="modal">
    <div class="modal-content">
      <h2><span class="close" @click="closeModal">&times;</span> {{ modalTitle }} 수정</h2>
      <input v-model="modalValue" :placeholder="modalTitle" type="text"/>
      <div v-if="errorMessage" class="text-danger">{{ errorMessage }}</div>
      <button class="btn btn-submit" @click="submitModal">수정</button>
    </div>
  </div>

  <div v-if="passModal" class="modal">
    <div class="modal-content">
      <h2><span class="close" @click="passModal=false">&times;</span> 비밀번호 수정</h2>
      <div>
        <label for="oldPass">기존 비밀번호</label>
        <input :placeholder="modalTitle" type="password" id="oldPass" placeholder="기존 비밀번호" v-model="oldPass"/>
      </div>
      <div>
        <label for="newPass">새 비밀번호</label>
        <input :placeholder="modalTitle" type="password" id="newPass" placeholder="새 비밀번호" v-model="newPass"/>
      </div>
      <div>
        <label for="checkPass">비밀번호 확인</label>
        <input :placeholder="modalTitle" type="password" id="checkPass" placeholder="비밀번호 확인" v-model="checkPass"/>
      </div>
      <div v-if="errorMessage" class="text-danger">{{ errorMessage }}</div>
      <button class="btn btn-submit" @click="submitPassword">수정</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useMemberStore } from '@/stores/member';
import axios from 'axios';

const store = useMemberStore();
const showModal = ref(false);
const passModal = ref(false);
const modalTitle = ref('');
const modalValue = ref('');
const editField = ref('');
const errorMessage = ref('');

// 비밀번호 관련
const oldPass = ref('');
const newPass = ref('');
const checkPass = ref('')


const openModal = (field) => {
  editField.value = field;
  modalTitle.value = field === 'name' ? '닉네임' : field === 'alternateEmail' ? '대체 이메일' : field;
  modalValue.value = store.member[field];
  errorMessage.value = '';
  showModal.value = true;
};

const openModalPassword = (field) => {
  editField.value = field;
  modalValue.value = store.member[field];
  errorMessage.value = '';
  passModal.value = true;
};


const closeModal = () => {
  showModal.value = false;
  errorMessage.value = '';
};

  const validateInput = () => {
    if (!modalValue.value) {
      return '값을 입력하세요.';
    }

    switch (editField.value) {
      case 'name':
        if (modalValue.value.length < 2) {
          return '이름은 최소 2자 이상이어야 합니다.';
        }
        break;
      case 'phone':
        const phonePattern = /^010-\d{4}-\d{4}$/;
        if (!phonePattern.test(modalValue.value)) {
          return '전화번호는 010-0000-0000 형식이어야 합니다.';
        }
        break;
      case 'alternateEmail':
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(modalValue.value)) {
          return '유효한 이메일을 입력하세요.';
        }
        break;
      default:
        return '';
    }
    return '';
  };

  const submitModal = () => {
    const error = validateInput();
    if (error) {
      errorMessage.value = error;
      return;
    }

    const memberId = store.member.memberId;
    const updateData = {
      name: store.member.name,
      password: store.member.password,
      phone: store.member.phone,
      alternateEmail: store.member.alternateEmail,
      [editField.value]: modalValue.value
    };

    axios.put(`http://localhost:8080/api-member/member/${memberId}`, updateData)
      .then((response) => {
        store.getMemberInfo(); // 업데이트된 정보를 다시 불러옵니다.
        closeModal();
      })
      .catch((error) => {
        console.error("Failed to update member info:", error);
      });
  };

  const validatePassword = () => {
    if (!oldPass.value || !newPass.value || !checkPass.value) {
      return '값을 입력하세요.';
    }
    
    if(oldPass.value != store.member.password) {
      return '기존 비밀번호가 다릅니다.'
    }

    if(newPass.value !== checkPass.value) {
      return '새로운 비밀번호가 확인할 비밀번호와 다릅니다.'
    }

    if(newPass.value.length < 6) {
      return '비밀번호는 최소 6자 이상이어야 합니다.'
    }

    return ''
  };

  const submitPassword = () => {
    const error = validatePassword();
    if (error) {
      errorMessage.value = error;
      return;
    }

    const memberId = store.member.memberId;
    const updateData = {
      name: store.member.name,
      password: newPass.value,
      phone: store.member.phone,
      alternateEmail: store.member.alternateEmail
    };

    axios.put(`http://localhost:8080/api-member/member/${memberId}`, updateData)
      .then((response) => {
        store.getMemberInfo(); // 업데이트된 정보를 다시 불러옵니다.
        passModal.value=false;
        alert('비밀번호가 변경되었습니다.')
      })
      .catch((error) => {

      });
  };

  onMounted(() => {
    store.getMemberInfo();
  });
</script>

<style scoped>
* {
  font: var(--base-pre-font);
}
h2 {
  font: var(--xl-pre-font);
}
.menu-box {
  border: 2px #7cbbf3 solid;
  background-color: #fff;
  padding: 16px;
  border-radius: 10px;
  margin-bottom: 24px;
}
.contact-info {
  margin: 20px 0 0;
}
.contact-info > p {
  border-bottom: 1px #c4c4c4 solid;
  display: flex;
  justify-content: space-between;
}
.contact-info > p:last-of-type {
  margin: 0;
}
.contact-info .bi {
  margin-right: 10px;
}
button {
  display: inline-block;
  border: none;
  border-radius: 8px;
  background-color: #7cbbf3;
  font: var(--sm-pre-font);
  padding: 3px 10px;
}

label{
  width: 100px;
}
.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0.4);
}
.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: fit-content;
}
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
.text-danger {
  color: red;
  font-size: 0.875em;
}

input[type="password"] {
  display: block;
  margin: 0 auto 16px;
  border: 1px black solid;
  border-radius: 8px;
  width: 420px;
  height: 48px;
  padding: 0 20px;
}

input[type="text"] {
  display: block;
  margin: 16px auto;
  border: 1px black solid;
  border-radius: 8px;
  width: 420px;
  height: 48px;
  padding: 0 20px;
}
.btn-submit{
  display: block;
  margin: 0 auto;
  padding: 8px 16px;
  width: fit-content;
}

.bi-toggle-on, .bi-toggle-off{
  scale: 180%;
}

.bi-toggle-on{
  color: var(--main-color)
}
</style>
