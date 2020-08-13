<template>
    <div>
        <img :src="teacherInfo.teacherImage" alt="">
        <h3>{{ teacherInfo.teacherName }} 선생님</h3>
        <h5>{{ teacherInfo.teacherIntro }}</h5>
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
        // const requestHeaders = {
        //     headers: {
        //         Authorization: this.$cookies.get('auth-token')
        //     }
        // }
        axios.get(this.SERVER_URL + `/api/teachers/list/detail/${this.teacherId}`, this.teacherId)
        .then(res => {
            console.log(res)
            // const res = {
            //     data: {
            //         teacherInfo: {
            //             teacherName: '조규성',
            //             teacherIntro: '나도 요가 초보임',
            //             teacherImage: '...'
            //         },
            //         ptList: [
            //             {
            //                 id: 1,
            //                 ptName: '파국이다'
            //             },
            //             {
            //                 id: 2,
            //                 ptName: '계란국이다'
            //             }
            //         ]
            //     }
            // }
            this.teacherInfo.teacherName = res.data.teacherInfo.userName
            this.teacherInfo.teacherIntro = res.data.teacherInfo.userIntro
            this.teacherInfo.teacherImage = res.data.teacherInfo.userProfile
            this.ptList = res.data.ptList
        })
        .catch(err => console.error(err))

    }
}
</script>

<style>

</style>