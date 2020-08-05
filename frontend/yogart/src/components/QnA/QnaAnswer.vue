
<template>
    <!-- 질문 상세 하단에 댓글 방식으로 달리는 답변과, 답변을 다는 기능을 가지는 component // 답변 작성은 관리자 혹은 질문글 작성자만 -->
    <div>
        <div v-for="answer in answers" :key="answer.qnaReplyId">
            <p>{{ answer.qnaReplyContent }}</p>
            <p>{{ answer.createDate }}</p>  
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
            answers: [],
            isAdmin: true,
            SERVER_URL: this.$store.state.SERVER_URL,
            answerData: null,
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
div {
    z-index: 50 !important;
}
</style>