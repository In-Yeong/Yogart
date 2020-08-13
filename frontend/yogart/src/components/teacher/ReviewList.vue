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
            reviews: [],
        }
    },
    components: {
        ReviewItem,
    },
    mounted() {
        axios.get(this.SERVER_URL + 'api/review/list')
        .then(res => {
            this.reviews = res.data.reviews
        })
        .catch(err => console.error(err))
    }
}
</script>

<style>

</style>