<template>
    <div>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: TeacherDetail,
    // 어떤 정보들을 가져와야 할까
    data() {
        return {
            teacherId: $route.params.id
        }
    },
    mounted() {
        
    }
}
</script>

<style>

</style>