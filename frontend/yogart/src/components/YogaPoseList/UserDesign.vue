<template>
    
    <div class="container">
        <h3 class="m-5">사용자 지정 코스</h3>
        <div id="mylist" class="row">
            <div class="col-4 rect create-list row align-items-center" @click="newCourse()"><h1 class="col align-self-center">+</h1></div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name : 'UserDesign',
        data() {
            return{
                SERVER_URL : this.$store.state.SERVER_URL
            }
        },
        created() {
            this.getUserCourse()
        },
        methods: {
            clickSet(num) {
                console.log(num)
                this.$emit("clickSet",num)
            },
            newCourse() {
                this.$router.push(`/coaching/yogapose/`)
            },
            getUserCourse() {
                let self = this
                axios.get(this.SERVER_URL + `/api/aicoach/list`, { 'headers': { 'auth-token': window.$cookies.get('auth-token') } })
                .then(res => {
                    console.log(res)
                    res.data.forEach(e => {
                        var list = document.createElement('div');
                        list.classList.add('col-4', 'border', 'rect', 'd-flex', 'flex-column');
                        list.addEventListener('click', function(){
                            self.clickSet(e.aiCourseId)
                        })
                        list.innerText = e.aiCourseName + '\n' + e.aiCourseOrder + '\n' + e.aiCourseUserNickname.userNickname
                        document.getElementById('mylist').prepend(list)
                    });

                })
                .catch(err => console.error(err))
            }
        }
 
    }
</script>
    
<style>
.create-list {
    border-style: dashed;
    border-color: skyblue;
    height: 50%;
}

</style>