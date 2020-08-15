<template>
<div class="padding-for-nav">
    <div class="profile-box">
        <div class="img-wrap d-inline-block">
            <img v-if="imgSrc" class="user-profile-img" :src="imgSrc" alt="user profile image">
            <img v-else class="user-profile-img" src="../../assets/userDefault.jpg" alt="user profile image">
        </div>
        <div class="intro-wrap d-inline-block">
            <div class="box sb2">{{userIntro}}</div>
        </div>
        <div class="profile-content">
            <div class="nickname">{{ userNickname }}</div>
            <i class="fas fa-pencil-alt fa-2x" @click="clickUpdate()"></i>
            <div class="email">{{ userEmail }}</div>
            <div class="spoon-wrap">
                <i class="fas fa-utensil-spoon fa-1x d-inline-block"></i><div class="spoon d-inline-block">{{spoons}}개</div>
            </div>
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
            // userName: '',
            // userNickname: this.$store.state.userNickname,
            // userEmail: '',
            // userProfile : '',
            // userIntro : '',
            // userId : undefined,
            // imgSrc : "http://localhost:8000/api/users/profileImage?authToken=" + this.$cookies.get('auth-token'),
            // spoons : 0,
            userName: '박인영',
            userNickname: 'IN.0',
            userEmail: 'in0_2015@naver.com',
            userProfile : '대충사진들어가는곳',
            userIntro : '안녕하세요, 박인영입니다.',
            userId : 1,
            imgSrc : "http://localhost:8000/api/users/profileImage?authToken=" + this.$cookies.get('auth-token'),
            spoons : 100,
        }
    },
    mounted() {
        this.getUserData()
    },
    methods : {
        clickUpdate() {
            this.$router.push(`/mypage/update`)
        },
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
            this.userEmail = res.data.user.userEmail
            this.userProfile = res.data.user.userProfile
            this.userIntro = res.data.user.userIntro
            this.spoons = res.data.user.userSpoon

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

<style scoped>
.profile-box {
    margin-top: -5rem;
    padding: 3rem;
    background-color: rgba(143, 160, 242, 0.1);
    margin-left: 30vw;
    margin-right: 30vw;
    border-radius: 20rem;
}
.box {
    width: 300px;
    margin: 50px auto;
    background: rgba(143, 160, 242, 1);
    padding: 20px;
    text-align: center;
    font-weight: 900;
    color: #fff;
    font-family: arial;
    position: relative;
}
.sb2:before {
    content: "";
    width: 0px;
    height: 0px;
    position: absolute;
    border-left: 10px solid transparent;
    border-right: 10px solid rgba(143, 160, 242, 1);
    border-top: 10px solid rgba(143, 160, 242, 1);
    border-bottom: 10px solid transparent;
    left: -19px;
    top: 6px;
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
.img-wrap {
    position: relative;
    left: 10rem;
    margin-bottom: 1rem;
}
.intro-wrap {
    position: relative;
    left: 10rem;
}
.nickname {
    font-size: 20px;
    font-weight: bold;
    display: inline-block;
    padding-left: 2rem;
}
.fa-pencil-alt {
    display: inline-block;
    margin-left: 0.5rem;
    cursor: pointer;
}
.fa-pencil-alt:hover {
    color: rgba(242, 157, 143, 1);
}
.email {
    font-weight: bold;
}
.spoon {
    font-size: 15px;
}
.spoon-wrap {
    display: inline-block;
    padding: 0.3rem;
    background-color: rgba(242, 157, 143, 1);
    border-radius: 1rem;
}


</style>