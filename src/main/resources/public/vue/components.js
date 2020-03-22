import c1 from "./sub_component/c1.js"
$(function(){
	new Vue({
		el:"#display_area",
		data : {
			para1 : {
				name:"zzy",
				labelt : "abc",
				nanyi : "hard"
			}
		},
		components :{
			c1
		}
	})
	console.log(c1)
	console.log("main-page which load sub-componen:c1 run over...")
})
