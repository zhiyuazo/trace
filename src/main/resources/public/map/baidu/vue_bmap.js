$(function(){
	Vue.use(VueBaiduMap.default, {
	    ak: 'n0H5GZU4wEC1eVZNjieII5bM23KNxIGC',
	})
	Vue.component("baidu",{
	    template:'#baidu'
	})
	new Vue({
	    el:'#map',
	    data:{
	      center: {"lng": 0, "lat": 0},
	      zoom: 3
	    }
	})	
	new Vue({
	    el:'#map_vue_only',
	    data:{
	      center: {"lng": 113.284, "lat": 23.125},
	      zoom: 13
	    }
	})
})	