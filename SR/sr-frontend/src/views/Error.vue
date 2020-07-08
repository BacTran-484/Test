<template>
  <div id="errorPage">
    <div class="error-wrapper">
      <div class="error-title">
        You don't have permission to view this page.
      </div>
      <div class="error-content">
        The requested page requires authorization for access. <br /><br />
        We suggest that you return to the <a href="/"> SAMSUNG SDS homepage </a
        ><br />
        or <a href="/login" v-on:click="logout()"> log out </a> this user
        account.
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "ErrorPage",
  data() {
    return {
      content:
        "The reuqested page requires authorization for access. <br> We suggest that you return to the SAMSUNG"
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("user");
    }
  }
};
</script>
<style scoped>
.error-wrapper {
  padding: 5em 3.5em;
  /* border: 1px solid #9b2c2c; */
  border: 1px solid #034ea2;
  border-radius: 8px;
  width: 720px;
  margin: auto;
  /* top: 30%; */
  position: relative;
  text-align: center;
  margin-bottom: 30em;
}
.error-title {
  font-size: 3em;
  font-family: BoldRoboto;
  color: #034ea2;
}
.error-content {
  font-size: 1.9em;
}
.error-content a {
  color: #034ea2;
}
</style>
