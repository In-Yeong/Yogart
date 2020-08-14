<template>
    <div>
        <h2>{{ ptInfo.ptName }}</h2>
        <h5>{{ ptInfo.ptPrice }}스푼으로 함께해요</h5>
        <h5>{{ ptInfo.ptIntro }}</h5>
        <!-- 데이터 피커를 이용해 날짜를 받으면, 그 날짜에 열리는 모든 수업을 리스팅한다. -->
        <DatePicker format="yyyy-MM-dd-D" :highlighted="highlighted" @selected="selectDate" :language="ko"></DatePicker>
        <div v-for="time in showArray" :key="time.time">
            <PtListItem :ptInfo="ptInfo" :time="time" :clickedDate="clickedDate"></PtListItem>
        </div>
        <div>

        </div>
    </div>
</template>

<script>
import axios from 'axios'
import DatePicker from 'vuejs-datepicker'
import { ko } from 'vuejs-datepicker/dist/locale'
import PtListItem from './PtListItem.vue'

export default {
    name: 'ClassRegi',
    components: {
        DatePicker,
        PtListItem,
    },
    props: {
        ptId: Number,
    },
    methods: {
        selectDate(date) {
            // 유저가 날짜를 선택하면 그날에 열리는 수업을 보여줘야 합니다.
            this.clickedDate = date
            let day = date.getDay()
            this.showArray.length = 0
            let self = this
            this.showArray = this.ptTimes.filter(pt => {
                let isSoldOut = true
                for (let k = 0; k < self.soldOut.length; k++) {
                    if (date.getDate() === self.soldOut[k].getDate() && pt.ptTime === self.soldOut[k].getHours()) {
                        isSoldOut = false
                    }
                }
                return pt.ptDay === day && isSoldOut
            })
        }
    },
    data() {
        return {
            highlighted: {
                dates: [],
            },
            ko: ko,
            ptInfo: {
                ptTeacher: null,
                ptId: null,
                ptName: null,
                ptPrice: null,
                ptIntro: null,
            },
            ptTimes: [],
            soldOut: [],
            showArray: [],
            clickedDate: null,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    mounted() {
        // 강사의 수업 정보와 이미 예약된 PT리스트를 가져옵니다.
        axios.get(this.SERVER_URL + `/api/teachers/pt/${this.ptId}`, this.ptId)
        .then(res => {
            // console.log(res)
            // let res = {
            //     data: {
            //         ptTeacher: 12, // 요가강사 id값
            //         ptId: 1,
            //         ptName: '요가를 처음 하는 사람을 위함',
            //         ptPrice: 250,
            //         ptIntro: '편하게 시작해 봐요',
            //         clicked: [{day: 3, time:20}, {day:4, time: 19}, {day: 4, time:18}, {day:3, time: 18}],
            //         soldOut: [ new Date(2020, 7, 13, 19), new Date(2020, 7, 12, 18)]
            //     }
            // }
            this.ptTimes = res.data.clicked
            this.ptInfo.ptTeacher = res.data.ptInfo.ptTeacherId
            this.ptInfo.ptId = res.data.ptInfo.ptId
            this.ptInfo.ptName = res.data.ptInfo.ptName
            this.ptInfo.ptPrice = res.data.ptInfo.ptPrice
            this.ptInfo.ptIntro = res.data.ptInfo.ptIntro
            const self = this
            res.data.soldOut.forEach(function(e){
                let yyyy = Number(e.slice(0, 4))
                let mm = Number(e.slice(5,7)) - 1
                let dd = Number(e.slice(8, 10))
                self.soldOut.push(new Date(yyyy, mm, dd))
            })
            const today = new Date() // 오늘의 날짜
            for (let i = 0; i < 14; i++) {
                let cnt = 0
                let nextDay = new Date(today)
                nextDay.setDate(today.getDate() + i) // 오늘 + i 날
                let day = nextDay.getDay()
                for (let j = 0; j < self.ptTimes.length; j++) {
                    if (self.ptTimes[j].ptDay === day) {
                        cnt++
                    }
                }
                for (let j = 0; j < self.soldOut.length; j++) {
                    if (nextDay.getDate() === self.soldOut[j].getDate()) {
                        cnt--
                    }
                }
                if (cnt > 0) {
                    self.highlighted.dates.push(nextDay)
                }
            }
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>