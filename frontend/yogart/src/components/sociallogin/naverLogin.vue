<template>
    <div class="box">
        <a :href=naverLoginURL class="social-login-btn"><img :src="repo.src" style="height:48px"/></a> 
    </div>
     
   
</template>

<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script>
const naverClientId = '4eWVX86Cci4TFZzNvA3b'
const naverCallbackUrl = "http://localhost:8000/api/users/naverLogin"
const naverDomainUrl = "http://localhost:3000"


export default {
    name:'naverLogin',
    data() {
      return {
          // 로그인 버튼 이미지
          repo: {name: 'loginBtn', src: require('../../assets/naverLoginBtn/naver_login_green.png')},
          //  FIXME state 값 random string 으로 변경
          state: 123,
          naverLoginURL: 'https://nid.naver.com/oauth2.0/authorize?response_type=code',
        }
    },
    created (){
      this.naverLoginURL += '&client_id=' + naverClientId
      this.naverLoginURL += '&redirect_uri=' + naverCallbackUrl
      this.naverLoginURL += '&state=' + this.state

    }

}
</script>

<style>

</style>