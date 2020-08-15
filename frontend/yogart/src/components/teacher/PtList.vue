<template>
    <!-- 예약된 모든 PT를 확인할 수 있는 곳입니다. -->
    <div>
        <div v-for="pt in ptList" :key="pt.id">
            <span>{{ pt.ptName }}</span>
            <span>{{ pt.studentName }}</span>
            <span>{{ pt.dateTime }}</span>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'PtList',
    data() {
        return {
            ptList: [],
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    props: {
        teacherInfo: Object,
    },
    mounted() {
        // 아직 완료되지 않은 PT 내역을 보여줍니다.
        const requestHeaders = {
            headers: {
                Authorization: this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/api/teachers/pt-list', requestHeaders)
        .then(res => {
            this.ptList = res.data.ptList
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>