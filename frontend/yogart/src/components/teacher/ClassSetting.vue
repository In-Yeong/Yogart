<template>
    <div>
        <label for="class_name">수업 이름</label>
        <input type="text" id="class_name">
        <br>
        <label for="class_price">회당 가격</label>
        <input type="text" id="class_price">
        <!-- 데이터 피커를 이용해 날짜를 받으면, 그 날짜에 열리는 모든 수업을 리스팅한다. -->
        <DatePicker></DatePicker>
    </div>
</template>

<script>
import DatePicker from 'vuejs-datepicker'

export default {
    name: 'ClassSetting',
    components: {
        DatePicker
    },
    mounted() {
    
    }
}
</script>

<style>

</style>