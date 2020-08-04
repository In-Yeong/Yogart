<template>
<div class="card mb-3" style="max-width: 540px;">
  <div class="row no-gutters">
    <div class="col-md-4">
      <img :src="teacher.imgUrl" class="card-img" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">{{ teacher.name }}</h5>
        <p class="card-text">{{ teacher.tags }}</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
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
    }
}
</script>

<style>

</style>