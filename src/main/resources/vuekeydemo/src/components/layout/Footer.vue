<template>
    <div>
        footer Part:双向绑定v-model定义:vuex:{{$store.state.msg}}
        <IEInput v-model="searchtext" />
    </div>
</template>

<script>
//自定义组件支持双向绑定v-model
// 将其 value attribute 绑定到一个名叫 value 的 prop 上
// 在其 input 事件被触发时，将新的值通过自定义的 input 事件抛出
import IEInput from '../basic/IEInput.vue'
export default {
    data:function(){
        return {
            searchtext : "just4test"
        }
    },
    watch:{
        searchtext:function(val){
            console.log("new search text is " + val)
        }
    },
    props:{
    },
    components : {
        IEInput
    }
}
</script>

<style scoped>
    div {
        background-color : blue ; 
        color : black  ;
        text-align : center;
        height : 100%  ; 
        width : 100%;
    }
</style>