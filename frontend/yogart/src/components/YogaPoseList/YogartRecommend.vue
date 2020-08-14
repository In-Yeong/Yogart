<template>
    
    <div>
        <h3 class="m-5">요가트 추천 코스</h3>
        <carousel-3d :controls-visible="true" :clickable="false" :controls-prev-html="'&#10092;'" :controls-next-html="'&#10093;'">
            <slide :index="0">
                <figure>
                    <img  @click="clickSet(1)" :src="require('../../../public/yogaList/relax.jpg')"/>
                     <figcaption>
                         바디 릴렉싱 요가
                    </figcaption>
                </figure>
              
            </slide>
            <slide :index="1" @click="clickSet(2)">
                <figure>
                    <img  :src="require('../../../public/yogaList/energy.jpg')"/>
                     <figcaption>
                         에너지 넘치는 아침 요가
                    </figcaption>
                </figure>
            </slide>     
        </carousel-3d>
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