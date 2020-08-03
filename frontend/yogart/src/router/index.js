import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import Home from '../views/Home.vue'
import SignupView from '../views/accounts/SignupView.vue'
import GraphView from '../views/mypage/GraphView.vue'
import Login from '../views/Login.vue'
import Callback from '../views/Callback.vue'
import QnaView from '../views/QnA/QnaView.vue'
import QnaCreate from '../views/QnA/QnaCreate.vue'
import NoticeListView from '../views/notice/NoticeListView.vue'
import NoticeDetailView from '../views/notice/NoticeDetailView.vue'
import NoticeFormView from '../views/notice/NoticeFormView.vue'
import MyPage from '../views/mypage/MyPage.vue'

const requireAuth = () => (from, to, next) => {
    console.log(store.state.isLogin)
    if (store.state.isLogin) return next()
    $('#loginStaticBackdrop').modal('show')
  }

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/callback',
        name: 'Callback',
        component: Callback,
    },
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/accounts/signup',
        name: 'SignupView',
        component: SignupView
    },
    {
        path: '/mypage',
        name: 'MyPage',
        component: MyPage
    },    
    {
        path: '/mypage/graph',
        name: 'Graph',
        component: GraphView,
        beforeEnter: requireAuth(),
    },
    {
        path: '/notice',
        name: 'NoticeList',
        component: NoticeListView
    },
    {
        path: '/notice/form',
        name: 'NoticeForm',
        component: NoticeFormView
        // 관리자만 접근가능
    },
    {
        path: '/notice/:notice_id',
        name: 'NoticeDetail',
        component: NoticeDetailView
    },
    {
        path: '/qna',
        name: 'QnaView',
        component: QnaView
    },
    {
        path: '/qna/question',
        name: 'QnaCreate',
        component: QnaCreate,
        beforeEnter: requireAuth(),
    },
]

    const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
    })

export default router
