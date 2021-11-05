import Vue from 'vue'
import App from './App'
import sortUtil from "static/until/sortUtil.js"
import idMaker from "static/until/idMaker.js"


Vue.config.productionTip = false
Vue.prototype.sortUtil = sortUtil;
Vue.prototype.idMaker = idMaker;
App.mpType = 'app'
const app = new Vue({
    ...App
})
app.$mount()
