<template>
    <div>
        <h1>스푼 구매</h1>
        <b-form-group label="구매할 스푼의 수를 선택하세요.">
            <b-form-radio v-model="selected" name="some-radios" value="0">10 스푼</b-form-radio>
            <b-form-radio v-model="selected" name="some-radios" value="1">50 스푼</b-form-radio>
            <b-form-radio v-model="selected" name="some-radios" value="2">110 스푼</b-form-radio>
            <b-form-radio v-model="selected" name="some-radios" value="3">220 스푼</b-form-radio>
        </b-form-group>
        <div class="mt-3">결제금액 : <strong>{{ price[selected] }}원</strong></div>
        <button @click="onClick">구매하기</button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'SpoonPurchase',
    data() {
        return {
            selected: null,
            price: [1000, 5000, 10000, 20000],
            spoons: [10, 50, 110, 220],
            SERVER_URL: this.$store.state.SERVER_URL,
        }
    },
    methods: {
        onClick(e) {
            const requestHeaders = {
                headers: {
                    Authorization: this.$cookies.get('auth-token')
                }
            }
            const purchaseData = {
                quantity: String(this.spoons[this.selected]),
                price: String(this.price[this.selected]),
            }
            axios.post(this.SERVER_URL + '/api/users/pay', purchaseData, requestHeaders)
            .then(res => {
                // 카카오페이 결제창 오픈
                this.$cookies.set('kakao-tid', res.data.tid)
                window.open(res.next_redirect_pc_url)
            })
            .catch(err => console.error(err))
        }
    }

}
</script>

<style>

</style>