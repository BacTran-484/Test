<template>
  <div class="title-block" v-if="menuActive">
    <div class="title">{{ menuActive }}</div>
    <div class="title-line"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { MenuTitleMapping } from "@/common/constants/Config";
export default {
  name: "Title",
  computed: {
    ...mapState({
      menuActive: state => MenuTitleMapping[state.navBar.menuActive]
    })
  }
};
</script>
<style scoped>
.title-line {
  position: relative;
  bottom: 6px;
  z-index: 1;
  display: block;
  width: 100%;

  /* height: 1px; */
  /* background: #000; */
  font-size: 35px;
  border-bottom: 1px solid #034ea2;
}
.title {
  position: relative;

  /* line-height: 1; */
  z-index: 2;
  display: inline-block;
  padding-right: 10px;
  color: #034ea2;
  font-family: BoldRoboto;
  font-size: 35px;
  background: #fff;
}
.title-block {
  position: relative;
  text-align: left;
  margin-bottom: 78px;
  line-height: 1;
}
</style>
