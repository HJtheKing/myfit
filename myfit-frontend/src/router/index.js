import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import YoutubeView from '../views/YoutubeView.vue'

import MypageView from '../views/MypageView.vue'
import Profile from '../components/mypage/Profile.vue'
import Activity from '../components/mypage/Activity.vue'
import Help from '../components/mypage/Help.vue'

import InbodyView from '../views/InbodyView.vue'
import DietView from '../views/DietView.vue'
import LoginView from '../views/LoginView.vue'
import SignupView from '../views/SignupView.vue'

import Form from '../components/diet/Form.vue'
import Result from '../components/diet/Result.vue'

import PostView from '../views/PostView.vue'
import PostCreate from '@/components/post/PostCreate.vue'
import PostUpdate from '@/components/post/PostUpdate.vue'
import PostList from '@/components/post/PostList.vue'
import PostDetail from '@/components/post/PostDetail.vue'

import VideoList from '@/components/video/VideoList.vue'
import VideoDetail from '@/components/video/VideoDetail.vue'
import { usePostStore } from '@/stores/post'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },    {
      path: '/signup',
      name: 'signup',
      component: SignupView
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MypageView,
      children: [
        {
          path: 'profile',
          name: 'profile',
          component: Profile,
        },
        {
          path: 'activity',
          name: 'activity',
          component: Activity,
        },
        {
          path: 'help',
          name: 'help',
          component: Help,
        }
      ],
    },
    {
      path: '/youtube',
      name: 'youtube',
      component: YoutubeView
    },
    {
      path: '/video',
      name: 'video',
      component: PostView,
      children: [
        {
          path: '',
          name: 'videoList',
          component: VideoList,
        },
        {
          path: 'write',
          name: 'write',
          component: PostCreate,
        },
        {
          path: ':id',
          name: 'videoDetail',
          component: VideoDetail,
        }
    ]},
    {
      path: '/inbody',
      name: 'inbody',
      component: InbodyView,
      props: (route) => ({ memberId: route.query.memberId })
    },
    {
      path: '/diet',
      name: 'diet',
      component: DietView,
      children:[
        {
          path: 'form',
          name: 'form',
          component: Form,
        },
        {
          path: 'result',
          name: 'result',
          component: Result,
        }
      ]
    },
    {
      path: '/search',
      name: 'search',
      component: DietView
    },
    {
      path: '/post',
      name: 'post',
      component: PostView,
      children: [
        {
          path: '',
          name: 'list',
          component: PostList,
        },
        {
          path: 'write/:videoId',
          name: 'write',
          component: PostCreate,
        },
        {
          path: 'update',
          name: 'update',
          component: PostUpdate,
          beforeEnter: (to, from, next)=>{
            const postStore = usePostStore();
            const memberId = sessionStorage.getItem("memberId"); // 현재 로그인한 사용자의 ID를 가져옴
              if (postStore.post.memberId == memberId) {
                next(); // 작성자가 맞으면 페이지로 이동
              } else {
                alert('수정은 작성자만 할 수 있습니다.')
                next('/'); // 작성자가 아니면 홈 페이지로 리디렉션
              }
            }
        },
        {
          path: ':id',
          name: 'postDetail',
          component: PostDetail,
        }
    ]}
  ]
})

export default router
