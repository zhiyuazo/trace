//-------------------------------------------
new Vue({ //for heroId
	el:"#heroId",
	data:{fixedVal:""},
	mounted:function(){
		self=this;
		var v = 142857+ Math.floor(Math.round(Math.random()*100+1));
	 	this.fixedVal = v;
	}
});
//-------------------------------------------
new Vue({ //for nation
	el:"#heroNation",
	data:{
		nations:{} 
	},
	created:function(){
		this.getNations();
	},
	methods :{
		getNations:function(){
			self = this;
			$.ajax({
	            url: "../getNationDict", 
	            type:'get',
	            async:false,
	            data:{},
	            success: function(result,status){
	            	self.nations = result;
	            }
	        });	
		},
		groupAsdata: function(){
			console.log("run into nation:groupAsdata");
			var all = JSON.stringify($("form").serializeObject());
			$("#new_hero_data").val(all).trigger("input");
		}
	},
	mounted:function(){
		//This is not worked to populate the camps variable. 
		//to get Full Variable..
	}
});
//-------------------------------------------
//-------------------------------------------
new Vue({ //for pos
	el:"#heroPos",
	data:{
		poss:{} 
	},
	created:function(){
		this.getPoss();
	},
	methods :{
		getPoss:function(){
			self = this;
			$.ajax({
	            url: "../getPosDict", 
	            type:'get',
	            async:false,
	            data:{},
	            success: function(result,status){
	            	self.poss = result;
	            }
	        });	
		},
		groupAsdata: function(){
			console.log("run into pos:groupAsdata");
			var all = JSON.stringify($("form").serializeObject());
			$("#new_hero_data").val(all).trigger("input");
		}
	}
});
//-------------------------------------------

new Vue({ //for Cate
	el:"#heroCate",
	data:{cates:{}},
	created:function(){
		this.getCates();
	},
	methods :{
		getCates:function(){
			self = this;
			$.ajax({
	            url: "../getCateDict", 
	            type:'get',
	            async:false,
	            data:{},
	            success: function(result,status){
	            	self.cates = result;
	            }
	        });	
		},
		groupAsdata: function(){
			console.log("run into kind:groupAsdata");
			var all = JSON.stringify($("form").serializeObject());
			$("#new_hero_data").val(all).trigger("input");
		}
	}
});
//-------------------------------------------
new Vue({ //for kind
	el:"#heroKind",
	data:{kinds:{}},
	created:function(){
		this.getKinds();
	},
	methods :{
		getKinds:function(){
			self = this;
			$.ajax({
	            url: "../getKindDict", 
	            type:'get',
	            async:false,
	            data:{},
	            success: function(result,status){
	            	self.kinds = result;
	            }
	        });	
		},
		groupAsdata: function(){
			console.log("run into kind:groupAsdata");
			var all = JSON.stringify($("form").serializeObject());
			$("#new_hero_data").val(all).trigger("input");
		}
	}
});

//-------------------------------------------
new Vue({ //for Prop
	el:"#heroProp",
	data:{props:{}},
	created:function(){
		this.getProps();
	},
	methods :{
		getProps:function(){
			self = this;
			$.ajax({
	            url: "../getPropDict", 
	            type:'get',
	            async:false,
	            data:{},
	            success: function(result,status){
	            	self.props = result;
	            }
	        });	
		},
		groupAsdata: function(){
			console.log("run into prop:groupAsdata");
			var all = JSON.stringify($("form").serializeObject());
			$("#new_hero_data").val(all).trigger("input");
		}
	}
});
//-------------------------------------------
