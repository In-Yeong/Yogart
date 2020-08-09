<template>
    <div>
        <h1 class="m-5">{{listId}}번 리스트 디테일 페이지 입니다</h1>
        <div class="row justify-content-center border-top border-bottom m-5">
            <div class="col-sm-2" id="poses" v-for="poseId in yogaList" :key="posefiles[poseId].pose_name">
            
                <img class="user-profile m-3" :src="require(`../../../public/photos/${posefiles[poseId].file_reference}`)">
                <p>{{posefiles[poseId].pose_name}}</p>
            </div>
        </div>
        <div class="mx-auto my-3">
            설명하는곳
        </div>
        <div class="m-5">
            <button @click="clickStart()">시작하기 버튼</button>
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
            backCommingString: '1,2,3,4,5',
            yogaList: [],
            courseName: '',
            SERVER_URL: this.$store.state.SERVER_URL
        }
    },
    components: {
        
    },
    methods: {
        getCourseList() {
            axios.get(this.SERVER_URL + `/api/aicoach/list/${listId}`)
            .then(response => {
                console.log(response)
                this.courseName = response.data.courseName
                this.backCommingString = response.data.course
            })
            .catch(err => console.error(err))
        },
        StringToArr() {
            this.yogaList = this.backCommingString.split(',')
        },
        clickStart() {
            this.$router.push(`/coaching`)
        }

    },
    created() {
        this.getCourseList()
        this.StringToArr()
    },
}
</script>

<style>

</style>