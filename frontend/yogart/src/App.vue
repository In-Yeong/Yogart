<template>
    <div id="app">
        <NavBar/>
        <div style="height:99px;"></div>
        <login-modal @loginComplete="loginComplete"></login-modal>
        <div id="nav" v-if="false">
            <router-link to="/login">login</router-link> |  
            <router-link to="/">Home</router-link> | 
            <router-link to="/mypage/graph">Graph</router-link> | 
            <router-link to="/callback">callback</router-link> | 
            <router-link to="/accounts/signup">Signup</router-link> |
            <router-link to="/qna">Q&A</router-link> |
            <router-link to="/notice">공지사항</router-link> |
        </div>
            <router-view @submit-signup-data="signup" />
        <Footer/>
    </div>
</template>

<script>
import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
const API_URL = 'http://127.0.0.1:8000'

export default {
    name: "App",
    data() {
        return {
            isLogin: false,
        }
    },
    components: {
        NavBar,
        Footer,
    },
    methods: {
        setCookie(key) {
          this.$cookies.set('auth-token', key)
          this.isLogin = true
        },
        signup(signupData) {
        console.log(signupData)
        axios.post(`${API_URL}/api/users/signup`, signupData)
            .then(response => {
                if (response.data.statusCode === 200) {
                    this.setCookie(response.data.token)
                    this.$router.push({name: 'Home'})
               } else {
                   alert('중복된 이메일입니다.')
               }
            })
            .catch(err => {
            console.log(err.response)
            alert('회원가입 실패')
            })
        },
        showLoginModal() {
			// 로그인 모달 창을 보여줍니다.
			$('#loginStaticBackdrop').modal('show')
		},
		loginComplete() {
			this.$store.commit('storeLogin')
			$('#loginStaticBackdrop').modal('hide')
		},
		logout() {
			// 로그아웃은 쿠키를 삭제하는 것으로 마무리합니다.
			this.removeCookie()
			this.$store.commit('storeLogout')
			// 로그아웃이 완료되면 사용자를 홈페이지로 던집니다.
			this.$router.replace({ name: 'Home' })
		},
		removeCookie() {
			this.$cookies.remove('auth-token')
		},
    }

}
</script>


<style>
#app {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    min-height: calc(100vh - 127.55px);
}

#nav {
    padding: 30px;
}

#nav a {
    font-weight: bold;
    color: #2c3e50;
}

#nav a.router-link-exact-active {
    color: #42b983;
}

.box {
  margin-top : 3px;
}

.margin-top {
    margin-top: 100px !important;
}

.user-profile {
  display: inline-block;
  width: 150px;
  height: 150px;
  border-radius: 50%;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}

.user-level {
  position: absolute;
  top: 60px;
  left: 80px;
}
</style>
