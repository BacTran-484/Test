<template>
  <b-navbar
    pills
    class="top-navbar fixed-top"
    toggleable="lg"
    type="dark"
    variant="dark"
  >
    <b-navbar-brand href="/">
      <img
        src="@/assets/images/common/SAMSUNG-SDS_White.png"
        alt="SAMSUNG SDS"
      />
    </b-navbar-brand>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item
          v-for="(val, prop) in MenuDetail"
          :key="prop"
          :active="menuActive == prop"
          @click="setActive(prop)"
          :to="'/' + val['url']"
          >{{ prop }}</b-nav-item
        >
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown class="notify" right>
          <!-- Using 'button-content' slot -->
          <template v-slot:button-content>
            <img src="@/assets/images/common/notifications-white.png" />
            <span v-if="notifyNumber" class="notify-number">{{
              notifyNumber
            }}</span>
          </template>
          <span v-for="(item, index) in notifyList" :key="index">
            <b-dropdown-text class="dropdown-padding">
              <div>
                <span class="roboto-bold">{{ item.assignee }}</span>
                {{ item.message }}
              </div>
            </b-dropdown-text>
            <b-dropdown-divider></b-dropdown-divider>
          </span>

          <b-dropdown-text class="dropdown-padding">
            <b-button variant="outline-dark" class="btn-logout"
              >Mark all as read</b-button
            >
          </b-dropdown-text>
        </b-nav-item-dropdown>
        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template v-slot:button-content>
            <img
              class="user-avatar"
              src="@/assets/images/common/user-default-avatar.png"
            />
          </template>
          <b-dropdown-text class="dropdown-padding">
            <div>
              Name: <span class="roboto-bold">{{ userFullName }}</span>
            </div>
            <div>
              ID: <span class="roboto-bold">{{ userId }}</span>
            </div>
          </b-dropdown-text>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-text class="dropdown-padding">
            <b-button variant="outline-dark" class="btn-logout" @click="logout"
              >Log out</b-button
            >
          </b-dropdown-text>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>
<script>
import { mapState } from "vuex";

import {
  MenuUrlMapping,
  MenuList,
  MenuDetail
} from "@/common/constants/Config";

export default {
  name: "NavBar",
  data: function() {
    return {
      userFullName: JSON.parse(localStorage.getItem("user"))
        ? JSON.parse(localStorage.getItem("user")).empName
        : "",
      userId: JSON.parse(localStorage.getItem("user"))
        ? JSON.parse(localStorage.getItem("user")).empno
        : "",
      userPermission: 1,
      notifyNumber: 11,
      notifyList: [
        { assignee: "Phat Nguyen", message: "has assign you a new task" },
        { assignee: "Phat Nguyen", message: "has assign you a new task" },
        { assignee: "Phat Nguyen", message: "has assign you a new task" },
        { assignee: "Phat Nguyen", message: "has assign you a new task" },
        { assignee: "Phat Nguyen", message: "has assign you a new task" }
      ],
      MenuUrlMapping: MenuUrlMapping,
      MenuList: MenuList
    };
  },
  computed: {
    ...mapState({ menuActive: state => state.navBar.menuActive }),
    MenuDetail() {
      return Object.keys(MenuDetail)
        .filter(prop => MenuDetail[prop].menu.includes(this.userPermission))
        .reduce((obj, key) => {
          obj[key] = MenuDetail[key];
          return obj;
        }, {});
    }
  },
  methods: {
    setActive(menuTitle) {
      this.$store.dispatch("navBar/setActive", menuTitle);
    },
    logout() {
      localStorage.removeItem("user");
      location.reload(true);
    },
    setBodyBackground(color) {
      document.body.style.backgroundColor = color;
    }
  }
};
</script>

<style>
.btn-logout {
  width: 100%;
  background: rgba(255, 255, 255, 0.071) 0 0 no-repeat padding-box;
  border-color: #6b778c4d !important;
  border-radius: 5px !important;
}

.user-avatar {
  width: 48px;
  height: 48px;
}

.notify img {
  width: 38px;
  height: 38px;
}

.notify-number {
  position: absolute;
  right: 0;
  bottom: 0;
  min-width: 22px;
  min-height: 22px;
  color: #034ea2;
  font-size: 10px;
  line-height: 1.1rem;
  text-align: center;
  background: #fff;
  border: 2px solid;
  border-radius: 10px;
}

.notify.nav-item .nav-link {
  height: 48px !important;
}

.dropdown-menu {
  font-family: RegularRoboto;
}

.dropdown-menu .dropdown-padding {
  padding: 0.25rem 2.5rem;
  /* letter-spacing: .5px; */
}

.notify .dropdown-menu .dropdown-padding {
  padding: 0.25rem 1.5rem;
  /* letter-spacing: .5px; */
}

.notify .dropdown-menu .b-dropdown-text {
  font: 14px/17px RegularRoboto;
}

.dropdown-menu .b-dropdown-text {
  padding: 0;
}

.dropdown-menu .dropdown-divider {
  border-top: 1px solid #6b778c4d;
}

.dropdown-toggle::after {
  display: none !important;
}

.navbar-brand {
  padding-top: 0 !important;
  padding-bottom: 0 !important;
  margin-right: 3.5rem !important;
  font-size: 1.25rem;
  line-height: 0 !important;
  white-space: nowrap;
}

.navbar.top-navbar {
  padding: 12px 49px 12px !important;
  background-color: #034ea2 !important;
}

.navbar-brand img {
  width: 158px;
  height: 19px;
}

.ml-auto .nav-link {
  padding: 0 !important;
}

.navbar-nav .nav-item .nav-link {
  display: table-cell;
  height: 43.32px;
  padding: 10px 17px;
  color: #fff;
  font: 20px/24px RegularRoboto;
  line-height: 1;
  letter-spacing: 0;
  text-align: left;
  vertical-align: middle;
}

.nav-item {
  margin: 0;
}

.nav-item .nav-link {
  padding: 0;
}

.navbar-nav .nav-item {
  margin: 0 8.07px;
}

.navbar-dark .navbar-nav .nav-item .nav-link {
  color: #fff;
}

.navbar-dark .navbar-nav .show > .nav-link,
.navbar-dark .navbar-nav .active > .nav-link,
.navbar-dark .navbar-nav .nav-link.show,
.navbar-dark .navbar-nav .nav-link.active {
  background: #003d82 0 0 no-repeat padding-box;
  border-radius: 5px;
}

.navbar-dark .navbar-nav .nav-link:focus {
  color: rgba(255, 255, 255);
}

.dropdown-menu {
  width: 292px;
  padding: 1.1rem 0 !important;
  box-shadow: 0 3px 6px hsla(0, 0%, 0%, 0.349) !important;
  border: 1px solid #6b778c !important;
  border-radius: 8px !important;
}

.dropdown-menu li {
  display: list-item;
}
</style>
