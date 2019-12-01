/**
 * 
 */


$(function(){
	new Vue({
		el:"#loginArea",
		data: {
			user : {"name":"" , "password":""}
		},
		methods:{
			login: function(){
				var self = this;
				console.log(this.user.name)
				console.log(this.user.password)
				$.ajax({
				    url: "/login",
				    type:'post',
				    //不用字符串报错.后台直接解析不了JSON对象
				    //日后追查
				    data:JSON.stringify(self.user),
		            contentType : "application/json;charset=UTF-8",
				    success: function(result){
				    	if(result == 1){
				    		return null;
				    	}else if(result ==0){
					    	location.href="/login/home.html";
				    	}
				    }
				});
			}
		}
	})
	
	
})