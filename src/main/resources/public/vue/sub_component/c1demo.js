$(function(){
	var tmpl=document.getElementById("tmpl").innerHTML;
	Vue.component("tentity",{
		props:['detailz'],
		template:tmpl, 
		methods:{
			selectedMark:function(e){
				console.log("Click a task....")
			}
		}
	});
	
	new Vue({
		el:"#display_area",
		data : {
			para1 : {
				name:"zzy",
				labelt : "abc",
				nanyi : "hard"
			}
			
		}
	})
})
