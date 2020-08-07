<template>   
    <div> 
        <div id="AI"  v-if="!loading">
            <h1>AI Coaching Service</h1>

            <h5>{{cur+1}}번째 동작 :{{course[cur]}}</h5>
            <button v-if="startBtn" class="w3-btn w3-round-xlarge w3-red w3-xlarge m-5" type="button" @click="init()">Get Start!</button>
        </div>
       
        <div id="loading" v-if="loading">
        <!-- <div id="loading" v-if="true"> -->
            <h3 class="m-5">AI 요가 코칭 서비스를 시작합니다</h3>
            <p>{{cur+1}}번째 동작 :{{course[cur]}}</p>
    
            <i class="fa fa-spinner fa-pulse fa-5x fa-fw m-5" ></i>
            <span class="sr-only">Loading...</span>

            <h3 class="m-5">웹캠을 켜주시고 잠시만 기다려 주세요</h3>
            <p> AI Yoga Coaching Service is running, Please turn on your webcam and wait</p>
        </div>
        <div class="row">
            <div v-if="aiPage" class="col-4">
                <div id="pose-data">
                    <p>{{time}}</p>
                    <div>포즈 이름: {{ posefiles[course[cur]].pose_name }}</div>
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
                <div v-if="flag"> 
                    <!-- <i class="fa fa-refresh fa-spin fa-3x fa-fw" aria-hidden="true"></i> -->
                    <span class="sr-only">Refreshing...</span>
                
                    
                </div>
            </div>
            <div class="col-4">
                <div id="pie-chart" class="pie-chart"><span class="center" id="seconds-counter">30</span></div>
            </div>
        </div>
        
        

    </div>
</template>

<script>
    import '@tensorflow/tfjs'
    import * as tmPose from "@teachablemachine/pose"
    import posefiles from "../json"
    // npm install --save @tensorflow/tfjs @teachablemachine/pose
    console.log(posefiles)
    let model, webcam, ctx, labelContainer, maxPredictions;
    // var el = document.getElementById('seconds-counter')
    
    export default {
        name : 'Yoga1',
        data() {
            return{
                startBtn : true,
                loading : false,
                aiPage : false,
                predictionTime : 3,
                startTime : true,
                endTime : true,
                requestId : undefined,
                course : [1,2,3,4,5],
                cur : 0,
                flag : false,
                seconds : 30,
                time : '',
                stopBtn : false,
                restartBtn : false,
                counter : undefined,
                posefiles : posefiles,

            }
        },
        created() {
            
            var d = new Date();
            var hour = d.getHours();
            var minutes = d.getMinutes();
            var sec = d.getSeconds();
            this.time = hour%12+"시 "+minutes%60+"분 "+sec%60+"초에 시작하셨습니다."
            
        },
        methods: {
            incrementSeconds() {
                this.seconds--;
                document.getElementById('seconds-counter').innerText = this.seconds
                document.getElementById('pie-chart').style.background = `conic-gradient(#ffffff 0% ${100-100*(this.seconds/30)}%, red ${100-100*(this.seconds/30)}% 100%)`
                console.log(document.getElementById('pie-chart').style)
                if (this.seconds===0) {
                    clearInterval(this.counter) 
                    this.next()
                } 
            },
            next() {
                clearInterval(this.counter) 
                this.cur++;
                this.flag = !this.flag
                document.getElementById('seconds-counter').innerText = 30
                document.getElementById('pie-chart').style.background = "red"

                console.log("current : ",this.cur)
                console.log("다음동작",this.course[this.cur],"을 실행합니다.")
                this.init(this.course[this.cur])
            },
            stop() {
                this.stopBtn = true;
                this.restartBtn = false;
                console.log("click stop btn",this.requestId)
                // if (this.requestId) {
                //     window.cancelAnimationFrame(this.requestId);
                //     this.requestId = undefined;
                //     console.log("click stop btn",this.requestId)
                // }   
                if (this.flag && this.stopBtn && this.counter!==undefined) {
                    clearInterval(this.counter)
                    this.counter = undefined
                    console.log("카운트 멈춤, ",this.seconds, this.counter)
                }
            },
            restart() {
                this.stopBtn = false;
                this.restartBtn = true;
                console.log("click restart btn",this.requestId)
                if (!this.requestId) {
                    this.requestId = window.requestAnimationFrame(this.loop);
                    console.log("click restart btn",this.requestId)
                }
                if(this.flag && this.restartBtn && this.counter===undefined) {
                    this.counter = setInterval(this.incrementSeconds,1000)
                } 
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

                // 예상 소요시간 > 디폴트소요시간이면 갱신
                console.log("this.prediction",this.predictionTime)
                if (this.predictionTime < maxPredictions) {
                    this.predictionTime = maxPredictions
                }
                console.log("this.prediction",this.predictionTime)
                console.log("maxPredictions",maxPredictions)
               
        
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
                document.getElementById('pie-chart').style.visibility= 'visible'
                
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
            },
    
            async predict() {
                // Prediction #1: run input through posenet
                // estimatePose can take in an image, video or canvas html element
                const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
                // Prediction 2: run input through teachable machine classification model
                const prediction = await model.predict(posenetOutput);

                // Rule : class1은 항상 요가포즈, class2는 에러, class3은 
                for (let i = 0; i < maxPredictions; i++) {
                    //90퍼이상 일치하는 동작의 클래스명을 보여준다.
                    if (prediction[i].probability.toFixed(2) >= 0.9){
                        labelContainer.childNodes[0].innerHTML = prediction[i].className;
                        //만약 제대로된 요가동작이 인식되면 밑에 카운트 시작 메세지가 같이 뜬다
                        if (prediction[i].className === String(this.course[this.cur]) && !this.flag ) {
                            this.flag = true;
                            console.log(this.flag,this.seconds)
                            this.seconds = 30;
                            this.counter = setInterval(this.incrementSeconds,1000)  
                        
                        } else if (prediction[i].className === String(this.course[this.cur])) {
                            this.restart()
                        } else {
                            labelContainer.childNodes[1].innerHTML = ""
                            this.stop()
                        }
                     }                    
                 } 

                // 원래코드
                // for (let i = 0; i < maxPredictions; i++) {
                //     const classPrediction =
                //         prediction[i].className + ": " + prediction[i].probability.toFixed(2);
                //     labelContainer.childNodes[i].innerHTML = classPrediction;
                // }
        
                // finally draw the poses
                //this 붙여줘야!!!!!!!!!!!
                this.drawPose(pose);
            },
        
            drawPose(pose) {
                if (webcam.canvas) {
                    ctx.drawImage(webcam.canvas, 0, 0);
                    // draw the keypoints and skeleton
                    if (pose) {
                        const minPartConfidence = 0.5;
                        tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
                        tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
                    }
                }
            }
        }
 
    }


  

</script>
    
<style scoped>
.pie-chart {
  position: relative;
  display:inline-block;
  width: 160px;
  height: 160px;
  border-radius: 50%;
  background: red;
  visibility: hidden;
  
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

</style>