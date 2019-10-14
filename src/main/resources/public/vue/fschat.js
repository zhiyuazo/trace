Vue.component('blog-post', {
  props: ['post'],
  template: '<div class="blog-post">\
			      <h3>{{ post.title }}</h3>\
	  			  <button v-on:click="$emit(\'enlarge-text\')" class="btn btn-primary btn-xs">+</button>\
	  			  <button v-on:click="$emit(\'ensmall-text\',-0.1)" class="btn btn-primary btn-xs">-</button>\
			      <div v-html="post.content"></div>\
	  			  <slot></slot>\
			 </div>'
})	
$(function(){
	var post = new Vue({
	  el: '#blog-demo',
	  data: {
		postFontSize: 1,
	    posts: [
	      { id: 1, title: 'classical-1',content:"<span style='color:red'>尼罗河上的惨案</span>"},
	      { id: 2, title: 'classical-2',content:"<span style='color:red'>罗杰疑案</span>"},
	      { id: 3, title: 'classical-3',content:"<span style='color:red'>道德经</span>"}
	    ]
	  },
	  methods:{
		  large: function(event){
			  this.postFontSize += 1;
			  console.log(event)
		  },
		  small:function(delta,event){
			  //delt值由自组建抛出
			  this.postFontSize += delta
			  console.log(event);
		  }
	  }
	})
})