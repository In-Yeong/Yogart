<template>
  <div>
      <canvas id="dougnutChart"></canvas>
  </div>
</template>

<script>
export default {
    name : 'dougnutChart',
    props: {
        dougnutdata : Array,
    },
    mounted() {
        this.createChart()
    },
    methods : {
        createChart() {
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
                        fontSize : 25,
                        text : '태그별 비율'
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
        }
    }
}
</script>

<style>
#dougnutChart {
    height : 400px !important;
    width : 800px !important;
    margin : 0;
}
</style>