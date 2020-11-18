<template>
    <div>
        <button>I'm Router-1</button>
        <button v-on:click="goBack">返回主页</button>
        <p>vuex-store-method:{{$store.state.msg}}</p>
        <IEButton v-bind:status="'OK'">{{$store.state.msg}}</IEButton>
        <br/>
        <input type="text" v-model="msgval"/>
        <button v-on:click="updateVuexStore">更改vuex-store</button>
        <button v-on:click="printVuexStore">打印vuex-store</button>
    </div>
</template>

<script>
import IEButton from '../basic/IEButton.vue'
export default {
    name:"ROUTER1",
    data:function(){
        return {
            count : 0 ,
            msgval :''
        }
    },
    components :{
        IEButton
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
    props :{
        status:String
    }
}
</script>

<style scoped>
    button{
        background-color :  yellow ;
    }

</style>