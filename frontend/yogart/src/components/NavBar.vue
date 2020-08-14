<template>
    <div id="navbar-container">
        <div class="navbar navbar-expand navbar-light fixed-top w-100" :class="{ 'navbar--hidden': !showNavbar, 'change--color': lastScrollPosition > 100 }">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav nav-left">
                <li class="nav-item">
                    <a class="nav-link" href="/coaching/yogaposelist">AI coaching</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/class">1:1 PT</a>
                </li>
                </ul>
                <a class="navbar-brand" href="/"><img class="logo-img" src="../assets/logo.png" alt=""></a>
                <ul class="navbar-nav nav-right">
                <li class="nav-item dropdown">
                    <div class="nav-link" id="navbarDropdown"  data-toggle="dropdown" >
                    HELP
                    </div>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <a class="sub-item nav-link" href="/notice">공지사항</a>
                        <a class="sub-item nav-link" href="/qna">Q&A</a>
                    </div>
                </li>
                
                <li class="nav-item dropdown" v-if="isLogin">
                    <!-- <img class="user-profile-img m-3" :src="userPic"> -->
                    <a class="nav-link" id="navbarDropdown" data-toggle="dropdown">
                    {{userNickname}}님
                    </a>
                    <div v-if="isAdmin" class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <a class="sub-item nav-link" href="/mypage">마이페이지</a>
                        <a class="sub-item nav-link" href="/notice/form">공지작성</a>
                        <a class="sub-item nav-link" @click="logoutEmmit">로그아웃</a>
                    </div>
                    <div v-else-if="isTeacher" class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <a class="sub-item nav-link" href="/mypage">마이페이지</a>
                        <a class="sub-item nav-link" href="#">수업관리</a>
                        <a class="sub-item nav-link" @click="logoutEmmit">로그아웃</a>
                    </div>
                    <div v-else class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <a class="sub-item nav-link" href="/mypage">마이페이지</a>
                        <a class="sub-item nav-link" @click="logoutEmmit">로그아웃</a>
                    </div>
                </li>
                <li class="nav-item" v-else>
                    <a class="nav-link" @click="openLoginModal">LOGIN</a>
                </li>
                </ul>
            </div>
        </div>
    </div> 

</template>

<script>
export default {
    name: 'NavBar',
    data() {
        return {
            userNickname: this.$cookies.get('userNickname'),
            userPic: "http://localhost:8000/api/users/profileImage?authToken=" + this.$cookies.get('auth-token'),
            showNavbar: true,
            lastScrollPosition: 0
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
        onScroll () {   // 스크롤 내리면 nav 사라지고 올리면 생기는 함수
            const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop
            if (currentScrollPosition < 0) {
                return
            }
            // Stop executing this function if the difference between
            // current scroll position and last scroll position is less than some offset
            if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 60) {
                return
            }
            this.showNavbar = currentScrollPosition < this.lastScrollPosition
            this.lastScrollPosition = currentScrollPosition
        }

    },
    mounted () {
        window.addEventListener('scroll', this.onScroll)
    },
    beforeDestroy () {
        window.removeEventListener('scroll', this.onScroll)
    },
    props: {
        isLogin: Boolean
    },
}

</script>

<style>
#navbar-container{
    height: 8vh;
}
.logo-img {
    height: 8vh;
}
.navbar {
    transform: translate3d(0, 0, 0);
    transition: 0.1s all ease-out;
    padding: 0 !important;
}
.navbar.navbar--hidden {
  box-shadow: none;
  transform: translate3d(0, -100%, 0);
}

.navbar.change--color {
    background-color: rgba(255, 255, 255, 0.5) !important;
}

.navbar-brand {
    text-align: center;
    margin: 0 auto !important;
}

.nav-link {
    color: gray !important;
    position: relative;
    font-size: 1.2rem;
    font-weight: 500;
    padding: 1rem !important;
}
.nav-link:after {    
  content: "";
  display: block;
  height: 4px;
  background: rgba(242, 157, 143, 1);
  transition: width 0.7s ease 0s, left 0.3s ease 0s;
  width: 0;
}
.nav-link:hover:after { 
  width: 8rem !important;
}
.nav-right {
    position: fixed;
    right: 1rem;
}
.nav-left {
    position: fixed;
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

.dropdown-menu {
    display: none;
    background-color: rgba(255, 255, 255, 0) !important;
    border: none !important;
    padding: 0 !important;
    top: 8vh !important;
}
.dropdown-menu-right {
    position: fixed !important;
     right: 0 !important;
}
.dropdown:hover .dropdown-menu {
    display: block;
    margin-top: 0;
 }

.sub-item {
    text-align: center;
}
</style>