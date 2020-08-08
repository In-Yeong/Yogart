<template>
<div style="margin: 50px; ">
    <h2 class="m-3">AI 코칭 결과</h2>
    <h5>총 소요시간  |    {{totalTime}}</h5>
    <div class="d-flex mx-auto pr-0">
        <canvas id="lineChart"></canvas>
        <canvas id="dougnutChart"></canvas>
   
    </div>
</div>
</template>

<script>
import posefiles from '../../../public/json.js'
import 'chartjs-plugin-labels'
export default {
    name : 'AICoachingResultPage',
    data() {
        return {
            posefiles : posefiles,
            courseStr : '1,2,3,4,5',
            lineLabels: [],
            linedata : ['소요시간들'],
            dougnutdata : [],
            totalTime : '00 : 04 : 32',
            
        }
    },
    methods : {
        createLineLabels() {
            var course = this.courseStr.split(',')
            var labels = []  
            course.forEach(function (poseID, idx){
                labels.push(posefiles[poseID].pose_name)
            })
            this.lineLabels = labels
            console.log(this.lineLabels)
        }
    },
    created(){
         this.createLineLabels()
    },
    mounted() {
       


        var ctx = document.getElementById('lineChart').getContext('2d');
        var gradientStroke = ctx.createLinearGradient(500, 0, 100, 0);
        gradientStroke.addColorStop(0, "#80b6f4");
        gradientStroke.addColorStop(1, "#f49080");
        var gradientFill = ctx.createLinearGradient(500, 0, 100, 0);
        gradientFill.addColorStop(0, "rgba(128, 182, 244, 0.6)");
        gradientFill.addColorStop(1, "rgba(244, 144, 128, 0.6)");
        // gradientFill.addColorStop(0, "#80b6f4");
        // gradientFill.addColorStop(1, "f#49080");
        var lineChart = new Chart(ctx, {
        //  multiTooltipTemplate: "<%= datasetLabel %> - <%= value %>",
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            data: {
                labels: this.lineLabels,
                datasets: [{
                    
                    label: '소요 시간(초)',
                    // backgroundColor: 'rgb(255, 99, 132)',
                    // borderColor: '#3c5b74',
                    // hoverBackgroundColor : '#3c5b74',
                    data: [30, 40, 50, 32, 44, 35, 30],
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
                }]
            },

            // Configuration options go here
            options: {
               scales: {
                    xAxes: [{
                    scaleLabel: {
                        fontWeight : 'bold',
                        fontSize : 20,
                        display: true,
                        labelString: '요가 동작'
                    }
                    }],
                    yAxes: [{
                    scaleLabel: {
                        fontWeight : 'bold',
                        fontSize : 20,
                        display: true,
                        labelString: '소요시간(초)'
                    }
                    }]
                } ,    
                legend: {
                    labels: {
                        // This more specific font property overrides the global property
                        fontColor: 'black',
                        fontSize : 20,
                    },

                },
                title : {
                    display : true,
                    fontSize : 30,
                    text : '동작 별 소요시간'
                } ,
                scaleLabel : {
                    display : true,
                },
                plugins: {
                    datalabels: {
                        color: 'blue',
                        labels: {
                          
                            title: {
                                font: {
                                    weight: 'bold'
                                }
                            },
                            value: {
                                color: 'green'
                            }
                        }
                    }
                }             
            }

        });
   
        var ctx2 = document.getElementById('dougnutChart').getContext('2d');
        var dougnutChart = new Chart(ctx2, {
            // The type of chart we want to create
            type: 'doughnut',

            // The data for our dataset
            data: {
                labels: ['다리', '척추', '복근', '전신', '에너지','릴렉스'],
                datasets: [{
                    label: '부위 포함',
                    hoverBackgroundColor : '#8e6b73',
                    data: [1,4,3,2,1,1],
                    backgroundColor: [
                        '#8e6b73',
                        '#a94300',
                        '#ffb000',
                        '#ffdf00',
                        '#5b9b37',
                        '#153d02',            
                        
                    ]
                   
                }]      
            
            },
            

            // Configuration options go here
            options: {
                legend: {
                    labels: {
                        // This more specific font property overrides the global property
                        fontColor: 'black',
                        fontSize : 20,
                        
                        
                    },

                },
                title : {
                    display : true,
                    fontSize : 30,
                    text : '부위별 비율'
                } ,
                plugins: {
                  
                    labels: [
                        {
                        fontColor : 'black',
                        fontSize : 20,
                        render: 'label',
                        position: 'outside'
                        },
                        {
                        fontColor : 'black',
                        fontSize : 20,
                        render: 'percentage'
                        }
                    ]
                }
               
            }

        });
        
    },
}
</script>

<style >
#lineChart {
    height : 400px !important;
    width : 700px !important;
    margin-left : 50px;
}
#dougnutChart {
    height : 400px !important;
    width : 800px !important;
    margin : 0;
}
</style>