<template>
  <div class="d-flex flex-column" style="height:700px;">
      <div id="startBtn" class="m-5">
          <h5>Button1</h5>
          <div class="btn1">BUTTON
              <div class="btn1-sub"></div>
              </div>
      </div>
      <div>
        <h5>Button2</h5>
        <a class="btn2" href="#">START</a>
      </div>
      <div>
        <h5>Button3</h5>
        <button class="btn3" href="#">Button</button>
      </div>
      <div>
        <h5>Button4</h5>
        <button class="btn4" href="#">Button</button>
      </div>
      <div>
        <h5>Button5</h5>
        <button class="btn5" href="#">Button</button>
      </div>
      <div>
        <h5>Button56/h5>
        <button class="btn6" href="#">Button</button>
      </div>
    <button>Hover</button>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

.btn1, .btn1-sub {
    width :150px;
    height :60px;
    font-size :24px;
    text-align :center;
    line-height :60px;
    color :rgba(255,255,255,0.9);
    border-radius :50px;
    background :linear-gradient(-45deg, #FFA63D, #FF3D77, #338AFF, #3CF0C5);
    background-size :500%;
    animation : start1-animate 16s linear infinite;
}
.btn1:hover{
    color: black;
    cursor : pointer;
}
.btn1-sub:hover{
    color:black;
    cursor : pointer;
}
.btn1-sub{
    position: absolute;
     margin-top: -50px;
     z-index :-1;
     filter :blur(20px);
     opacity :0.8;
}
 @keyframes start1-animate {
   0% {background-position: 0% 50% ;}  
   50% {background-position: 100% 50%;}
   100% {background-position: 0% 50%;}
 }



.btn2{
    position: absolute;
    /* top: 50%;
    left: 50%; */
    transform: translate(-50%,-50%);
    width: 150px;
    height: 60px;
    text-align: center;
    line-height: 60px;
    color: #fff;
    font-size: 24px;
    text-transform: uppercase;
    text-decoration: none;
    font-family: sans-serif;
    box-sizing: border-box;
    background: linear-gradient(90deg,#5bcedf, #f96161, #fc8f46, #36e494);
    background-size: 400%;
    border-radius: 30px;
    z-index: 1;
}
.btn2:hover{
    animation: start2-animate 8s linear infinite;
}
.btn2:before{
    content: '';
    position: absolute;
    top: -5px;
    left: -5px;
    right: -5px;
    bottom: -5px;
    z-index: -1;
    background: linear-gradient(90deg,#5bcedf, #f96161, #fc8f46, #36e494);
    background-size: 400px;
    border-radius: 40px;
    opacity: 0;
    transition: 0.5s;
}
.btn2:hover:before{
    filter: blur(10px);
    opacity: 1;
}

@keyframes start2-animate {
    0%{
        background-position: 0%;
    }
    100%{
        background-position: 400%;
    }
}

.btn3 {
  transition-duration: 0.4s;
  background-color: #e8e6e5; /* Green */
  border: 3px solid #ab6fac;
  color: #ab6fac;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
  border-radius: 40px;
}
.btn3:hover {
  background-color: #ab6fac; /* Green */
  color: #e8e6e5;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 40px;
}

.btn4 {
  transition-duration: 0.4s;
  background-color: #f29d8f; 
  border: 3px solid #f29d8f;
  color: #e8e6e5;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 20px;
  font-size: 24px;
  margin: 4px 2px;
  transition:800ms ease all;
  outline:none;
}
.btn4:hover {
  background-color: #e8e6e5;
  color: #f29d8f;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
  cursor: pointer;
}
.btn4:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #f29d8f;
  transition:400ms ease all;
}
.btn4:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.btn4:hover:before,.btn4:hover:after{
  width:100%;
  transition:800ms ease all;
}



.btn5 {
  transition-duration: 0.4s;
  background-color: #0F4C81; 
  border: 3px solid #0F4C81;
  color:#F0C05A;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
}
.btn5:hover {
  /* background-color: #F0C05A; */
  background-color: white;
  color: #0F4C81;
  padding: 10px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  margin: 4px 2px;
  cursor: pointer;
}



.btn6{
  background:#0F4C81;
  color:#F0C05A;
  border:none;
  position:relative;
  width : 150px;
  height:60px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}
.btn6:hover{
  background:#F0C05A;
  color:#0F4C81;
}
.btn6:before,.btn6:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #0F4C81;
  transition:400ms ease all;
}
.btn6:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.btn6:hover:before,.btn6:hover:after{
  width:100%;
  transition:800ms ease all;
}
</style>