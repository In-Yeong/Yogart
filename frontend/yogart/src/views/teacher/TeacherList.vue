<template>
    <div class="padding-for-nav">
        <div class="page-index d-flex justify-content-between">
            <div class="page-name">강사 목록</div>
        </div>
        <div class="container">
            <div class="row">
                <TeacherCard v-for="teacher in teachers" :key="teacher.id" :teacher="teacher" @click.native="onClick(teacher.id)"></TeacherCard>
            </div>
        </div>
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
        axios.get(this.SERVER_URL + '/api/teachers/list')
        .then(res => {
            console.log(res)
            this.teachers = res.data
        })
        .catch(err => cosole.error(err))
    },
    methods: {
        onClick(teacherId) {
            this.$router.push(`/teachers/${teacherId}`)
        }
    }
}
</script>

<style scoped>
.page-index {
    width: 75%;
    margin: auto;
    margin-bottom : 1rem;
    border-bottom: 2px solid rgba(143, 160, 242, 0.5);
}
.page-name {
    margin-left: 1vh;
    font-size: 4vh;
    font-weight: bold;
    color: rgba(0, 0, 0, 0.6);
}
</style>