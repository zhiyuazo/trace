$(function(){
	var yanpanWork = new Vue({
		el:"#testWork",
		data:{
			dpool:[],
			tmp:{
				"name":"zhiyuan",
				"alias":"zzy",
				"sex":"male",
				"age":30
			},
			tmp1:{
				"name":"zhangping",
				"alias":"zp",
				"sex":"female",
				"age":27
			}
		},
		watch:{
			//method-1:可以监听push..etc 数据生效，渲染成功
			//		        可以监听[i]=xx 数据生效，但不能渲染....
//			dpool: {
//				handler:function(v1 , v2){
//					this.$nextTick(function(){//下一次渲染完成调用
//						console.log("arr changed..")
//					});
//				},
//				deep:true
//			},
			//method-2:可以监听push..etc 数据生效，渲染成功 
			//		        可以监听[i]=xx.. 数据生效，不能渲染...
			dpool:function(v1){
				console.log("arr changed..");
			},
			//method-1:可以监听Vue.set 新增的key值
			//		        不可以监听json.key = xxx.已有的key值
//			tmp1 : function (v1){
//				console.log("json changed..");
//				console.log(v1)
//			},
			//method-2:可以监听Vue.set 新增的key值
			//		        可以监听json.key = xxx.已有的key值
			tmp1:{
				handler:function(v1 , v2){
					this.$nextTick(function(){//下一次渲染完成调用
						console.log("json changed..")
						console.log(this.tmp1)
					});
				},
				deep:true
			}
		},
		methods:{
			addArr: function(){
				this.dpool.push(this.tmp)
			},
			subArr: function(){
				this.dpool[0] = this.tmp1 ;
				console.log(this.dpool)
			},
			addJson: function(){
				Vue.set(this.tmp1,"faver","coding")
			},
			changeJson: function(){
				Vue.set(this.tmp1,"name","iecas")
			},
			inject : function(){
				for(var i=0 ; i <3 ; i++){
					this.dpool.push(this.tmp);
				}
			}
			
		},
		mounted:function(){
		},
		created:function(){
			this.inject();
		}
	});
})
