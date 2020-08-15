<template>
    <div class="padding-for-nav">
        <div class="page-index d-flex justify-content-between">
        <div class="page-name">수업 목록</div>

        <div class="form-group">
            <select class="form-control" id="sel1">
                <option value="0" @click="resorting('new')"> 최신순</option>
                <option value="1" @click="resorting('low')">낮은가격순</option>
                <option value="2" @click="resorting('high')">높은가격순</option>
            </select>
        </div>

            <!-- <div class="page-select">
                <div class="option option-active" id="new" @click="resorting('new')">최신순</div>
                <div class="option" id="low" @click="resorting('low')">낮은가격순</div>
                <div class="option" id="high" @click="resorting('high')">높은가격순</div>
            </div> -->
        </div>
        
        <!-- <div class="box row align-items-center" @click="btnClick(yogaClass.id)" v-for="yogaClass in yogaList" :key="yogaClass.id">
            <div class="d-inline-block col-2">
                <img class="user-profile m-3" :src="require('@/assets/Hedgehog.jpg')">
            </div>
            <div class="d-inline-block col-10">
                <div class="h5">{{ yogaClass.name }}</div>
                <div>{{ yogaClass.teacher_nickName }} | <span class="price"> {{ yogaClass.price}} </span> 스푼</div>
            </div>
        </div> -->
        <div class="box px-3 d-flex justify-content-between align-items-center" @click="btnClick(yogaClass.id)" v-for="yogaClass in yogaList" :key="yogaClass.id">
            <div class="d-flex align-items-center">
                <img class="user-profile m-3" :src="require('@/assets/Hedgehog.jpg')">
                <div class="h5">{{ yogaClass.name }}</div>
            </div>
            <div>{{ yogaClass.teacher_nickName }} | <span class="price"> {{ yogaClass.price}} </span> 스푼</div>
        </div>
        <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
    </div>
</template>

<script>
import InfiniteLoading from 'vue-infinite-loading'
import axios from 'axios'
import selectBox from './selectBox'

export default {
    name: 'ClassList',
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
            limit: 0,
            yogaList : [{
                id: 1,
                name: '상욱쌤과 함께하는 스프링 요가',
                price: 10,
                teacher_nickName: 'ㅇㅅㅇ'
            },
            {
                id: 2,
                name: '[초급] 아침을 여는 요가',
                price: 20,
                teacher_nickName: '또희'
                
            },
            {
                id: 3,
                name: '상급자 요가 클래스',
                price: 300,
                teacher_nickName: '규동도롱'
            },
            {
                id: 4,
                name: '심야 릴렉싱 요가 클래스',
                price: 40,
                teacher_nickName: '요갓두'
            }]
        }
    },
    components: {
        InfiniteLoading
    },
    created() {
        window.scrollTo(0,0);
    },
    mounted(){
        this.dropdown()
    },
    methods: {
        // resorting(str) {
        //     var options = document.getElementsByClassName('option')
        //     options.forEach(el => {
        //         el.classList.remove('option-active')
        //     });
        //     document.getElementById(str).classList.add('option-active')
        //     // axios로 요청보내기

        // },
        btnClick(classId) {
            console.log(classId)
            // 해당 수업 상세 페이지로 이동
        },
        infiniteHandler($state) {
            axios.get(`${this.SERVER_URL}/api/class/list/${this.limit + 10}`)
            .then(res => {
                setTimeout(()=> {
                    if (res.data.length) {
                        this.yogaList = this.yogaList.concat(res.data);
                        $state.loaded();
                        this.limit += 10
                        if (this.yogaList.length / 10 === 0){
                            $state.complete();
                        }
                    } else {
                        $state.complete();
                    }
                    
                }, 500)
            })
            .catch(err => {
                console.log(err)
            })
        }

    },
}
</script>

<style scoped>
.page-index {
    height: 8vh;
    width: 60%;
    margin: auto;
    margin-bottom : 1rem;
    border-bottom: 2px solid rgba(143, 160, 242, 0.5);
}
.page-name {
    float: left;
    margin-left: 1vh;
    font-size: 4vh;
    font-weight: bold;
    color: rgba(0, 0, 0, 0.6);
}
.page-select {
    float: right;
    margin-right: 1vh;
    position: relative;
    top: 2vh;
}
.option {
    display: inline-block;
    margin-left: 2vh;
    font-size: 2.3vh;
    font-weight: bold;
    color: rgba(0, 0, 0, 0.5);
}
.option:hover {
    cursor: pointer;
    text-decoration-line: underline;
}
.option-active {
    color: rgba(242, 157, 143, 1);
}
.user-profile {
  display: inline-block;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border : 2px solid rgba(255, 255, 255, 0.5);

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}
.price {
    font-weight: bold;
    color:rgba(143, 160, 242, 1)
}
.class-box {
    width: 90vh;
    background-color: rgba(255, 255, 255, 0.5);
    margin: 3rem auto;
    border-bottom: 3px solid rgba(143, 160, 242, 1);
    border-radius: 5rem;
}
.class-box:hover {
    cursor: pointer;
    background-color: rgba(0, 0, 0, 0.1);
}
.box{
    width :60%;
    margin : auto;
    background-color: rgba(255, 255, 255, 0.3);
}
.box:hover{
    cursor:pointer;
    background-color: rgba(143, 160, 242, 0.2);
    border : 2px solid rgba(143, 160, 242, 0.5);
}

option, select#sel1.form-control{
    width:150px;
    background-color: rgba(255, 255, 255, 0.3);
    border : 1px solid rgba(143, 160, 242, 0.5);
}

select option{
    background-color: rgba(255, 255, 255, 0.3);
}
</style>