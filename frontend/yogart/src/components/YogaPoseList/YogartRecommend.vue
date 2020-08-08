<template>
    
    <div>
        <h1 class="m-5">요가트 추천 코스</h1>
        <div class="row">
            <div class="col-4 border rect" @click="clickSet(1)">Relexing Body</div>
            <div class="col-4 border rect" @click="clickSet(2)">Energy Morning</div>
        </div>
    </div>
</template>

<script>
    export default {
        name : 'YogartRecommend',
        data() {
            return{
             
            }
        },
        methods: {
            clickSet(num){
                this.$emit("clickSet",num)
            }
        },
 
    }
</script>
    
<style scoped>

</style>