<template>
    <div>
        <h2>요가 강사 자격증명</h2>
        <h4>Top and Bottom</h4>
        <b-card v-for="application in applications" :key="application.id" :img-src="application.img" img-alt="Card image" img-top>
            <b-card-text>
                {{ application.userName }}
            </b-card-text>
        </b-card>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'AdminPage',
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
            applications: [],
        }
    },
    mounted() {
        const requestHeaders = {
            headers: {
                Authorization: this.$cookies.get('auth-token')
            }
        }
        axios.get(this.SERVER_URL + '/api/admin', requestHeaders)
        .then(res => {
            this.applications = res.data.applications
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>