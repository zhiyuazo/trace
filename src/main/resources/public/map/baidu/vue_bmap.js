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
	    },
		methods:{
		    init ({BMap, map}) {
		        var point = new BMap.Point(113.284, 23.125)
		        map.centerAndZoom(point, 13)
		        var marker = new BMap.Marker(point) // 创建标注
		        map.addOverlay(marker) // 将标注添加到地图中
		        var circle = new BMap.Circle(point, 6, { strokeColor: 'Red', strokeWeight: 6, strokeOpacity: 1, Color: 'Red', fillColor: '#f03' })
		        map.addOverlay(circle)
		      },
	        getClickInfo (e) {
		    	  console.log(e.point.lng)
		    	  console.log(e.point.lat)
		    	  this.center.lng = e.point.lng
		    	  this.center.lat = e.point.lat
	        }
		}
	})
})	