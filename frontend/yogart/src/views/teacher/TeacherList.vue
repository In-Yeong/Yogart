<template>
    <div>
        <h1>강사 목록</h1>
        <TeacherCard v-for="teacher in teachers" :key="teacher.id" :teacher="teacher" @click.native="onClick(teacher.id)"></TeacherCard>
    </div>
</template>

<script>
import axios from 'axios'
import TeacherCard from '@/components/teacher/TeacherCard'

export default {
    name: 'TeacherList',
    components: {
        TeacherCard,
    },
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
            teachers: null,
        }
    },
    created() {
        axios.get(this.SERVER_URL + '/api/teachers')
        .then(res => {
            this.teachers = res.data.teachers
        })
        .catch(err => cosole.error(err))
    },
    methods: {
        onClick(teacherId) {
            this.$router.push(`/teacher/${teacherId}`)
        }
    }
}
</script>

<style>

</style>