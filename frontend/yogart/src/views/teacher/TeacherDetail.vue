<template>
    <div>
        <img :src="teacherInfo.teacherImage" alt="">
        <h3>{{ teacherInfo.teacherName }} 선생님</h3>
        <h5>{{ teacherIntro }}</h5>
        <b-tabs content-class="mt-3">
            <b-tab v-for="pt in ptList" :key="pt.id" :title="pt.ptName">
                <ClassRegi :ptId="pt.id"/>
            </b-tab>
        </b-tabs>
    </div>
</template>

<script>
import axios from 'axios'
import ClassRegi from '@/components/teacher/ClassRegi.vue'

export default {
    name: 'TeacherDetail',
    components: {
        ClassRegi,
    },
    data() {
        return {
            teacherId: this.$route.params.teacher_id,
            SERVER_URL: this.$store.state.SERVER_URL,
            teacherInfo: {
                teacherName: null,
                teacherIntro: null,
                teacherImage: null,
            },
            ptList: [],
        }
    },
    mounted() {
        const requestHeaders = {
            headers: {
                Authorization: 'Token ' + this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/api/teacher-detail', this.teacherId, requestHeaders)
        .then(res => {
            this.teacherInfo.teacherName = res.data.teacherInfo.teacherName
            this.teacherInfo.teacherIntro = res.data.teacherInfo.teacherIntro
            this.teacherInfo.teacherImage = res.data.teacherInfo.teacherImage
            this.ptList = res.data.ptList
        })
        .catch(err => console.error(err))

    }
}
</script>

<style>

</style>