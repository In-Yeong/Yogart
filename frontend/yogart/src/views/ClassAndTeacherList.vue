<template>
    <div class="m-5">
         <b-tabs content-class="mt-3">
            <b-tab title="수업 목록" active><ClassList /></b-tab>
            <b-tab title="강사 목록"><TeacherList /></b-tab>
        </b-tabs>
    </div>
</template>

<script>
import ClassList from '../components/ClassList.vue'
import TeacherList from './teacher/TeacherList.vue'

export default {
    name: 'ClassAndTeacherList',
    data() {
        return {
           
        }
    },
    components: {
        ClassList,
        TeacherList
    },
    
    methods: {
        
    },
}
</script>

<style scoped>

</style>