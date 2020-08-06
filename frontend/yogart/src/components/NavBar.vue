<template>
      <!-- 여기 네브바 넣음 -->
    <!-- Start Navbar Area -->
    <div class="navbar-area" style="margin-bottom=55px;">
      <!-- Menu For Mobile Device -->
      <div class="container">

        <div class="mobile-nav">
          <router-link to="/" class="logo">
            Logo
          </router-link>
        </div>
      </div>

      <!-- Menu For Desktop Device -->
      <div class="main-nav">
        <div class="container">
          <nav class="navbar navbar-expand-md navbar-light">
            <router-link class="navbar-brand" to="/">
              Logo
            </router-link>
            <div class="collapse navbar-collapse mean-menu" id="navbarSupportedContent">
              <ul class="navbar-nav ml-auto">
                <!-- <li class="nav-item">
                  <a href="#" class="nav-link dropdown-toggle active">Service</a>
                  <ul class="dropdown-menu">
                    <li class="nav-item">
                     
                      <a href="#" class="nav-link">AI Coaching</a>
                    </li>
                    <li class="nav-item">
                      <a href="#" class="nav-link">1:1 Yoga PT</a>
                    </li>
                   
                    </li>
                  </ul>
                </li> -->
                <li class="nav-item">
                  <router-link to="/notice" class="nav-link">Notice</router-link>
                </li>
                <!-- 로그인과 사인업 로그인 중에는 안 보이도록 해야함. -> 로그아웃, 마이페이지 출력 -->
                <template v-if="!isLogin">
                  <li class="nav-item">
                    <a @click="openLoginModal" style="cursor:pointer;" class="nav-link">Login</a>
                  </li>
                  <li class="nav-item">
                    <router-link to="/accounts/signup" class="nav-link">Signup</router-link>
                  </li>
                </template>
                <template v-else>
                  <li class="nav-item">
                    <router-link to="/mypage" class="nav-link">Mypage</router-link>
                  </li>
                  <li class="nav-item">
                    <a @click="logoutEmmit" style="cursor:pointer;" class="nav-link">Logout</a>
                  </li>
                </template>

                <li class="nav-item">
                  <router-link to="#" class="nav-link dropdown-toggle">Pages</router-link>
                  <ul class="dropdown-menu">
                    <li class="nav-item">
                      <router-link to="#" class="nav-link">About</router-link>
                    </li>
                    <li class="nav-item">
                      <router-link to="#" class="nav-link">Services</router-link>
                    </li>
                    <li class="nav-item">
                      <router-link to="#" class="nav-link">Team</router-link>
                    </li>
                    <li class="nav-item">
                      <router-link to="#" class="nav-link">Testimonials</router-link>
                    </li>
                    <li class="nav-item">
                      <router-link to="#" class="nav-link">Contact</router-link>
                    </li>
                  </ul>
                </li>
                <li class="nav-item">
                  <router-link to="#" class="nav-link dropdown-toggle">Service</router-link>
                  <ul class="dropdown-menu">
                    <li class="nav-item">
                      <router-link to="#" class="nav-link dropdown-toggle">AI Coaching</router-link>
                      <ul class="dropdown-menu">
                        <li class="nav-item">
                          <router-link to="/coaching" class="nav-link">AI Coaching Page</router-link>
                        </li>
                        <li class="nav-item">
                          <router-link to="/coaching/yogapose" class="nav-link">Yoga Pose</router-link>
                        </li>
                        <li class="nav-item">
                          <router-link to="/coaching/yogaposelist" class="nav-link">Yoga Pose List</router-link>
                        </li>
                      </ul>
                    </li>
                    <li class="nav-item">
                      <router-link to="#" class="nav-link dropdown-toggle">1:1 Yoga PT</router-link>
                      <ul class="dropdown-menu">
                        <li class="nav-item">
                          <router-link to="#" class="nav-link">Yoga Teachers</router-link>
                        </li>
                        <li class="nav-item">
                          <router-link to="#" class="nav-link">Yoga Classes</router-link>
                        </li>
                        <li class="nav-item">
                          <router-link to="#" class="nav-link dropdown-toggle">Submenu 3</router-link>
                          <ul class="dropdown-menu">
                            <li class="nav-item">
                              <router-link to="#" class="nav-link">Item 1</router-link>
                            </li>
                            <li class="nav-item">
                              <router-link to="#" class="nav-link">Item 2</router-link>
                            </li>
                            <li class="nav-item">
                              <router-link to="#" class="nav-link">Item 3</router-link>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                    <li class="nav-item">
                      <router-link to="/qna" class="nav-link">Q&A</router-link>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </div>
    <!-- End Navbar Area -->
    <!-- 네브바 끝 -->
</template>

<script>
export default {
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
.mobile-nav {
    background: #000;
    position: absolute;
    top: 0;
    left: 0;
    padding-top: 15px;
    padding-bottom: 15px;
    width: 100%;
    z-index: 999;
    height: 99px;
}
</style>