<template>
  <div class="container my-5" >
      <h1 class="my-5">나만의 코스 만들기</h1>
        <h5>코스명</h5>
        <span>
            <input class="w-25" v-model="courseName">
            <button @click="makeList(courseName)" class="w3-btn w3-round-xlarge w3-red w3-small m-3">생성</button>
        </span>
        <h5>선택한 포즈 :</h5>
        <p>{{poseList}}</p>
        <!-- <p>{{poseIndexList}}</p> -->
       <div class="d-flex flex-column">
            <div class="w3-bar" >
            <!-- <button class="w3-bar-item w3-button w3-black" @click="onDiary">다이어리</button> -->
            <button class="w3-bar-item w3-button w3-black" style="width:150px;" @click="allBtn">전체</button>
            <button class="w3-bar-item w3-button w3-gray" style="width:150px;" @click="beginnerBtn">초급</button>
            <button class="w3-bar-item w3-button w3-teal" style="width:150px;" @click="intermediateBtn">중급</button>
            <button class="w3-bar-item w3-button w3-red" style="width:150px;"  @click="expertBtn">고급</button>
            </div>
            <div class="pose-box">
            
                <div v-if="all">
                    <div class="row">
                       
                    <div class="col-sm-2" id="poses" v-for="posefile in posefiles" :key="posefile.pose_name">  
                       <div class="pose-img">
                        <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                        <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')">
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                    </div>      
                    </div>
                </div>
                <div v-if="beginner">
                    <div class="row">
                       
                    <div class="col-sm-2" id="poses" v-for="posefile in beginnerPosefiles" :key="posefile.pose_name">  
                         <div class="pose-img">
                            <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                            <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')">
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                        <!-- <p>{{posefile.id}}</p> -->
                    </div>      
                    </div>
                </div>
                <div v-if="intermediate">
                    <div class="row">
                       
                    <div class="col-sm-2" id="poses" v-for="posefile in intermediatePosefiles" :key="posefile.pose_name">  
                        
                         <div class="pose-img">
                            <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                            <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')">
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                        <!-- <p>{{posefile.id}}</p> -->
                    </div>      
                    </div>
                </div>
                <div v-if="expert">
                    <div class="row">
                       
                    <div class="col-sm-2" id="poses" v-for="posefile in expertPosefiles" :key="posefile.pose_name">  
                        
                         <div class="pose-img">
                            <img @click="poseChoose(posefile)" class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                            <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')">
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                        <!-- <p>{{posefile.id}}</p> -->
                    </div>      
                </div>
            </div>  
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
            SERVER_URL : this.$store.state.SERVER_URL
            
            
        }
    },
    mounted() {
        this.difficultyDistribution()
        // this.tagDistribution()
    },
    methods : {
        difficultyDistribution() {
            this.posefiles.forEach(function(posefile) {
     
            if (posefile.difficulty === 'Beginner') {
              
                    this.beginnerPosefiles.push(posefile)
                }
                else if (posefile.difficulty === 'Intermediate') {
                    this.intermediatePosefiles.push(posefile)
                }
                else if (posefile.difficulty === 'Expert') {
                    
                    this.expertPosefiles.push(posefile)
                    
                }
        }.bind(this))
        },
        tagDistribution() {
            const tags_idx = ['전신','팔','다리','복근','에너지','릴렉싱','척추']
            var tags_val = [0,0,0,0,0,0,0]
            var error = 0
            this.posefiles.forEach(function(posefile) {
                // var pose = posefile
                console.log(posefile)
                posefile.tag.forEach(function(tag){

                if (tag === '전신') {
                        tags_val[0] ++
                        
                    }
                    else if (tag === '팔') {
                        tags_val[1] ++
                    }
                    else if (tag === '다리') {
                        tags_val[2] ++
                    }
                    else if (tag === '복근') {
                        tags_val[3] ++
                    }
                    else if (tag === '에너지') {
                        tags_val[4] ++
                    }
                    else if (tag === '릴렉싱') {
                        tags_val[5] ++
                    } 
                    else if (tag === '척추') {
                        tags_val[6] ++
                    } 
                    else {
                        error ++
                    }
                    
                })
        }.bind(this))
        console.log(tags_idx,tags_val,"오타:",error)
        },
        poseChoose(btnPose) {
            if (this.poseList.length < this.max ) {
                if (this.poseList.includes(btnPose.korean_pose_name)){

                    var idx = this.poseList.indexOf(btnPose.korean_pose_name)
                    this.poseList.splice(idx,1);
                    this.poseIndexList.splice(idx,1);
                }
                else {
                    this.poseList.push(btnPose.korean_pose_name)
                    this.poseIndexList.push(btnPose.id)
                }
            }
            else {
                alert('최대 7개의 동작만 가능합니다.')
            }
        },
        makeList(courseName) {
            let poseCourse = this.poseIndexList.join(',')
            console.log(this.poseIndexList)
            console.log(courseName,poseCourse)
            window.confirm(`${courseName} 리스트를 만드시겠습니까?`);
            axios.post(this.SERVER_URL + '/api/aicoach/list/create',
            { 'headers': { 'auth-token': window.$cookies.get('auth-token')},'courseName' : courseName, 'poseCourse': poseCourse } )
            .then(res => {
                console.log("이거 res",res)
                //이 안에서 코스 pk뽑아오기
                // 쿠키에 저장
                this.$cookies.set('coaching-list', res.data)
                this.$router.push(`/coaching/yogaposelist`)
            })
            .catch(err => {console.log(err)})
            

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
.user-profile:hover{
    background: url("../../../public/css/heart.png") no-repeat;
   
}
.pose-img {
    position: relative;
    display: inline-block;
  
}
.pose-img .pose-img-top {
    display: none;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 99;
    cursor:pointer;
}
.pose-img:hover .pose-img-top {
    display: inline;
}
</style>