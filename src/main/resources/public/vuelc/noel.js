$(function(){
	  var test = new Vue({
//		    el: '#test', //注释掉后只到created...
		    data: {
		      message: 'No-EL--Vue的生命周期',
		      company : "iecas"
		    },
		    methods : {
		    	change: function(){
		    		this.message = "no-el--Vue的生命周期"
		    	}
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
		    },
		    beforeUpdate: function () {
		      console.group('beforeUpdate 更新前状态===============》');
		      console.log("%c%s", "color:red","el     : " + this.$el);
		      console.log(this.$el);   
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); 
		      console.log("%c%s", "color:red","message: " + this.message); 
		    },
		    updated: function () {
		      console.group('updated 更新完成状态===============》');
		      console.log("%c%s", "color:red","el     : " + this.$el);
		      console.log(this.$el); 
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); 
		      console.log("%c%s", "color:red","message: " + this.message); 
		    },
		    beforeDestroy: function () {
		      console.group('beforeDestroy 销毁前状态===============》');
		      console.log("%c%s", "color:red","el     : " + this.$el);
		      console.log(this.$el);    
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); 
		      console.log("%c%s", "color:red","message: " + this.message); 
		    },
		    destroyed: function () {
		      console.group('destroyed 销毁完成状态===============》');
		      console.log("%c%s", "color:red","el     : " + this.$el);
		      console.log(this.$el);  
		      console.log("%c%s", "color:red","data   : " + JSON.stringify(this.$data)); 
		      console.log("%c%s", "color:red","message: " + this.message)
		    }
		  })
})