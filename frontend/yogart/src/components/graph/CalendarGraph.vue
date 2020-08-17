<template>
    <div class="timeGraph">
        <div class="graph-name">일별 운동량</div>
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
                // [new Date(2020, 8, 17), 20]
                ],
                chartOptions: {

                title: "",
                calendar: { cellSize: 11 },
                height: 150

                }
            };
        },
        props: {
            calendar: Object,
        },
        watch: {
            calendar() {
                var calendarArr = Object.entries(this.calendar)
                console.log('여기~~',calendarArr.length)
                calendarArr.forEach(e => {
                    var date = e[0].split('-')
                    console.log('날짜',date)
                    var run = e[1]*1
                    this.chartData.push([new Date(date[0]*1, (date[1]*1-1), date[2]*1), run])
                });
            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.wrapper {
    width: 95%;
    margin: auto;
    padding-top: 1.5rem;
}
.graph-name {
    margin: 2rem;
    font-size: 4vh;
    font-weight: bold;
    color: rgba(0, 0, 0, 0.6);
}

</style>