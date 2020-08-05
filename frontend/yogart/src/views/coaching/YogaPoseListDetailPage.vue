<template>
  <div>
        <h1>{{list_id}}번 리스트 디테일 페이지 입니다</h1>
        <div class="col-sm-3" id="poses" v-for="posefile in tempPosefiles" :key="posefile.pose_name">
            
            <img class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
            <p>{{posefile.pose_name}}</p>
        </div>

      
  </div>
</template>

<script>
import posefiles from '../../../public/json.js'
export default {
    name : 'YogaPoseListDetailPage',
    data() {
        return {
            posefiles : posefiles,
            list_id : document.location.href.split("yogaposelist/")[1],
            yoga_list: "2/3/4"
        }
    },
    components: {
        
    },
    methods: {

    },
    created() {
    },
}
</script>

<style>


</style>