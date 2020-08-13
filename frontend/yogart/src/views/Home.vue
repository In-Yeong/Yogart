<template>
<div class="main">
    <div class="index-image1"></div>
    <div class="index-image2"></div>
</div>
  
</template>

<script>
export default {
  name : 'Home',
  methods: {
    clickAiCoaching() {
        this.$router.push('/coaching/yogaposelist')
    }
  },
}
</script>

<style>
.index-image1 {
  background-image: url("../assets/index/main1.jpg");
  height: 56vw;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  
}
.index-image2 {
  background-image: url("../assets/index/main2.jpg");
  height: 56vw;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
.main {
    position: relative;
    top: -8vh;
}
</style>