<template>
    <div v-if="ptManagement">
        <span>
            <button class="past-btn" @click="pastPT()">과거 수업 내역</button>
            <button class="today-btn active" @click="todayPT()">오늘 진행 수업</button>
            <button class="future-btn" @click="futurePT()">진행 예정인 수업</button>
        </span>
        <div class="my-3">
            <div v-if="today">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <!-- <th scope="col">#</th> -->
                            <th scope="col">강사명</th>
                            <th scope="col">수업명</th>
                            <th scope="col">수업일시</th>
                            <th scope="col">바로가기</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="todayCourse in todayCourses" :key="todayCourse.courseID">
                            <!-- <th scope="row">1</th> -->
                            <td>{{todayCourse.ptClickedName.ptName}}</td>
                            <td>{{todayCourse.ptClickedName.ptIntro}}</td>
                            <td>{{todayCourse.dateTime}}</td>
                            <td><button @click="goPt(todayCourse)" class="w3-bar-item w3-button w3-red">입장하기</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div v-if="past">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <!-- <th scope="col">#</th> -->
                            <th scope="col">강사명</th>
                            <th scope="col">수업명</th>
                            <th scope="col">수업일시</th>
                            <th scope="col">출석현황</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="pastCourse in pastCourses" :key="pastCourse.courseID">
                            <!-- <th scope="row">1</th> -->
                            <td>{{pastCourse.ptClickedName.ptTeacherId.userName}}</td>
                            <td>{{pastCourse.ptClickedName.ptName}}</td>
                            <td>{{pastCourse.dateTime}}</td>
                            <td>{{pastCourse.isAttend}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div v-if="future">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <!-- <th scope="col">#</th> -->
                            <th scope="col">강사명</th>
                            <th scope="col">수업명</th>
                            <th scope="col">수업일시</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="futureCourse in futureCourses" :key="futureCourse.courseID">
                            <!-- <th scope="row">1</th> -->
                            <td>{{futureCourse.ptClickedName.ptTeacherId.userName}}</td>
                            <td>{{futureCourse.ptClickedName.ptName}}</td>
                            <td>{{futureCourse.dateTime}}</td>
                        </tr>
                    </tbody>
                </table>
            </div> 
        </div>   
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name : 'PTManagement',
    props : {
        ptManagement : Boolean,
    },
    data() {
      return {
        SERVER_URL : this.$store.state.SERVER_URL,
        past : false,
        today : true,
        future : false,
        pastCourses : [],
        todayCourses : [],
        futureCourses : [],

      }
    },
    mounted() {
      this.getPT()
    },
    methods : {
      goPt(todayCourse) {
        console.log('@@@@', todayCourse)
        window.open(`https://i3d202.p.ssafy.io:8080/rtc.html?room=${todayCourse.ptClickedName.ptTeacherId.teacherCode}`)
      },
      getPT() {
        //오늘 날짜 기준으로 과거,오늘,미래로 나눠서 1:1 PT수업리스트를 받는 함수
        // user(auth-token을 보내주면 그 유저에 연결된 1:1 pt 수업을 보내주세요 - 내용 : 수업명, 수업id, 강사명, 출석현황(isAttend),수업날짜(dataTime) 포함된 object의 리스트)
        const RequestHeaders = {
          headers : {
            Authorization : this.$cookies.get('auth-token')
          }
        }
        console.log('@@@@@@@@@@@@@@@@@@@', RequestHeaders)
        axios.get(this.SERVER_URL + '/api/mypage/ptlist', RequestHeaders)
        .then(res => {
          console.log(res)
          console.log("PTManagement 성공",res)
          //과거 pt 기록들 날짜,시간순으로 배열로 넘겨주세요
          this.pastCourses = res.data.pastCourses
          //오늘 pt 기록들 시간순으로 배열로 넘겨주세요
          this.todayCourses = res.data.todayCourses
          //미래 pt 기록들 날짜,시간순으로 배열로 넘겨주세요
          this.futureCourses = res.data.futureCourses
          console.log(this.pastCourses,this.todayCourses,this.futureCourses)
        })
        .catch(err => console.log(err))
      },
      pastPT() {
        this.past = true,
        this.today = false,
        this.future = false
        document.getElementsByClassName('active')[0].classList.remove('active')
        document.getElementsByClassName('past-btn')[0].classList.add('active')
        console.log(document.getElementsByClassName('past-btn')[0])
      },
      todayPT() {
        this.past = false,
        this.today = true,
        this.future = false
        document.getElementsByClassName('active')[0].classList.remove('active')
        document.getElementsByClassName('today-btn')[0].classList.add('active')
      },
      futurePT() {
        this.past = false,
        this.today = false,
        this.future = true
        document.getElementsByClassName('active')[0].classList.remove('active')
        document.getElementsByClassName('future-btn')[0].classList.add('active')
      }
    }
}
</script>

<style scoped>
.past-btn {
    border: 2px solid rgba(143, 160, 242, 0.8);
    border-radius: 10px 0px 0px 10px;
    background: rgba(255, 255, 255, 0);
    height: 40px;
    outline: none;
}
.today-btn {
    border-top: 2px solid rgba(143, 160, 242, 0.8);
    border-bottom: 2px solid rgba(143, 160, 242, 0.8);
    border-left: none;
    border-right: none;
    background: rgba(255, 255, 255, 0);
    height: 40px;
    outline: none;
}
.future-btn {
    border: 2px solid rgba(143, 160, 242, 0.8);
    border-radius: 0px 10px 10px 0px;
    background: rgba(255, 255, 255, 0);
    height: 40px;
    outline: none;
}
.active {
    background-color: rgba(143, 160, 242, 0.8) !important;
    color: white !important;
}
</style>