import Vue from 'vue' 
import Router from 'vue-router'
import ROUTER1 from '../components/routers/router1.vue'
import ROUTER2 from '../components/routers/router2.vue'
import USER from '../components/routers/routerUsers.vue'
import ENTRY from '../components/Entry.vue'
Vue.use(Router);

export default new Router({
    routes:[
        {
            path:'/',
            name:'home',
            component:ENTRY
        },
          //normal router1 demo
        {
            path:'/router1',
            name:'router1',
            component:ROUTER1
        },
        //normal router2 demo
        {
            path:'/router2',
            name:'router2',
            component:ROUTER2
        },
        //normal router2 demo
        {
            path:'/routerUser/:id',
            name:'user',
            component:USER
        }
    ]
})



