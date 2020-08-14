<template>
    <div>
        <span>수업이름: {{ ptInfo.ptName }}</span>
        <span> 시간: {{ time.time }}시 시작</span>
        <span> 가격: {{ ptInfo.ptPrice }}스푼</span>
        <span> 소개: {{ ptInfo.ptIntro }}</span>
        <b-button id="show-btn" @click="showModal">신청</b-button>
        <b-modal ref="my-modal" hide-footer :title="modalTitle">
            <div class="d-block text-center">
                <h3>{{ptInfo.ptName}}<br>
                {{ dDay }} {{ time.time }}시</h3>
            </div>
                <b-button class="mt-3" variant="outline-danger" block @click="ptRegi">신청하기</b-button>
        </b-modal>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'PtListItem',
    props: {
        ptInfo: Object,
        time: Object,
        clickedDate: Date,
    },
    data() {
        return {
            modalTitle: `${ this.ptInfo.ptPrice } 스푼을 사용하시겠습니까?`,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    mounted() {
        this.clickedDate.setHours(this.time.time)
    },
    methods: {
        ptRegi() {
            // back으로 가격, clickedDate, ptInfo 보냅니다. 
            // 수업 생성하고, 유저 스푼 차감합니다.
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token')
                }
            }
            const ptData = {
                ptInfo: this.ptInfo,
                time: this.clickedDate
            }
            console.log(requestHeaders)
            console.log(ptData)
            axios.post(this.SERVER_URL + '/api/teachers/pt-regist', ptData, requestHeaders)
            .then(res => {
                this.$refs['my-modal'].hide()
                this.$router.push('/mypage')
            })
            .catch(err => console.error(err))
        },
        showModal() {
            this.$refs['my-modal'].show()
        },
    },
    computed: {
        dDay() {
            let year = this.clickedDate.getYear()
            let month = this.clickedDate.getMonth()
            let date = this.clickedDate.getDate()
            let day = this.clickedDate.getDay()
            const days = ['일', '월', '화', '수', '목', '금', '토']
            return `${year+1900}년 ${month+1}월 ${date}일 ${days[day]}요일`
        }
    }
}
</script>

<style>

</style>