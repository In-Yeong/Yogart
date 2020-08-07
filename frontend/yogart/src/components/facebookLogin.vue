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