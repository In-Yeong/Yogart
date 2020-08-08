<template>
    
    <div class="m-5">
        <h1 class="m-5">부위별 추천 코스</h1>
        <div class="d-flex">
            <div class="d-flex flex-column border rect" @click="clickSet(3)"><img style="width:300px; height:261px;" :src="require('../../../public/yogaList/legs.jpg')"/><p>다리</p></div>
            <div class="d-flex flex-column border rect" @click="clickSet(4)"><img style="width:300px; height:261px;" :src="require('../../../public/yogaList/arm.jpg')"/><p>팔</p></div>
            <div class="d-flex flex-column border rect" @click="clickSet(5)"><img style="width:300px; height:261px;" :src="require('../../../public/yogaList/abs.jpg')"/><p>복근</p></div>
            <div class="d-flex flex-column border rect" @click="clickSet(6)"><img style="width:300px; height:261px;" :src="require('../../../public/yogaList/spine.jpg')"/><p>척추</p></div>
            <div class="d-flex flex-column border rect" @click="clickSet(7)"><img style="width:300px; height:261px;" :src="require('../../../public/yogaList/body.jpg')"/><p>전신</p></div>
            
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