<template>
  <div>
      <h1 class="m-3" style="font-weight:700">요가 포즈 리스트</h1>
 
      <!-- <YogartRecommend @clickSet="yogaListDetail"/> -->
      <BodyPartRecommend @clickSet="yogaListDetail"/>
      <UserDesign @clickSet="yogaListDetail"/>
      
  </div>
</template>

<script>
// import YogartRecommend from '@/components/YogaPoseList/YogartRecommend.vue'
import BodyPartRecommend from '@/components/YogaPoseList/BodyPartRecommend.vue'
import UserDesign from '@/components/YogaPoseList/UserDesign.vue'

export default {
    name : 'YogaPoseListPage',
    components: {
        // YogartRecommend,
        BodyPartRecommend,
        UserDesign
        
    },
    
    methods: {
        yogaListDetail(num) {
            // 쿠키에 저장
            this.$cookies.set('coaching-list', num)
            this.$router.push(`/coaching/yogaposelist/detail`)
        }
    },
}
</script>

<style>
 
.carousel-3d-container figure {
  margin: 0;
}

.carousel-3d-container figcaption {
  
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  bottom: 0;
  position: absolute;
  bottom: 0;
  padding: 15px;
  font-size: 30px;
  min-width: 100%;
  min-height:100%;
  line-height : 270px;
  box-sizing: border-box;
}
.carousel-3d-container figcaption:hover {
  cursor: pointer;
  background-color: rgba(0, 0, 0, 0);
  
}
</style>