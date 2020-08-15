<template>
    <!-- Pt를 진행하는 페이지 입니다. 가장 가까운 시간의 PT를 보여줍니다. -->
    <div>
        <span>{{ pt.ptName }}</span>
        <span>{{ pt.studentName }}</span>
        <span>{{ pt.dateTime }}</span>
        <a :href="ptUrl">수업 시작하기</a>
    </div>
</template>

<script>
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
            this.ptData = res.data.ptData
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>