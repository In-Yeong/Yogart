<template>
    <div>
        <!-- 수업 설정, 수업 진행, 수업 현황 -->
        <div>
            <b-tabs content-class="mt-3">
                <b-tab title="오늘의 수업" :teacherInfo="teacherInfo" active><PtAdmin /></b-tab>
                <b-tab title="예약된 수업" :teacherInfo="teacherInfo" ><PtList /></b-tab>
                <b-tab title="수업 추가" :teacherInfo="teacherInfo"><ClassSetting /></b-tab>
            </b-tabs>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import PtAdmin from '@/components/teacher/PtAdmin.vue'
import PtList from '@/components/teacher/PtList.vue'
import ClassSetting from '@/components/teacher/ClassSetting.vue'

export default {
    name: 'TeacherPage',
    components: {
        PtAdmin,
        PtList,
        ClassSetting,
    },
    data() {
        return {
            teacherInfo: null,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    mounted() {
        const requestHeaders = {
            headers: {
                Authorization: this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/api/teacher', requestHeaders)
        .then(res => {
            this.teacherInfo = res.data.teacherInfo
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>