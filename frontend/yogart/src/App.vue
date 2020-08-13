<template>
    <div id="app">
        <login-modal @loginComplete="loginComplete"></login-modal>
        <!-- <NavBar @logout="logout" :isLogin="isLogin"/> -->
        <div style="height:99px;"></div>
        <div id="nav" v-if="true">
            <router-link to="/login">login</router-link> |  
            <router-link to="/">Home</router-link> | 
            <router-link to="/mypage/graph">Graph</router-link> | 
            <router-link to="/callback">callback</router-link> | 
            <router-link to="/accounts/signup">Signup</router-link> |
            <router-link to="/qna">Q&A</router-link> |
            <router-link to="/notice">공지사항</router-link> |
            <router-link to="/coaching">AICoachingPage</router-link> |
            <router-link to="/coaching/yogaposelist">YogaPoseListPage</router-link> |
            <router-link to="/coaching/yogapose">YogaPosePage</router-link>
        </div>
            <router-view @submit-signup-data="signup" />
        <!-- <Footer/> -->
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
            isLogin: this.$store.state.isLogin,
            SERVER_URL: this.$store.state.SERVER_URL,
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
        // console.log(signupData)
        axios.post(`${this.SERVER_URL}/api/users/signup`, signupData)
            .then(response => {
                if (response.data.statusCode === 200) {
                    this.setCookie(response.data.token)
                    this.$store.commit('storeLogin')
                    this.$router.push({name: 'Home'})
               } 
               else {
                   alert('회원가입 실패')
               }
            })
            .catch(err => {

                console.log(err.response)
                if (err.response.data.statusCode === 403) {
                   if (err.response.data.message === 'email') {
                       alert('이메일이 이미 존재합니다.')
                   } else if (err.response.data.message === 'nickname') {
                       alert('닉네임이 이미 존재합니다.')
                   } else {
                       alert('이메일과 닉네임이 이미 존재합니다.')
                   }
                }
                else {  
                    alert('회원가입 실패')
                }
            
            })
        },
        showLoginModal() {
			// 로그인 모달 창을 보여줍니다.
			$('#loginStaticBackdrop').modal('show')
		},
		loginComplete() {
			this.$store.commit('storeLogin')
            this.isLogin = true
            $('#loginStaticBackdrop').modal('hide')
			this.$router.replace({ name: 'Home' })
		},
		logout() {
			// 로그아웃은 쿠키를 삭제하는 것으로 마무리합니다.
			this.$store.commit('storeLogout')
            this.isLogin = false
            this.removeCookie()
			// 로그아웃이 완료되면 사용자를 홈페이지로 던집니다.
			this.$router.replace({ name: 'Home' })
		},
		removeCookie() {
			this.$cookies.remove('auth-token')
		},
    },
    created() {
        Kakao.init('688de69414ec5331cee58badb1cad1ea');
    }

}
</script>


<style>
/* body{
    font-family: 'Song Myung', serif;
} */
#app {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    /* font-family: 'Song Myung', serif; */
    color: #2c3e50;
    min-height: calc(100vh - 127.55px);
    background-image: url('./assets/watercolor1.jpg');
    background-size: 100%;
    
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

/* .user-profile {
  display: inline-block;
  width: 150px;
  height: 150px;
  border-radius: 50%;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
} */
.shadow-box{
    background-color: rgba(255,255,255,0.3);
    border: 1px solid;
    padding: 10px;
    box-shadow: 5px 10px #f29d8f;
}

.user-level {
  position: absolute;
  top: 60px;
  left: 80px;
}
.btn1 {
  transition-duration: 0.4s;
  /* background-color:rgba(255,255,255,0.3);
  border: 3px solid #f29d8f;
  color: #f29d8f; */
  background-color: #f29d8f;
  border: 5px solid #f29d8f;
  color: white;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;

  font-size: 24px;
  margin: 4px 2px;
  transition:800ms ease all;
  outline:none;
}
.btn1:hover {
  background-color:  #2c3e50;
  border: 5px solid #2c3e50;
  color: #f29d8f;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
  cursor: pointer;
}
.btn1:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #f29d8f;
  transition:400ms ease all;
}
.btn1:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.btn1:hover:before,.btn1:hover:after{
  width:100%;
  transition:800ms ease all;
}
.btn4 {
  transition-duration: 0.4s;
  /* background-color:rgba(255,255,255,0.3);
  border: 3px solid #f29d8f;
  color: #f29d8f; */
  background-color:#f29d8f;
  border: 3px solid #f29d8f;
  color: white;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;

  font-size: 24px;
  margin: 4px 2px;
  transition:800ms ease all;
  outline:none;
}
.btn4:hover {
  background-color:  rgba(255,255,255,0.3);
  color: #f29d8f;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
  cursor: pointer;
}
.btn4:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #f29d8f;
  transition:400ms ease all;
}
.btn4:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.btn4:hover:before,.btn4:hover:after{
  width:100%;
  transition:800ms ease all;
}

</style>
