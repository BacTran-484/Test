import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home";
import Login from "@/views/Login";
import UserManagement from "@/views/UserManagement";
import CommonCodeManagement from "@/views/CommonCodeManagement";
import SRList from "@/views/SRList";
import SRDetail from "@/views/SRDetail";
import UserSearch from "@/views/UserSearch";
import Role from "../_services/role";
import Error from "@/views/Error";

import { MenuUrlMapping } from "@/common/constants/Config";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/login",
      component: Login
    },
    {
      path: "/" + MenuUrlMapping.Home,
      component: Home,
      meta: {
        requiresAuth: true,
        authorize: [
          Role.SuperUser,
          Role.Manager,
          Role.Developer,
          Role.PartTimer,
          Role.SA,
          Role.General,
          Role.Operator
        ]
      }
    },
    {
      path: "/" + MenuUrlMapping.Admin,
      component: UserManagement,
      meta: {
        requiresAuth: true,
        authorize: [Role.SuperUser, Role.Manager]
      }
    },
    {
      path: "/" + MenuUrlMapping.Code,
      component: CommonCodeManagement,
      meta: {
        requiresAuth: true,
        authorize: [Role.SuperUser]
      }
    },
    {
      path: "/" + MenuUrlMapping.User,
      component: UserSearch,
      meta: {
        requiresAuth: true,
        authorize: [
          Role.SuperUser,
          Role.Manager,
          Role.Developer,
          Role.SA,
          Role.Operator
        ]
      }
    },
    {
      path: "/" + MenuUrlMapping.SRList,
      component: SRList,
      meta: {
        requiresAuth: true,
        authorize: [
          Role.SuperUser,
          Role.Manager,
          Role.Developer,
          Role.PartTimer,
          Role.SA,
          Role.General,
          Role.Operator
        ]
      }
    },
    {
      path: "/" + MenuUrlMapping.SRDetail,
      component: SRDetail,
      meta: {
        requiresAuth: true,
        authorize: [Role.SuperUser]
      }
    },
    {
      path: "/permission-denied",
      component: Error
    },
    // otherwise redirect to home
    { path: "*", redirect: "/" }
  ]
});

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const publicPages = ["/login", "/permission-denied"];
  const authRequired = !publicPages.includes(to.path);
  const currentUser = JSON.parse(localStorage.getItem("user"));
  const metaObj = to.meta;

  if (authRequired) {
    if (!currentUser) {
      next({
        path: "/login",
        redirect: to.fullPath
      });
    } else {
      let flag = false;
      for (let i = 0; i < currentUser.roles.length; i++) {
        if (metaObj.authorize.includes(currentUser.roles[i])) flag = true;
      }
      if (!flag) {
        next({ path: "/permission-denied" });
      }
    }
  } else {
    next();
  }
  next();
});

export default router;

// Vue.use(VueRouter)
// function lazyLoad(view){
//     return () => import(`@/views/${view}`);
//   }
//   var routes = [];
//   for(var obj in MenuDetail){
//     routes = [...routes,...[{path:'/'+MenuDetail[obj].url,component: lazyLoad(MenuDetail[obj].component)}]];
//   }

// const router = new VueRouter({
//   mode: 'history',
//   routes: [...routes,{ path: '*', redirect: '/' }]
// })
