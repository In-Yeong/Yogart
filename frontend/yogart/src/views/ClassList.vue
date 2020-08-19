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
        </div>
        <div class="box px-3 d-flex justify-content-between align-items-center" v-for="yogaClass in yogaList" :key="yogaClass.ptId" @click="btnClick(yogaClass.ptTeacherId.id)">
            <div class="d-flex align-items-center">
                <img class="user-profile m-3" :src="require('@/assets/Hedgehog.jpg')">
                <div class="h5">{{ yogaClass.ptName }}</div>
            </div>
            <div>{{ yogaClass.ptTeacherId.userNickname }} | <span class="price"> {{ yogaClass.ptPrice}} </span> 스푼</div>
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
    mounted(){
        this.dropdown()
    },
    methods: {
        btnClick(teacherId) {
            // 해당 수업 상세 페이지로 이동
            this.$router.push(`/teachers/${teacherId}`)
        },
        infiniteHandler($state) {
            axios.get(`${this.SERVER_URL}/api/teachers/class/list/${this.limit + 10}`)
            .then(res => {
                setTimeout(()=> {
                    if (res.data.content.length) {
                        this.yogaList = this.yogaList.concat(res.data.content);
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
                console.error(err)
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