<template>
    <div>
        <button>I'm Router-User:{{$route.params.id}}</button>
        <button v-on:click="goBack">返回主页</button>
        <br/>
        <button v-on:click="printVuexStore">打印vuex-store</button>

    </div>
</template>

<script>
export default {
    name:"ROUTER1",
    data:function(){
        return {
            count : 0 
        }
    },
    methods:{
        goBack: function(){
            window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/');
        },
        printVuexStore : function(){
            console.log(this.$store.state.msg)
        }
    },
    props :{
        status:String
    },
    mounted:function(){
        console.log(this.$store.state.msg)
    },

    watch:{
        '$route' : function(to , from){
            // console.log(JSON.stringify(to)   + ":" + JSON.stringify(from));
            console.log(this.$route) 
            console.log(JSON.stringify(to+from)) 
        },
        '$store':function(){
            console.log("store changed!...")
        }
    }//,
    // beforeRouteUpdate (to, from, next) {
    //     //等同上面watch功能..
    //     // console.log(to)
    //     // console.log(from)
    //     // console.log(next)
    // }
}
</script>

<style scoped>
    button{
        background-color : #BBFFFF ;
    }

</style>