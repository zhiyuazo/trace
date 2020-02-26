$(function(){
	
	
	var  testStr = '{"name":"zzy","work":"java","sex":"male"}';
	var  testArr = ["A" , "B" , "C"];
	var  testJSON = {//不能存储date对象，需将date转换为字符串..
						"name":"zzy" , 
						"work": "java" ,
						"sex": "male" 
					};
	var  testJSONArr = ["Google","Nokia","Iecas"]; //可以看成JSON数组(可以用JSON.stringify转化为字符串)，也可以看成普通数组
	
	console.log("-----下面是字符串和JSON的转换-----------------------")
	//JSON-->String
		console.log("JSON-->string:" + JSON.stringify(testJSON));
	//String-->JSON
		console.log("String -->JSON:" + JSON.parse(testStr).name);   //安全， 快  JSON.parse(text[, reviver]) reviver 用于预先处理字符串中的值
		console.log("String -->JSON:" +eval("(" + testStr + ")").name); //不安全，略慢，会解析并执行任何代码
		console.log("String -->JSON:" + $.parseJSON(testStr).name);//JQuery方法
		
	console.log("-----下面是数组的元素处理-----------------------")
	//数组元素处理
		for (var i = 0; i < testArr.length; i++) {
			console.log("for-i:" +i + ":" +testArr[i])
		}
		for (var index in testArr) {
			console.log("for-in: " +index);
		}
		for (var item of testArr) {
			console.log("for-of: " +item);
		}
		for (var item of testArr.entries()) {
			console.log("for-of: " +item);
		}
		
		for(var key of testArr.keys()){
			console.log(key)
		}
		
		testArr.forEach(function(item, index ,arr){
			console.log(item + "?")
			if(item == "B"){
				return false; //不能停止
			}
		})
		
		$.each(testArr,function(index, item){
			console.log(item + "?")
			if(item == "B"){
				return false; //可以停止
			}
		})
		
	console.log("--------下面是JSON的处理------------------")
	//JSON素处理
		for (key in testJSON) { //迭代JSON对象
			console.log("for-in: "+key +"--" +testJSON[key] + "-" + testJSON.key + "[]OK,.Bad"  );
		}
	
		for (key in testJSONArr) { //迭代JSON对象
			console.log("for-in: "+key +"--" +testJSONArr[key] + "-" + testJSONArr.key + "[]OK,.Bad"  );
		}
		
		for (var i=0;i<testJSONArr.length ; i++) { //迭代JSON对象
			console.log("for-in: "+i +"--" +testJSONArr[i] + "-" + testJSONArr.i + "[]OK,.Bad"  );
		}
	
})