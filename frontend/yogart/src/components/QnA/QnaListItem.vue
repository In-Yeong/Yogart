<template>
    <div class="container p-0">
        <div id="title" class="title row" @click="itemClick">
            <div class="col-1 p-0"><i class="fas fa-caret-right fa-2x"></i></div>
            <div class="col-7 p-0">{{ item.qnaTitle }}</div>
            <div class="col-2 p-0">admin</div>
            <div class="col-2 p-0">{{ item.createDate }}</div>
        </div>
        <div class="content">
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
        target: document.getElementById('title')
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
        itemClick(e) {
            this.isClicked = !this.isClicked
            var target = e.target
            console.log(target)
            if (target.id !='title') {
                target = target.parentElement
            }
            console.log(this.isClicked)
            if (this.isClicked) {
                target.style = "background-color: rgba(215, 159, 215, 1);"
                target.firstElementChild.firstElementChild.style = "color: rgba(255, 255, 255, 0.7);"
                target.firstElementChild.style ="transform: rotate( 90deg );"
            } else {
                target.style = "background-color: rgba(255, 255, 255, 0.7);"
                target.firstElementChild.firstElementChild.style = "color: rgba(215, 159, 215, 1);"
                target.firstElementChild.style ="transform: rotate( 0deg );"
            }
        }
    }
}
</script>

<style scoped>
.title {
    background-color: rgba(255, 255, 255, 0.7);
    /* border-bottom: 2px solid rgba(215, 159, 215, 1); */
    margin: 0rem;
    padding: 1rem 0rem;
    cursor: pointer;
    transition: all ease 0.5s;

}
.content {
    border-bottom: 2px solid rgba(215, 159, 215, 1);
    border-top: 2px solid rgba(215, 159, 215, 1);
    background-color: rgba(255, 255, 255, 0.7);
}
.fa-caret-right {
    color: rgba(215, 159, 215, 1);
}
</style>