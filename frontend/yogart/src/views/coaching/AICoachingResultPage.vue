<template>
<div style="margin: 50px; ">
    <h2 class="m-3">AI 코칭 결과</h2>
    <h5>총 소요시간  |    {{totalTime}}</h5>
    <div class="d-flex mx-auto pr-0">
        <lineChart :lineLabels="lineLabels" :linedata="linedata"/>
        <dougnutChart :dougnutdata="dougnutdata"/>
    </div>
</div>
</template>

<script>
import posefiles from '../../../public/json.js'
import 'chartjs-plugin-labels'
import lineChart from '../../components/aiResult/lineChart.vue'
import dougnutChart from '../../components/aiResult/dougnutChart.vue'

export default {
    name : 'AICoachingResultPage',
    components : {
        lineChart,
        dougnutChart,
    },
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
 
}
</script>

<style >

</style>