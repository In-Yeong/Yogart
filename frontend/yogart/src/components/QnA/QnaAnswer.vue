
<template>
    <!-- 질문 상세 하단에 댓글 방식으로 달리는 답변과, 답변을 다는 기능을 가지는 component // 답변 작성은 관리자 혹은 질문글 작성자만 -->
    <div class="answer-box container">
        <div class="row" v-for="answer in answers" :key="answer.qnaReplyId">
            <pre class="col-9 text-left custom-break-word">{{ answer.qnaReplyContent }}</pre>
            <pre class="col-3 text-right custom-break-word">{{ answer.createDate }}</pre>  
        </div>
        <form v-if="isAdmin" @submit="onSubmit">
            <input type="text" v-model="ReplyContent">
            <button type="submit">작성</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'QnaAnswer',
    props: {
        itemId: Number,
    },
    data() {
        return {
            ReplyContent: '',
            answers: [{
                "qnaReplyId": 1,
                "qnaReplyContent": '댓글~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~',
                "createDate": '2020-08-15'

            },
            {
                "qnaReplyId": 2,
                "qnaReplyContent": '댓글2dfsfsdgbrwwedefrwefewfefejdkdls;jcfksd;chsdjk;fhscjk;dszjkj hjklhjlkyi;yhi;hujil;hjlghkfghdjcgfj',
                "createDate": '2020-08-15'

            }],
            isAdmin: true,
            SERVER_URL: this.$store.state.SERVER_URL,
            answerData: this.ReplyContent,
        }
    },
    mounted() {
        axios.get(this.SERVER_URL + `/api/qna/reply/list/${this.itemId}`, this.itemId)
        .then(res => {
            console.log(res)
            this.answers = res.data.list
            this.isAdmin = res.data.admin
        })
        .catch(err => console.log(err))
    },
    methods: {
        onSubmit(e) {
            e.preventDefault()
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token')
                }
            }
            const replycontents = {
                ReplyContent: this.ReplyContent,
                qnaId: this.itemId
            }
            // 데이터 형식 및 URL 체크 
            console.log(replycontents) 
            if (this.answerData !== null && this.isAdmin === true) [
            axios.post(this.SERVER_URL + '/api/qna/reply/make', replycontents, requestHeaders)
            .then(res => {
                axios.get(this.SERVER_URL + `/api/qna/reply/list/${this.itemId}`, this.itemId)
                .then(res => {
                console.log(res)
                this.answers = res.data.list
                this.isAdmin = res.data.admin
                })
                .catch(err => console.log(err))
            })
            .catch(err => console.error(err))
            ]
            console.log(e)
        }
    },
}
</script>

<style scoped>
.answer-box {
    margin: 3rem 0rem 1rem;
    padding-top: 2rem;
    border-top: 1px solid rgba(215, 159, 215, 1);
}
</style>