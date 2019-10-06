$(function(){
		var unknownList = new Vue({
			el:"#unknownList",
			data:{
		   		wzlist : [
						   {"alt":0,"bz":"","guanxi":"浓情蜜意","area":"四川","id":"98229","alias":"测1","mbbh":"3","cate":"牧师","name":"测0","pp":"亡灵","class":"艾泽拉斯","type":"尼米兹级"},
						   {"alt":0,"bz":"","guanxi":"卿卿我我","area":"重庆","id":"98231","alias":"测2","mbbh":"7","cate":"骑士","name":"测1","pp":"德伊","class":"艾泽拉斯","type":"小蝌蚪级"},
						   {"alt":0,"bz":"","guanxi":"若即若离","area":"广东","id":"98233","alias":"测3","mbbh":"9","cate":"恶魔","name":"测2","pp":"牛头","class":"艾泽拉斯","type":"大鹏级"},
						   {"alt":0,"bz":"","guanxi":"肝肠寸断","area":"大连","id":"98225","alias":"测4","mbbh":"2","cate":"猎人","name":"测3","pp":"精灵","class":"艾泽拉斯","type":"蜩级"}
//						   {"alt":0,"bz":"","guanxi":"明月相思","area":"邯郸","id":"98229","alias":"测5","mbbh":"3","cate":"盗贼","name":"测4","pp":"矮人","class":"艾泽拉斯","type":"的卢马级"},
//						   {"alt":0,"bz":"","guanxi":"怦然心动","area":"保定","id":"98231","alias":"测6","mbbh":"7","cate":"术士","name":"测5","pp":"侏儒","class":"艾泽拉斯","type":"雪鹰级"},
//						   {"alt":0,"bz":"","guanxi":"藕断丝连","area":"西安","id":"98233","alias":"测7","mbbh":"8","cate":"法师","name":"测6","pp":"巨魔","class":"艾泽拉斯","type":"大象级"},
//						   {"alt":0,"bz":"","guanxi":"大厦将倾","area":"山西","id":"98225","alias":"测8","mbbh":"2","cate":"战士","name":"测7","pp":"人类","class":"艾泽拉斯","type":"狗级"}
						]
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
		
	    //Capture YanPan TS trackPoint from unknownList 
		var yanpanWork = new Vue({
			el:"#yanpanWork",
			data:{
				wz_origin:{},
				wz_after:{}
			},
			watch:{
				wz_origin: {
					handler:function(v1 , v2){//侦听数组...
						yanpanWork.wz_after = JSON.parse(JSON.stringify(v1));
						this.$nextTick(function(){//下一次渲染完成调用
							console.log("渲染完成...")
						});
					},
					deep:true
				}
			},
			methods:{
				drop:function(e){
					e.preventDefault();
					console.log("in drop...");
					var d = e.dataTransfer.getData("text/plain");
					this.wz_after =  JSON.parse(d);
				},
				dragenter:function(e){
					e.preventDefault();
					console.log("in dragenter...");
				},
				dragleave:function(e){
					e.preventDefault();
					console.log("in dragleave...");
				},
				allowDrop:function(e){
					e.preventDefault();
					console.log("in dragover...");
				}
			}
		});
		
	})