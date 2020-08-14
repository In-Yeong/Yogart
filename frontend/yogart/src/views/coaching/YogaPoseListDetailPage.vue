<template>
    <div>
        <h1 class="m-5">{{courseName}} 코스</h1>
         
        <p>다음 동작 순서대로 AI 코칭이 진행됩니다.</p>
     
        <div class="box d-flex justify-content-around">
            <div v-for="poseId in yogaList" :key="poseId">
                <div>
                    <img class="user-profile m-2" :src="require(`../../../public/photos/${posefiles[poseId].file_reference}`)">
                </div> 
                <h5>{{posefiles[poseId].korean_pose_name}}</h5>
            </div>           
        </div>
        <div class="d-flex justify-content-center my-5">
            <div class="btn-white m-5" @click="goBackToList()">다른 코스 둘러보기</div>
            <div class="btn-white  m-5" @click="clickStart()">AI 코칭 시작할래요</div>
            <!-- <div class="box-btn m-5">다른 코스 둘러보기</div>
            <div class="box-btn  m-5">AI 코칭 시작할래요</div> -->
        </div>
      
    </div>
</template>

<script>
import posefiles from '../../../public/json.js'
import axios from 'axios'

export default {
    name : 'YogaPoseListDetailPage',
    data() {
        return {
            posefiles : posefiles,
            listId : this.$cookies.get('coaching-list'),
            backCommingString: '',
            yogaList: [],
            courseName: '',
            SERVER_URL: this.$store.state.SERVER_URL
        }
    },
    components: {
        
    },
    methods: {
        getCourseList() {
            axios.get(this.SERVER_URL + `/api/aicoach/list/${this.listId}`)
            .then(response => {
                this.courseName = response.data.courseName
                this.backCommingString = response.data.course
                this.StringToArr()
            })
            .catch(err => console.error(err))
        },
        StringToArr() {
            const Course =  this.backCommingString.split(',')
            const filteredCourse =  []
            Course.forEach(function(courseID){
                if (courseID !== "1000"){
                    filteredCourse.push(courseID)
                }
            })
            this.yogaList = filteredCourse
        },
        clickStart() {
            this.$cookies.set('coaching-list', this.listId)
            this.$router.push(`/coaching`)
        },
        goBackToList(){
            this.$router.push(`/coaching/yogaposelist`)
        }

    },
    created() {
        this.getCourseList()
    },
}
</script>

<style scoped>
.btn-white{
    width : 30%;
}
.box{
    padding : 10px;
    margin : auto;
    width : 70%;
    background-color: rgba(255, 255, 255, 0.5);
}
.box:hover{
    border : 2px solid white;
    cursor: pointer;
}
/* .box-btn{
    color : black;
    margin : auto;
    width : 30%;
    height : 50px;
    line-height : 50px;
    background-color: rgba(255, 255, 255, 0.5);
    border : 2px solid white;
}
.box-btn:hover{
    border : 2px solid#d79fd7;
    color : #d79fd7;
    cursor: pointer;
} */

</style>