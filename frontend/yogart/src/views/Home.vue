<template>
<div>
    <div class="index-image1">
      <div class="index-text">
        <h1>이제는 스마트 홈트시대</h1>
        <h1>AI 요가 코칭을 통해 바른 자세로 교정해보세요</h1>
        <button class="w3-button w3-red w3-large my-3">START</button>
         <div>
          <i class="fa fa-2x fa-chevron-down mt-5" aria-hidden="true" style="color:black"></i>
        </div>
      </div>
    </div>
    <div class="index-image2">
      <div class="index-text">
        <h1>헬스 PT말고 요가를 원하셨다면</h1>
        <h1>1:1 요가 PT 시작해보세요</h1>
        <button class="w3-button w3-teal w3-large my-3">START</button>
      </div>
    </div>
    <!-- <div class="index-image">
      <div class="index-text">
        <h2>로딩 중입니다.</h2>
        <div>
          <i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i>
          <span class="sr-only">Loading...</span>
          <i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i>
      `   <span class="sr-only">Loading...</span>
      
      <i class="fa fa-refresh fa-spin fa-3x fa-fw" aria-hidden="true"></i>
      <span class="sr-only">Refreshing...</span>
      </div>
       
      </div>
    </div> -->
</div>
  
</template>

<script>
export default {
  name : 'Home',
}
</script>

<style>
.index-image1 {
  background-image: url("../assets/index/index_AI.jpg");
  background-color: black;
  height: 60vw;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
  
}
.index-image2 {
  background-image: url("../assets/index/yoga3.jpg");
  background-color: black;
  opacity: 0.9;
  height: 60vw;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}


.index-text {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: black;
}
</style>