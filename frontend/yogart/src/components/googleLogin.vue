<template>
    <div class="mx-auto box">
        <GoogleLogin 
        class="google-btn"
        :params="params" 
        :renderParams="renderParams"
        :onSuccess="onSuccess" 
        :onFailure="onFailure">
        </GoogleLogin>
<!-- 
        <GoogleLogin 
        style="width:225px; height:50px;" 
        :params="params" 
        :logoutButton=true>
        Google Logout
        </GoogleLogin> -->
    </div>
</template>

<script>
import GoogleLogin from 'vue-google-login'
export default {
    name:'googleLogin',
    components: {
    GoogleLogin
    },
    data() {
        return{
            //for google-login
            params: {
                client_id : '464033788490-4n32ni2tto41a0j7crvvo8nuft697un6.apps.googleusercontent.com'
            },
            // 구글 ui로 렌더하고 싶다면 
            renderParams: {
                width: 225,
                height: 50,
                longtitle: true
            }
        }
    },
    methods : {
        onSuccess(googleUser) {
            // 여기가 구글로그인이 성공하면 googleUser에 정보가 담겨서 넘어오는 곳입니까?
            if (localStorage.getItem('auth-token')) {
                return alert('이미 로그인 되어 있습니다.')
            }
            console.log(googleUser)
            console.log(googleUser.getBasicProfile())
        },
        onFailure(googleUser) {
            console.log(googleUser)
            console.log(googleUser.getBasicProfile())
        },


        //만약 구글 로그인 생겼을 시 버튼활성화와 함께 이 함수 활성화
        signinButton() {
            if (localStorage.getItem('JWT_token')) {
                return alert('이미 로그인 되어 있습니다.')
            }
            // signInCallback defined in step 6.
            auth2.grantOfflineAccess().then(signInCallback);
        }
    }
}
 
</script>

<style>
.abcRioButton {
    margin : auto;
}
</style>