<template>
  <div>
      <canvas id="dougnutChart"></canvas>
  </div>
</template>

<script>
export default {
    name : 'dougnutChart',
    data() {
        return{
            dougnutdata : this.$cookies.get('dougnutdataStr').split(','),
        }
    },
    mounted() {
        console.log("도넛차트",this.dougnutdata)
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
                    labels: ['팔', '다리', '복근', '척추','전신', '에너지','릴렉스'],
                    datasets: [{
                        label: '부위 포함',
                        hoverBackgroundColor : '#8e6b73',
                        data: this.dougnutdata,
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
                            fontSize : 15,
                            
                            
                        },

                    },
                    title : {
                        display : true,
                        fontSize : 20,
                        text : '태그별 비율'
                    } ,
                    plugins: {
                    
                        labels: [
                            {
                            fontColor : 'black',
                            fontSize : 13,
                            render: 'label',
                            position: 'outside'
                            },
                            {
                            fontColor : 'black',
                            fontSize : 13,
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
    width : 700px !important;
    margin : 0;
}
</style>