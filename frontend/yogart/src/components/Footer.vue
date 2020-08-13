<template>
     <!-- Footer -->
    <footer class="page-footer font-small" style="color:rgba(242, 157, 143, 1);">
        <!-- Footer Elements -->
        <div class="container" v-if="!isLogin">
            <!-- Call to action -->
            <ul class="list-unstyled list-inline text-center">
            <li class="list-inline-item">
                <h5 class="mb-1">Register for free</h5>
            </li>
            <li class="list-inline-item">
                <a href="/accounts/signup" class="btn outline-white btn-rounded">Sign up!</a>
            </li>
            </ul>
        </div>
        <!-- Copyright -->
        <div class="text-center">© 2020 Copyright:
            <a href="https://mdbootstrap.com/" style="color: rgba(242, 157, 143, 1);"> yogart.com</a>
        </div>
    </footer>
</template>

<script>
export default {
    name: 'NavBar',
    props: {
        isLogin: Boolean
    },
}

</script>

<style>
.outline-white{
    border : 2px solid white;
}

</style>