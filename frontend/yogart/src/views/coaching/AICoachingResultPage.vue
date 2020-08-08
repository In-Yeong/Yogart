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
            courseStr : '1,2,3,4,5', //get으로 가져와야
            lineLabels: ['자세이름들'],
            linedata : this.$cookies.get('resultPoseTimes').split('.'),
            dougnutdata : [], //태그별 계산해야함
            totalTime : this.$cookies.get('resultRunTime'),
            
            
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

        //axios.get요청 보내서 코스이름이랑 코스순서 받아와서 태그로 부위별 그래프에 넣기
        //점수 가져와서 표시하기
        //db에 오늘날짜, 경과시간,부위별 태그횟수 보내서 저장

        console.log("this.poseTimes")
         this.createLineLabels()
    },
 
}
</script>

<style >

</style>