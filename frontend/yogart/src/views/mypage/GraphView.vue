<template>
    <div class="GraphView">
        <TimeGraph/>
        <CalendarGraph/>
        <BodyGraph/>
    </div>
</template>

<script>
// @ is an alias to /src
import TimeGraph from '@/components/TimeGraph.vue'
import CalendarGraph from '@/components/CalendarGraph.vue'
import BodyGraph from '@/components/BodyGraph.vue'

export default {
    name: 'GraphView',
    components: {
        TimeGraph,
        CalendarGraph,
        BodyGraph
    }
}
</script>
