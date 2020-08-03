<template>
    <div class="noticeList">
        <div v-for="item in items" :key="item.qnaId">
            <QnaListItem :item="item"/>
        </div>
    </div>
</template>

<script>
import QnaListItem from './QnaListItem.vue'

export default {
    name: 'QnaList',
    components: {
        QnaListItem,
    },
    props: {
        items: Array,
    }
}
</script>

<style>
.noticeList{
    height : 600px;
}
</style>
