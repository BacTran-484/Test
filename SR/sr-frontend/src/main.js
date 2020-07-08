import Vue from "vue";
import App from "./App.vue";
import BootstrapVue from "bootstrap-vue";
import VueRouter from "vue-router";
import router from "@/router/router";
import Vuex from "vuex";
import store from "@/store/store";
import Select2 from "v-select2-component";
import Axios from "axios";
import vSelect from "vue-select";
import vueHeadful from "vue-headful";
import Vuelidate from "vuelidate";
import Scrollbar from "vue2-perfect-scrollbar";

import "@fortawesome/fontawesome-free/css/all.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "./assets/css/font.css";
import "vue-select/dist/vue-select.css";
import "vue-ads-table-tree/dist/vue-ads-table-tree.css";
import "vue2-perfect-scrollbar/dist/vue2-perfect-scrollbar.css";

Vue.prototype.$http = Axios;
Vue.config.productionTip = false;

Vue.use(Vuex);
Vue.use(Vuelidate);
Vue.use(VueRouter);
Vue.use(BootstrapVue);
Vue.use(Scrollbar);
Vue.component("Select2", Select2);
Vue.component("v-select", vSelect);
Vue.component("vue-headful", vueHeadful);

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount("#app");
