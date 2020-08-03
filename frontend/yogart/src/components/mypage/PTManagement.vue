<template>
  <div v-if="ptManagement">
    <h3>PT Management</h3>
     
    <P>수강 중인 요가 수업</P>
    <P>남은 횟수</P>
    <P>박인영 강사님</P>
     
  </div>
</template>

<script>
export default {
    name : 'PTManagement',
    props : {
        ptManagement : Boolean,
    }
}
</script>

<style>

</style>