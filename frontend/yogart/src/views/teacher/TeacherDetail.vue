<template>
    <div class="padding-for-nav padding-remove">
        <div class="under-border my-3">
            <img v-if="teacherInfo.teacherImage" :src="teacherInfo.teacherImage" alt="" class="user-profile">
            <img v-else src="../../assets/userDefault.jpg" class="user-profile m-3">
            <h3>{{ teacherInfo.teacherName }} 선생님</h3>
            <h5 v-if="teacherInfo.teacherIntro">{{ teacherInfo.teacherIntro }}</h5>
            <h5 v-else>등록된 소개가 없습니다.</h5>
        </div>
        <b-tabs content-class="mt-3">
            <b-tab v-for="pt in ptList" :key="pt.ptId" :title="pt.ptName">
                <ClassRegi :ptId="pt.ptId"/>
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
            this.teacherInfo.teacherName = res.data.teacherInfo.userNickname
            this.teacherInfo.teacherIntro = res.data.teacherInfo.userIntro
            this.teacherInfo.teacherImage = res.data.teacherInfo.userProfile
            this.ptList = res.data.ptList
        })
        .catch(err => console.error(err))

    }
}
</script>

<style scoped>
.under-border{
    box-shadow: 3px 3px rgba(0,0,0,0.1);
}
.padding-remove{
    margin-top: -8rem;
    margin-bottom: 17rem;
}

</style>
