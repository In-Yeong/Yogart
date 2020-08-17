<template>
    <div class="page-index">
        <div class="under-border text-left">
            <h2>강사 목록</h2>
        </div>
        
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
    height: 8vh;
    width: 70%;
    margin: auto;
    margin-bottom : 1rem;
    /* border-bottom: 2px solid rgba(143, 160, 242, 0.5); */
}

</style>