<template>
    <div>
        <div v-for="review in reviews" :key="review.id">
            <ReviewItem :review="review"></ReviewItem>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import ReviewItem from './ReviewItem.vue'

export default {
    name: 'ReviewList',
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    components: {
        ReviewItem,
    },
    mounted() {
        axios.get()
    }
}
</script>

<style>

</style>