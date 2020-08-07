
<template>
    <!-- 질문 상세 하단에 댓글 방식으로 달리는 답변과, 답변을 다는 기능을 가지는 component // 답변 작성은 관리자 혹은 질문글 작성자만 -->
    <div>
        <div v-for="answer in answers" :key="answer.id">
            <p>{{ answer.text }}</p>
            <p>{{ answer.createdAt }}</p>
        </div>
        <form v-if="isAdmin" @submit="onSubmit">
            <input type="text" v-model="answerData">
            <button type="submit">작성</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'QnaAnswer',
    props: {
        ItemId: String,
    },
    data() {
        return {
            answers: [],
            isAdmin: true,
            SERVER_URL: this.$store.state.SERVER_URL,
            answerData: null,
        }
    },
    mounted() {
        // 이 컴포넌트가 실행될 때, 실행자가 질문을 작성한 본인 혹은 관리자라면 isAdmin을 true로 보내줍니다.
        axios.get(this.SERVER_URL + '/api/qna/answers', this.ItemId)
        .than(res => {
            this.answers = res.data.answers
            this.isAdmin = res.data.isAdmin
        })
        .catch(err => console.error(err))
    },
    methods: {
        onSubmit(e) {
            e.preventDefault()
            // console.log(e)
            if (this.answerData !== null && this.isAdmin === true) [
                axios.post(this.SERVER_URL + '/api/qna/answer', this.answerData)
                .then(res => {
                    this.answers = res.data.answers
                })
                .catch(err => console.error(err))
            ]
        }
    },
}
</script>

<style scoped>
div {
    z-index: 50 !important;
}
</style>