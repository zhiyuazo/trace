<template>
    <div>
        <div id="prompt" v-bind:style="{fontSize:textsize +'em'}">Main Part：父子传值:vuex:{{$store.state.msg}}</div>
        <div>
            <span v-for="post in posts" v-bind:key="post.postid">
                <IEPost  v-bind:postDesc="post" 
                         v-on:enlarge-textA="textsize += 0.1" 
                         v-on:enlarge-textB="textsize -= $event"
                         v-on:enlarge-textC="enlargeTextPlusC">
                </IEPost>
            </span>
        </div>
    </div>

</template>

<script>
//该子组件演示了向父组件传值的方法 emit 可能遇到的3种形式传参
//以及父组件向子组件传值的方法v-bind
import IEPost from '../basic/IEPost.vue'
export default {
    data:function(){
        return {
            posts:[
                {
                    postid:1,
                    title:"熟悉Vue-CLI的基本特性",
                    posttime:"2020-11-05 09:00:00",
                    postauthor:"zhiyuan",
                    content:"一个组件默认可以拥有任意数量的 prop，任何值都可以传递给任何 prop。在上述模板中，你会发现我们能够在组件实例中访问这个值，就像访问 data 中的值一样。"
                },
                {
                    postid:2,
                    title:"熟悉Vue-CLI的基本特性",
                    posttime:"2020-11-05 09:00:00",
                    postauthor:"zhiyuan",
                    content:"一个组件默认可以拥有任意数量的 prop，任何值都可以传递给任何 prop。在上述模板中，你会发现我们能够在组件实例中访问这个值，就像访问 data 中的值一样。"
                }
            ],
            textsize :1.0
        }
    },
    props:{
    },
    components:{
        IEPost
    },
    methods : {
        enlargeTextPlusC : function(p1){
            this.textsize += new Number(p1);//这里传着传着就变成字符串,用Number解决
        }
    }
}
</script>

<style scoped>
    #prompt {
        background-color : white ; 
        color : black  ;
        text-align : center;
        height : 100px  ; 
        width : 100%;
    }
</style>