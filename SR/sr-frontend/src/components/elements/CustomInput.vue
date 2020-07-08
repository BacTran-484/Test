<template>
  <b-row class="align-left">
    <b-col :sm="labelCol">
      <label
        :style="lblStyle"
        :class="'roboto-medium ' + customClass"
        :for="id"
        >{{ label }}</label
      >
    </b-col>
    <b-col :sm="inputCol">
      <b-form-input
        class="text"
        :type="type"
        :id="id"
        :name="id"
        :value="value"
        :style="txtStyle"
        v-on:input="$emit('input', $event)"
      >
      </b-form-input>
      <div class="invalid-feedback d-block" v-if="validate">
        {{ label }} is required
      </div>
    </b-col>
  </b-row>
</template>

<script>
export default {
  props: {
    id: {
      type: String
    },
    label: {
      type: String
    },
    value: {},
    customClass: {
      type: String
    },
    type: {
      type: String,
      default: "text"
    },
    labelCol: {
      type: String,
      default: "text"
    },
    inputCol: {
      type: String,
      default: "text"
    },
    txtStyle: {
      type: String
    },
    lblStyle: {
      type: String
    },
    validate: {
      type: Boolean
    }
  }
};
</script>
<style scoped>
.text.form-control {
  height: calc(1.5em + 0.45rem + 2px);
  font-size: 1em;
}
</style>
