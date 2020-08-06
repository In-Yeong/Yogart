<template>
  <div class="container my-5" >
      <h1 class="my-5">Making Yoga Pose List</h1>
        <h5>Wish poses :</h5>
        <p>{{poseList}}</p>
        <p>{{poseIndexList}}</p>
        
       <button @click="makeList" class="w3-btn w3-round-xlarge w3-red w3-large m-3">make pose list</button>
      <!-- <button class="w3-btn w3-round-xlarge w3-black w3-large">Put into my list</button> -->
       <div class="d-flex flex-column">
            <div class="w3-bar" >
            <!-- <button class="w3-bar-item w3-button w3-black" @click="onDiary">다이어리</button> -->
            <button class="w3-bar-item w3-button w3-black" style="width:150px;" @click="allBtn">All</button>
            <button class="w3-bar-item w3-button w3-gray" style="width:150px;" @click="beginnerBtn">beginner</button>
            <button class="w3-bar-item w3-button w3-teal" style="width:150px;" @click="intermediateBtn">intermediate</button>
            <button class="w3-bar-item w3-button w3-red" style="width:150px;"  @click="expertBtn">expert</button>
            </div>
            <div class="pose-box">
            
                <div v-if="all">
                    <div class="row">
                    <div class="col-sm-3" id="poses" v-for="posefile in posefiles" :key="posefile.pose_name">  
                        <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                      
                        <p>{{posefile.pose_name}}</p>
                    </div>      
                    </div>
                </div>
                <div v-if="beginner">
                    <div class="row">
                    <div class="col-sm-3" id="poses" v-for="posefile in beginnerPosefiles" :key="posefile.pose_name">  
                        <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                        <p>{{posefile.pose_name}}</p>
                    </div>      
                    </div>
                </div>
                <div v-if="intermediate">
                    <div class="row">
                    <div class="col-sm-3" id="poses" v-for="posefile in intermediatePosefiles" :key="posefile.pose_name">  
                        <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                       
                        <p>{{posefile.pose_name}}</p>
                    </div>      
                    </div>
                </div>
                <div v-if="expert">
                    <div class="row">
                    <div class="col-sm-3" id="poses" v-for="posefile in expertPosefiles" :key="posefile.pose_name">  
                        <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                        <p>{{posefile.pose_name}}</p>
                    </div>      
                    </div>
                </div>
              
            
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
import axios from 'axios'

// console.log(posefiles)
export default {
    name : 'YogaPosePage',

    data() {
        return {
            tempPosefiles : posefiles,
            posefiles : posefiles,
            count : 0,
            poseList : [],
            poseIndexList : [],
            max : 7,
            beginnerPosefiles : [],
            intermediatePosefiles : [],
            expertPosefiles : [],
            all : true ,
            beginner : false,
            intermediate : false,
            expert : false,
            idx_count : 0,
            
        }
    },
    mounted() {
        this.difficultyDistribution()
    },
    methods : {
        difficultyDistribution() {
            this.posefiles.forEach(function(posefile) {
            console.log(posefile.difficulty)
            if (posefile.difficulty === 'Beginner') {
                    console.log(posefile.difficulty)
                    this.beginnerPosefiles.push(posefile)
                }
                else if (posefile.difficulty === 'Intermediate') {
                    this.intermediatePosefiles.push(posefile)
                }
                else if (posefile.difficulty === 'Expert') {
                    console.log(this)
                    console.log(this.expertPosefiles)
                    this.expertPosefiles.push(posefile)
                    
                }
        }.bind(this))
        },
        poseChoose(btnPose) {
            if (this.poseList.length < this.max ) {
                if (this.poseList.includes(btnPose.pose_name)){

                    var idx = this.poseList.indexOf(btnPose.pose_name)
                    this.poseList.splice(idx,1);
                    this.poseIndexList.splice(idx,1);
                }
                else {
                    this.poseList.push(btnPose.pose_name)
                    this.poseIndexList.push(btnPose.id)
                }
            }
            else {
                alert('최대 7개의 동작만 가능합니다.')
            }
        },
        makeList() {
            let poseCourse = this.poseIndexList.join('/')
            console.log(this.poseIndexList)
            console.log(poseCourse)
        //   window.confirm("리스트로 넘어갑니다.");
        //   axios.post('주소')
        //   .then(res => {
        //       console.log(res)
        //       this.$router.push(`/coaching/yogaposelist/${코스번호}`,poseCourse)
        //   })
        //   .catch(err => {console.log(err)})
          

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
.pose-box {
    background-color: lightgray;
}
</style>