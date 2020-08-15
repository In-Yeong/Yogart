<template>
    <div>
        <router-link class="btn" to="/qna/question">글 작성</router-link>
        <QnaList :items="items"></QnaList>
        <paginate v-model="page" 
        :page-count="totalPages" 
        :page-range="3" 
        :margin-pages="2" 
        :click-handler="getQna" 
        :prev-text="'Prev'" 
        :next-text="'Next'" 
        :container-class="'pagination'" 
        :page-class="'page-item'"> </paginate>
    </div> 

</template>

<script>
import QnaList from '@/components/qna/QnaList.vue'
import axios from 'axios'
import Paginate from 'vuejs-paginate'
import Vue from 'vue'
Vue.component('paginate', Paginate)

export default {
    name: 'QnaView',
    components:{
        QnaList,
    },
    data() {
        return {
            items: null,
            page: 1,
            totalPages: 10,
            SERVER_URL: this.$store.state.SERVER_URL
        }
    },
    methods: {
        getQna(pageNum) {
            axios.get(this.SERVER_URL + `/api/qna/list/${pageNum}`)
            .then(res => {
                console.log(res.data.content)
                this.items = res.data.content
                this.totalPages = res.data.totalPages
                this.items.forEach(element => element.createDate = element.createDate.substr(0,10))
            })
            .catch(err => console.error(err))
        }

    },
    mounted() {
        this.getQna(1)
    }
}
</script>

<style>

</style>