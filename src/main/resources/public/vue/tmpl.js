/**
 * 
 */
$(function(){
	var tmpl=document.getElementById("tmpl").innerHTML;
	Vue.component("tentity",{
		props:['detailz'],
		template:tmpl, 
		methods:{
			selectedMark:function(e){
				$(this.$el).toggleClass("SELECTED_Mark")
				var class_str =  $(this.$el).attr("class");
				if(class_str.indexOf("Mark")==-1){
					var removeFlag = false;
					var removeIndex = 0;
					for(var i=0;i<tpool.task_marked.length;i++){
						if(tpool.task_marked[i].id == this.detailz.id){
							removeFlag = true;
							removeIndex = i;
							break;
						}
					}
					if(removeFlag){
						tpool.task_marked.splice(i,1);
					}
					
				}else{
					var insertFlag = true;
					for(var i=0;i<tpool.task_marked.length;i++){
						if(tpool.task_marked[i].id == this.detailz.id){
							insertFlag = false;
							break;
						}
					}
					if(insertFlag){
						tpool.task_marked.push(this.detailz);
					}
				}
			}
		}
	});
	
	var tpool = new Vue({
		el: "#taskPool",
		data:{
			task_marked:[],
			task_set:[]
		},
		methods:{
			sim: function(e){
				//直接写函数是不行的
//				for(var i= 0  ; i < 20 ; i++){
//					var newt = {
//						"id": i ,	
//						"name": "task-"+i ,	
//						"labelt": "旅游" ,	
//						"owner": "zhiyuan" ,	
//						"group": "自由组" ,	
//						"nanyi": "难"	
//					}
//					this.task_set[i] = newt;
//				}
					self = this;
					$.ajax({
			            url: "http://localhost:8888/ltmpl", 
			            type:'get',
			            async:false,
			            data:{},
			            success: function(result,status){
			            	self.task_set = result;
			            }
			        });	
			}
		},
		created:function(e){
			this.sim(e);
		},
		mounted:function(e){
			;
		}
	})
})
