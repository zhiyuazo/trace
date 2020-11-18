import Vue from 'vue' //如果使用这个 要配置vue.config.js中的runtimeCompiler 配置项
// import Vue from 'vue/dist/vue.esm.js' //这个解决Aside.template不能使用的问题
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue' 
import router from './router'
import store from './store'

Vue.use(ElementUI);
Vue.config.productionTip = false
//vuex全局store模式，以下似乎为JS方式，子件并不能够访问...
// var storejsmode = {
//   debug: true,
//   state: {
//     message: 'Hello!'
//   },
//   setMessageAction (newValue) {
//     if (this.debug) console.log('setMessageAction triggered with', newValue)
//     this.state.message = newValue
//   },
//   clearMessageAction () {
//     if (this.debug) console.log('clearMessageAction triggered')
//     this.state.message = ''
//   }
// }
//----↑ 以上仅供观赏，无实际作用
new Vue({
  router,
  store, //当前同页面共享store ,不同页面独有store...总感觉有问题..
  render: h => h(App),
}).$mount('#app')  
