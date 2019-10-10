$(function(){
	
	//-----
	var panel = new Vue({
		el:"#panel",
		data:{
			url : "/abpool",
			data_set_display : [],
			inc :0,
			data_set_send : [0,"zhiyuan","zzy","dev",30]
		},
		created:function(){
			this.fetch();
		},
		methods:{
			fetch:function(e){
				self = this
	            axios.get(this.url).then(function(response){
	            	self.data_set_display = response.data;
	            }).catch(function(err){
	            	return ;
	            });
				
			},
			save:function(e){
				e.preventDefault();
				var sts = $(this.$el).find(".newab");
				var ab_id = $(sts).find("input[name=ab_id]").val();
				var ab_name = $(sts).find("input[name=ab_name]").val();
				var ab_alias = $(sts).find("input[name=ab_alias]").val();
				var ab_label = $(sts).find("input[name=ab_label]").val();
				var ab_age = $(sts).find("input[name=ab_age]").val();
				var ab_JSON = {
						"id" :ab_id,
						"name":ab_name,
						"alias":ab_alias,
						"label":ab_label,
						"age":ab_age
				}
				self = this;
	            axios.post(this.url,ab_JSON).then(function(response){
	            	self.inc +=1;
	            	self.data_set_send = [self.inc,"zhiyuan","zzy","dev",30];
	            	self.fetch(e);
	            }).catch(function(err){
	            	return ;
	            });
			},
			update:function(e){
				e.preventDefault();
				var sts = $(this.$el).find(".newab");
				var ab_id = $(sts).find("input[name=ab_id]").val();
				var ab_name = $(sts).find("input[name=ab_name]").val();
				var ab_alias = $(sts).find("input[name=ab_alias]").val();
				var ab_label = $(sts).find("input[name=ab_label]").val();
				var ab_age = $(sts).find("input[name=ab_age]").val();
				var ab_JSON = {
						"id" :ab_id,
						"name":ab_name,
						"alias":ab_alias,
						"label":ab_label,
						"age":ab_age
				}
				self = this;
	            axios.put(this.url,ab_JSON).then(function(response){
	            	self.data_set_send = [1,"zhiyuan","zzy","dev",30];
	            	self.fetch(e);
	            }).catch(function(err){
	            	return ;
	            });
			},
			remove:function(e){
				var index = $(this.$el).find(".newab").find("input[name=ab_id]").val();
				var self =this ;
	            axios.delete(this.url+"/" +index).then(function(response){
	            	self.fetch(e);
	            }).catch(function(err){
	            	return ;
	            });
			},
			clean:function(e){
				var self =this ;
	            axios.delete(this.url).then(function(response){
	            	self.fetch(e);
	            }).catch(function(err){
	            	return ;
	            });
			}
		},
		mounted:function(e){
				;
		}
	});
	//-----
})
