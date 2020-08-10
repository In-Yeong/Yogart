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
             
            }
        },
        created() {
            this.getUserCourse()
        },
        methods: {
            clickSet() {
                console.log('here~~~')
                var num = this.$cookies.get('clickId')*1
                console.log(num)
                this.$emit("clickSet",num)
            },
            newCourse() {
                this.$router.push(`/coaching/yogapose/`)
            },
            getUserCourse() {
                axios.get(this.SERVER_URL + `/api/aicoach/list`, { 'headers': { 'auth-token': window.$cookies.get('auth-token') } })
                .then(res => {
                    console.log(res)
                    res.data.forEach(e => {
                        var list = document.createElement('div');
                        list.classList.add('col-4', 'border', 'rect', 'd-flex', 'flex-column');
                        list.addEventListener('click', function(){
                            // 미완 (테스트 필요)
                            this.$cookies.set('clickId', e.listId)
                            this.clickSet
                        })
                        list.innerText = e.courseName
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