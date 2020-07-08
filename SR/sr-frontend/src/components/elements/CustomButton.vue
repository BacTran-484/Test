<template>
  <b-button size="sm" @click="onClickFunc" class="btn Button">
    {{ btnName }}
  </b-button>
</template>
<script>
export default {
  props: {
    btnName: String,
    onClickFunc: {
      type: Function
    }
  }
};
</script>
<style scoped>
.Button {
  border-radius: 5px;
  background-color: #034ea2;
  color: white;
  font-family: Roboto;
  font-size: 1em;
  padding: 0.32em 1.65em;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: 1.23;
  letter-spacing: normal;
  text-align: center;
  border-width: 1px;
  border-style: solid;
  border-color: #0000001a;
  min-width: 150px;
}
.Button:hover {
  transition: 0.3s;
  background: #0060cc;
}
.Button:active,
.Button:focus {
  background: #033975 !important;
}
</style>
