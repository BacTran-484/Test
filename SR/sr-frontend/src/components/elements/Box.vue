<template>
  <div class="my-card">
    <div class="my-card-container">
      <router-link :to="urlPage">
        <div class="my-card-image">
          <img :src="require('@/assets/images/' + imgSrc)" />
          <div class="my-card-title">{{ title }}</div>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "Box",
  props: {
    title: String,
    imgSrc: String,
    urlPage: String
  }
};
</script>

<style scoped>
.my-card-image {
  display: flex;
  position: relative;
  height: 274px;
  width: 398px;
  /* flex-wrap: wrap-reverse; */
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.my-card-container {
  height: 274px;
  width: 398px;
}
.my-card {
  background: #ffffff 0% 0% no-repeat padding-box;
  opacity: 1;
  border-radius: 11px;
  border: 1px solid #6b778c;
}
.my-card img {
  height: 51.3%;
  width: auto;
}
.my-card:hover {
  box-shadow: 1px 8px 20px lightgrey;
  transition: 0.3s;
  transform: translate(0, -10px);
}
.my-card-title {
  text-align: center;
  font: Bold 25px Roboto;
  letter-spacing: 0px;
  color: #034ea2;
  opacity: 1;
  margin-top: 15px;
}
a {
  text-decoration: none;
}
.row {
  width: 100vw;
}
</style>
