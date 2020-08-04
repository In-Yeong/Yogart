<template>
  <div class="box d-flex flex-column">
        <div>
            <facebook-login 
            class="button social-login-btn facebook-btn"
            :appId="facebookAppId"
            @login="onLogin"
            @logout="onLogout"
            @sdk-loaded="sdkLoaded">
           </facebook-login>
        </div>
         
  </div>
</template>

<script>
import Vue from 'vue'
import facebookLogin from 'facebook-login-vuejs';
const facebookAppId = process.env.VUE_APP_FACEBOOK_APP_ID

export default {
    name : 'facebookLoginCom',
    components : {
        facebookLogin
    },
    data() {
        return {
            facebookAppId : facebookAppId,
        }
    },
    methods : {
        getUserData() {
            this.FB.api('/me', 'GET', {fields:'id,name,email'},
                userInformation => {
                    // 여기가 로그인이 완료되면 사용자의 id, name, email이 넘어오는 곳 입니까?
                    console.warn("get data from fb", userInformation)
                    this.personalID = userInformation.id;
                    this.email = userInformation.email;
                    this.name = userInformation.name;
                }
            )
        },
        sdkLoaded(payload) {
            // isConnected는 어디에?
            this.isConnected = payload.isConnected
            this.FB = payload.FB
            if (this.isConnected) {
                this.getUserData()
                }
        },
        onLogin() {
            this.isConnected = true;
            this.getUserData()
        },
        onLogout() {
            this.isConnected = false;
        }
    },
    
}
</script>

<style>
.facebook-btn > button {
    height: 50px;
}
.spinner {
    visibility: hidden;
}
</style>