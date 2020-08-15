<template>
    <div class="padding-for-nav">
        <div class="page-index">
            <div class="page-name">수업 목록</div>

            <div class="page-select">
                <div class="option option-active" id="new" @click="resorting('new')">최신순</div>
                <div class="option" id="low" @click="resorting('low')">낮은가격순</div>
                <div class="option" id="high" @click="resorting('high')">높은가격순</div>
            </div>
        </div>
        
        <div class="class-box row align-items-center" @click="btnClick(yogaClass.ptId)" v-for="yogaClass in yogaList" :key="yogaClass.ptId">
            <div class="d-inline-block col-2">
                <img class="user-profile m-3" :src="require('@/assets/Hedgehog.jpg')">
            </div>
            <div class="d-inline-block col-10">
                <div class="h3">{{ yogaClass.ptName }}</div>
                <div>{{ yogaClass.ptTeacherId.userNickname}} | <span class="price">{{ yogaClass.ptPrice}}</span> 스푼</div>
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
            yogaList : []
        }
    },
    components: {
        InfiniteLoading
    },
    created() {
        window.scrollTo(0,0);
    },
    methods: {
        resorting(str) {
            var options = document.getElementsByClassName('option')
            options.forEach(el => {
                el.classList.remove('option-active')
            });
            document.getElementById(str).classList.add('option-active')
            // axios로 요청보내기

        },
        btnClick(classId) {
            console.log(classId)
            // 해당 수업 상세 페이지로 이동
        },
        infiniteHandler($state) {
            axios.get(`${this.SERVER_URL}/api/teachers/class/list/${this.limit + 10}`)
            .then(res => {
                console.log(res)
                setTimeout(()=> {
                    if (res.data.content.length) {
                        this.yogaList = this.yogaList.concat(res.data.content);
                        console.log(this.yogaList);
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
    width: 90vh;
    margin: auto;
    border-bottom: 2px solid rgba(143, 160, 242, 0.3);
}
.page-name {
    float: left;
    position: relative;
    top: 8.5vh;
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

</style>