<template>
  <div class="common-footer">
    <div class="footer-line" :style="footerLineStyle"></div>
    <b-navbar style="padding-top: 0.5em;">
      <b-nav-text
        :style="
          footerStyle + 'margin-right:49px;margin-left:34px;width: 32.33%;'
        "
        >Copyright 2020 SAMSUNG SDS. All rights reserved.</b-nav-text
      >
      <b-nav-text :style="footerStyle + 'width: 32.33%;text-align: center;'"
        >Support: jack.park@samsung.com</b-nav-text
      >
      <div class="footer-img" style="width: 32%; text-align: right">
        <img
          v-if="!isLogin"
          src="@/assets/images/SAMSUNG-SDS_Blue.png"
          alt="SAMSUNG SDS"
        />
        <div v-if="isLogin">
          <img
            src="@/assets/images/common/SAMSUNG-SDS_White.png"
            alt="SAMSUNG SDS"
          />

          <img
            class="samsung-white"
            src="@/assets/images/1200px-Samsung_Logo.svg.png"
            alt="Samsung-white-logo"
          />
        </div>
      </div>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "Footer",
  props: {
    footerStyle: {
      type: String
    },
    footerLineStyle: {
      type: String
    },
    isLogin: {
      type: Boolean
    }
  }
};
</script>

<style scoped>
.footer-line {
  display: block;
  width: calc(100% - 100px);
  height: 1px;
  background: #034ea2;
  margin: 0 50px;
}
.footer-line li {
  color: #034ea2;
  font: 1.4em RegularRoboto;
}
.footer-img img {
  width: 85px;
  display: inline-block;
  margin-right: 34px;
}
.footer-img img.samsung-white {
  top: 1024px;
  left: 1803px;
  width: 64px;
  height: 21px;
}
.common-footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  z-index: 1000;
  background-color: white;
  height: 4.5em;
  font: 1.4em RegularRoboto;
  color: #034ea2;
}
.common-footer .navbar-expand {
  flex-flow: row nowrap;
  justify-content: space-between;
}
@media only screen and (max-width: 1200px) {
  .common-footer {
    font-size: 13px;
    position: relative;
  }
  .footer-img {
    margin-right: 3%;
  }
}

.samsung-blue {
  float: right;
  top: 1029px;
  left: 1707px;
  width: 85px;
  height: 10px;
}
</style>
