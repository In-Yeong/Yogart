<template>
    <div class="GraphView">
        <TimeGraph/>
        <CalendarGraph/>
        <BodyGraph/>
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
            SERVER_URL: this.$store.state.SERVER_URL
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
                    // body1, body2... 부분에는 back에서 받는 태그이름 입력
                    bodydata = [res.data.wholeBody, res.data.spine, res.data.Abs, res.data.arm, res.data.leg, res.data.relaxing, res.data.energy]
                })
                .catch(err => {
                    console.error(err)
                })
        }
    },
}
</script>
