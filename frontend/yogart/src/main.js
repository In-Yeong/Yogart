import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'
import LoginModal from './components/login/LoginModal.vue'
import { ValidationObserver, ValidationProvider, extend } from 'vee-validate'
import { required, email, min } from 'vee-validate/dist/rules'
import CKEditor from '@ckeditor/ckeditor5-vue'

// for google login
// import GAuth from 'vue-google-oath2'
// Vue.use(GAuth, {clientId: "464033788490-4n32ni2tto41a0j7crvvo8nuft697un6.apps.googleusercontent.com", scope: 'profile email https://www.googleapis.com/auth/plus.login'})

Vue.config.productionTip = false
Vue.use(VueCookies)
Vue.use(CKEditor)

Vue.component(LoginModal.name, LoginModal)
Vue.component('ValidationObserver', ValidationObserver)
Vue.component('ValidationProvider', ValidationProvider)

extend('email', {
    ...email,
    message: '올바르지 않은 이메일 형식입니다.'
})
  
extend('required', {
    ...required,
    message: '필수 입력 항목입니다.'
})

extend('min', {
    ...min,
    message: '비밀번호는 6자리 이상'
})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
