<template>
    <div>
        <span>{{ Pt.ptName }}</span>
        <span>{{ Pt.date }}</span>
        <span>{{ Pt.price }}</span>
        <span>{{ Pt.intro }}</span>
        <button>신청</button>
    </div>
</template>

<script>
export default {
    name: 'PtListItem',
    props: {
        Pt: Object,
    },
    
}
</script>

<style>

</style>