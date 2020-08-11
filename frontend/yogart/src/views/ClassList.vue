<template>
    <div class="signupView pb-5">
        <h1 class="py-3">수업 조회</h1>
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
    </div>
</template>

<script>

export default {
    name: 'ClassList',
    data() {
        return {
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
                name: '상급자 요가 클래스dddddddddddddddddddddddddddddd',
                price: 300,
                teacher_nickName: '규동도롱'
            }]
        }
    },
    methods: {
        btnClick(classId) {
            console.log(classId)
            // 해당 수업 상세 페이지로 이동
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