<template>
    <!-- Pt를 진행하는 페이지 입니다. 가장 가까운 시간의 PT를 보여줍니다. -->
    <div>
        <span>{{ ptData.ptName }}</span>
        <span>{{ ptData.studentName }}</span>
        <span>{{ ptData.dateTime }}</span>
        <a :href="ptUrl">수업 시작하기</a>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'PtAdmin',
    data() {
        return {
            ptData: null,
            SERVER_URL: this.$store.state.SERVER_URL,
            ptUrl: null,
        }
    },
    props: {
        teacherInfo: Object,
    },
    mounted() {
        const requestHeaders = {
            headers: {
                Authorization: this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/api/teachers/pt-now', requestHeaders)
        .then(res => {
            console.log("최근 수업 가져오기 성공",res)
            this.ptData = res.data.ptData
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>