<template>
  <div class="box d-flex flex-column">
        <div>
            <facebook-login 
            class="button social-login-btn facebook-btn"
            :appId="291260192294247"
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


export default {
    name : 'facebookLoginCom',
    components : {
        facebookLogin
    },
<<<<<<< HEAD
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
=======

>>>>>>> a2e9f35f81e64c9460b65002075720ab3ed41709
    methods : {
        getUserData() {
            this.FB.init()
            // this.FB.api('/me', 'GET', {fields:'id,name,email'},
            //     userInformation => {
            //         console.warn("get data from fb", userInformation)
            //         this.personalID = userInformation.id;
            //         this.email = userInformation.email;
            //         this.name = userInformation.name;
            //     }
            // )
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