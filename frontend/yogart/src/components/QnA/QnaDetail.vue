<template>
    <!-- 질문 리스트에서 클릭 시 하단에 질문의 detail이 표시되는 component  -->
    <div>
<<<<<<< HEAD
        {{ item.qnaContent }}
=======
        <p>{{ item.qnaContent }}</p>
        <!-- QnaAnswer를 추가해 답변과 답변을 다는 공간을 보여줍니다. -->
>>>>>>> 694498c284b71afd7981e1fb85759dba6ccb8168
        <QnaAnswer :itemId="item.qnaId"/>
    </div>
</template>

<script>
import QnaAnswer from './QnaAnswer'

export default {
    name: 'QnaDetail',
    props: {
        item: Object
    },
    components: {
        QnaAnswer
    },

}
</script>

<style scoped>

</style>