<template>
  <div class=" my-5" >
      <h1 style="font-weight:700px;" class="my-5">나만의 코스 만들기</h1>
      <!-- <div class="d-flex flex-column" style="background-color: rgba(242, 157, 143, 0.5);"> -->
      <div id="course-list">
        <div class="field-row mx-5">
            <input name="courseName" id="courseName" v-model="courseName" type="text" required @keyup-enter="makeList(courseName)"/>
            <label for="courseName">코스명 + Enter</label>
            <!-- <button @click="makeList(courseName)" class="btn-white">생성</button> -->
        </div>
       
        <p style="color:#888; margin-top:5px;">포즈를 선택해 주세요( 최대 7개 )<span @click="reset" class="reset-btn">Reset</span></p>
        <div class="d-flex justify-content-around">

                <div v-for="idx in poseIndexList" :key="idx">
                 <div class="pose-img">
                    <img class="user-profile-mini m-2" :src="require(`../../../public/photos/${posefiles[idx].file_reference}`)">
                </div> 
                <p>{{posefiles[idx].korean_pose_name}}</p>
                </div>
          
        </div>
    </div>
        <!-- <p>{{poseList}}</p> -->
        <!-- <p>{{poseIndexList}}</p> -->
       <div class="container">
           <div>
            <div class="d-flex justify-content-around" >
            <div id="all-btn" class="btn-white"  @click="allBtn">전체</div>
            <div id="beg-btn" class="btn-white"  @click="beginnerBtn">초급</div>
            <div id="int-btn" class="btn-white"  @click="intermediateBtn">중급</div>
            <div id="exp-btn" class="btn-white"  @click="expertBtn">고급</div>
            </div>
           
            
                <div v-if="all" class="row" >       
                    <div @click="poseChoose(posefile)" class="col-sm-2 box all-box" :id="posefile.id" v-for="posefile in posefiles" :key="posefile.pose_name">  
                       <div class="pose-img">
                        <img :title="posefile.korean_pose_name"  class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                        <!-- <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')"> -->
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                    </div>      
                    </div>
                </div>
                <div v-if="beginner" >
                    <div class="row">
                       
                    <div @click="poseChoose(posefile)" class="col-sm-2 box beginner-box" :id="posefile.id" v-for="posefile in beginnerPosefiles" :key="posefile.pose_name">  
                         <div class="pose-img" >
                            <img  class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                            <!-- <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')"> -->
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                        <!-- <p>{{posefile.id}}</p> -->
                    </div>      
                    </div>
                </div>
                <div v-if="intermediate">
                    <div class="row">
                       
                    <div @click="poseChoose(posefile)" class="col-sm-2 box intermediate-box" :id="posefile.id" v-for="posefile in intermediatePosefiles" :key="posefile.pose_name">  
                        
                         <div class="pose-img">
                            <img class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                            <!-- <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')"> -->
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                        <!-- <p>{{posefile.id}}</p> -->
                    </div>      
                    </div>
                </div>
                <div v-if="expert" >
                    <div class="row">
                       
                    <div @click="poseChoose(posefile)" class="col-sm-2 box expert-box" :id="posefile.id" v-for="posefile in expertPosefiles" :key="posefile.pose_name">  
                        
                         <div class="pose-img">
                            <img class="user-profile m-3" :src="require(`../../../public/photos/${posefile.file_reference}`)">
                            <!-- <img @click="poseChoose(posefile)" class="user-profile m-3 pose-img-top" :src="require('../../../public/css/heart.png')"> -->
                        </div> 
                        <p>{{posefile.korean_pose_name}}</p>
                        <!-- <p>{{posefile.id}}</p> -->
                    </div>      
                </div>
            </div>  
        </div>
    </div>

</template>

<script>
import posefiles from '../../../public/json.js'
import axios from 'axios'
var allItems = document.getElementsByClassName("all-box");
var beginnerItems = document.getElementsByClassName("beginner-box");
var intermediateItems = document.getElementsByClassName("intermediate-box");
var expertItems = document.getElementsByClassName("expert-box");

// var all = document.getElementById('all-btn')
// var beg = document.getElementById('beg-btn')
// var int = document.getElementById('int-btn')
// var exp = document.getElementById('exp-btn')


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
        this.allSelected()
        this.beginnerSelected()
        this.intermediateSelected()
        this.expertSelected()
    },
    methods : {
        reset(){
            this.poseList = []
            this.poseIndexList = []
        },
        allSelected() {
            console.log("allSelected implemented",this.poseIndexList)
            for(var i=0; i < allItems.length; i++) {
                //만약 이미 리스트에 있으면
                if (this.poseIndexList.includes(parseInt(allItems[i].id))){
                    console.log("여긴 올셀렉트",allItems[i].id)
                     allItems[i].style.backgroundColor = 'rgba(242, 157, 143,0.5)'
                    //  allItems[i].style.backgroundColor = 'rgba(255, 255, 255, 0.9)'
                    }
                else{
                    allItems[i].style.backgroundColor = 'rgba(0,0,0,0)'
                }       
            }
        },
        beginnerSelected() {
            console.log("beginnerItems implemented",this.poseIndexList)
            for(var i=0; i < beginnerItems.length; i++) {
                //만약 이미 리스트에 있으면
                console.log(beginnerItems[i].id)
                if (this.poseIndexList.includes(parseInt(beginnerItems[i].id))){
                    console.log(beginnerItems[i].id)
                    beginnerItems[i].style.backgroundColor = 'rgba(242, 157, 143,0.5)'
                    console.log("바꿨다!")
                    }
                else{
                    beginnerItems[i].style.backgroundColor = 'rgba(0,0,0,0)'
                }       
            }
        },
        intermediateSelected() {
            console.log("intermediateItems implemented",this.poseIndexList)
            for(var i=0; i < intermediateItems.length; i++) {
                //만약 이미 리스트에 있으면
                console.log(intermediateItems[i].id)
                if (this.poseIndexList.includes(parseInt(intermediateItems[i].id))){
                    console.log(intermediateItems[i].id)
                     intermediateItems[i].style.backgroundColor = 'rgba(242, 157, 143,0.5)'
                    }
                else{
                    intermediateItems[i].style.backgroundColor = 'rgba(0,0,0,0)'
                }       
            }
        },
        expertSelected() {
            console.log("allSelected implemented",this.poseIndexList)
            for(var i=0; i < expertItems.length; i++) {
                //만약 이미 리스트에 있으면
                console.log(expertItems[i].id)
                if (this.poseIndexList.includes(parseInt(expertItems[i].id))){
                    console.log(expertItems[i].id)
                     expertItems[i].style.backgroundColor = 'rgba(242, 157, 143,0.5)'
                    }
                else{
                    expertItems[i].style.backgroundColor = 'rgba(0,0,0,0)'
                }       
            }
        },
        
       
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
            
            if (this.poseList.includes(btnPose.korean_pose_name)){
                var idx = this.poseList.indexOf(btnPose.korean_pose_name)
                this.poseList.splice(idx,1);
                    this.poseIndexList.splice(idx,1);
                
            }
            else{
                if (this.poseList.length < this.max ) {
                    this.poseList.push(btnPose.korean_pose_name)
                    this.poseIndexList.push(btnPose.id)
                }
                else {
                    alert('최대 7개의 동작만 가능합니다.')
                }
            }
            
            this.allSelected()
            this.beginnerSelected()
            this.intermediateSelected()
            this.expertSelected()

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
            this.all = true
            this.beginner = false
            this.intermediate = false
            this.expert = false
            // all.style.backgroundColor = '#f29d8f'
            // beg.style.backgroundColor = ' rgba(255,255,255,0.3)'
            // int.style.backgroundColor = ' rgba(255,255,255,0.3)'
            // exp.style.backgroundColor = ' rgba(255,255,255,0.3)'
            this.allSelected()
        },
        beginnerBtn() {
            this.all = false
            this.beginner = true
            this.intermediate = false
            this.expert = false
            // all.style.backgroundColor = ' rgba(255,255,255,0.3)'
            // beg.style.backgroundColor = ' #f29d8f'
            // int.style.backgroundColor = ' rgba(255,255,255,0.3)'
            // exp.style.backgroundColor = ' rgba(255,255,255,0.3)'
            this.beginnerSelected()
            
        },
        intermediateBtn() {
            this.all = false
            this.beginner = false
            this.intermediate = true
            this.expert = false
            // all.style.backgroundColor = 'rgba(255,255,255,0.3)'
            // beg.style.backgroundColor = 'rgba(255,255,255,0.3)'
            // int.style.backgroundColor = '#f29d8f'
            // exp.style.backgroundColor = 'rgba(255,255,255,0.3)'
            this.intermediateSelected()
                 
        },
        expertBtn() {
            this.all = false
            this.beginner = false
            this.intermediate = false
            this.expert = true
            // all.style.backgroundColor = 'rgba(255,255,255,0.3)'
            // beg.style.backgroundColor = 'rgba(255,255,255,0.3)'
            // int.style.backgroundColor = 'rgba(255,255,255,0.3)'
            // exp.style.backgroundColor = '#f29d8f'
            this.expertSelected()
           
        },
        
    }
}



</script>

<style scoped>
/* .container{
    padding: 40px;
    border : 2px solid rgba(242, 157, 143, 0.5);
} */
p {
    font-weight: 500;
}
.btn-white{
    margin-bottom : 10px;
    font-size:20px;
    border-radius: 5px;
    width : 150px;
    height: 40px;
    line-height: 15px;
}
.btn-white:visited{
    background-color: #f29d8f;
    color : white;
}
.reset-btn{
    padding : 3px;
    background-color:  rgba(255, 255, 255, 0.5);
    border : 1.5px solid #f29d8f;
    color : #f29d8f;
    font-size:10px;
    border-radius: 5px;
    height:20px; 
    width:50px; 
    line-height:10px;
}
.reset-btn:hover{
    cursor: pointer;
    background-color: #f29d8f;
    color : white;
}

.box{
    /* margin : 5px; */
    background-color: rgba(255, 255, 255, 0.0);
}
.box:hover{
    border : 2px solid white;
    cursor: pointer;
}
.box:active{
    background-color: rgba(255, 255, 255, 0.5);
}

.user-profile {
  display: inline-block;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border : 2px solid rgba(242, 157, 143);

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}
.user-profile-mini {
  display: inline-block;
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border : 2px solid rgba(242, 157, 143);

  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
}
@import 'https://fonts.googleapis.com/css?family=Titillium+Web';
.field-row {
	font-family: 'Titillium Web', sans-serif;
	margin:30px 0 0 10px;
	position:relative;
}
label {
	font-size:16px;
	color:#888;
	position:absolute;
	top: 10px;
    bottom: 0;
    left: 310px;
	transition: .3s ease;
	cursor:text;
}
input {
    background :rgba(255, 255, 255, 0.5);
	font-size:16px;
	line-height:18px;
	padding: 10px 10px 10px 0;
	border:0;
	border-bottom:1px solid #ccc;
	outline:none;
	/* width:220px; */
}
input:focus {
	border-color:#888;
}
input:focus ~ label, input:valid ~ label {
	font-size:15px;
	/* color:#d79fd7; */
	color:black;
	transform: translate3d(0, -30px, 0);
}
#course-list{
    width:1140px; 
    background-color: rgba(242, 157, 143,0.5);
    margin : auto;
    margin-bottom: 3rem;
    padding-top: 5px;
}
</style>