<template>
    <div class="GraphView">
        <TimeGraph :time="time" />
        <CalendarGraph :calendar="calendar" />
        <BodyGraph :body="body" />
    </div>
</template>

<script>
// @ is an alias to /src
import TimeGraph from '@/components/graph/TimeGraph.vue'
import CalendarGraph from '@/components/graph/CalendarGraph.vue'
import BodyGraph from '@/components/graph/BodyGraph.vue'
import axios from 'axios'

export default {
    name: 'GraphView',
    components: {
        TimeGraph,
        CalendarGraph,
        BodyGraph
    },
    data() {
        return {
            SERVER_URL: this.$store.state.SERVER_URL,
            time: null,
            calendar: null,
            body: null,
        }
    },
    created() {
      this.getData()  
    },
    methods: {
        getData() {
             axios.get(this.SERVER_URL + `/api/mypage/graph`, { 'headers': { 'auth-token': window.$cookies.get('auth-token') } })
                .then(res => {
                    console.log(res)
                    this.time = res.data.timeCount
                    this.calendar = res.data.attendance
                    this.body = res.data.tags
                    console.log(this.body)
                })
                .catch(err => {
                    console.error(err)
                })
        }
    },
}
</script>

<style scoped>
    
</style>
