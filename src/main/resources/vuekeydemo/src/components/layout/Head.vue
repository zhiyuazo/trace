<template>
    <div id="root">
        <div>Head Part 基础-bind传值，slot,self-event:vuex:{{$store.state.msg}}</div>
        <IEButton v-bind:status="'OK'">ClickHead</IEButton>
        <IEButton v-bind:status="'OK'">{{$store.state.msg}}</IEButton>
    </div>
</template>

<script>
//该组件演示了插槽slot的简单使用
//组件自定义事件
//父组件向子组件vue-bind方式传值
import IEButton from '../basic/IEButton.vue'
export default {
    props:{
    },
    components : {
        IEButton
    }
}
</script>

<style scoped>
    #root {
        background-color : red ; 
        color : black  ;
        text-align : center;
        height : 400px ; 
        width : 100%;
    }
</style>