<template>
<div class="card mb-3" style="max-width: 540px;">
  <div class="row no-gutters">
    <div class="col-md-4">
      <img :src="'http://localhost:8000/api/users/profileImageByEmail?userEmail=' + teacher.userEmail" class="card-img" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">{{ teacher.userName }}</h5>
        <p class="card-text">{{ teacher.userIntro }}</p>
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

<style>

</style>