<template>
<div class="modal fade" id="loginStaticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Login Modal</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <div class="modal-body">
                <span v-if="errorState">아이디 혹은 비밀번호를 다시 확인하세요.</span>
                <span v-if="serverState">연결할 수 없습니다. 잠시 후 다시 시도해주세요.</span>
                <ValidationObserver v-slot="{ invalid }">
                    <br>
                    <ValidationProvider name="email" rules="required|email" v-slot="{ errors }">
                        <input v-model="loginData.userEmail" type="text" placeholder="아이디"><br>
                        <span>{{ errors[0] }}</span>
                    </ValidationProvider>
                    <br>
                    <ValidationProvider rules="required|min:6" v-slot="{ errors }">
                        <input type="password" v-model="loginData.userPassword" placeholder="비밀번호"><br>
                        <span>{{ errors[0] }}</span>
                    </ValidationProvider>
                    <br>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" :disabled="invalid" @click="onSubmit">Login</button>
                </ValidationObserver>
                <hr>
                <div class="my-5"></div>
                <kakaoLogin @loginComplete="setUserData"></kakaoLogin>
                <naverLogin @loginComplete="setUserData"></naverLogin>
                <router-link to="/accounts/signup" class="btn btn-primary">회원가입</router-link>
                <div class="my-5"></div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
import googleLogin from '@/components/googleLogin.vue'
import naverLogin from '@/components/sociallogin/naverLogin.vue'
import kakaoLogin from '@/components/sociallogin/kakaoLogin.vue'
import facebookLoginCom from '@/components/facebookLogin.vue'

export default {
    name: 'login-modal',
    data () {
        return {
            loginData: {
                userEmail: null,
                userPassword: null,
            },
            errorState: null,
            serverState: null,
            API_URL: this.$store.state.SERVER_URL,
        }
    },
    components: {
        googleLogin,
        naverLogin,
        kakaoLogin,
        facebookLoginCom,
    },
    methods: {
        onSubmit(e) {
            e.preventDefault()
            axios.post(this.API_URL + '/api/users/login', this.loginData)
            .then(res => {
                console.log('then')
                console.log(res);
                // 로그인이 실패했다면 errorState에 status code를 저장해 오류를 출력합니다.
                this.setUserData(res.data)
            })
            .catch(err => {
                console.log('catch')
                console.error(err)
                this.errorState = true
                this.loginData.userPassword = null
            })
        },
        setCookie(token) {
            this.$cookies.set('auth-token', token)
        },
        setUserData(data) {
            if (data.token !== null){
                this.setCookie(data.token)
            } else {
                this.serverState = true
            }
            console.log('setUserData')
            this.$store.commit('setUserData', data)
            this.$emit('loginComplete')
        }
    },
}
</script>

<style scoped>
  div {
    padding: 0px
  }

  div span {
    margin: 20px
  }
</style>