<template>
    <tr>
        <th scope="row">{{ time }}</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</template>

<script>
export default {
    name: 'TimeTable',
    props: {
        time: Number,
    },
    mounted() {
        console.log(this.time)
    }
}
</script>

<style scoped>
td {
    border-style: solid;
}
</style>