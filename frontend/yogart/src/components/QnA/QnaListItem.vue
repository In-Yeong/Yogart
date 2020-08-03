<template>
    <div>
        <div @click="itemClick">
            <span class="m-3">{{ item.qnaTitle }}</span>
            <span class="m-3">작성자</span>
            <span class="m-3">{{ item.createDate }}</span>
        </div>
        <div>
          <QnaDetail v-if="isClicked" :item="item"/>
        </div>
    </div>
</template>

<script>
import QnaDetail from './QnaDetail.vue'

export default {
    name: 'QnaListItem',
    props: {
        item: Object,
    },
    components: {
        QnaDetail
    },
    data() {
        return {
            isClicked: false,
        }
    },
    methods: {
        itemClick() {
            this.isClicked = !this.isClicked
        }
    }
}
</script>

<style scoped>
div {
    z-index: -99 !important;
}
</style>