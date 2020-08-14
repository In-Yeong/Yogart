<template>
    <div>
        <h4>요가 수업을 진행하기 위해서는 요가지도자 자격을 인증하셔야 합니다.</h4>
        <h4>이를 위해 아래에 해당하는 이미지를 첨부해주세요.</h4>
        <p>1. 신분증 사본</p>
        <p>2. 요가지도자 자격증 사본(종류 무관)</p>
        <input type="file" ref="tI" id="t-i" accept=".jpg, .jpeg, .gif, .png" multiple="multiple">
        <div>모든 파일은 인증 후 파기됩니다.</div>
        <button @click="onSubmit"></button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'TeacherApply',
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL
        }
    },
    methods: {
        onSubmit() {
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token'),
                    'Content-Type' : 'multipart/form-data',
                }
            }
            // 확인 필요
            let fd = new FormData();
            // let file = [];
            for (let i = 0; i < this.$refs.tI.files.length; i++) {
                // file.push(this.$refs.tI.files[i]);
                let file = this.$refs.tI.files[i];
                console.log(file);
                fd.append('files', file)
         
            }
            // fd.append("files",file);
            // console.log(file);
            console.log(fd);
            axios.post(this.SERVER_URL + '/api/users/imageUpload', fd, requestHeaders)
            .then(res => console.log(res)) //다른 곳으로 이동 필요
            .catch(err => console.error(err))
        },
    }

}
</script>

<style>

</style>