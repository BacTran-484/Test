<template>
  <div>
    <vue-headful title="SR Allocation Management" />
    <NavBar v-if="!isLogin"></NavBar>
    <div class="container content">
      <Title v-if="!isLogin"></Title>
      <router-view></router-view>
    </div>
    <Footer
      :style="footerStyle"
      :footerStyle="footerStyle"
      :footerLineStyle="footerLineStyle"
      :isLogin="isLogin"
    ></Footer>
  </div>
</template>

<script>
import NavBar from "@/components/layouts/NavBar";
import Footer from "@/components/layouts/Footer";
import Title from "@/components/layouts/Title";
import { mapState } from "vuex";
import { bgColor, MenuDetail } from "@/common/constants/Config";

export default {
  name: "App",
  components: {
    NavBar,
    Title,
    Footer
  },
  data: function() {
    return { isLogin: false, footerStyle: "", footerLineStyle: "" };
  },
  mounted() {
    let currentMenu = this.getCurrentUrl();
    let activeMenu = "";
    currentMenu.length > 1
      ? (activeMenu = currentMenu[1])
      : (activeMenu = currentMenu[0]);
    if (activeMenu == "Login") {
      this.isLogin = true;
      this.setBodyBackground(bgColor);
      this.footerStyle = "background: " + bgColor + "; color: #fff;";
      this.footerLineStyle = "background: #fff; color: " + bgColor + ";";
    } else {
      this.isLogin = false;
      this.setBodyBackground("#fff");
      this.footerStyle = "background: #fff; color: " + bgColor + ";";
      this.footerLineStyle = "background: " + bgColor + "; color: #fff;";
    }
    this.$store.dispatch("navBar/setActive", activeMenu);
  },
  computed: {
    ...mapState({
      menuActive: state => state.navBar.menuActive
    })
  },
  methods: {
    setBodyBackground(bgColor) {
      document.body.style.backgroundColor = bgColor;
    },
    setFooterBackground(bgColor, color) {
      let footer = document.getElementsByClassName(".common-footer");
      footer.style.backgroundColor = bgColor;
      footer.style.color = color;
    },
    getCurrentUrl() {
      let currentUrl = window.location.href;
      return Object.keys(MenuDetail).filter(prop =>
        currentUrl.includes(MenuDetail[prop].url)
      );
    }
  },
  watch: {
    $route() {
      let currentMenu = this.getCurrentUrl();
      let activeMenu = "";
      currentMenu.length > 1
        ? (activeMenu = currentMenu[1])
        : (activeMenu = currentMenu[0]);
      if (activeMenu == "Login") {
        this.isLogin = true;
        this.setBodyBackground(bgColor);
        this.footerStyle = "background: " + bgColor + "; color: #fff;";
        this.footerLineStyle = "background: #fff; color: " + bgColor + ";";
      } else {
        this.isLogin = false;
        this.setBodyBackground("#fff");
        this.footerStyle = "background: #fff; color: " + bgColor + ";";
        this.footerLineStyle = "background: " + bgColor + "; color: #fff;";
      }
    }
  }
};
</script>

<style>
@import url("App.css");
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
</style>