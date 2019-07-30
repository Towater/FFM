import VueRouter from 'vue-router'


import Login from './components/login.vue'
import Main from './components/main.vue'
import Circle from './components/circle.vue'
import Square from './components/square.vue'
import Zhexian from './components/zhexian.vue'
import Adduser from './components/adduser.vue'
import UChangePwd from './components/UChangePwd.vue'
import Datadown from './components/Datadown.vue'
import Datashow from './components/Datashow.vue'
import Dataup from './components/Dataup.vue'
import Income from './components/incomeinfo.vue'
import UIndex from './components/UIndex.vue'
import Gold from './components/Gold.vue'
import Exchange from './components/Exchange.vue'
import Stock from './components/Stock.vue'
import pay from './components/payinfo.vue'
// xin

var router = new VueRouter({
    mode:'history',
    routes: [ // 配置路由规则
      { path: '/', redirect: '/Login' },
      { path: '/login', component: Login },
      { 
          path:'/main',
          name:'index',
          component:Main,
          children:[
            {
              path:"circle",
              component:Circle
            },
            {
             path:"square",
             component:Square
           },
           {
               path:"zhexian",
               component:Zhexian
           },
           {
             path:"adduser",
             component:Adduser
           },
           {
              path:"UChangePwd",
              component:UChangePwd
           },
           {
            path: "datadown",
            component: Datadown
          },
          {
            path: "datashow",
            component: Datashow
          },
          {
            path: "dataup",
            component: Dataup
          },
          {
            path: "UIndex",
            component: UIndex
          },
          {
            path:'income',
            component: Income
          },
          {
            path:'Gold',
            component: Gold
          },
          {
            path:'Exchange',
            component: Exchange
          },
          {
            path:'Stock',
            component: Stock
          },
          {
            path:'pay',
            component:pay
          }
           
          ]
      }

    ]
  })
  

  export default router