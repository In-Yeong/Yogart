<template>
    <div>
        <h1>질문 작성</h1>
        <form @submit="onSubmit">
            <input type="text" v-model="questionTitle">
            <editor
                :initialValue="editorText"
                ref="toastuiEditor"
                height="500px"
                initialEditType="wysiwyg"
                previewStyle="vertical"
            />
            <button type="submit">작성</button>
            <p v-if="isEmpty">아무것도 묻지 않음으로 질문 할 수는 없어요.</p>
        </form>
    </div>
</template>

<script>
import axios from 'axios'
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';

import { Editor } from '@toast-ui/vue-editor';

export default {
    name: 'QnaCreate',
    components: {
        editor: Editor,
    },
    methods: {
        onSubmit(e) {
            e.preventDefault()
            this.getHtml()
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token')
                }
            }
            const questionData = {
                qnaTitle: this.questionTitle,
                qnaContent: this.questionContent,
            }
            if (this.questionContent !== null && this.questionTitle !== null) {
                this.isEmpty = false
            }
            // 데이터 형식 및 URL 체크
            if (!this.isEmpty) {
                axios.post(this.SERVER_URL + '/api/qna/make', questionData, requestHeaders)
                .then(res => {
                    history.back()
                })
                .catch(err => console.error(err))
            } else {
                return
            }
        },
        getHtml() {
            let html = this.$refs.toastuiEditor.invoke('getHtml')
            this.questionContent = html
        },
    },
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
            questionTitle: null,
            editorText: null,
            questionContent: null,
            isEmpty: true,
        }
    },
}
</script>

<style>
.ck-content {
    height: 50vh;
}
</style>