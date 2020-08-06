<template>
    
    <div class="container">
        <h1 class="m-5">사용자 지정 코스</h1>
        <div class="row">
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
            
        },
        methods: {
            clickSet(num) {
                this.$emit("clickSet",num)
            },
            newCourse() {
                this.$router.push(`/coaching/yogapose/`)
            },
            getUserCourse() {
                axios.get(this.SERVER_URL + `/api/aicoach/list`, { 'headers': { 'auth-token': window.$cookies.get('auth-token') } })
                .then(response => {
                    console.log(response)
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