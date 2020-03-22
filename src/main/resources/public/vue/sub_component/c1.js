//c1.js 如何载入一个组件的HTML 呢？
//理想情况下的层次结构，这里定义组件的行为逻辑!!!
var c1 = null;
var content =  $("<div id='tmpl'></div>").load("./sub_component/c1.html",function(){
	var tmpl=content.html();
	console.log(tmpl)
	c1 = Vue.component("tentity",{
		props:['detailz'],
		template:tmpl, 
		methods:{
			selectedMark:function(e){
				console.log("Click a task....")
			}
		}
	});
	console.log(Vue)
})
console.log("sub_component:c1 load-over...")
export default c1;

//var content =  $("<div id='tmpl'></div>").load("./sub_component/c1.html");
//var tmpl=content.html();
//console.log(tmpl)
//Vue.component("tentity",{
//	props:['detailz'],
//	template:tmpl, 
//	methods:{
//		selectedMark:function(e){
//			console.log("Click a task....")
//		}
//	}
//});
//console.log("sub_component:c1 load-over...")