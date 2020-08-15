import Vue from 'vue'
import Vuex from 'vuex'
import VueCookies from 'vue-cookies'

Vue.use(Vuex)
Vue.use(VueCookies)

export default new Vuex.Store({
    state: {
        isLogin: !!window.$cookies.get('auth-token'),
        SERVER_URL: 'http://localhost:8000',
        LOCAL_URL: 'http://localhost:3000',
        userNickname: null,
        spoons: null,
        imageUrl: null,
    },
    mutations: {
        storeLogin(state) {
            // console.log('login complete')
            state.isLogin = true
            // console.log(state)
        },
        storeLogout(state) {
            // console.log('logout complete')
            state.isLogin = false
            // console.log('@@@@@@@', state)

        },
        setUserData(state, payload) {
            console.log(state,payload)
            // console.log(payload.user.userNickname)
            state.userNickname = payload.user.userNickname
            state.spoons = payload.spoons
            state.imageUrl = payload.user.imageUrl
        },
    },
    actions: {
    },
    modules: {
    }
  
})
