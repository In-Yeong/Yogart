<template>
  <div class="d-flex flex-column" style="height:700px;">

        <form>
            <input id="input-1" type="text" placeholder="코스명을 적어주세요" required autofocus />
            <label for="input-1">
                <span class="label-text" >코스 생성하기</span>
                <span class="nav-dot"></span>
                <div class="fill-button-trigger">CLick here and fill the form2323</div>
            </label>
                <!-- <input id="input-2" type="email" placeholder="email@address.com" required />
            <label for="input-2">
                <span class="label-text">Email</span>
                <span class="nav-dot"></span>
            </label>
            <input id="input-3" type="text" placeholder="Company's name" required />
            <label for="input-3">
                <span class="label-text">Company</span>
                <span class="nav-dot"></span>
            </label>
            <input id="input-4" type="text" placeholder="Requirement" required />
            <label for="input-4">
                <span class="label-text">Requirement</span>
                <span class="nav-dot"></span>
            </label> -->
            <button class="submit-button" type="submit">생성하기</button>
            <p class="tip">Press Tab</p>
            <div class="fill-button">포즈는 선택하셨나요?(최대 7개)</div>
        </form>




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
        <h5>Button56</h5>
        <button class="btn6" href="#">Button</button>
      </div>
       <button>Hover</button>
  </div>
</template>

<script>
export default {
  name : 'test',
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


input, button {
	 position: fixed;
	 top: 50%;
	 left: 50%;
	 transform: translate(-50%, -300%);
	 display: block;
	 width: 500px;
	 opacity: 0;
	 pointer-events: none;
	 transition: all 0.5s cubic-bezier(0.4, 0.25, 0.8, 0.3);
}
 input {
	 padding: 0.25rem 0;
	 border: 0;
	 border-bottom: 1px solid #f29d8f;
	 outline: 0;
	 background: transparent;
	 color: black;
	 font-size: 1rem;
	 line-height: 2rem;
	 letter-spacing: 0.125rem;
	 transition: all 0.5s cubic-bezier(0.4, 0.25, 0.8, 0.3);
}
 input::selection {
	 background: rgba(249, 33, 74, 0.25);
}
 button, .fill-button {
	 padding: 0.25em 0;
	 border: 0;
	 outline: 0;
	 background: #f29d8f;
	 color: rgba(255, 255, 255, 0.85);
	 font-size: 2rem;
	 line-height: 3.6rem;
	 letter-spacing: 0.0625rem;
	 box-shadow: 0 3px 5px 1px rgba(0, 0, 0, 0.25);
	 text-shadow: 0 -2px 0 rgba(0, 0, 0, 0.25), 0 1px 0 rgba(255, 255, 255, 0.2);
}
 input:focus, button:focus {
	 opacity: 1;
	 transform: translate(-50%, -100%);
	 pointer-events: auto;
	 transition: all 0.4s cubic-bezier(0.1, 0.45, 0.1, 0.85) 0.5s;
	 z-index: 10;
}
 input:focus ~ label .label-text {
	 transform: translate(-50%, 300%);
	 transition: all 0.5s ease-in;
}
 input:focus ~ .tip {
	 opacity: 1;
}
 input:focus ~ .fill-button, button:focus ~ .fill-button {
	 opacity: 0;
}
 input:focus + label .label-text {
	 opacity: 1;
	 transform: translate(-50%, -100%);
	 transition: all 0.3s cubic-bezier(0.1, 0.45, 0.1, 0.85) 0.4s;
}
 input:focus + label .nav-dot:before {
	 background: #f29d8f;
	 box-shadow: 0 0 0 0.15rem #111, 0 0 0.05rem 0.26rem #f29d8f;
}
 .tip {
	 position: fixed;
	 top: 52%;
	 left: 38%;
	 transform: translate(-50%, -50%);
	 width: 70%;
	 opacity: 0;
	 color: black;
	 font-size: 0.825rem;
	 font-weight: 300;
	 letter-spacing: 0.125rem;
	 text-transform: uppercase;
	 text-align: right;
	 transition: opacity 0.25s 0.5s;
}
 .fill-button, .fill-button-trigger {
	 position: fixed;
	 top: 50%;
	 left: 50%;
	 transform: translate(-50%, -100%);
	 width: 500px;
	 padding: 0.1rem 0;
	 line-height: 3.6rem;
	 text-align: center;
	 pointer-events: none;
	 cursor: pointer;
	 transition: opacity 0.4s 0.3s;
}
 .fill-button-trigger {
	 opacity: 0;
	 pointer-events: auto;
}
 .label-text {
	 position: fixed;
	 top: calc(50% - 4rem);
	 left: 50%;
	 transform: translate(-50%, -300%);
	 width: 500px;
	 padding: 3.125rem 0 1.5rem;
	 text-transform: uppercase;
	 color: black;
	 opacity: 0;
	 font-size: 1rem;
	 font-weight: 500;
	 letter-spacing: 0.125rem;
	 pointer-events: none;
	 transition: all 0.4s cubic-bezier(0.4, 0.25, 0.8, 0.3) 0.05s;
 }
 * {
	 margin: 0;
	 padding: 0;
	 box-sizing: border-box;
}
 html, body {
	 width: 100%;
	 height: 100%;
	 background-image: linear-gradient(to bottom right, #111e25 0%, #111 100%);
	 font-family: 'Lato', sans-serif;
}
 form {
	 width: 100%;
	 height: 100%;
	 overflow: hidden;
}
</style>