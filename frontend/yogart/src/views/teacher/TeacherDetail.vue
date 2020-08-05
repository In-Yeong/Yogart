<template>
    <div>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'TeacherDetail',
    // 어떤 정보들을 가져와야 할까
    data() {
        return {
            teacherId: $route.params.teacher_id
        }
    },
    mounted() {

    }
}
</script>

<style>

</style>