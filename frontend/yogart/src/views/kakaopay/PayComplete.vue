<template>
    <div>
        결제 완료
    </div>
</template>

<script>
import axios from 'axios'

function getUrlParams() {
    var params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
    return params;
}

export default {
    name: 'PayComplete',
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    mounted() {
        const kakaoData = getUrlParams()
        const tid = this.$cookies.get('kakao-tid')
        this.$cookies.remove('kakao-tid')
        const pgToken = kakaoData.pg_token
        const requestHeaders = {
            headers: {
                Authorization: this.$cookies.get('auth-token')
            }
        }
        const payData = {
            tid: tid,
            pgToken: pgToken
        }
        console.log(payData)
        axios.post(this.SERVER_URL + '/api/users/kakaopay', payData, requestHeaders)
        .then(res => {
            console.log(res)
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>