import Vue from 'vue'
import Vuex from 'vuex'
import navBar from './modules/NavBar'
import login from './modules/Login'
import { alert } from './modules/alert.module';
import { authentication } from './modules/authentication.module';
 
Vue.use(Vuex)
const debug = process.env.NODE_ENV !== 'production'
 
export default new Vuex.Store({
  modules: {
    navBar,
    login,
    alert,
    authentication
  },
  strict: debug
})