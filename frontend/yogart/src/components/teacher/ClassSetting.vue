<template>
    <div>
        <label for="class_name">수업 이름</label>
        <input type="text" id="class_name">
        <br>
        <label for="class_price">회당 가격</label>
        <input type="text" id="class_price">
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