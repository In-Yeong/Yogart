<template>
  <div class="container">
      <h1>요가 포즈 페이지 입니다</h1>
        <p>{{poseList}}</p>
       <button @click="makeList" class="w3-btn w3-round-xlarge w3-red w3-large m-3">make pose list</button>
      <button class="w3-btn w3-round-xlarge w3-black w3-large">Put into my list</button>
       <div class="d-flex flex-column">
            <div class="w3-bar" >
            <!-- <button class="w3-bar-item w3-button w3-black" @click="onDiary">다이어리</button> -->
            <button class="w3-bar-item w3-button w3-black" style="width:150px;" @click="all">All</button>
            <button class="w3-bar-item w3-button w3-gray" style="width:150px;" @click="beginner">beginner</button>
            <button class="w3-bar-item w3-button w3-teal" style="width:150px;" @click="intermediate">intermediate</button>
            <button class="w3-bar-item w3-button w3-red" style="width:150px;"  @click="expert">expert</button>
            </div>
            <div class="schedule">
                <PTManagement :ptManagement="ptManagement"/>
                <!-- <Diary :diary="diary"/> -->
                <ExerciseHistory :exerciseHistory="exerciseHistory"/>
            </div>
        </div>

      
      <div class="row">
        <div class="col-sm-3" id="poses" v-for="posefile in tempPosefiles" :key="posefile.pose_name">
            
                <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                <p>{{posefile.pose_name}}</p>
       </div>      
         
          

      </div>
  </div>
</template>

<script>
import posefiles from '../../../public/json.js'
// console.log(posefiles)
export default {
    name : 'YogaPosePage',
    data() {
        return {
            posefiles : posefiles,
            count : 0,
            poseList : [],
            max : 7,
            tempPosefiles : posefiles,
            
        }
    },
    methods : {
        poseChoose(btnPose) {
            if (this.poseList.length <= this.max ) {
                if (this.poseList.includes(btnPose.pose_name)){
                    // this.poseList.filter(function(value, index, arr){ return value > 5;})
                    var idx = this.poseList.indexOf(btnPose.pose_name)
                    this.poseList.splice(idx,1);
                }
                else {
                    this.poseList.push(btnPose.pose_name)
                }
            }
            else {
                alert('최대 7개의 동작만 가능합니다.')
            }
        },
        makeList() {
          alert('최대 7개의 동작만 가능합니다.')
          

        },
        all() {
            this.tempPosefiles = this.posefiles
        }
        ,
        beginner() {
            this.tempPosefiles = []
            this.posefiles.forEach(function(profile) {
                if (profile.difficulty === 'Beginner') {
                    this.tempPosefiles.push(profile)
                }
            })
             
        },
    }
}



</script>

<style>
.user-profile {
  display: inline-block;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border : 2px solid black;

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}
</style>