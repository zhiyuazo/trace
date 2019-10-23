$(function(){
	var demo = new Vue({
	  el: '#demo',
	  data:{
		  value1:'',
		  value2:'',
		  pwdvalue:'',
		  advice1 :'',
		  advice2 :'',
		  advice3 :'',
		  restaurants: []
	  },
	  methods: {
	        querySearch(queryString, cb) { //这个函数是个奇葩存在..
	          console.log("enter into qs=" + queryString + "|" + "callback=" +cb);
	          var restaurants = this.restaurants;
	          var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
	          // 调用 callback 返回建议列表的数据
	          cb(results);
	        },
	        createFilter(queryString) {
	          return (restaurant) => {
	            return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
	          };
	        },
	        handleSelect(item) {
	          console.log(JSON.stringify(item));
	        },
	        handleIconClick(ev) {
	            console.log(ev);
	        }
	      },
	      mounted() {
	        this.restaurants = window.globalConfig.restaurants;
	      }
	})
})