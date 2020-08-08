<template>
    
    <div class="container">
        <h1 class="m-5">부위별 추천 코스</h1>
        <div class="row">
            <div class="col-4 border rect" @click="clickSet(3)">다리</div>
            <div class="col-4 border rect" @click="clickSet(4)">복근</div>
            <div class="col-4 border rect" @click="clickSet(5)">척추</div>
            <div class="col-4 border rect" @click="clickSet(6)">전신</div>
            
        </div>
    </div>
</template>

<script>
    export default {
        name : 'BodyPartRecommend',
        data() {
            return{
             
            }
        },
        created() {
            
        },
        methods: {
            clickSet(num){
                this.$emit("clickSet",num)
            }
        }
 
    }
</script>
    
<style>

</style>