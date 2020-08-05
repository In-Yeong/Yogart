<template>
  <div class="container">
      <h1>요가 포즈 페이지 입니다</h1>
        <p>{{poseList}}</p>
       <button @click="makeList" class="w3-btn w3-round-xlarge w3-red w3-large m-3">make pose list</button>
      <button class="w3-btn w3-round-xlarge w3-black w3-large">Put into my list</button>
       <div class="d-flex flex-column">
            <div class="w3-bar" >
            <!-- <button class="w3-bar-item w3-button w3-black" @click="onDiary">다이어리</button> -->
            <button class="w3-bar-item w3-button w3-black" style="width:150px;" @click="allBtn">All</button>
            <button class="w3-bar-item w3-button w3-gray" style="width:150px;" @click="beginnerBtn">beginner</button>
            <button class="w3-bar-item w3-button w3-teal" style="width:150px;" @click="intermediateBtn">intermediate</button>
            <button class="w3-bar-item w3-button w3-red" style="width:150px;"  @click="expertBtn">expert</button>
            </div>
            <div class="schedule">
            
                <!-- <div v-if="all"><YogaPoseAll :posefiles="posefiles"/></div>
                <div v-if="beginner"><YogaPoseBeginner  :posefiles="beginnerPosefiles"/></div>
                <div v-if="intermediate"><YogaPoseIntermediate  :posefiles="intermediatePosefiles"/></div>
                <div v-if="expert"><YogaPoseExpert :posefiles="expertPosefiles"/></div>
             -->
            </div>
        </div>

      
      <div class="row">
        <div class="col-sm-3" id="poses" v-for="posefile in tempPosefiles" :key="posefile.pose_name">
            
                <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../public/photos/${posefile.file_reference}`)">
                <p>{{posefile.pose_name}}</p>
       </div>      
      </div>
  </div>
</template>

<script>
import posefiles from '../../public/json.js'
// import YogaPoseAll from '../components/YogaPose/YogaPoseAll.vue'
// import YogaPoseBeginner from '../components/YogaPose/YogaPoseBeginner.vue'
// import YogaPoseIntermediate from '../components/YogaPose/YogaPoseIntermediate.vue'
// import YogaPoseExpert from '../components/YogaPose/YogaPoseExpert.vue'
// console.log(posefiles)
export default {
    name : 'YogaPosePage',
    components : {
        // YogaPoseAll,
        // YogaPoseBeginner,
        // YogaPoseIntermediate,
        // YogaPoseExpert
    },
    data() {
        return {
            tempPosefiles : posefiles,
            posefiles : posefiles,
            count : 0,
            poseList : [],
            max : 7,
            beginnerPosefiles : [],
            intermediatePosefiles : [],
            expertPosefiles : [],
            all : true ,
            beginner : false,
            intermediate : false,
            expert : false,

            
            
        }
    },
    created() {
        // this.difficultyDistribution()
       },
    methods : {
        // difficultyDistribution() {
        //     this.posefiles.forEach(function(posefile) {
        //     console.log(posefile.difficulty)
        //     if (posefile.difficulty === 'Beginner') {
        //             console.log(posefile.difficulty)
        //             this.beginnerPosefiles.push(posefile)
        //         }
        //         else if (posefile.difficulty === 'Intermediate') {
        //             this.intermediatePosefiles.push(posefile)
        //         }
        //         else if (posefile.difficulty === 'Expert') {
        //             console.log("gg")
        //             this.expertPosefiles.push(posefile)
        //             console.log(this.expertPosefiles)
        //         }
        // })
        // },
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
        allBtn() {
            console.log('all버튼을 눌렀습니다.')
           this.all = true
           this.beginner = false
           this.intermediate = false
           this.expert = false
           console.log(this.all,this.beginner,this.intermediate,this.expert)
        }
        ,
        beginnerBtn() {
            console.log('beginner버튼을 눌렀습니다.')
           this.all = false
           this.beginner = true
           this.intermediate = false
           this.expert = false

        console.log(this.all,this.beginner,this.intermediate,this.expert) 
        },
        intermediateBtn() {
            console.log('intermediate버튼을 눌렀습니다.')
           this.all = false
           this.beginner = false
           this.intermediate = true
           this.expert = false
           console.log(this.all,this.beginner,this.intermediate,this.expert) 
             
        },
        expertBtn() {
            console.log('expert버튼을 눌렀습니다.')
           this.all = false
           this.beginner = false
           this.intermediate = false
           this.expert = true
           console.log(this.all,this.beginner,this.intermediate,this.expert)  
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