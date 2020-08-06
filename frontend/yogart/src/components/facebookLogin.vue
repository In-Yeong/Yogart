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
    created() {
//         FB.init({
//             appId  : '넣업'
//             status : true, // check login status
//             cookie : true, // enable cookies to allow the server to access the session
//             xfbml  : true  // parse XFBML
// });
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
                    console.warn("get data from fb", userInformation)
                    this.personalID = userInformation.id;
                    this.email = userInformation.email;
                    this.name = userInformation.name;
                }
            )
        },
        sdkLoaded(payload) {
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