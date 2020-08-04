<template>
    <div>
        <h1>질문 작성</h1>
        <form @submit="onSubmit">
            <input type="text" v-model="questionTitle">
            <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
            <button type="submit">작성</button>
            <!-- <span v-if="isEmpty">아무것도 묻지 않음으로 질문 할 수는 없어요.</span> -->
        </form>
    </div>
</template>

<script>
import axios from 'axios'
import ClassicEditor from '@ckeditor/ckeditor5-editor-classic/src/classiceditor'
import '@ckeditor/ckeditor5-build-classic/build/translations/ko'
import Image from '@ckeditor/ckeditor5-image/src/image'
import EssentialsPlugin from '@ckeditor/ckeditor5-essentials/src/essentials';
import BoldPlugin from '@ckeditor/ckeditor5-basic-styles/src/bold';
import ItalicPlugin from '@ckeditor/ckeditor5-basic-styles/src/italic';
import LinkPlugin from '@ckeditor/ckeditor5-link/src/link';
import ParagraphPlugin from '@ckeditor/ckeditor5-paragraph/src/paragraph';
import ImageToolbar from '@ckeditor/ckeditor5-image/src/imagetoolbar';
import ImageCaption from '@ckeditor/ckeditor5-image/src/imagecaption';
import ImageStyle from '@ckeditor/ckeditor5-image/src/imagestyle';
import Autoformat from '@ckeditor/ckeditor5-autoformat/src/autoformat';
import Font from '@ckeditor/ckeditor5-font/src/font';
import Heading from '@ckeditor/ckeditor5-heading/src/heading';
import EasyImage from '@ckeditor/ckeditor5-easy-image/src/easyimage';
import CKFinder from '@ckeditor/ckeditor5-ckfinder/src/ckfinder';

export default {
    name: 'QnaCreate',
    methods: {
        onSubmit(e) {
            e.preventDefault()
            console.log(this.editorData)
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token')
                }
            }
            questionData = {
                editorData: this.editorData,
                questionTitle: this.questionTitle
            }
            // 데이터 형식 및 URL 체크  
            axios.post(this.SERVER_URL + '/api/qna/make', questionData, requestHeaders)
            .then(res => {
                history.back()
            })
            .catch(err => console.error(err))
        }
    },
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
            editor: ClassicEditor,
            questionTitle,
            editorData: null,
            editorConfig: {
                language: 'ko',
                plugins: [
                    Autoformat,
                    EssentialsPlugin,
                    BoldPlugin,
                    ItalicPlugin,
                    LinkPlugin,
                    ParagraphPlugin,
                    Image,
                    ImageToolbar,
                    ImageCaption,
                    ImageStyle,
                    Font,
                    Heading,
                    EasyImage,
                    CKFinder,
                ],
                toolbar: {
                    items: [
                        'heading',
                        'fontsize',
                        'bold',
                        'italic',
                        'link',
                        '|',
                        'undo',
                        'redo',
                        '|',
                        'imageUpload',
                    ] 
                },
                image: {
                    toolbar: [ 'imageTextAlternative', '|', 'imageStyle:full', 'imageStyle:side' ]
                },
                ckfinder: {
                //     openerMethod: 'popup',
                    // Upload the images to the server using the CKFinder QuickUpload command.
                    uploadUrl: 'https://example.com/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images&responseType=json',
                    // Define the CKFinder configuration (if necessary).
                    options: {
                        resourceType: 'Images'
                    }
                }
                // cloudServices: {
                //     tokenUrl: 'https://example.com/cs-token-endpoint',
                //     uploadUrl: 'https://your-organization-id.cke-cs.com/easyimage/upload/'
                // },
            },
        }
    }
}
</script>

<style>
.ck-content {
    height: 50vh;
}
</style>