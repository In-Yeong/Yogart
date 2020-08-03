<template>
    <div v-if="diary">
        <h3>다이어리</h3>
        <p>아직 게시물이 없습니다.</p>
    </div>
</template>

<script>
export default {
    name : 'Diary',
    props : {
        diary : Boolean,
    },

}
</script>

<style>

</style>