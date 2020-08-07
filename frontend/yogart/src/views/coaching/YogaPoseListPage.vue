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
            // 쿠키에 저장
            this.$cookies.set('coaching-list', num)
            this.$router.push(`/coaching/yogaposelist/detail`)
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