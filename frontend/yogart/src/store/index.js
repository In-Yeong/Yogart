import Vue from 'vue'
import Vuex from 'vuex'
import VueCookies from 'vue-cookies'

Vue.use(Vuex)
Vue.use(VueCookies)

export default new Vuex.Store({
    state: {
        isLogin: !!window.$cookies.get('auth-token'),
        SERVER_URL: "http://localhost:8000",
        userNickname: null,
        spoons: null,
        imageUrl: null,
    },
    mutations: {
        storeLogin(state) {
            state.isLogin = true
        },
        storeLogout(state) {
            state.isLogin = false
        },
        setUserData(state, userData) {
            state.userNickname = userData.userNickname
            state.spoons = userData.spoons
            state.imageUrl = userData.imageUrl
        },
    },
    actions: {
    },
    modules: {
    }
  
})
