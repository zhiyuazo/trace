$(function(){
	    //Receive data from Geovis's ball 
		window.addEventListener('message',function(event){
			if(event.origin !== 'http://localhost:8888')
				return;
			var message = event.data ; 
			var origin = event.origin
			unknownList.wzlist.push(message); 
			console.log("push完成...")
		},false)
		
	    //Group all data from Geovis 
		var unknownList = new Vue({
			el:"#unknownList",
			data:{
				wzlist:[]
			},
			watch:{
				wzlist: {
					handler:function(v1 , v2){//侦听数组...
						for(var i in v1){
							console.log(v1);
						};
						this.$nextTick(function(){//下一次渲染完成调用
							console.log("渲染完成...")
						});
					},
					deep:true
				}
			},
			methods:{
				delUnknown:function(e, index){
					e.preventDefault();
					this.wzlist.splice(index,1)
				},
				send2wz_origin:function(e,wz){
					e.preventDefault();
					yanpanWork.wz_origin = wz
					
				},
				dragstart:function(e,wz){
					console.log("in dragstart " + wz.mbmc)
					e.dataTransfer.setData("text/plain",JSON.stringify(wz));
				},
				drag:function(e,wz){
					console.log("in drag " + wz.mbmc)
				},
				dragend:function(e,wz){
					console.log("in dragend " + wz.mbmc)
				}
			}
		});
	})