<template>
    
    <div class="container">
        <h1 class="m-5">사용자 지정 코스</h1>
        <button @click="newCourse()">새로운 코스 만들기</button>
        <div class="row">
            <div class="col-4 border rect" @click="clickSet(5)">5번 리스트</div>
            <div class="col-4 border rect" @click="clickSet(6)">6번 리스트</div>
            <div class="col-4 border rect" @click="clickSet(7)">7번 리스트</div>
        </div>
    </div>
</template>

<script>
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
            }
        }
 
    }
</script>
    
<style>

</style>