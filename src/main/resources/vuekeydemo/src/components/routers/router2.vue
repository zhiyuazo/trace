<template>
    <div>
        <button>I'm Router-2</button>
        <button v-on:click="goBack">返回主页</button>
        <p>vuex-store-method:{{$store.state.msg}}</p>
        <input type="text" v-model="msgval"/>
        <button v-on:click="updateVuexStore">更改vuex-store</button>
        <button v-on:click="printVuexStore">打印vuex-store</button>
    </div>
</template>

<script>
export default {
    name:"ROUTER2",
    data:function(){
        return {
            count : 0,
            msgval :''
        }
    },
    props :{
        status:String
    },
    methods:{
        goBack: function(){
            window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/');
        },
        updateVuexStore : function(){
            this.$store.commit("change",this.msgval);
        },
        printVuexStore : function(){
            console.log(this.$store.state.msg)
        }
    },
    mounted:function(){
        // console.log( this.$router);
         console.log( this.$store.state.msg);//打印vuex全局变量
    },
    created:function(){
        // console.log(this.$store)
        // console.log( this.$router);
    }
}
</script>

<style scoped>
    button{
        background-color :  green ;
    }

</style>