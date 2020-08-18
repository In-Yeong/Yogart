<template>
    <!-- Pt를 진행하는 페이지 입니다. 가장 가까운 시간의 PT를 보여줍니다. -->
    <div class="w-75 white-box ">
        <div id="className">
            <h1>{{ ptName }}</h1>
        </div>
        <div>
            <h5 class="m-5">{{ studentNickname }} 수강생</h5>
        </div>
        <div>
            <h5>{{ date }} ({{koreanDay[day]}})  {{time}}시</h5>
        </div>
        <a :href="ptUrl" class="btn-blue">라이브 스트리밍 수업 시작</a>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'PtAdmin',
    data() {
        return {
            studentNickname : null,
            ptName : null,
            SERVER_URL: this.$store.state.SERVER_URL,
            ptUrl: null,
            date : null,
            day : null,
            time : null,
            koreanDay : ['일','월','화','수','목','금','토']
        }
    },
    props: {
        teacherInfo: Object,
    },
    mounted() {
        const requestHeaders = {
            headers: {
                Authorization: this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/api/teachers/pt-now', requestHeaders)
        .then(res => {
            this.studentNickname = res.data.ptOpen.ptStudentId.userNickname
            this.ptName = res.data.ptOpen.ptClickedName.ptName
            this.time = res.data.ptOpen.ptTime
            this.day = res.data.ptOpen.ptDay
            this.date = res.data.ptOpen.dateTime.slice(0,10)
            this.ptUrl = `https://i3d202.p.ssafy.io:8080/rtc.html?room=${res.data.teacher.teacherCode}`
        })
        .catch(err => console.error(err))
    },
   
    
}
</script>

<style scoped>
#className{

    border-bottom: 2px solid rgba(143, 160, 242, 1);
}
.box{
    padding : 30px;
    background-color: rgba(255,255,255,0.5);
}

.btn-blue{
    margin-top : 30px;
    height : 40px;
    line-height: 15px;
    width : 100%;
    font-size : 1.5rem;
    border-radius : 5px;
}   
</style>
