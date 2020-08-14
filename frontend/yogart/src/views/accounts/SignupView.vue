<template>
    <div class="padding-for-nav">
        <h1 class="py-3">회원가입</h1>
        <div class="container d-flex justify-content-center text-left">
            <div class="d-block">
                <div class="form-group">
                    <label for="userEmail">이메일</label>
                    <br>
                    <div class="input-box">
                        <input class="border-0" type="text" id="userEmail" v-model="signupData.userEmail" @keyup="checkEmail">
                        <!-- <span class="allIcon" id="userEmailCheckIcon">아이콘</span> -->
                    </div>
                    
                </div>
                <div class="form-group">
                    <label for="userName">이름</label>
                    <br>
                    <div class="input-box">
                        <input class="border-0" type="text" id="userName" v-model="signupData.userName" @keyup="checkName">
                        <!-- <span class="allIcon" id="userNameCheckIcon">아이콘</span> -->
                    </div>
                </div>
                <div class="form-group">
                    <label for="userNickname">닉네임</label>
                    <br>
                    <div class="input-box">
                        <input class="border-0" type="text" id="userNickname" v-model="signupData.userNickname" @keyup="checkNickname">
                        <!-- <span class="allIcon" id="userNicknameCheckIcon">아이콘</span> -->
                    </div>
                </div>
                <div class="form-group">
                    <label>비밀번호</label>
                    <br>
                    <div class="input-box">
                        <input class="border-0" type="password" id="userPassword" v-model="signupData.userPassword" @keyup="checkPassword">
                        <span class="allIcon" id="userPasswordCheckIcon">아이콘</span>
                    </div>
                    <small class="form-text text-muted">숫자, 영문 포함 8자 이상</small>
                </div>
                <div class="form-group">
                    <label>비밀번호 확인</label>
                    <br>
                    <div class="input-box">
                        <input class="border-0" type="password" id="userRePassword" v-model="signupData.userRePassword" @keyup="checkRePassword">
                        <span class="allIcon" id="userRePasswordCheckIcon">아이콘</span>
                    </div>
                </div>
                <button @click="signup" class="btn btn-primary mt-4 col-12">회원가입</button>
            </div> 
        </div>
    </div>
</template>

<script>

export default {
    name: 'SignupView',
    data() {
        return {
            signupData: {
                userEmail: '',
                userName: '',
                userNickname: '',
                userPassword: '',
                userRePassword: '',
            },
            formCheck: {
                userEmail: false,
                userName: false,
                userNickname: false,
                userPassword: false,
                userRePassword: false,
            },
        }
    },
    methods: {
        signup() {
            // App.vue로 이벤트 발생
            console.log(this.formCheck)

            if (this.formCheck.userEmail === false) {
                alert('올바른 이메일을 입력해주세요.')
            } else if (this.formCheck.userName === false) {
                alert('올바른 이름을 입력해주세요.')
            } else if (this.formCheck.userNickname === false) {
                alert('올바른 닉네임을 입력해주세요.')
            } else if (this.formCheck.userPassword === false) {
                alert('숫자, 영문 포함 8자 이상 비밀번호를 입력해주세요')
            } else if (this.formCheck.userRePassword === false) {
                alert('비밀번호가 일치하지 않습니다.')
            } else {
                this.$emit('submit-signup-data', this.signupData)
            }

        },
        checkEmail() {
            // trim으로 공백 제거
            this.signupData.userEmail = this.signupData.userEmail.trim()
            // 이메일 형식(@와 .)을 갖추고 있고 45자 이하이면 체크 아이콘 색상 변경
            if (this.signupData.userEmail.includes('@') & this.signupData.userEmail.includes('.') & this.signupData.userEmail.length < 46) {      
                this.formCheck.userEmail = true
            } else {
                this.formCheck.userEmail = false
            }
        },
        checkName() {
            // trim으로 공백 제거
            this.signupData.userName = this.signupData.userName.trim()
            // 1~45자 사이면 체크 아이콘 색상 변경
            if (this.signupData.userName.length > 0 & this.signupData.userName.length < 46) {
                this.formCheck.userName = true
            } else {
                this.formCheck.userName = false
            }
        },
        checkNickname() {
            // trim으로 공백 제거
            this.signupData.userNickname = this.signupData.userNickname.trim()
            // 1~45자 사이면 체크 아이콘 색상 변경
            if (this.signupData.userNickname.length > 0 & this.signupData.userNickname.length < 46) {
                this.formCheck.userNickname = true
            } else {
                this.formCheck.userNickname = false
            }
        },
        checkPassword() {
            // 입력을 시작하면 체크 아이콘이 보이게 함
            document.getElementById('userPasswordCheckIcon').style.visibility = 'visible'
            // 영문과 숫자 체크
            var checkNumber = this.signupData.userPassword.search(/[0-9]/g);
            var checkEnglish = this.signupData.userPassword.search(/[a-z]/ig);
            // 영문과 숫자를 포함하고 8~45자 사이면 체크 아이콘 색상 변경
            if (checkNumber !== -1 & checkEnglish !== -1 & this.signupData.userPassword.length > 7 & this.signupData.userPassword.length < 46) {
                document.getElementById('userPasswordCheckIcon').style.color = 'blue'
                this.formCheck.userPassword = true
            } else {
                document.getElementById('userPasswordCheckIcon').style.color = 'red'
                this.formCheck.userPassword = false
            }
        },
        checkRePassword() {
            // 입력을 시작하면 체크 아이콘이 보이게 함
            document.getElementById('userRePasswordCheckIcon').style.visibility = 'visible'
            // 비밀번호가 일치하면 체크 아이콘 색상 변경
            if (this.signupData.userRePassword === this.signupData.userPassword) {
                document.getElementById('userRePasswordCheckIcon').style.color = 'blue'
                this.formCheck.userRePassword = true
            } else {
                document.getElementById('userRePasswordCheckIcon').style.color = 'red'
                this.formCheck.userRePassword = false
            }
        }
    },
}
</script>

<style scoped>
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
.input-box {
    border-style: solid;
    border-color: #dadada;
    border-width: 0.1rem;
    padding: 0.5rem;
    background-color: white;
}
</style>