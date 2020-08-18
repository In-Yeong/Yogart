<template>
    <div>
        <div @click="getImage">
            <div>
                <span>{{ application.userEmail }}</span>
                <span>{{ application.userName }}</span>
                <span>{{ application.userNickname }}</span>
            </div>
            <img v-if="isClicked" :src="getImageUrl"/>
        </div>
        <button v-if="isClicked" @click="permit">강사승인</button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'ApplicationListItem',
    props: {
        application: Object,
    },
    data() {
        return {
            isClicked: false,
            SERVER_URL: this.$store.state.SERVER_URL,
            getImageUrl: this.$store.state.SERVER_URL + '/api/users/registrationImage?userEmail=' + this.application.userEmail
        }
    },
    methods: {
        getImage() {
            this.isClicked = !this.isClicked
        },
        permit() {
            this.$emit('permit', this.application.userEmail)
        }
    }
}
</script>

<style scoped>
img {
    max-height: 500px;
}
</style>