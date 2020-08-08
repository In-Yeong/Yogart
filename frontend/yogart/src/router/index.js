import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import Home from '../views/Home.vue'
import SignupView from '../views/accounts/SignupView.vue'
import GraphView from '../views/mypage/GraphView.vue'
import Callback from '../components/sociallogin/naverLogin.vue'
import QnaView from '../views/QnA/QnaView.vue'
import QnaCreate from '../views/QnA/QnaCreate.vue'
import NoticeListView from '../views/notice/NoticeListView.vue'
import NoticeDetailView from '../views/notice/NoticeDetailView.vue'
import NoticeFormView from '../views/notice/NoticeFormView.vue'
import MyPage from '../views/mypage/MyPage.vue'
import UserUpdate from '../views/mypage/UserUpdate.vue'
import TeacherList from '../views/teacher/TeacherList.vue'
import TeacherDetail from '../views/teacher/TeacherDetail.vue'
import TeacherApply from '../views/teacher/TeacherApply.vue'
import AICoachingPage from '../views/coaching/AICoachingPage.vue'
import AICoachingResultPage from '../views/coaching/AICoachingResultPage.vue'
import YogaPoseListPage from '../views/coaching/YogaPoseListPage.vue'
import YogaPoseListDetailPage from '../views/coaching/YogaPoseListDetailPage.vue'
import YogaPosePage from '../views/coaching/YogaPosePage.vue'
import ClassSetting from '../components/teacher/ClassSetting.vue'

const requireAuth = () => (from, to, next) => {
    // console.log(store.state.isLogin)
    if (store.state.isLogin) return next()
    $('#loginStaticBackdrop').modal('show')
}

const requireUNAuth = () => (from, to, next) => {
    // console.log(store.state.isLogin)
    if (!store.state.isLogin) return next()
    return
}

const requireAdmin = () => (from, to, next) => {
    
}

Vue.use(VueRouter)

const routes = [
    {
        path: '/teachers/class-setting',
        name: 'ClassSetting',
        component: ClassSetting,
    },
    {
        path: '/teachers',
        name: 'TeacherList',
        component: TeacherList,
    },
    {
        path: '/teachers/:teacher_id',
        name: 'TeacherDetail',
        component: TeacherDetail,
    },
    {
        path: '/teacher-apply',
        name: 'TeacherApply',
        component: TeacherApply
    },
    {
        path: '/naver/callback',
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
        component: SignupView,
        beforeEnter: requireUNAuth(),
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
        path: '/mypage/update',
        name: 'UserUpdate',
        component: UserUpdate,
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
    {
        path: '/coaching',
        name: 'AICoachingPage',
        component: AICoachingPage
      },
    {
        path: '/coachingresult',
        name: 'AICoachingResultPage',
        component: AICoachingResultPage
      },
      {
        path: '/coaching/yogaposelist',
        name: 'YogaPoseListPage',
        component: YogaPoseListPage
      },
      {
        path: '/coaching/yogaposelist/detail',
        name: 'YogaPoseListDetailPage',
        component: YogaPoseListDetailPage
      },
      {
        path: '/coaching/yogapose',
        name: 'YogaPosePage',
        component: YogaPosePage
      },
]

    const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
    })

export default router
