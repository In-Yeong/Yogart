<template>
    <div class="m-5">
        <h1>수업 개설</h1>
        <div class="d-flex mx-auto w-75 mb-3 justify-content-center">
        <div class="mr-3">
            <div class="field-row">
                <input name="class_name" id="class_name" v-model="ptName" type="text" required autofocus/>
                <label for="class_name">수업명</label> 
            </div>
            <div class="field-row">
                <input name="class_price" id="class_price" v-model="ptPrice" type="text" required/>
                <label for="class_price">회당 가격</label> 
            </div>
            <div class="field-row">
                <input name="class_intro" id="class_intro" v-model="ptIntro" type="text" required/>
                <label for="class_intro">수업 소개</label> 
            </div>
        </div>
        <div style="margin-top:30px;">
            <button id="makeClassBtn" @click="onSubmit">수업 생성</button>
        </div>
    </div>




        <table class="table w-75 mx-auto">
        <thead>
            <tr>
            <th scope="col">시</th>
            <th scope="col">월</th>
            <th scope="col">화</th>
            <th scope="col">수</th>
            <th scope="col">목</th>
            <th scope="col">금</th>
            <th scope="col">토</th>
            <th scope="col">일</th>
            </tr>
        </thead>
        <tbody>
            <TimeTable v-for="time in times" :key="time" :time="time" :clicked="clicked" @cellClicked="cellClicked"></TimeTable>
        </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios'
import TimeTable from './TimeTable.vue'

export default {
    name: 'ClassSetting',
    components: {
        TimeTable
    },
    props: {
        teacherInfo: Object,
    },
    data() {
        return {
            times: [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
            clicked: [],
            ptName: null,
            ptPrice: null,
            ptIntro: null,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    methods: {
        cellClicked(payload) {
            console.log(payload.day, payload.time)
            if (payload.isClicked) {
                this.clicked.push({day: payload.day, time: payload.time})
            } else {
                const idx = this.clicked.findIndex(dayTime => {
                    return dayTime.day === payload.day && dayTime.time === payload.time
                })
                if (idx > -1) this.clicked.splice(idx, 1)
            }
            console.log(this.clicked)
        },
        onSubmit(e) {
            e.preventDefault()
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token')
                }
            }
            const ptData = {
                ptName: this.ptName,
                ptPrice: this.ptPrice,
                ptIntro: this.ptIntro,
                clicked: this.clicked,
            }
            // console.log(ptData)
            // 데이터 저장을 요청합니다. 데이터를 저장하며 clicked에 맞춰 수업을 생성해야 합니다.
            console.log(ptData)
            axios.post(this.SERVER_URL + '/api/teachers/pt-create', ptData, requestHeaders)
            .then(res => {
                console.log(res)
                // 강사 상세페이지로 이동
                // this.$router.push('')
            })
            .catch(err => console.error(err))
        }
    },
    mounted() {
        // // 전에 저장했던 강사의 수업 가능 시간을 받아 옵니다.
        // axios.get(this.SERVER_URL + '/api/teachers/pt-timetable, pt)
        // .then(res => {
        //     this.clicked = res.data.clicked
        // })
    },
}
</script>

<style scoped>
#makeClassBtn{
    width : 100px;
    height : 190px;
    background-color: rgba(143, 160, 242, 1);
    color : white;
    border :2px solid white;
    border-radius : 10px; 
    font-weight: 500;
}
th {
    background-color: rgba(143, 160, 242, 1);
    color : white;
    border :2px solid white;
    height : 5px;
    line-height: 25px;
    font-size : 15px;
}
@import 'https://fonts.googleapis.com/css?family=Titillium+Web';
.field-row {
	font-family: 'Titillium Web', sans-serif;
	margin:30px 0 0 10px;
	position:relative;

}
label {
	font-size:16px;
	color:#888;
	position:absolute;
	top: 10px;
    bottom: 0;
    left: 10px;
	transition: .3s ease;
	cursor:text;
}
input {
    width : 400px;
    background :rgba(255, 255, 255, 0.8);
	font-size:16px;
	line-height:18px;
	padding: 10px 10px 10px 0;
	border:0;
	border-bottom:1px solid #ccc;
	outline:none;
	
}
input:focus {
	border-color:#888;
}
input:focus ~ label, input:valid ~ label {
	font-size:15px;
	
	color:black;
	transform: translate3d(0, -30px, 0);
}
.select{
    background-color: rgba(143, 160, 242, 0.6);
}
</style>
