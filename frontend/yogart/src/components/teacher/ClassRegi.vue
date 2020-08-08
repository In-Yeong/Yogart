<template>
    <div>
        <h1>Hello</h1>
        <!-- 데이터 피커를 이용해 날짜를 받으면, 그 날짜에 열리는 모든 수업을 리스팅한다. -->
        <DatePicker format="yyyy-MM-dd-D" :value="date" :highlighted="highlighted"></DatePicker>
    </div>
</template>

<script>
import axios from 'axios'
import DatePicker from 'vuejs-datepicker'

export default {
    components: {
        DatePicker
    },
    methods: {
    },
    data() {
        return {
    highlighted: {
        dates: [],
    },
            date: null,
        }
    },
    mounted() {
        // 강사의 모든 열린 수업을 가져옵니다.
        // axios.get()
        // .then(res => {
            // 가져온 모든 열린 수업에 대해서 this.highlighted.dates에 new Date(yyyy, M, d)를 추가한다.
            const res = {
                data: {
                    classes: ['2020-08-09-sat']
                }
            }
            for (let i = 0; i < res.data.classes.length; i++) {
                let yyyy = Number(res.data.classes[i].slice(0, 4))
                let mm = Number(res.data.classes[i].slice(5,7)) - 1
                let dd = Number(res.data.classes[i].slice(8, 10))
                console.log(yyyy, mm, dd)
                this.highlighted.dates.push(new Date(yyyy, mm, dd))
                console.log(this.highlighted.dates[0])
            }
        // })
    }
}
</script>

<style>

</style>