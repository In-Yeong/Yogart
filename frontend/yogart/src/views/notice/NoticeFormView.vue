<template>
    <div class="NoticeFormView">
        <h1 class="p-5">공지사항 작성 페이지</h1>
        <div id="app">
                   <form @submit="onSubmit">
            <input type="text" v-model="noticeTitle">
            <editor
                :initialValue="editorText"
                ref="toastuiEditor"
                height="500px"
                initialEditType="wysiwyg"
                previewStyle="vertical"
            />
            <button type="submit">작성</button>
        </form>
        </div>
        <button @click="test()">test</button>

    </div>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';

import { Editor } from '@toast-ui/vue-editor';

export default {
    name: 'NoticeFormView',
    components: {
        editor: Editor,
    },
    data() {
        return {
            noticeTitle: null,
            noticeContent: '',
            SERVER_URL: this.$store.state.SERVER_URL,
        }
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
            const noticeData = {
                noticeTitle: this.noticeTitle,
                noticeContent: this.noticeContent,
            }
            if (this.noticeContent !== null && this.noticeTitle !== null) {
                this.isEmpty = false
            }
            // 데이터 형식 및 URL 체크
            if (!this.isEmpty) {
                axios.post(this.SERVER_URL + '/api/notice/create', noticeData, requestHeaders)
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
            this.noticeContent = html
        },
    },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>