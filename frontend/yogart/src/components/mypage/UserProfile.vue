<template>
<div>
    <div class="gray d-flex  my-5">
        <img class="user-profile m-3" :src="require('@/assets/Hedgehog.jpg')">
        <div class="d-flex flex-column mt-3" >
            <div class="d-flex mb-5" >
                <h5>승희</h5>
                <div class="d-flex">
                    
                    <button class="w3-button w3-black w3-tiny update">회원정보 수정</button>
                    <button class="w3-button w3-black w3-tiny delete">회원탈퇴</button>
                </div>
            </div>
            
            <p>저는 요가를 좋아하는 요린이 입니다.

            </p>
        </div>
    </div>
    
</div>    
</template>

<script>
export default {
    name : 'UserProfile',
}
</script>

<style>
.update  {
    position : absolute;
    right : 13vw;
    margin-right:75px;
}
.delete{
    
    position : absolute;
    right : 13vw;
 

}
</style>