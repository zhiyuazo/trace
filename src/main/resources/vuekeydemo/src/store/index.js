import Vue from 'vue' 
import Vuex from 'vuex'
Vue.use(Vuex);

export default new Vuex.Store({
    state: {
      msg: 'hello'
    },
    mutations: {
      change (state,payload) {
            console.log("chang to " + payload)
            state.msg = payload;
      }
    }
  })



