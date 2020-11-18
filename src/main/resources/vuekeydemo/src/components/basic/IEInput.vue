<template>
    <div>
        <input v-bind:value="value" 
               v-on:input="$emit('input',$event.target.value)" 
               type="text"
        >
    </div>
</template>

<script>
//自定义组件支持双向绑定v-model
// 将其 value attribute 绑定到一个名叫 value 的 prop 上
// 在其 input 事件被触发时，将新的值通过自定义的 input 事件抛出
export default {
    name : "IEInput",
    props:{
        value:String
    }
}
</script>

<style scoped>
    div {
        background-color : red ; 
        color : green  ;
        text-align : left;
    }
</style>