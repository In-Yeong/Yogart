<template>
    <div class="NoticeDetailView">
        <h1 class="m-5">공지사항 상세 페이지</h1>
        <div class="container">
            <div class="row m-0 border-top border-bottom">
                <div class="col-2 bg-light">제목</div>
                <div class="col-5">{{ notice.noticeTitle }}</div>
                <div class="col-2 bg-light">날짜</div>
                <div class="col-3">{{ notice.createDate }}</div>
            </div>
            <div class="text-left border-bottom min-height-5 mb-3">
                <pre class="m-3">{{ notice.noticeContent }}</pre>
            </div>
            <!-- <div class="row m-0 border-top border-bottom">
                <div class="col-2 bg-light">제목</div>
                <div class="col-5">어쩌구저쩌구</div>
                <div class="col-2 bg-light">등록일</div>
                <div class="col-3">2020.07.28</div>
            </div>
            <div class="text-left border-bottom min-height-5 mb-3">
                <pre class="m-3">내용내용
                    내용
                </pre>
            </div> -->
            <div class="my-5">
                <button @click="toNoticeList()">목록으로</button>
            </div>
        </div>
     
    </div>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'
const API_URL = 'http://127.0.0.1:8000'

export default {
    name: 'NoticeDetailView',
    data() {
      return {
        notice: null
      }
    },
    methods: {
        getNotice() {
            var noticeId = document.location.href.split("notice/")[1]

            axios.get(`${API_URL}/api/notice/${noticeId}/`)
            .then(response => {
            console.log(response)
            this.notice = response.data
            this.notice.createDate = this.notice.createDate.substr(0,10)
            console.log(this.notice.createDate.substr(0,10))
            })
        },
        toNoticeList() {
            this.$router.push(`/notice`)
        }
    },
    created() {
        this.getNotice()
    },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.min-height-5 {
    min-height: 20rem;
}

</style>