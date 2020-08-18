<template>
    
    <div class="m-5">
        <h3 style="font-weight:500px;">사용자 지정 코스</h3>
        <carousel-3d id="user-carousel" :controls-visible="true" :clickable="true"  :controls-prev-html="'&#10092;'" :controls-next-html="'&#10093;'">
            <slide class="slide" :index="0">
            <figure>
                <figcaption class="text-center" style="font-size:100px;" @click="newCourse()">
                     +
                </figcaption>
            </figure>
            </slide>
            <slide class="slide" v-for="(slide, i) in slides" :key="i" :index="i+1">
            <figure>
                <!-- <img src="https://placehold.it/360x270"> -->
                <figcaption class="text-center" style="font-size:25px;" @click="clickSet(slides_id[i])">
                    {{slide}}
                </figcaption>
            </figure>
            </slide>
         
        </carousel-3d>
    
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name : 'UserDesign',
        data() {
            return{
                slides : [],
                slides_id : [],
                SERVER_URL : this.$store.state.SERVER_URL,
            }
        },
        created() {
            this.getUserCourse()
        },
        watch: {
            slides() {
                document.getElementById('user-carousel').style = "width: 100%;"
            }
        },
        methods: {
            clickSet(num) {
                console.log(num)
                this.$emit("clickSet",num)
            },
            newCourse() {
                this.$router.push(`/coaching/yogapose/`)
            },
            getUserCourse() {
                let self = this
                axios.get(this.SERVER_URL + `/api/aicoach/list`, { 'headers': { 'auth-token': window.$cookies.get('auth-token') } })
                .then(res => {
                    console.log(res)
                    res.data.forEach(e => {
                        this.slides.push(e.aiCourseName)
                        this.slides_id.push(e.aiCourseId)
                        console.log('추가함', this.slides)
                        console.log("내 요가 리스트",e.aiCourseId)

                    });

                })
                .catch(err => console.error(err))
            }
        }
 
    }
</script>
    
<style scoped>
img{
    width :360px;
    height:270px;
}
.create-list {
    border-style: dashed;
    border-color: skyblue;
    height: 50%;
}
span{
    min-height : 80px;
}
</style>