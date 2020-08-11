<template>
    <div class="timeGraph">
        <h1>시간별 트레이닝 횟수</h1>
        <div class="wrapper">
            <canvas id="myChart"></canvas>
        </div>
    </div>
</template>

<script>
    import LineChart from './LineChart.js'
    export default {
        name: 'TimeGraph',
        components: {
            LineChart
        },
        data() {
            return {
            }
        },
        props: {
            time: Object,
        },
        mounted () {
        this.fillData()
        },
        methods: {
        fillData () {
            var ctx = document.getElementById('myChart').getContext("2d");

            var gradientStroke = ctx.createLinearGradient(600, 0, 0, 0);
            gradientStroke.addColorStop(0, '#80b6f4');
            gradientStroke.addColorStop(1, '#f49080');

            var gradientFill = ctx.createLinearGradient(600, 0, 0, 0);
            gradientFill.addColorStop(0, "rgba(128, 182, 244, 0.6)");
            gradientFill.addColorStop(1, "rgba(244, 144, 128, 0.6)");

            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ["06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00", "01", "02", "03", "04", "05",],
                    datasets: [{
                        label: "시간 별 트레이닝 횟수",
                        borderColor: gradientStroke,
                        pointBorderColor: gradientStroke,
                        pointBackgroundColor: gradientStroke,
                    pointHoverBackgroundColor: gradientStroke,
                        pointHoverBorderColor: gradientStroke,
                        pointBorderWidth: 10,
                        pointHoverRadius: 10,
                        pointHoverBorderWidth: 1,
                        pointRadius: 3,
                        fill: true,
                        backgroundColor: gradientFill,
                        borderWidth: 4,
                        data: [1, 2, 3, 4, 1, 2, 1, 3, 1, 1, 2, 3, 4, 5, 6, 4, 5, 1, 2, 0, 0, 0, 0, 3,] // 데이터 (시간(06시~05시) 별 횟수) 들어가는 부분
                    }]
                },
                options: {
                    legend: {
                        position: "bottom"
                    },
                    scales: {
                        yAxes: [{
                            ticks: {
                                fontColor: "rgba(0,0,0,0.5)",
                                fontStyle: "bold",
                                beginAtZero: true,
                                maxTicksLimit: 5,
                                padding: 20
                            },
                            gridLines: {
                                drawTicks: false,
                                display: false
                            }

                        }],
                        xAxes: [{
                            gridLines: {
                                zeroLineColor: "transparent"
                            },
                            ticks: {
                                padding: 20,
                                fontColor: "rgba(0,0,0,0.5)",
                                fontStyle: "bold"
                            }
                        }]
                    }
                }
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