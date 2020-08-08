<template>
    <div class="m-5">
        <label for="class_name">수업 이름</label>
        <input type="text" id="class_name">
        <br>
        <label for="class_price">회당 가격</label>
        <input type="text" id="class_price">
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
            <TimeTable v-for="time in times" :key="time" :time="time"></TimeTable>
        </tbody>
        </table>
    </div>
</template>

<script>
import TimeTable from './TimeTable.vue'

export default {
    name: 'ClassSetting',
    components: {
        TimeTable
    },
    data() {
        return {
            times: [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
        }
    },
    mounted() {
    
    }
}
</script>

<style>
th {
    border-style: solid;
}
</style>