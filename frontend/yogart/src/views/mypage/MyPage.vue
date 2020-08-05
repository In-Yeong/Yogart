<template>
    <div class="mb-5" style="margin-top : 50px">
        <UserProfile/>
        <div class="d-flex flex-column">
            <div class="w3-bar" style="margin-left: 10vw;">
                <!-- <button class="w3-bar-item w3-button w3-black" @click="onDiary">다이어리</button> -->
                <button class="w3-bar-item w3-button w3-teal" @click="onPTMang">PT관리</button>
                <button class="w3-bar-item w3-button w3-red" @click="onExerHis">운동기록</button>
            </div>
            <div class="schedule">
                <PTManagement :ptManagement="ptManagement"/>
                <!-- <Diary :diary="diary"/> -->
                <ExerciseHistory :exerciseHistory="exerciseHistory"/>
            </div>
        </div>
        
    </div>
</template>

<script>
import UserProfile from '@/components/mypage/UserProfile.vue'
import PTManagement from '@/components/mypage/PTManagement.vue'
// import Diary from '@/components/mypage/Diary.vue'
import ExerciseHistory from '@/components/mypage/ExerciseHistory.vue'

export default {
    name : 'MyPage',
    components : {
        UserProfile,
        PTManagement,
        // Diary,
        ExerciseHistory
    },
    data() {
        return {
            ptManagement : true,
            // diary : true,
            exerciseHistory : false,
        }
    },
    mounted() {
        // const diary = document.getElementById('diary')
        const pt = document.getElementById('pt')
        const exchis = document.getElementById('exc-his')
        pt.style.backgroundColor = 'lightgray'
    },
    methods : {
        // onDiary() {
        //     this.ptManagement = false
        //     this.diary = true
        //     this.exerciseHistory = false
        //     diary.style.backgroundColor = 'lightgray'
        //     pt.style.backgroundColor = 'gray'
        //     exchis.style.backgroundColor = 'red'
        // },
        onPTMang() {
            this.ptManagement = true
            // this.d/ary = false
            this.exerciseHistory = false
            // diary.style.backgroundColor = 'gray'
            pt.style.backgroundColor = 'lightgray'
            exchis.style.backgroundColor = 'gray'
 
        },
        onExerHis() {
            this.ptManagement = false
            // this.diary = false
            this.exerciseHistory = true
            // diary.style.backgroundColor = 'gray'
            pt.style.backgroundColor = 'gray'
            exchis.style.backgroundColor = 'lightgray'
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

.gray {
    margin-left: 10vw;
    margin-right: 10vw;
    padding : 1rem;
    border : 2px solid gray;
}


</style>
