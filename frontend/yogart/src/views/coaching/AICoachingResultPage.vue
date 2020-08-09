<template>
<div style="margin: 50px; ">
    <h2 class="m-3">{{this.courseName}}코스 AI 코칭 결과</h2>
    <h5>총 소요시간  |    {{this.minutes}} : {{this.seconds}}</h5>
    <div class="d-flex mx-auto pr-0">
        <lineChart/>
        <dougnutChart/>
    </div>
</div>
</template>

<script>
import posefiles from '../../../public/json.js'
import 'chartjs-plugin-labels'
import lineChart from '../../components/aiResult/lineChart.vue'
import dougnutChart from '../../components/aiResult/dougnutChart.vue'
import axios from 'axios'

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
            dougnutdata : [0,0,0,0,0,0,0],
            minutes : 0,
            seconds : 0,          
            SERVER_URL : this.$store.state.SERVER_URL
              
        }  
    },
    created() {
        // this.getCourse()
    },
    mounted(){

        //점수 가져와서 표시하기
        this.getCourse()
        this.splitTotaltime()
        
        this.saveResult()
    },
    methods : {
        splitTotaltime() {
            const totalTime = this.$cookies.get('resultRunTime').split('.')
            this.minutes = totalTime[0]
            this.seconds = totalTime[1]
          
        },
        getCourse() {
            this.courseId = this.$cookies.get('coaching-list')   
            axios.get(this.SERVER_URL + `/api/aicoach/list/${this.courseId}`)
            .then(res => {
                console.log("result에서 axios 성공",res)
                //코스 이름과 코스 리스트 save
                this.courseName = res.data.courseName
                
                const Course =  res.data.course.split(',') 
                const filteredCourse =  []
                Course.forEach(function(courseID){
                    if (courseID < 1000){
                        console.log("if 안",courseID)
                        filteredCourse.push(courseID)
                    }
                })
                this.course = filteredCourse
                console.log("filter",this.course)
                this.createLineLabels()
                this.createDougnutData()
                //
            })
            .catch(err => {
                console.error(err)
            })
        },
        createLineLabels() { //라벨 - 동작이름들
            const lineLabels = []
            console.log(this.course)
            this.course.forEach(function (poseID){
                lineLabels.push(posefiles[poseID].korean_pose_name)
            }.bind(this))
            const lineLabelStr = lineLabels.join(',')
            this.$cookies.set('lineLabelStr',lineLabelStr) 
        },
        createDougnutData() { //data - 태그별 카운트 
            console.log("도넛데이터만들기,코스 : ",this.course)
            this.course.forEach(function (poseID){
                this.posefiles[poseID].tag.forEach(function(tag){
                    // console.log(tag,this.dougnutdata)
                    if (tag === '팔') {
                        this.dougnutdata[0] ++       
                    }
                    else if (tag === '다리') {
                        this.dougnutdata[1] ++
                    }
                    else if (tag === '복근') {
                        this.dougnutdata[2] ++
                    }
                    else if (tag === '척추') {
                        this.dougnutdata[3] ++
                    }
                    else if (tag === '전신') {
                        this.dougnutdata[4] ++
                    }
                    else if (tag === '에너지') {
                        this.dougnutdata[5] ++
                    } 
                    else if (tag === '릴렉싱') {
                        this.dougnutdata[6] ++
                    } 
                }.bind(this))         
            }.bind(this))
            const dougnutdataStr = this.dougnutdata.join(',')
            this.$cookies.set('dougnutdataStr',dougnutdataStr)
        },
        
        saveResult() {
            //오늘 날짜
            const startDateTime = new Date();
            //총 경과 시간
            const totalTime = String(60 * this.minutes + this.seconds)
            //부위별 태그 횟수
            const tagCounting = this.dougnutdata.join(',')
           
            //db에 오늘날짜, 경과시간,부위별 태그횟수 보내서 저장
            axios.post(this.SERVER_URL + `/api/aicoach/result`,
                { 'headers': { 'auth-token': window.$cookies.get('auth-token')},
                'totalTime' : totalTime, 
                'startDateTime': startDateTime, 
                'tagCounting' : tagCounting 
                }
            )
            .then(res => {
                console.log("AI 코칭 결과 저장 성공",res) 
            })
            .catch(err => {console.log(err)})
        }
    },
  
 
}
</script>

<style >

</style>