<template>
    <div v-if="exerciseHistory">
        <h3>운동기록</h3>
        <p>그래프들이 추가될 예정</p>
    </div>
</template>

<script>
export default {
    name : 'ExerciseHistory',
    props : {
        exerciseHistory : Boolean,
    }
}
</script>

<style>

</style>