<template>
  <div class="home-content">
    <div
      v-for="(val, prop) in HomeIconMapping"
      :key="prop"
      class="column"
      @click="setActive(prop)"
    >
      <Box
        :title="MenuTitleMapping[prop]"
        :imgSrc="val"
        :urlPage="MenuUrlMapping[prop]"
      />
    </div>
  </div>
</template>

<script>
import Box from "@/components/elements/Box";
import { mapState } from "vuex";
import {
  MenuUrlMapping,
  HomeIconMapping,
  MenuTitleMapping
} from "@/common/constants/Config";

export default {
  name: "Home",
  data: function() {
    return {
      MenuUrlMapping: MenuUrlMapping,
      HomeIconMapping: HomeIconMapping,
      MenuTitleMapping: MenuTitleMapping
    };
  },
  components: {
    Box
  },

  computed: {
    ...mapState({
      menuActive: state => state.navBar.menuActive
    })
  },
  methods: {
    setActive(menuTitle) {
      this.$store.dispatch("navBar/setActive", menuTitle);
    }
  }
};
</script>

<style></style>
