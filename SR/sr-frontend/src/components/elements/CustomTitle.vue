<template>
  <div class="col-12">
    <span class="title">{{ title }}</span>
    <span class="title-line"></span>
  </div>
</template>

<script>
export default {
  props: {
    title: String
  }
};
</script>

<style scroped>
h3 {
  margin-left: 30px;
}
.title {
  font-family: Roboto;
  font-size: 35px;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: 1.2;
  position: relative;
  letter-spacing: normal;
  text-align: left;
  color: #034ea2;
  text-align: left;
  background: #fff;
  display: block;
  top: 3px;
  float: left;
  margin-bottom: -7px;
}
@media only screen and (max-width: 1080px) {
  .title {
    font-size: 25px;
  }
}
.title-line {
  display: block;
  border-bottom: 1px solid #034ea2;
  margin-bottom: 2px;
  width: 100%;
  float: left;
}
</style>
