import Vue from 'vue'
import VueRouter from 'vue-router'
import router from '../index/router'

import echarts from 'echarts'
import iView from 'iview'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'iview/dist/styles/iview.css'


import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../../../node_modules/bootstrap/dist/js/bootstrap.min.js'
// ./ 当前目录。
// ../ 父级目录。
// / 根目录。
// import './layui/css/layui.css'
// import './layui/layui.js'

import axios from 'axios';
import VueResource from 'vue-resource'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)

Vue.use(VueResource)

Vue.use(VueRouter)
Vue.use(iView)
Vue.use(ElementUI)

Vue.prototype.$axios = axios
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
Vue.http.options.emulateJSON = true;

import Index from './index.vue'



axios.defaults.baseURL = '/api' 
new Vue({
  render: h => h(Index),
  router
}).$mount('#index')
