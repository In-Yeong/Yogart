<template>
    <div class="NoticeFormView">
        <h1 class="m-5">공지사항 작성 페이지</h1>
        <div id="app">
            <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
        </div>
        <button @click="test()">test</button>

    </div>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'
// import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import ClassicEditor from '@ckeditor/ckeditor5-editor-classic/src/classiceditor';
const API_URL = 'http://127.0.0.1:8000'

export default {
    name: 'NoticeFormView',
    data() {
        return {
            editor: ClassicEditor,
            editorData: '',
            editorConfig: {
                // The configuration of the editor.
            }

        }
    },
    methods: {
        test() {
            console.log(this.editorData)
        }
    },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>