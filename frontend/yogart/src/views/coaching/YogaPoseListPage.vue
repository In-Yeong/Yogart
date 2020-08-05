<template>
  <div>
      <h1>요가 포즈 리스트 페이지 입니다</h1>
      <YogartRecommend @clickSet="yogaListDetail"/>
      <BodyPartRecommend @clickSet="yogaListDetail"/>
      <UserDesign @clickSet="yogaListDetail"/>
      
  </div>
</template>

<script>
import YogartRecommend from '@/components/YogaPoseList/YogartRecommend.vue'
import BodyPartRecommend from '@/components/YogaPoseList/BodyPartRecommend.vue'
import UserDesign from '@/components/YogaPoseList/UserDesign.vue'

export default {
    name : 'YogaPoseListPage',
    components: {
        YogartRecommend,
        BodyPartRecommend,
        UserDesign
        
    },
    methods: {
        yogaListDetail(num) {
            this.$router.push(`/yogaposelist/${num}`)
        }
    },
}
</script>

<style>
    .rect {
        width: 30rem;
        height: 20rem;
        margin: auto;
        cursor: pointer;
    }

</style>