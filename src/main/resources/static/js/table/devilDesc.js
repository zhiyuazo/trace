//-------------------------------------------
new Vue({ //for devilId
	el:"#devilId",
	data:{fixedVal:""},
	mounted:function(){
		self=this;
		var v = 1+ Math.floor(Math.round(Math.random()*10+1));
	 	this.fixedVal = v;
	}
});
//-------------------------------------------
new Vue({ //for nation
	el:"#devilNation",
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
			$("#new_devil_data").val(all).trigger("input");
		}
	},
});
//-------------------------------------------
//-------------------------------------------
new Vue({ //for Cate
	el:"#devilCate",
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
			console.log("run into cate:groupAsdata");
			var all = JSON.stringify($("form").serializeObject());
			$("#new_devil_data").val(all).trigger("input");
		}
	}
});
//-------------------------------------------
new Vue({ //for kind
	el:"#devilKind",
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
			$("#new_devil_data").val(all).trigger("input");
		}
	}
});
//-------------------------------------------