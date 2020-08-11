<template>
    <div class="signupView pb-5">
        <h1 class="py-3">수업 조회</h1>
        <select name="cars" id="cars" onchange="resorting()">
            <option value="new">최신순</option>
            <option value="low">낮은가격순</option>
            <option value="high">높은가격순</option>
        </select>
        <div class="border my-3 mx-auto class-box row align-items-center" v-for="yogaClass in yogaList" :key="yogaClass.id">
            <div class="d-inline-block col-2">
                <img class="user-profile m-3" :src="require('@/assets/Hedgehog.jpg')">
            </div>
            <div class="d-inline-block col-8">
                <div class="h3">{{ yogaClass.name }}</div>
                <div>{{ yogaClass.teacher_nickName}} | {{ yogaClass.price}} 스푼</div>
            </div>
            <div class="d-inline-block col-2">
                <button class="btn btn-primary" @click="btnClick(yogaClass.id)">신청하기</button>
            </div>
        </div>
        <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
    </div>
</template>

<script>
import InfiniteLoading from 'vue-infinite-loading'
import axios from 'axios'

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
            }]
        }
    },
    components: {
        InfiniteLoading
    },
    methods: {
        resorting(str) {
            console.log(str)
        },
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
.user-profile {
  display: inline-block;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border : 2px solid black;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}
.class-box {
    width: 60rem;
}

</style>