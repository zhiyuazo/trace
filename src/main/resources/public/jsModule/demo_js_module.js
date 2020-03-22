import {say} from "./modules/module_1st.js";
import abcd,{ps} from "./modules/module_2nd.js";

var person_0 = new Object({
	 count : 0,
	　m1 : function (){
    	console.log("对象-模块写法,result:" + "zzy")
	　},
	　m2 : function (){
    	console.log("对象-模块写法,result:" + "zp")
	　}
});

var person_1 = (function() {
    var count = 0;
    var m1 = function() {
    	console.log("立即执行函数-模块写法,result:" + count)
    };
    var m2 = function() {
    	console.log("立即执行函数-模块写法,result:" + (count + 10))
    };
    return {
	    m1: m1,
	    m2: m2
    }
})();

$(function(){
	say();
	person_0.m2();
	person_1.m2();
	console.log(ps)
	abcd()
})