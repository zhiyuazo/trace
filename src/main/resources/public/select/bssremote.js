/**
 * 
 */
	$(function(){
		var srhPanel = new Vue({
			el:"#search_panel",
			data:{
				globalConstNations : ["美国","中国","日本"],
				globalConstNames : ["A","B","C"],
				key_nation : "根据国家筛选",
				key_name : "根据名称筛选"
			},
			created:function(){
				this.getNations();
				this.getNames();
			},
			methods:{
				getNations:function(e){
					self = this;
					$.ajax({
			            url: "http://localhost:8888/nationDict_token", 
			            type:'get',
			            async:false,
			            data:{},
			            success: function(result,status){
			            	self.globalConstNations = result;
			            }
			        });	
				},
				getNames:function(e){
					self = this;
					$.ajax({
			            url: "http://localhost:8888/nameDict_token", 
			            type:'get',
			            async:false,
			            data:{},
			            success: function(result,status){
			            	self.globalConstNames = result;
			            }
			        });	
				},
				srhWithNN:function(){
					console.log("run into srch withNN");
					var nation_in_url ;
					var name_in_url ;
					if(this.key_nation=="根据国家筛选"){
						nation_in_url = "";
					}else{
						nation_in_url = this.key_nation;
					}
					if(this.key_name=='根据名称筛选'){
						name_in_url = "";
					}else{
						name_in_url = this.key_name;
					}
					$.ajax({
			            url: "http://localhost:8888/nn_data?srhKey="+nation_in_url + "_" + name_in_url, 
			            type:'get',
			            async:false,
			            data:{},
			            success: function(result,status){
			            	tp.data_set = result;
			            }
			        });	
				}
			}
		});
		var npcTMPL=document.getElementById("npcTMPL").innerHTML;
		Vue.component("npcentity",{
			props:['profile'],
			template:npcTMPL, 
			methods:{
				selectedMark:function(e){
					$(this.$el).toggleClass("SELECTED_Mark")
					var class_str =  $(this.$el).attr("class");
					if(class_str.indexOf("Mark")==-1){
						var removeFlag = false;
						var removeIndex = 0;
						for(var i=0;i<tp.data_marked.length;i++){
							if(tp.data_marked[i].id == this.profile.id){
								removeFlag = true;
								removeIndex = i;
								break;
							}
						}
						if(removeFlag){
							tp.data_marked.splice(i,1);
						}
						
					}else{
						var insertFlag = true;
						for(var i=0;i<tp.data_marked.length;i++){
							if(tp.data_marked[i].id == this.profile.id){
								insertFlag = false;
								break;
							}
						}
						if(insertFlag){
							tp.data_marked.push(this.profile);
						}
					}
				}
			}
		});
		
		var tp = new Vue({
			el: "#npcPool",
			data:{
				data_marked:[],
				data_set:[],
			},
			created:function(){
				this.getTargets();
			},
		    watch:{
		          data_marked:function(val) {
		        	  sendpanel.ctl_subGroupFor = [];//此步至关重要....
		        	  for( var i = 0 ; i <this.data_marked.length;i++ ){
		        		  //Vue Array[index] = xx  can not trigger refreshDOM...
		        		  //change with this Way...
		        		  Vue.set(sendpanel.ctl_subGroupFor,i,(this.data_marked[i].name + ":" +this.data_marked[i].zhenying));
		        	  }
		          }
		    },
			methods:{
				getTargets:function(){
					self = this;
					$.ajax({
			            url: "http://localhost:8888/nn_data?limit=10", 
			            type:'get',
			            async:false,
			            data:{},
			            success: function(result,status){
			            	self.data_set = result;
			            }
			        });	
				}
			},
			mounted:function(e){
//				console.log(this.data_set);
			}
		})
		
		var sendpanel = new Vue({
			el:"#ctl_panel",
			data:{
				ctl_group_id : 8128,
				ctl_group_name : "",
				ctl_basic_default:[0,"副本名称",80,120],
				ctl_subGroupFor:[],
				ctl_subGroups :[]
			},
			methods:{
				send:function(e){
					e.preventDefault();
					if(tp.data_marked.length ==0){
						alert("请至少选择一个wowNPC")
					}
					
					var tname = $(this.$el).find("input[name=group_name]");
					if(tname.val()==""){
						alert("请添加组队名称..");
						$(tname).focus();
						return ;
					}
					//subgroup-json Array
					var sts = $(this.$el).find(".subGroup");
					for(var i =0 ; i< sts.length ; i++){
						var sub_group_id = $(sts[i]).find("input[name=sub_group_id]").val();
						var sub_group_for = $(sts[i]).find("input[name=sub_group_for]").val();
						var sub_group_blue = $(sts[i]).find("input[name=sub_group_blue]").val();
						var sub_group_blood = $(sts[i]).find("input[name=sub_group_blood]").val();
						var tmp = {
								"sub_group_id" :sub_group_id,
								"sub_group_for":sub_group_for,
								"sub_group_blue":sub_group_blue,
								"sub_group_blood":sub_group_blood
						}
						this.ctl_subGroups[i] = tmp;
					}
					var sendJson = {
						"GroupRequest":{
							"GroupId":this.ctl_group_id,
							"GroupName":this.ctl_group_name,
							"subGroups":this.ctl_subGroups
						}
					}
					console.log(sendJson);
				}
			},
			created:function(){
				;
			}
		})
		
	})