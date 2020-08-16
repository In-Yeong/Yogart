<template>
    <div class="py-5">
        <UserProfile/>
        <div class="">
            <div class="w3-bar" style="margin-left: 10vw;">
                <button class="w3-bar-item w3-button w3-teal" @click="onPTMang">PT관리</button>
                <button class="w3-bar-item w3-button w3-red" @click="onExerHis">운동기록</button>
            </div>
            <div class="schedule">
                <PTManagement :ptManagement="ptManagement"/>
                <ExerciseHistory :exerciseHistory="exerciseHistory"/>
            </div>
        </div>
        
    </div>
</template>

<script>
import UserProfile from '@/components/mypage/UserProfile.vue'
import PTManagement from '@/components/mypage/PTManagement.vue'
import ExerciseHistory from '@/components/mypage/ExerciseHistory.vue'

export default {
    name : 'MyPage',
    components : {
        UserProfile,
        PTManagement,
        ExerciseHistory
    },
    data() {
        return {
            ptManagement : true,
            exerciseHistory : false,
        }
    },
    mounted() {
        // const diary = document.getElementById('diary')
        // const pt = document.getElementById('pt')
        // const exchis = document.getElementById('exc-his')
        // pt.style.backgroundColor = 'lightgray'
        console.log(!this.$store.state.isLogin)
        if (!this.$store.state.isLogin) {
            $('#loginStaticBackdrop').modal('show')
        }
    },
    methods : {
   
        onPTMang() {
            this.ptManagement = true
            this.exerciseHistory = false

 
        },
        onExerHis() {
            this.ptManagement = false
            this.exerciseHistory = true

        },
    }
}
</script>

<style>
.schedule {
    margin-left: 10vw;
    margin-right: 10vw;
    padding : 1rem;
    border : 2px solid lightgray;
    background-color : lightgray;
}
.schedule-btn {
    padding-top : 5px;
    padding-bottom : 5px;
    font-size : 20px;
    /* border : 2px solid lightgray; */
    background-color : gray;
}
.schedule-btn:hover {
    background-color: rgb(209, 236, 165);
}


</style>
