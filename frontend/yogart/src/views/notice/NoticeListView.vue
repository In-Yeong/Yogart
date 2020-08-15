<template>
    <div class="NoticeListView">
        <h1 class="p-5">공지사항</h1>
        <button @click="callNoticeForm()">글 작성(관리자만 보이게 설정)</button>
        <table class="table m-auto">
            <thead class="thead-dark container p-0">
                <tr class="row m-0">
                    <th class="col-1">#</th>
                    <th class="col-8">제목</th>
                    <th class="col-3">작성일</th>
                </tr>
            </thead>
            <tbody class="container p-0">
                <tr class="row m-0" v-for="notice in notices" :key="notice.noticeId" @click="callNoticeDetail(notice.noticeId)">
                    <th class="col-1">{{ notice.noticeId }}</th>
                    <td class="col-8 title">{{ notice.noticeTitle }}</td>
                    <td class="col-3">{{ notice.createDate }}</td>
                </tr>
                <tr class="row m-0" @click="callNoticeDetail(2)">
                    <td class="col-1">2</td>
                    <td class="col-8 title">요가트 오픈 이벤트22222222222222!!!!!!!!!!</td>
                    <td class="col-3">2020.07.28</td>
                </tr>
                <tr class="row m-0" @click="callNoticeDetail(1)">
                    <td class="col-1">1</td>
                    <td class="col-8 title">요가트 오픈 이벤트!!!!!!!!!!</td>
                    <td class="col-3">2020.07.27</td>
                </tr>
            </tbody>
        </table>

        <paginate v-model="page" 
        :page-count="totalPages" 
        :page-range="3" 
        :margin-pages="2" 
        :click-handler="getNotices" 
        :prev-text="'Prev'" 
        :next-text="'Next'" 
        :container-class="'pagination'" 
        :page-class="'page-item'"> </paginate>


    </div>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'
import Paginate from 'vuejs-paginate'
import Vue from 'vue'
Vue.component('paginate', Paginate)

export default {
    name: 'NoticeListView',
    data() {
        return {
            notices: null,
            searchNotice: "", 
            page: 1,
            totalPages: 10,
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    methods: {
        getNotices(pageNum) {
            // console.log(this.page)
            axios.get(`${this.SERVER_URL}/api/notice/list/${pageNum}`)
            .then(response => {
                console.log(response)
                this.notices = response.data.content
                this.totalPages = response.data.totalPages
                this.notices.forEach(element => element.createDate = element.createDate.substr(0,10))
            })

        },
        callNoticeDetail(noticeId) {
            this.$router.push(`/notice/${noticeId}`)
        },
        callNoticeForm() {
            this.$router.push('/notice/form')
        },

    },
    mounted() {
        this.getNotices(1)
    }


}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
table {
    width: 80%;
}

tr:hover {
    background-color: #e8e6e5;
    cursor: pointer;
}
.NoticeListView{
    height : 600px;
}
th{
    background-color: #0C1C5A !important;
}

</style>
