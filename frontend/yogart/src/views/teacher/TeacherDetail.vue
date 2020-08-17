<template>
    <div>
        <img :src="teacherInfo.teacherImage" alt="">
        <h3>{{ teacherInfo.teacherName }} 선생님</h3>
        <h5>{{ teacherInfo.teacherIntro }}</h5>
        <div v-for="pt in ptList" :key="pt.ptId" @click="displayChange(pt.ptId)">{{ pt.ptName }}</div>
        <div v-for="pt in ptList" :key="pt.ptId" id="pt.ptId" style="display: none;">
            <ClassRegi :ptId="pt.ptId"></ClassRegi>
        </div>
        <!-- <b-tabs content-class="mt-3">
            <b-tab v-for="pt in ptList" :key="pt.ptId" :title="pt.ptName">
                <ClassRegi :ptId="pt.ptId"/>
            </b-tab>
        </b-tabs> -->
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
            showPt: ptLisit[0].ptId,
        }
    },
    mounted() {
        axios.get(this.SERVER_URL + `/api/teachers/list/detail/${this.teacherId}`, this.teacherId)
        .then(res => {
            console.log(res)
            this.teacherInfo.teacherName = res.data.teacherInfo.teacherName
            this.teacherInfo.teacherIntro = res.data.teacherInfo.teacherIntro
            this.teacherInfo.teacherImage = res.data.teacherInfo.teacherImage
            this.ptList = res.data.ptList
        })
        .catch(err => console.error(err))
        displayChange(this.showPt)
    },
    methods: {
        displayChange(ptId) {
            document.getElementById(this.showPt).style = "display: none;"
            this.showPt = ptId
            document.getElementById(this.showPt).style = "display: block;"
        }
    }
}
</script>

<style>

</style>