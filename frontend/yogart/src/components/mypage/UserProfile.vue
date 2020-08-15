<template>
<div>
    <div class="profile-box d-flex  my-5">
        <template>
        <div style="font-size: 2rem">
            <b-avatar badge="BV"></b-avatar>
            <b-avatar badge="7" variant="primary" badge-variant="dark"></b-avatar>
            <b-avatar badge-variant="info" src="https://placekitten.com/300/300">
            <template v-slot:badge><b-icon icon="star-fill"></b-icon></template>
            </b-avatar>
        </div>
        </template>
        <img v-if="imgSrc" class="user-profile-img" :src="imgSrc" alt="user profile image">
        <img v-else class="user-profile-img" src="../../assets/userDefault.jpg" alt="user profile image">
        <div class="profile-content" >
            <div class="d-flex mb-5" >
                <h5>{{ userName }}</h5> 
                <div class="d-flex">
                    <router-link to="/mypage/update" class="w3-button w3-black w3-tiny update">회원정보 수정</router-link>
                </div>
            </div>
    
            <p v-if="spoons">보유중인 스푼 : {{spoons}}개</p>
            <p v-else>보유중인 스푼이 없습니다.</p>
         
            
            <p>{{userIntro}}</p>
        </div>
    </div>
    
</div>    
</template>

<script>
import axios from 'axios'

export default {
    name : 'UserProfile',
    data() {
        return {
            SERVER_URL : this.$store.state.SERVER_URL,
            userName: this.$store.state.userNickname,
            userProfile : '',
            userIntro : '',
            userId : undefined,
            imgSrc : "http://localhost:8000/api/users/profileImage?authToken=" + this.$cookies.get('auth-token'),
            spoons : null,
        }
    },
    mounted() {
        this.getUserData()
    },
    methods : {
        getUserData() {
            const requestHeaders = {
            headers: {
                Authorization: this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/api/users/myInfo', requestHeaders)
        .then(res => {
            console.log("UserProfile page 성공",res)

            this.userName = res.data.user.userName
            this.userId = res.data.user.Id
            this.userNickname = res.data.user.userNickname
            this.userProfile = res.data.user.userProfile
            this.userIntro = res.data.user.userIntro

            if (this.userProfile === undefined) {
                this.userProfile = 'userDefault'
            }
            if (this.userIntro === undefined) {
                this.userIntro = '상태 메세지를 적어보세요.'
            }
        })
        .catch(err => console.error(err))
        }
    }
}
</script>

<style>
.update  {
    position : absolute;
    right : 13vw;
    margin-right:75px;
}
.delete{
    
    position : absolute;
    right : 13vw;
 

}
.user-profile-img {
  display: inline-block;
  width: 200px;
  height: 200px;
  border-radius: 50%;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
} 
.profile-box {
    margin-left: 10vw;
    margin-right: 10vw;
    padding : 1rem;
    
}
</style>