<template>
  <div class="box">
        <div id="kakao-login-btn"></div>
  </div>
</template>

<script>
const JavaScriptApiKey = process.env.VUE_APP_KAKAO_JS_KEY
import axios from "axios";
export default {
    name : 'kakaoLogin',
    data() {
        return {
            JavaScriptApiKey : JavaScriptApiKey,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    created() {
        console.log(process.env)
        console.log("이거",this.JavaScriptApiKey)
        Kakao.init('688de69414ec5331cee58badb1cad1ea');
    },
    mounted() {
        document.addEventListener("DOMContentLoaded", function() {

        // @details 카카오톡 Developer API 사이트에서 발급받은 JavaScript Key
      
        // Kakao.init("688de69414ec5331cee58badb1cad1ea");
        // Kakao.init("804d91902e09ccbb770ad9e5f6e72c5f");



        // @breif 카카오 로그인 버튼을 생성합니다.

        Kakao.Auth.createLoginButton({
            container : "#kakao-login-btn",
            success : function( authObj ) {
                console.log( authObj );
                 axios
                .post(this.SERVER_URL + "/api/users/kakaoLogin", {
                    access_token: authObj.access_token,
                    expires_in: authObj.expires_in,
                    refresh_token: authObj.refresh_token,
                    refresh_token_expires_in: authObj.refresh_token_expires_in,
                    token_type: authObj.token_type
                })
                .then(res1 => {
                    // res1 확인
                    console.log(res1);
                    this.$emit('loginComplete', res1.data.userData)
                })
                .catch(error => {
                    console.log(error);
                });
                
                Kakao.API.request({
                      url : "/v2/user/me"
                    , success : function( res ) {
                        console.log('리퀘스트 성공')
                        console.log( res );
                    }, fail : function( error ) {
                        alert( JSON.stringify( error ) );
                    }
                });
            }
            , fail : function( error ) {
                alert( JSON.stringify( error ));

            }

        });

    });
    },
}
</script>

<style>

</style>
