<template>
    
    <div>
        <h1 class="m-5">요가트 추천 코스</h1>
        <div class="row">
            <div class="col-4 border rect d-flex flex-column" @click="clickSet(1)"><img style="width:450px; height:300px;" :src="require('../../../public/yogaList/relax.jpg')"/><p>Relexing Body</p></div>
            <div class="col-4 border rect d-flex flex-column" @click="clickSet(2)"><img style="width:450px; height:300px;" :src="require('../../../public/yogaList/energy.jpg')"/><p>Energy Morning</p></div>
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