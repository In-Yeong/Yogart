<template>
    <div>
        <h1>pt Regi
        </h1>
        <!-- 데이터 피커를 이용해 날짜를 받으면, 그 날짜에 열리는 모든 수업을 리스팅한다. -->
        <DatePicker format="yyyy-MM-dd-D" :highlighted="highlighted" @selected="selectDate" :language="ko"></DatePicker>
        <div v-for="pt in showArray" :key="pt.id">
            <PtListItem :pt="pt"></PtListItem>
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
    components: {
        DatePicker,
        PtListItem,
    },
    methods: {
        selectDate(date) {
            // 유저가 날짜를 선택하면 그에 맞는 날짜에 열리는 수업을 보여줘야 합니다.
            let year = date.getYear() + 1900
            let month = date.getMonth() + 1
            let day = date.getDate()
            let selectedDate = `${year}-${month}-${day}`
            this.showArray.length = 0
            this.showArray = this.ptArray.filter(pt => {
                return pt.date === selectedDate
            })
        }
    },
    data() {
        return {
            highlighted: {
                dates: [],
            },
            ko: ko,
            ptArray: [],
            showArray: [],
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    mounted() {
        // 강사의 모든 열린 수업을 가져옵니다.
        // axios.get(this.SERVER_URL + '/api/teachers/open-pt')
        // .then(res => {
            // 가져온 모든 열린 수업을 ptArray에 저장한다.
            // this.ptArray = res.ptArray
            // 수업이 열린 날을 표시하기 위해 this.highlighted.dates에 new Date(yyyy, M, d)를 추가한다.
            // for (let i = 0; i < res.data.pt.length; i++) {
            //     let yyyy = Number(res.data.pt[i].slice(0, 4))
            //     let mm = Number(res.data.pt[i].slice(5,7)) - 1
            //     let dd = Number(res.data.pt[i].slice(8, 10))
            //     this.highlighted.dates.push(new Date(yyyy, mm, dd))
//             // }
//             var day = new Date('Apr 30, 2000');
// console.log(day); // Apr 30 2000

// var nextDay = new Date(day);
// nextDay.setDate(day.getDate() + 1);
// console.log(nextDay); // May 01 2000 
        // })
    }
}
</script>

<style>

</style>