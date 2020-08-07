<template>
    <div class="updateView pb-5">
        <div>{{ userName }} 정보 수정</div>
        <img :src="userImageUrl" alt="user profile image">
        <input @change="fileChange" type="file" ref="userImage" id="user-image" accept=".jpg, .jpeg, .png">

        <div class="container d-flex justify-content-center text-left">
            <div class="d-block">
                <div class="form-group">
                    <label for="userEmail">닉네임</label>
                    <br>
                    <div class="input-box">
                        <input class="border-0" type="text" id="userEmail" v-model="userNickname">
                        <span class="allIcon" id="userEmailCheckIcon">아이콘</span>
                    </div>
                    <label for="userIntro">한마디</label>
                    <br>
                    <div class="input-box">
                        <input class="border-0" type="text" id="userIntro" v-model="userIntro">
                        <span class="allIcon" id="userEmailCheckIcon">아이콘</span>
                    </div>
                </div>
                <button @click="update" class="btn btn-primary mt-4 col-12">수정</button>
            </div> 
        </div>
    </div>
</template>

<script>
import axios from 'axios'


export default {
    name: 'UserUpdate',
    data() {
        return {
            userName: null,
            userId: null,
            userNickname: null,
            userImageUrl: null,
            userIntro: null,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    create() {
        // 유저 정보를 받아옵니다.
        const requestHeaders = {
            headers: {
                Authorization: 'Token ' + this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/myInfo/update', null, requestHeaders)
        .then(res => {
            this.userName = res.data.userName
            this.userId = res.data.userId
            this.userNickname = res.data.userNickname
            this.userImageUrl = res.data.userImageUrl
            this.userIntro = res.data.userIntro
        })
        .catch(err => console.error(err))
    },
    methods: {
        update() {
            const requestHeaders = {
                headers: {
                    Authorization: 'Token ' + this.$cookies.get('auth-token'),
                    'Content-Type' : 'multipart/form-data',
                }
            }
            let fd = new FormData()
            fd.append('userName', this.userName)
            fd.append('userId', this.userId)
            fd.append('userNickname', this.userNickname)
            fd.append('userImage', this.userImage)
            fd.append('userIntro', this.userIntro)

            axios.post(this.SERVER_URL + '/api/users/info-update', fd, requestHeaders)
            .then(res => {
                this.$router.replace({ name: 'MyPage' })
            })
            .catch(err => console.error(err))
        },
        fileChange(e) {
            var file = e.target.files[0]
            if (file && file.type.match(/^image\/(png|jpeg)$/)) {
                this.userImageUrl = window.URL.createObjectURL(file)
            }
            this.userImage = this.$refs.userImage.files[0]
        },
    },
}
</script>

<style>
input:focus {
    outline: none;
}
input {
    width: 30rem;
}
.allIcon {
    color: red;
    visibility: hidden;
}
.updateView {
    background-color: #f5f6f7;
}
.input-box {
    border-style: solid;
    border-color: #dadada;
    border-width: 0.1rem;
    padding: 0.5rem;
    background-color: white;
}
</style>