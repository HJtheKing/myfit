<template>
  <div class="menu-box">
    <h2>좋아요</h2>
    <div class="go-detail">
      <p @click="openLikesModal">
        <span>좋아요 누른 영상 확인하기</span>
        <i class="bi bi-chevron-compact-right"></i>
      </p>
    </div>
  </div>
  <div class="menu-box">
    <h2>게시글</h2>
    <div class="go-detail">
      <p @click="openPostsModal">
        <span>작성한 게시글 확인하기</span>
        <i class="bi bi-chevron-compact-right"></i>
      </p>
    </div>
  </div>

  <div v-if="showLikesModal" class="modal">
    <div class="modal-content">
      <div>
        <span class="close" @click="showLikesModal = false">&times;</span>
        <h2>좋아요 누른 영상</h2>
      </div>
      <div v-if="videosData.length">
        <ul class="video-list">
          <li v-for="video in videosData" :key="video.id" class="video-item">
            <a :href="`http://localhost:5173/video/${video.videoId}`">
              <h3 v-html="video.title"></h3>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>

  <div v-if="showPostsModal" class="modal">
    <div class="modal-content">
      <div>
        <h2>작성한 게시글</h2>
        <span class="close" @click="showPostsModal = false">&times;</span>
      </div>
      <div v-if="postsData && postsData.length">
        <ul class="post-list">
          <li v-for="post in postsData" :key="post.id" class="post-item">
            <a :href="`http://localhost:5173/video/${post.videoId}`">
              <h3>{{ post.title }}</h3>
            </a>
            <p>{{ post.content }}</p>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const likesData = ref([]);
const videosData = ref([]); // 좋아요 비디오 리스트
const postsData = ref([]); // 작성한 게시글 리스트
const memberId = sessionStorage.getItem("memberId");
const showLikesModal = ref(false);
const showPostsModal = ref(false);

const fetchLikesData = () => {
  axios.get(`http://localhost:8080/api-like/like/member/${memberId}`)
    .then(response => {
      likesData.value = response.data;
      fetchVideoData();
    })
    .catch(error => {
      console.error("좋아요 데이터를 불러오는 데 실패했습니다:", error);
    });
};

const fetchVideoData = () => {
  videosData.value = [];
  likesData.value.forEach(like => {
    axios.get(`http://localhost:8080/api-video/video/${like.videoId}`)
      .then(response => {
        videosData.value.push(response.data);
      })
      .catch(error => {
        console.error(`비디오 데이터 불러오기 실패 (videoId: ${like.videoId}):`, error);
      });
  });
};

const fetchPostsData = () => {
  axios.get(`http://localhost:8080/api-post/member/${memberId}`)
    .then(response => {
      postsData.value = response.data;
      console.log(postsData.value);
    })
    .catch(error => {
      console.error("게시글 데이터를 불러오는 데 실패했습니다:", error);
    });
};

const openLikesModal = () => {
  fetchLikesData();
  showLikesModal.value = true;
};

const openPostsModal = () => {
  fetchPostsData();
  showPostsModal.value = true;
};
</script>

<style scoped>
* {
  font: var(--base-pre-font);
}
h2 {
  font: var(--xl-pre-font);
}

.modal-content h2 {
  padding: 8px 0 0;
}

.modal-content h3 {
  padding: 8px 0 0;
  margin: 0;
}

.menu-box {
  border: 2px #7cbbf3 solid;
  background-color: #fff;
  padding: 16px;
  border-radius: 10px;
  margin-bottom: 24px;
}
.go-detail {
  margin: 20px 0 0;
}
.go-detail > p {
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}
.inbody-info > p {
  border-bottom: 1px #c4c4c4 solid;
}
.go-detail > p:last-of-type {
  margin: 0;
}
.go-detail .bi {
  scale: 130%;
  font-size: large;
  cursor: pointer; /* 커서를 손가락 모양으로 변경 */
  transition: color 0.3s ease; /* 색상 변경 애니메이션 */
}
.go-detail .bi:hover {
  color: #007bff; /* 마우스 올렸을 때 색상 변경 */
}
button {
  border: none;
  border-radius: 8px;
  background-color: #7cbbf3;
  font: var(--sm-pre-font);
  margin-right: 0;
}
.delete {
  background-color: #c4c4c4;
}

.modal {
  display: block; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.8); /* Black w/ opacity */
}

.modal-content {
  background: linear-gradient(to right, #4285F0, #7CBBF3);
  margin: 10% auto; /* 10% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  max-width: 800px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  color: white;
}

.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #f1f1f1;
  text-decoration: none;
  cursor: pointer;
}

.video-list, .post-list {
  list-style: none;
  padding: 0;
}

.video-item, .post-item {
  background: rgba(255, 255, 255, 0.2);
  padding: 10px;
  margin: 10px 0;
  border-radius: 5px;
  transition: background 0.3s ease;
}

.video-item:hover, .post-item:hover {
  background: rgba(255, 255, 255, 0.3);
}

.video-item a, .post-item a {
  text-decoration: none;
  color: white;
  font-size: 1.2em;
  transition: color 0.3s ease;
}

.video-item a:hover, .post-item a:hover {
  color: #ffcc00;
}

.video-item h3, .post-item h3 {
  margin: 0 0 10px;
}

.post-item p {
  margin: 0;
  color: #d1d1e0;
}
</style>
