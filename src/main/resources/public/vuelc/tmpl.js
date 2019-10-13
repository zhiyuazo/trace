$(function(){
	var vm = new Vue({
		el: '#app',
		template: "<h1>{{message +'这是在template中的'}}</h1>", //在vue配置项中修改的
	    render: function(createElement) {
	        return createElement('h1', 'this is createElement')
	    },
		data: {
		  message: 'Vue的生命周期'
		},
	    beforeCreate: function() {
		      //this.$el : undefined
		      //data :    undefined
		      //message : undefined
		      console.group('------beforeCreate创建前状态------');
		      console.log("%c%s", "color:red" , "el     : " + this.$el); //undefined
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); //undefined 
		      console.log("%c%s", "color:red","message: " + this.message) //undefined 
		    },
		    created: function() {
		      //this.$el : undefined
		      //data : 已经初始化 
		      //message : 已经初始化
		      console.group('------created创建完毕状态------');
		      console.log("%c%s", "color:red","el     : " + this.$el); //undefined
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); //已被初始化 
		      console.log("%c%s", "color:red","message: " + this.message); //已被初始化
		    },
		    beforeMount: function() {
		      //this.$el : 已经初始化 但是html中仍然用 {{xx.yy}}进行占位
		      //data : 已经初始化 
		      //message : 已经初始化
		      console.group('------beforeMount挂载前状态------');
		      console.log("%c%s", "color:red","el     : " + (this.$el)); 
		      console.log(this.$el);
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); //已被初始化  
		      console.log("%c%s", "color:red","message: " + this.message); //已被初始化  
		    },
		    mounted: function() {
		      //this.$el : 已经初始化 tml中{{xx.yy}}被替换为真实数据...
		      //data : 已经初始化 
		      //message : 已经初始化
		      console.group('------mounted 挂载结束状态------');
		      console.log("%c%s", "color:red","el     : " + this.$el); //已被初始化
		      console.log(this.$el);    
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); //已被初始化
		      console.log("%c%s", "color:red","message: " + this.message); //已被初始化 
		    }
	}) 	
})