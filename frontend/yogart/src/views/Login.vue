<template>
    <div>
        <googleLogin/>
        <kakaoLogin/>
        <naverLogin/>
        <facebookLoginCom/>
    </div>
</template>

<script>
import googleLogin from '@/components/googleLogin.vue'
import naverLogin from '@/components/naverLogin.vue'
import kakaoLogin from '@/components/kakaoLogin.vue'
import facebookLoginCom from '@/components/facebookLogin.vue'

export default {
    name: 'Login',
    components: {
        googleLogin,
        kakaoLogin,
        naverLogin,
        facebookLoginCom,
      
    }
}
</script>

<style>
.social-login-btn :hover{
  opacity: 0.7;
}
.login-box {
    width : 50%;
    margin : auto;
    background-color:lightgray;
    border : 2px solid gray;
    padding : 2rem;
}
</style>
