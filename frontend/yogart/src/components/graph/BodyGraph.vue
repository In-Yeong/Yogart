<template>
    <div class="timeGraph">
        <h1>태그별 트레이닝 횟수</h1>
        <div class="wrapper">
            <canvas id="bodyChart"></canvas>
        </div>
    </div>
</template>

<script>
    import LineChart from './LineChart.js'
    export default {
        name: 'BodyGraph',
        components: {
            LineChart
        },
        data() {
            return {
            }
        },
        mounted () {
        this.fillData()
        },
        methods: {
        fillData () {
            var bodydata = [0,0,0,0,0,0,0]
            var ctx = document.getElementById('bodyChart').getContext("2d");

            var birdsData = {
            labels: ["척추", "복근", "팔", "다리", "전신", "에너지", "릴렉싱"],
            datasets: [{
                data: bodydata,  // 데이터 들어가는 부분
                backgroundColor: [
                "rgba(255, 0, 0, 0.5)",
                "rgba(100, 255, 0, 0.5)",
                "rgba(200, 50, 255, 0.5)",
                "rgba(0, 100, 255, 0.5)",
                "rgba(255, 100, 0, 0.5)",
                "rgba(100, 100, 0, 0.5)",
                "rgba(100, 200, 150, 0.5)"
                ]
            }]
            };



            var bodyChart = new Chart(ctx, {
                data: birdsData,
                type: 'polarArea'
            });
        },

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