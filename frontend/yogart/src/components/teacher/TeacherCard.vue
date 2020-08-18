<template>
  <div class="container">
  <div class="row no-gutters">
    <div class="col-sm-4 d-flex flex-column box">
        <div>
          <img v-if="teacher.teacherImage" :src="teacher.teacherImage" class="user-profile" alt="...">
          <img v-else src="../../assets/userDefault.jpg" class="user-profile" alt="...">
          <h5>{{ teacher.userName }}</h5>
        <p v-if="teacher.userIntro">{{ teacher.userIntro }}</p>
        <p v-else>강사 소개를 해주세요.강사 소개를 해주세요.강사 소개를 해주세요.강사 소개를 해주세요.강사 소개를 해주세요.강사 소개를 해주세요.</p>
        </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
    name: 'TeacherCard',

    props: {
        teacher: Object,
    },

}
</script>

<style scoped>
.user-profile {
  display: inline-block;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border : 2px solid rgba(255, 255, 255, 0.5);

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}
.box{
    padding :10px;
    width : 100%;
    background-color: rgba(255, 255, 255, 0.3);
    box-shadow: 3px 3px rgba(143, 160, 242, 0.5);
}

.box:hover{
    cursor:pointer;
    background-color: rgba(143, 160, 242, 0.2);
    border : 2px solid rgba(143, 160, 242, 0.5);
}
.container{
  padding : 0
}
</style>