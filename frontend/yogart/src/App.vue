<template>
    <div id="app">
        <login-modal @loginComplete="loginComplete"></login-modal>
        <NavBar @logout="logout" :isLogin="isLogin"/>
        <router-view class="test" @submit-signup-data="signup" />
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

#app {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    /* font-family: 'Song Myung', serif; */
    /* color: #2c3e50;
    min-height: calc(100vh - 127.55px);

    background-image: url('./assets/marble1.jpg');
    background-size: 100%;
     */
    background-image: url("./assets/back1.jpg");
    /* background-repeat: no-repeat; */
    background-size: 100%;
    color: #2c3e50;
}
.test {
    min-height: 100vh;
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
} */
.shadow-box-pink{
    background-color: rgba(255,255,255,0.3);
    border: 1px solid;
    padding: 10px;
    box-shadow: 5px 10px #f29d8f;
}
.shadow-box{
  
    margin : 5px;
    background-color: rgba(255,255,255,0.5);
    border-radius: 10px;
    border: 1px solid white;
    box-shadow: 3px 3px rgba(0,0,0,0.1);
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
  background-color: rgba(242, 157, 143,0.3);
  border: 5px solid #f29d8f;
  color: #f29d8f;
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
  background: rgba(242, 157, 143,0.3);
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
  background: rgba(242, 157, 143,0.3);
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
.box {
  position: relative;
  display: inline-block;
  width: 450px;
  /* height: 100px; */
  border-radius: 5px;
  background-color: rgba(255, 255, 255, 0.0);
  box-shadow: 0 1px 2px rgba(0,0,0,0.15);
  transition: all 0.3s ease-in-out;
}

/* Create the hidden pseudo-element */
/* include the shadow for the end state */
.box::after {
  /* content: ''; */
  position: absolute;
  z-index: -1;
  width: 100%;
  height: 100%;
  opacity: 0;
  border-radius: 5px;
  box-shadow: 0 5px 15px rgba(0,0,0,0.3);
  transition: opacity 0.3s ease-in-out;
}
/* Scale up the box */
.box:hover {
  transform: scale(1.2, 1.2);
}

/* Fade in the pseudo-element with the bigger shadow */
.box:hover::after {
  opacity: 1;
}

::selection {
    background-color: rgba(242, 157, 143, 0.7);
    ;
    color: white;
}

::-webkit-scrollbar {
    width: 16px;
}

::-webkit-scrollbar-thumb {
    background-color: rgba(242, 157, 143, 0.3);
    background-clip: padding-box;
    border: 0.05em solid rgba(235, 235, 235, 0.5);
}

::-webkit-scrollbar-track {
    background-color: rgba(235, 235, 235, 0.5);
}

/* Buttons */
::-webkit-scrollbar-button:single-button {
    background-color: rgba(235, 235, 235, 0.5);;
    display: block;
    border-style: solid;
    height: 13px;
    width: 16px;
}

/* Up */
::-webkit-scrollbar-button:single-button:vertical:decrement {
    border-width: 0 8px 8px 8px;
    border-color: transparent transparent gray transparent;
}

/* Down */
::-webkit-scrollbar-button:single-button:vertical:increment {
    border-width: 8px 8px 0 8px;
    border-color: gray transparent transparent transparent;
}

</style>
