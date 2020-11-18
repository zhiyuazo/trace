<template>
    <div>
        Aside Part:动态组件
        <p style="line-height:12px;margin:0px;padding:0px;">vuex-store-method:{{$store.state.msg}}</p>
        <button style="line-height:12px;margin:0px;padding:0px;" v-on:click="updateVuexStore">更新vuex-store</button>
        <input style="line-height:12px;margin:0px;padding:0px;" type="text" v-model="storeval"/>
        <br/>
        <button v-for="tab in tabs"
                v-bind:key ="tab"
                v-on:click = "currentTab = tab"
                v-bind:class="['tab-button',{active:currentTab==tab}]">{{tab}}</button>
        <component v-bind:is="currentTabComponent" class="tab" > </component>

    </div>
</template>

<script>
//该组件演示了动态组件特性 <component v-bind:is="currentTabComponent"></component>实现
export default {
    props:{
    },
    data:function(){
        return {
            currentTab : "Home",
            tabs:["Home","Posts","Archive"],
            storeval:''
        }
    },
    methods:{
        updateVuexStore : function(){
            this.$store.commit("change",this.storeval);
        }
    },
    computed:{
        currentTabComponent : function(){
            var full_tab_name = "tab-" +  this.currentTab.toLowerCase();
            console.log(full_tab_name);
            return full_tab_name
        }
    },
    components : {
        'tab-home' :{
            template:"<div>1st--zzy say: this is home page!</div>"
        },
        'tab-posts' :{
            template:"<div>2nd--zzy say: this is post page!</div>"
        },
        'tab-archive':{
            template:"<div>3th--zzy say: this is archive page!</div>"
        }
    }
}
</script>

<style scoped>
    .tab-button {
        padding:6px 10px;
        border-top-left-radius :3px;
        border-top-right-radius :3px;
        border: 1px solid #ccc;
        cursor : pointer;
        background : #f0f0f0 ;
        margin-bottom:-1px;
        margin-right:-1px; 
    }
    .tab-button:hover{
        background : yellow ;
    }
    .tab-button:active{
        background : green ;
    }
    .tab {
        padding:10px;
        border: 1px solid #ccc;
        color : black;
        height : 50px;
        line-height :  18px;
    }
</style>