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
            courseId : 0,
            courseName : '',
            course : [], //course순서 array
            lineLabels: ['자세이름들'],
            linedata : this.$cookies.get('resultPoseTimes').split('.'),
            dougnutdata : [], //태그별 계산해야함
            totalTime : this.$cookies.get('resultRunTime'),
            startDateTime : undefined,
              
        }  
    },
    methods : {
       

        createLineLabels() {
            var labels = []  
            this.course.forEach(function (poseID, idx){
                labels.push(posefiles[poseID].pose_name)
            })
            this.lineLabels = labels
            console.log(this.lineLabels)
        },
        clickStart() {
            this.startDateTime = new Date();
                
        },
        getCourse() {
            this.courseId = this.$cookies.get('listId')   
            console.log(this.courseId)
            // axios.get(this.SERVER_URL + `/api/aicoach/list/${this.courseId}`)
            // .then(res => {
            //     console.log(res)
            //     //코스 이름과 코스 리스트 save
            //     this.courseName = res.data.courseName
            //     this.course = res.data.course.split(',') 
            //     console.log(this.course)
            //     //
            // })
            // .catch(err => console.error(err))

        
        }
    },
    created(){
        
        //axios.get요청 보내서 코스이름이랑 코스순서 받아와서 태그로 부위별 그래프에 넣기
        //점수 가져와서 표시하기
        //db에 오늘날짜, 경과시간,부위별 태그횟수 보내서 저장 +코스번호?
        this.getCourse()
        this.createLineLabels()
        this.clickStart()
    },
 
}
</script>

<style >

</style>