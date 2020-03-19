<template>
  <p>{{greeting}}  Vue-Cli</p>
</template>
 
<script>
 
export default {
    data () {
        return {
            greeting: 'Hi'
        }
    }
}
</script>
 
<style scoped>
    p{
        font-size: 2em;
        text-align:center;
        color:blue;
    }
</style>