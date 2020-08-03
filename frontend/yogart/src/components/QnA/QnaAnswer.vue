
<template>
    <!-- 질문 상세 하단에 댓글 방식으로 달리는 답변과, 답변을 다는 기능을 가지는 component // 답변 작성은 관리자만 -->
    <div>
        <div v-for="answer in answers" :key="answer.id">
            <p>{{ answer.text }}</p>
            <p>{{ answer.createdAt }}</p>
        </div>
        <form v-if="isAdmin" @submit="onSubmit">
            <input type="text">
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
            SERVER_URL: this.$store.SERVER_URL,
        }
    },
    // mounted() {
    //     axios.get(this.SERVER_URL + 'qna/answers', this.ItemId)
    //     .than(res => {
    //         this.answers = res.data.answers
    //         this.isAdmin = res.data.isAdmin
    //     })
    //     .catch(err => console.error(err))
    // },
    methods: {
        onSubmit(e) {
            e.preventDefault()
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