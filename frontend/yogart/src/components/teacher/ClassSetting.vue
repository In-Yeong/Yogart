<template>
    <div class="m-5">
        <label for="class_name">수업 이름</label>
        <input type="text" id="class_name" v-model="ptName">
        <br>
        <label for="class_price">회당 가격</label>
        <input type="text" id="class_price" v-model="ptPrice">
        <br>
        <label for="class_intro">수업 소개</label>
        <input type="text" id="class_intro" v-model="ptIntro">
        <table class="table table-dark">
        <thead>
            <tr>
            <th scope="col">시</th>
            <th scope="col">월</th>
            <th scope="col">화</th>
            <th scope="col">수</th>
            <th scope="col">목</th>
            <th scope="col">금</th>
            <th scope="col">토</th>
            <th scope="col">일</th>
            </tr>
        </thead>
        <tbody>
            <TimeTable v-for="time in times" :key="time" :time="time" :clicked="clicked" @cellClicked="cellClicked"></TimeTable>
        </tbody>
        </table>
        <button @click="onSubmit">수업 생성</button>
    </div>
</template>

<script>
import axios from 'axios'
import TimeTable from './TimeTable.vue'

export default {
    name: 'ClassSetting',
    components: {
        TimeTable
    },
    data() {
        return {
            times: [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
            clicked: [],
            ptName: null,
            ptPrice: null,
            ptIntro: null,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    methods: {
        cellClicked(payload) {
            console.log(payload.day, payload.time)
            if (payload.isClicked) {
                this.clicked.push({day: payload.day, time: payload.time})
            } else {
                const idx = this.clicked.findIndex(dayTime => {
                    return dayTime.day === payload.day && dayTime.time === payload.time
                })
                if (idx > -1) this.clicked.splice(idx, 1)
            }
            console.log(this.clicked)
        },
        onSubmit(e) {
            e.preventDefault()
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token')
                }
            }
            const ptData = {
                ptName: this.ptName,
                ptPrice: this.ptPrice,
                ptIntro: this.ptIntro,
                clicked: this.clicked,
            }
            console.log(ptData)
            // 데이터 저장을 요청합니다. 데이터를 저장하며 clicked에 맞춰 수업을 생성해야 합니다.
            axios.post(this.SERVER_URL + '/api/teachers/pt-create', ptData, requestHeaders)
            .then(res => {
                // 강사 상세페이지로 이동
                // this.$router.push('')
            })
            .catch(err => console.error(err))
        }
    },
    mounted() {
        // // 전에 저장했던 강사의 수업 가능 시간을 받아 옵니다.
        // axios.get()
        // .then(res => {
        //     this.clicked = res.data.clicked
        // })
    },
}
</script>

<style>
th {
    border-style: solid;
}
</style>