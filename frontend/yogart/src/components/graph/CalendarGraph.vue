<template>
    <div class="timeGraph">
        <h1>일별 운동량</h1>
        <div class="wrapper">
            <GChart
            :settings="{packages: ['calendar']}"
            type="Calendar"
            :data="chartData"
            :options="chartOptions"
            />
        </div>
    </div>
</template>

<script>
    import { GChart } from "vue-google-charts";
    export default {
        name: 'TimeGraph',
        components: {
            GChart
        },
        data() {
            return {
                chartData: [
                ['a','b'],// 데이터(날짜, 운동시간) 들어가는 부분
                [ new Date(2020, 7, 21), 100 ],
                [ new Date(2020, 7, 22), 50 ],
                [ new Date(2020, 7, 23), 30 ],
                [ new Date(2020, 7, 24), 70 ],
                [ new Date(2020, 7, 27), 60 ]
            ],
            chartOptions: {

            title: "",
            calendar: { cellSize: 14 },
            height: 150

            }
            };
        },
       



        
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.wrapper {
    width: 50rem;
    margin: auto;
}


</style>