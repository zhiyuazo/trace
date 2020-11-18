<template>
    <div>
        <button v-on:click="count++"><slot></slot></button>
        <span>{{ count }} times.{{status}}</span>
    </div>
</template>

<script>
//该组件演示了插槽slot的简单使用
//组件自定义事件
//父组件向子组件vue-bind方式传值
export default {
    data:function(){
        return {
            count : 0 
        }
    },
    props :{
        status:String
    }
}
</script>

<style scoped>
    span{
        background-color :  yellow ;
    }

</style>