<template>
    <div class="navbar-area" style="margin-bottom=55px;">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/coaching/yogaposelist">AI coaching</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/class">1:1 PT</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    HELP
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/notice">공지사항</a>
                        <a class="dropdown-item" href="/qna">Q&A</a>
                    </div>
                </li>
                
                <li class="nav-item dropdown" v-if="isLogin">
                    <!-- <img class="user-profile-img m-3" :src="userPic"> -->
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    {{userNickname}}님
                    </a>
                    <div v-if="isAdmin" class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/mypage">마이페이지</a>
                        <a class="dropdown-item" href="/notice/form">공지작성</a>
                        <a class="dropdown-item" @click="logoutEmmit">로그아웃</a>
                    </div>
                    <div v-else-if="isTeacher" class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/mypage">마이페이지</a>
                        <a class="dropdown-item" href="#">수업관리</a>
                        <a class="dropdown-item" @click="logoutEmmit">로그아웃</a>
                    </div>
                    <div v-else class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/mypage">마이페이지</a>
                        <a class="dropdown-item" @click="logoutEmmit">로그아웃</a>
                    </div>
                </li>
                <li class="nav-item" v-else>
                    <a class="nav-link" @click="openLoginModal">로그인</a>
                </li>
                </ul>
            </div>
        </nav>
    </div> 

</template>

<script>
export default {
    name: 'NavBar',
    data() {
        return {
            userNickname: this.$cookies.get('userNickname'),
            userPic: "http://localhost:8000/api/users/profileImage?authToken=" + this.$cookies.get('auth-token'),
        }
    },
    methods: {
        openLoginModal() {
              if (this.isLogin) return
              $('#loginStaticBackdrop').modal('show')
        },
        logoutEmmit() {
            this.$emit('logout')
        },
    },
    props: {
        isLogin: Boolean
    },
}
</script>

<style>

.user-profile-img {
  display: inline-block;
  width: 200px;
  height: 200px;
  border-radius: 50%;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
} 
</style>