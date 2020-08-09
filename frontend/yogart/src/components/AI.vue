<template>   
    <div> 
        <div id="AI"  v-if="!loading">
            <h1>AI Coaching Service</h1>

            <h5>{{cur+1}}번째 동작 :{{course[cur].korean_pose_name}}</h5>
            <button v-if="startBtn" class="w3-btn w3-round-xlarge w3-red w3-xlarge m-5" type="button" @click="clickStart()">Get Start!</button>
        </div>
       
        <div id="loading" v-if="loading">
        <!-- <div id="loading" v-if="true"> -->
            <h3 class="m-5">AI 요가 코칭 서비스를 시작합니다</h3>
            <p>{{courseName}} 코스 준비중</p>
    
            <i class="fa fa-spinner fa-pulse fa-5x fa-fw m-5" ></i>
            <span class="sr-only">Loading...</span>

            <h3 class="m-5">웹캠을 켜주시고 잠시만 기다려 주세요</h3>
            <p> AI Yoga Coaching Service is running, Please turn on your webcam and wait</p>
        </div>
        <div class="row">
            <div v-if="aiPage" class="col-4">
                <div id="pose-data">
                    <!-- <p>{{time}}</p> -->
                    <div>포즈 이름: {{ posefiles[course[cur]].korean_pose_name }}</div>
                    <div>카테고리: {{posefiles[course[cur]].category }}</div>
                    <div>난이도: {{posefiles[course[cur]].difficulty }}</div>
                    <img :src="require(`../../public/photos/${posefiles[course[cur]].file_reference}`)" alt="">
                </div>
                <div>
                    <button @click="next" class="w3-btn w3-round-xlarge w3-red w3-large m-5" type="button">Next Yoga Posture</button>
                </div>
            </div>
            <div class="col-4">
                <div><canvas id="canvas"></canvas></div>
                <div id="label-container"></div>
                <h2 id="good">GOOD</h2>
                <h2 id="bad">BAD</h2>
            </div>
            <div class="col-4 coaching-data" id="coaching-data">
                <div class="watch m-5">{{ watchMin }}:{{ watchSec}}</div>
                <div id="pie-chart" class="pie-chart m-5"><span class="center" id="seconds-counter">30</span></div>
                <div>{{poseTimes}}</div>
            </div>
        </div>
        
        

    </div>
</template>

<script>
    import axios from 'axios'
    import '@tensorflow/tfjs'
    import * as tmPose from "@teachablemachine/pose"
    import posefiles from "../../public/json"
    // npm install --save @tensorflow/tfjs @teachablemachine/pose
    let model, webcam, ctx, labelContainer, maxPredictions;

    
    export default {
        name : 'Yoga1',
        data() {
            return{
                SERVER_URL: this.$store.state.SERVER_URL,
                startBtn : true,
                loading : false,
                aiPage : false,
                startTime : true,
                endTime : true,
                requestId : undefined,
                // course : this.$cookies.get('course').split(','),
                course : [1,2,3],
                courseName : 'courseName',
                cur : 0,
                flag : false,
                seconds : 30,
                time : '',
                stopBtn : false,
                restartBtn : false,
                counter : undefined,
                posefiles : posefiles,
                startDateTime: '',
                watch: '',
                watchStamp: 0,
                watchMin: '00',
                watchSec: '00',
                poseTimes: [],
                scores:[],
                SERVER_URL : this.$store.state.SERVER_URL


            }
        },
        mounted(){
            this.getCourse()
            this.calculateScores()
            document.getElementById('good').style.display= 'none'
            document.getElementById('bad').style.display= 'none'
        },
        beforeDestroy() {
            window.location.reload()
        },
        methods: {
             getCourse() {
                const courseID = this.$cookies.get('coaching-list')   
                console.log('코스아이디', courseID)
                axios.get(this.SERVER_URL + `/api/aicoach/list/${courseID}`)
                .then(res => {
                    console.log("result에서 axios 성공",res)
                    //코스 이름과 코스 리스트 save
                    this.courseName = res.data.courseName
                    
                    const Course =  res.data.course.split(',') 
                    const filteredCourse =  []
                    Course.forEach(function(courseID){
                        if (courseID !== "1000"){
                            filteredCourse.push(courseID)
                        }
                    })
                    this.course = filteredCourse
                    console.log(this.course)
                    //
                })
                .catch(err => {
                    this.course = [2,7,11]
                    console.error(err)
                })
            },
            calculateScores() {
                console.log(this.poseTimes,this.scores)
                this.poseTimes.forEach(function(poseTime){
                    if (poseTime < 30){
                        this.scores.push(0)
                    }
                    else {
                        var score = Math.floor(30/poseTime*100)
                        this.scores.push(score)
                    }
                }.bind(this))
                console.log('here',this.scores)
            },
            clickStart() {
                this.startDateTime = new Date();
                this.init()
            },
            incrementSeconds() {
                this.seconds--;
                document.getElementById('seconds-counter').innerText = this.seconds
                document.getElementById('pie-chart').style.background = `conic-gradient(#ffffff 0% ${100-100*(this.seconds/30)}%, red ${100-100*(this.seconds/30)}% 100%)`
                if (this.seconds===0) {
                    clearInterval(this.counter) 
                    this.next()
                } 
            },
            next() {
                this.cur++;
                console.log('here!', this.course.length)
                if (this.cur < this.course.length){
                    clearInterval(this.counter) 
                    this.flag = !this.flag
                    var t = this.watch-this.watchStamp
                    this.poseTimes.push(t)
                    this.watchStamp = this.watch
                    document.getElementById('seconds-counter').innerText = 30
                    document.getElementById('pie-chart').style.background = "red"
                    this.init(this.course[this.cur])

                }
                else {
                    var t = this.watch-this.watchStamp
                    this.poseTimes.push(t)
                    const runTime = this.watchMin+'.'+this.watchSec
                    console.log(this.startDateTime.getDate() )
                    this.$cookies.set('resultScores', this.scores.join("."))
                    this.$cookies.set('resultPoseTimes', this.poseTimes.join("."))
                    this.$cookies.set('resultRunTime', runTime )
                    this.$router.push("/coaching/result")
                }
                
            },
            stop() {
                this.stopBtn = true;
                this.restartBtn = false;     
                if (this.flag && this.stopBtn && this.counter!==undefined) {
                    clearInterval(this.counter)
                    this.counter = undefined
                    console.log("카운트 멈춤, ",this.seconds, this.counter)
                }
            },
            restart() {
                this.stopBtn = false;
                this.restartBtn = true;
                if (!this.requestId) {
                    this.requestId = window.requestAnimationFrame(this.loop);
                }
                if(this.flag && this.restartBtn && this.counter===undefined) {
                    this.counter = setInterval(this.incrementSeconds,1000)
                } 
            },

            timerCustom(num) {
                num = num + '';
                if (num.length < 2) {
                    num = '0' + num
                }
                return num
            },

            async init() {

                console.log(this.course[this.cur])
                this.startBtn = false;
                
               
                console.log("click the start btn")
                // 로딩이 시작
                this.loading = true;
                
                const URL = `../YogaPoses/${this.course[this.cur]}/`;
                const modelURL = URL + "model.json";
                const metadataURL = URL + "metadata.json";
    
                // load the model and metadata
                // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
                // Note: the pose library adds a tmPose object to your window (window.tmPose)
              
                model = await tmPose.load(modelURL, metadataURL);
                console.log("model",model)
                maxPredictions = model.getTotalClasses();
        
                // Convenience function to setup a webcam
                //여기 웹캠 사이즈
                const width = 400;
                const height = 400;
                const flip = true; // whether to flip the webcam
                webcam = new tmPose.Webcam(width, height, flip); // width, height, flip
                await webcam.setup(); // request access to the webcam
                await webcam.play();
                // this 붙여줘야!!!!!!!!!!!
                this.requestId  = window.requestAnimationFrame(this.loop);
                console.log("Here is init",this.requestId)
                // append/get elements to the DOM
                const canvas = document.getElementById("canvas");
                canvas.width = width; 
                canvas.height = height;
                ctx = canvas.getContext("2d");
                labelContainer = document.getElementById("label-container");
                for (let i = 0; i < maxPredictions; i++) { // and class labels
                    labelContainer.appendChild(document.createElement("div"));
                }
            },
            async loop(timestamp) {
                this.loading = false;
                this.aiPage = true;
                document.getElementById('coaching-data').style.visibility= 'visible'
                
                if (this.startTime){
                    console.log("starttime",timestamp)
                }
                this.startTime = false;

                webcam.update(); // update the webcam frame
                //this.predict 로 this 붙여줘야!!!!!!!!!!!
                await this.predict();

                // stop&restart제어하기
                if (this.requestId) {
                    this.requestId = window.requestAnimationFrame(this.loop);
                }

                var nowDateTime = new Date();
                this.watch = Math.floor((nowDateTime-this.startDateTime)/(1000))
                this.watchMin = this.timerCustom(Math.floor(this.watch/60))
                this.watchSec = this.timerCustom(this.watch-this.watchMin*60)
            },
    
            async predict() {
                // Prediction #1: run input through posenet
                // estimatePose can take in an image, video or canvas html element
                const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
                // Prediction 2: run input through teachable machine classification model
                const prediction = await model.predict(posenetOutput);

                // Rule : class1은 항상 요가포즈, class2는 에러, class3은 
                for (let i = 0; i < maxPredictions; i++) {
                    document.getElementById('label-container').firstChild.style.display = 'none'
                    //90퍼이상 일치하는 동작의 클래스명을 보여준다.
                    if (prediction[i].probability.toFixed(2) >= 0.9){
                        labelContainer.childNodes[0].innerHTML = prediction[i].className;
                        //만약 제대로된 요가동작이 인식되면 밑에 카운트 시작 메세지가 같이 뜬다
                        if (prediction[i].className === String(this.course[this.cur]) && !this.flag ) {
                            this.flag = true;
                            document.getElementById('good').style.display = 'inline'
                            document.getElementById('bad').style.display = 'none'
                            console.log(this.flag,this.seconds)
                            this.seconds = 30;
                            this.counter = setInterval(this.incrementSeconds,1000)  
                        
                        } else if (prediction[i].className === String(this.course[this.cur])) {
                            document.getElementById('good').style.display = 'inline'
                            document.getElementById('bad').style.display = 'none'
                            this.restart()
                        } else {
                            document.getElementById('good').style.display = 'none'
                            document.getElementById('bad').style.display = 'inline'
                            this.stop()
                        }
                     }                    
                 } 

                this.drawPose(pose);
            },
        
            drawPose(pose) {
                if (webcam.canvas) {
                    ctx.drawImage(webcam.canvas, 0, 0);
                    // draw the keypoints and skeleton
                    // if (pose) {
                    //     const minPartConfidence = 0.5;
                    //     tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
                    //     tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
                    // }
                }
            }
        }
 
    }


  

</script>
    
<style scoped>
.watch {
    font-size: 2rem;
}
.pie-chart {
  position: relative;
  display:inline-block;
  width: 160px;
  height: 160px;
  border-radius: 50%;
  background: red;
}
span.center{
  background: #fff;
  display : block;
  position: absolute;
  top:50%; left:50%;
  width:150px; height:150px;
  border-radius: 50%;
  text-align:center; line-height: 150px;
  font-size:30px;
   transform: translate(-50%, -50%);
}
.coaching-data {
    visibility: hidden;
}

</style>