<template>
    <div id="postFrame">
        <div style="height:20px;">{{postDesc.title}}</div>
        <div>
            <span>{{postDesc.posttime}}</span><span>{{postDesc.postauthor}}</span>
            <p>{{postDesc.content}}</p>
        </div>
        <button v-on:click="$emit('enlarge-textA')">放大0.1</button>
        <button v-on:click="$emit('enlarge-textB',0.2)">缩小0.2</button>
        <button v-on:click="$emit('enlarge-textC',userLargeVal)">Scale-0.x</button>
        <input type="number" step="0.1" max="1" min="-1" v-model="userLargeVal">
    </div>
</template>

<script>
//该子组件演示了向父组件传值的方法 emit 可能遇到的3种形式传参
//以及父组件向子组件传值的方法v-bind
export default {
    data:function(){
        return {
            userLargeVal : 0.1
        }
    },
    props :{
        postDesc:Object
    }
}
</script>

<style scoped>
    #postFrame {
        width:400px;
        height : 300px;
        padding : 0px;
        margin:0px;
        display : inline-block;
    }
    span{
        background-color :  yellow ;
        line-height:14px;
        padding :0px;
        margin : 0px;
    }
    p{
        font-size:8px;
        line-height:9px;
    }
</style>