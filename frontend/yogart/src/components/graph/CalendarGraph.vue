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
                ],
                chartOptions: {

                title: "",
                calendar: { cellSize: 14 },
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
                calendarArr.forEach(e => {
                    var date = e[0].split('-')
                    var run = e[1]*1
                    this.chartData.push([new Date(date[0]*1, date[1]*1, date[2]*1), run])
                    console.log(date)
                });
            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.wrapper {
    width: 50rem;
    margin: auto;
}


</style>