<template>
  <main>
    <div class="header">
      <h2>인바디</h2>
      <div>
        <button class="btn btn-analysis" @click="analyzeInbody">분석</button>
        <button class="btn btn-insert" @click="showModal = true">인바디 등록</button>
      </div>
    </div>
    <div>
      <label for="inbody-select">Select Measurement Date:</label>
      <select id="inbody-select" v-model="selectedDate">
        <option value="">--날짜를 선택하세요--</option>
        <option v-for="data in formattedInbodyData" :key="data.inbodyId" :value="data.inbodyId">
          {{ data.measurementDate }}
        </option>
      </select>
    </div>
    <button v-if="selectedInbodyData" class="btn btn-outline-warning" @click="showEditModal = true">수정</button>
    <button v-if="selectedInbodyData" class="btn btn-outline-danger" @click="deleteInbodyData">삭제</button>

    <div v-if="selectedInbodyData" class="inbody-container">
      <div class="inbody-card">
        <h2>인바디 정보</h2>
        <div class="inbody-item">
          <div class="inbody-label">측정 날짜:</div>
          <div class="inbody-value">{{ selectedInbodyData.measurementDate }}</div>
        </div>
        <div class="inbody-item">
          <div class="inbody-label">키:</div>
          <div class="inbody-value">{{ selectedInbodyData.height }} cm</div>
        </div>
        <div class="inbody-item">
          <div class="inbody-label">연령:</div>
          <div class="inbody-value">{{ selectedInbodyData.age }} 세</div>
        </div>
        <div class="inbody-item">
          <div class="inbody-label">성별:</div>
          <div class="inbody-value">{{ selectedInbodyData.gender }}</div>
        </div>
        <div class="inbody-item">
          <div class="inbody-label">몸무게:</div>
          <div class="inbody-value">{{ selectedInbodyData.weight }} kg</div>
        </div>
        <div class="inbody-section">
          <h3>부위별 근육</h3>
          <div class="inbody-item">
            <div class="inbody-label">왼팔:</div>
            <div class="inbody-value">{{ selectedInbodyData.leftArmMuscle }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">오른팔:</div>
            <div class="inbody-value">{{ selectedInbodyData.rightArmMuscle }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">몸통:</div>
            <div class="inbody-value">{{ selectedInbodyData.trunkMuscle }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">왼다리:</div>
            <div class="inbody-value">{{ selectedInbodyData.leftLegMuscle }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">오른다리:</div>
            <div class="inbody-value">{{ selectedInbodyData.rightLegMuscle }} </div>
          </div>
        </div>
        <div class="inbody-section">
          <h3>부위별 지방</h3>
          <div class="inbody-item">
            <div class="inbody-label">왼팔:</div>
            <div class="inbody-value">{{ selectedInbodyData.leftArmFat }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">오른팔:</div>
            <div class="inbody-value">{{ selectedInbodyData.rightArmFat }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">몸통:</div>
            <div class="inbody-value">{{ selectedInbodyData.trunkFat }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">왼다리:</div>
            <div class="inbody-value">{{ selectedInbodyData.leftLegFat }} </div>
          </div>
          <div class="inbody-item">
            <div class="inbody-label">오른다리:</div>
            <div class="inbody-value">{{ selectedInbodyData.rightLegFat }} </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showModal = false">&times;</span>
        <h2>인바디 정보 입력</h2>
        <form @submit.prevent="submitInbodyDataForm">
          <div
            v-for="(value, key) in newInbodyData"
            :key="key"
            class="form-group"
            :class="{ 'has-error': errors[key] }"
          >
            <label :for="key">{{ key }}</label>
            <template v-if="key.endsWith('Fat') || key.endsWith('Muscle')">
              <select class="form-control" :id="key" v-model="newInbodyData[key]" @change="validateField(key)">
                <option value="">--선택하세요--</option>
                <option value="표준">표준</option>
                <option value="표준이하">표준이하</option>
                <option value="표준이상">표준이상</option>
              </select>
            </template>
            <template v-else-if="key === 'gender'">
              <select class="form-control" :id="key" v-model="newInbodyData[key]" @change="validateField(key)">
                <option value="">--성별을 선택하세요--</option>
                <option value="남성">남성</option>
                <option value="여성">여성</option>
              </select>
            </template>
            <template v-else-if="key === 'measurementDate'">
              <input
                type="date"
                class="form-control"
                :id="key"
                v-model="newInbodyData[key]"
                @input="validateField(key)"
              />
            </template>
            <template v-else>
              <input
                type="text"
                class="form-control"
                :id="key"
                v-model="newInbodyData[key]"
                @input="validateField(key)"
              />
            </template>
            <div v-if="errors[key]" class="text-danger">{{ errors[key] }}</div>
          </div>
          <div class="form-group">
            <label for="imageUpload">이미지 등록:</label>
            <input type="file" class="form-control-file" id="imageUpload" @change="handleFileUpload" />
            <div v-if="uploadedFileName">{{ uploadedFileName }}</div>
            <button type="button" class="btn btn-secondary" @click="readFile">읽기</button>
          </div>
          <button type="submit" class="btn btn-primary">등록</button>
        </form>
      </div>
    </div>

    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showEditModal = false">&times;</span>
        <h2>인바디 정보 수정</h2>
        <form @submit.prevent="submitEditInbodyDataForm">
          <div
            v-for="(value, key) in editInbodyData"
            :key="key"
            class="form-group"
            :class="{ 'has-error': errors[key] }"
          >
            <label :for="key">{{ key }}</label>
            <template v-if="key.endsWith('Fat') || key.endsWith('Muscle')">
              <select class="form-control" :id="key" v-model="editInbodyData[key]" @change="validateField(key)">
                <option value="">--선택하세요--</option>
                <option value="표준">표준</option>
                <option value="표준이하">표준이하</option>
                <option value="표준이상">표준이상</option>
              </select>
            </template>
            <template v-else-if="key === 'gender'">
              <select class="form-control" :id="key" v-model="editInbodyData[key]" @change="validateField(key)">
                <option value="">--성별을 선택하세요--</option>
                <option value="남성">남성</option>
                <option value="여성">여성</option>
              </select>
            </template>
            <template v-else-if="key === 'measurementDate'">
              <input
                type="date"
                class="form-control"
                :id="key"
                v-model="editInbodyData[key]"
                @input="validateField(key)"
              />
            </template>
            <template v-else>
              <input
                type="text"
                class="form-control"
                :id="key"
                v-model="editInbodyData[key]"
                @input="validateField(key)"
              />
            </template>
            <div v-if="errors[key]" class="text-danger">{{ errors[key] }}</div>
          </div>
          <button type="submit" class="btn btn-primary">수정하기</button>
        </form>
      </div>
    </div>
    <LoadingSpinner v-if="isLoading" class="isLoading"></LoadingSpinner>
  </main>
</template>

<script setup>
import { ref, onMounted, computed, watch } from "vue";
import { useInbodyStore } from "@/stores/inbody";
import { useRoute, useRouter } from "vue-router";
import LoadingSpinner from "@/components/common/LoadingSpinner.vue";
import axios from "axios";

const isLoading = ref(false)
const store = useInbodyStore();
const route = useRoute();
const router = useRouter();
const selectedDate = ref("");
const showModal = ref(false);
const showEditModal = ref(false);
const errors = ref({});
const uploadedFile = ref(null);
const uploadedFileName = ref("");
const selectedInbodyId = ref(null);

const memberId = sessionStorage.getItem("memberId"); // 세션에서 memberId를 가져옴

onMounted(() => {
  store.getInbodyData(memberId);
});

const formattedInbodyData = computed(() => store.formattedInbodyData);

const selectedInbodyData = computed(() => {
  const data = store.inbodyData.find((data) => data.inbodyId === selectedDate.value);
  selectedInbodyId.value = data ? data.inbodyId : null;
  return data;
});

const newInbodyData = ref({
  height: "",
  age: "",
  gender: "",
  bodyFatMass: "",
  weight: "",
  skeletalMuscleMass: "",
  idealWeight: "",
  weightControl: "",
  fatControl: "",
  muscleControl: "",
  basalMetabolicRate: "",
  recommendedIntakeCalories: "",
  leftArmMuscle: "",
  rightArmMuscle: "",
  trunkMuscle: "",
  leftLegMuscle: "",
  rightLegMuscle: "",
  leftArmFat: "",
  rightArmFat: "",
  trunkFat: "",
  leftLegFat: "",
  rightLegFat: "",
  measurementDate: "",
});

const editInbodyData = ref({ ...newInbodyData.value });

const validateField = (key) => {
  if (!newInbodyData.value[key] && !editInbodyData.value[key]) {
    errors.value[key] = "이 항목을 입력하세요.";
    return false;
  }

  const value = newInbodyData.value[key] || editInbodyData.value[key];

  switch (key) {
    case "height":
    case "bodyFatMass":
    case "weight":
    case "skeletalMuscleMass":
    case "idealWeight":
    case "basalMetabolicRate":
    case "recommendedIntakeCalories":
      if (isNaN(value) || value <= 0) {
        errors.value[key] = "유효한 숫자를 입력하세요.";
        return false;
      }
      break;
    case "weightControl":
    case "fatControl":
    case "muscleControl":
      if (isNaN(value)) {
        errors.value[key] = "유효한 숫자를 입력하세요.";
        return false;
      }
      break;
    case "age":
      if (!Number.isInteger(Number(value)) || value <= 0) {
        errors.value[key] = "유효한 나이를 입력하세요.";
        return false;
      }
      break;
    case "gender":
      if (value !== "남성" && value !== "여성") {
        errors.value[key] = "성별을 선택하세요.";
        return false;
      }
      break;
    case "leftArmMuscle":
    case "rightArmMuscle":
    case "trunkMuscle":
    case "leftLegMuscle":
    case "rightLegMuscle":
    case "leftArmFat":
    case "rightArmFat":
    case "trunkFat":
    case "leftLegFat":
    case "rightLegFat":
      if (!["표준", "표준이하", "표준이상"].includes(value)) {
        errors.value[key] = "올바른 값을 선택하세요.";
        return false;
      }
      break;
    case "measurementDate":
      if (isNaN(Date.parse(value))) {
        errors.value[key] = "유효한 날짜를 선택하세요.";
        return false;
      }
      break;
  }

  errors.value[key] = "";
  return true;
};

const validateForm = (data) => {
  let isValid = true;

  for (const key in data) {
    if (!validateField(key)) {
      isValid = false;
    }
  }

  return isValid;
};

const handleFileUpload = (event) => {
  const file = event.target.files[0];
  if (file) {
    uploadedFile.value = file;
    uploadedFileName.value = file.name;
  }
};

const readFile = () => {
  isLoading.value = true;
  if (!uploadedFile.value) {
    alert("파일을 선택하세요.");
    isLoading.value = false;
    return;
  }

  store
    .readFile(uploadedFile.value)
    .then((data) => {
      Object.keys(data).forEach((key) => {
        if (newInbodyData.value[key] !== undefined) {
          if (key === "measurementDate") {
            newInbodyData.value[key] = data[key].substring(0, 10); // yyyy-MM-dd 형식으로 자르기
          } else {
            newInbodyData.value[key] = data[key];
          }
        }
      });
      isLoading.value = false;
      alert("파일이 성공적으로 읽혔습니다.");
    })
    .catch(() => {
      isLoading.value = false;
      alert("파일 읽기 중 오류가 발생했습니다.");
    });
};

const submitInbodyDataForm = () => {
  if (!validateForm(newInbodyData.value)) {
    isLoading.value = false;
    return;
  }

  const inbodyDataWithMemberId = { ...newInbodyData.value, memberId };

  axios
    .post("http://localhost:8080/api-inbody/inbody", inbodyDataWithMemberId)
    .then((response) => {
      isLoading.value = false;
      alert("인바디 정보가 성공적으로 등록되었습니다.");
      showModal.value = false;
      store.getInbodyData(memberId);
    })
    .catch((error) => {
      isLoading.value = false;
      alert("인바디 정보 등록 중 오류가 발생했습니다.");
    });
};

const submitEditInbodyDataForm = () => {
  if (!validateForm(editInbodyData.value)) {
    return;
  }

  const editedInbodyDataWithMemberId = { ...editInbodyData.value, memberId };

  axios
    .put(`http://localhost:8080/api-inbody/inbody/${selectedInbodyId.value}`, editedInbodyDataWithMemberId)
    .then((response) => {
      alert("인바디 정보가 성공적으로 수정되었습니다.");
      showEditModal.value = false;
      store.getInbodyData(memberId);
    })
    .catch((error) => {
      alert("인바디 정보 수정 중 오류가 발생했습니다.");
    });
};

const deleteInbodyData = () => {
  if (!selectedInbodyId.value) {
    alert("삭제할 인바디 정보를 선택하세요.");
    return;
  }

  axios
    .delete(`http://localhost:8080/api-inbody/inbody/${selectedInbodyId.value}`)
    .then((response) => {
      alert("인바디 정보가 성공적으로 삭제되었습니다.");
      selectedDate.value = "";
      store.getInbodyData(memberId);
    })
    .catch((error) => {
      alert("인바디 정보 삭제 중 오류가 발생했습니다.");
    });
};

const analyzeInbody = () => {
  if (confirm("최신 일자의 인바디 정보로 데이터를 분석합니다. 분석하시겠습니까?")) {
    axios
      .get(`http://localhost:8080/api-inbody/inbody/solution/${memberId}`)
      .then((response) => {
        alert("분석이 완료되었습니다.");
        router.push({ name: "home" });
      })
      .catch((error) => {
        alert("분석 중 오류가 발생했습니다.");
      });
  }
};

watch(selectedInbodyData, (newData) => {
  if (newData) {
    Object.keys(editInbodyData.value).forEach((key) => {
      editInbodyData.value[key] = newData[key];
    });
  }
});
</script>

<style scoped>
* {
  font: var(--base-pre-font);
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

label {
  margin-right: 10px;
}

.btn-insert,
.btn-analysis {
  background-color: var(--action-color);
  margin: 0 0 0 8px;
}

h1 {
  font: var(--lg-pre-font);
}

h2 {
  font: var(--xl-pre-font);
  padding-top: 16px;
}

.modal {
  display: block;
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 2;
}

.modal-content {
  position: absolute;
  display: block;
  transform: translate(-50%, 0);
  left: 50%;
  background-color: #fefefe;
  margin: 100px auto;
  padding: 20px;
  border: 1px solid #888;
  max-width: 800px;
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

.form-group {
  margin-bottom: 15px;
}

.has-error input,
.has-error select {
  border-color: red;
}

.inbody-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.inbody-card {
  background-color: #7CBBF3;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  color: black;
  max-width: 400px;
  width: 100%;
}

.inbody-card h2 {
  font-size: 1.5em;
  font-weight: bold;
  margin-bottom: 15px;
  text-align: center;
}

.inbody-section {
  margin-top: 15px;
  border-top: 1px solid rgba(255, 255, 255, 0.3);
  padding-top: 15px;
}

.inbody-section h3 {
  font-size: 1.2em;
  font-weight: bold;
  color: black;
  margin-bottom: 8px;
}

.inbody-item {
  display: flex;
  justify-content: space-between;
  padding: 8px 0;
  margin-bottom: 8px;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 6px;
  transition: background 0.3s ease;
}

.inbody-item:hover {
  background: rgba(255, 255, 255, 0.3);
}

.inbody-label, .inbody-value {
  flex: 1;
  padding: 0 8px;
}

.inbody-label {
  font-weight: bold;
  font-size: 1em;
  color: black;
  text-align: left;
}

.inbody-value {
  font-size: 1em;
  text-align: right;
  color: black;
}
.isLoading{
  z-index: 3;
}
</style>
