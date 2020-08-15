<template>
    <div class="NoticeDetailView">
        <h1 class="p-5">공지사항 상세 페이지</h1>
        <div class="container">
            <div class="row m-0 border-top border-bottom">
                <div class="col-2 bg-light">제목</div>
                <div class="col-5">{{ notice.noticeTitle }}</div>
                <div class="col-2 bg-light">날짜</div>
                <div class="col-3">{{ notice.createDate }}</div>
            </div>
            <div class="text-left border-bottom min-height-5 mb-3">
                <viewer v-if="notice.noticeContent !== null" :initialValue="notice.noticeContent"/>
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
import '@toast-ui/editor/dist/toastui-editor-viewer.css';

import { Viewer } from '@toast-ui/vue-editor';

const API_URL = 'http://127.0.0.1:8000'

export default {
    name: 'NoticeDetailView',
    components: {
        viewer: Viewer
    },
    data() {
      return {
         notice: {
            noticeTitle: null,
            noticeContent: null,
            noticeId: null,
            createDate: null,
        },
      }
    },
    methods: {
        getNotice() {
            var noticeId = document.location.href.split("notice/")[1]

            axios.get(`${API_URL}/api/notice/${noticeId}/`)
            .then(response => {
            this.notice.noticeTitle = response.data.noticeTitle
            this.notice.noticeContent = response.data.noticeContent
            this.notice.noticeId = response.data.noticeId
            this.notice.createDate = response.data.createDate
            this.notice.createDate = this.notice.createDate.substr(0,10)
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