<template>
  <div>
    <b-modal :ref="modalRef" hide-backdrop hide-footer hide-header>
      <div v-if="modalType == 'warning'" class="warning">
        <div v-if="notifyHeader" class="notify-header">{{ notifyHeader }}</div>
        <div class="notify d-block text-center">
          {{ content }}
        </div>
        <b-button
          class="notify"
          variant="outline-secondary"
          @click="$emit('handleCancel')"
          >No</b-button
        >

        <b-button
          class="notify"
          variant="outline-primary"
          @click="$emit('handleOk')"
          >Yes</b-button
        >
      </div>
      <div v-if="modalType == 'success'" class="success">
        <div class="success-notify notify d-block text-center">
          <img src="@/assets/images/tick-01.png" />
        </div>
        <div class="notify d-block text-center">
          {{ content }}
        </div>
      </div>
      <div v-if="modalType == 'error'" class="error">
        <div class="error-notify notify d-block text-center">
          <img src="@/assets/images/tick-01.png" />
        </div>
        <div class="notify d-block text-center">
          {{ content }}
        </div>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  props: {
    content: {
      type: String,
      default: "Code... is exist! Are you want to update?"
    },
    modalRef: {
      type: String,
      default: "modal"
    },
    title: {
      type: String,
      default: "Title"
    },
    notifyHeader: {
      type: String
    }
  },
  data: function() {
    return {
      modalType: ""
    };
  },
  mounted() {
    //Params:
    // + showModal/hideModal - trigger the show/hide modal action
    // + req - the reference to process the action
    this.$root.$on("showModal", (req, ref) => {
      this.modalType = ref;
      if (req == this.modalRef) {
        // in case we have multi modal in same page
        this.$refs[req].show();
      }
    });
    this.$root.$on("hideModal", (req, ref) => {
      this.modalType = ref;
      if (req == this.modalRef) {
        // in case we have multi modal in same page
        this.$refs[req].hide();
      }
    });
  },
  beforeDestroy() {
    // Destroy show/hide modal
    this.$root.$off("showModal");
    this.$root.$off("hideModal");
  }
};
// export const ModalType = {
//     Success: 'success',
//     Error: 'error',
//     Warning: 'warning'
// }
</script>

<style>
.notify.btn-outline-primary:hover {
  color: #fff;
  background-color: #034ea2;
  border-color: #034ea2;
}
.notify.btn-outline-primary {
  color: #034ea2;
  border-color: #034ea2;
}
.notify.text-center,
button.notify {
  font-size: 1.9em;
}

button.notify {
  min-width: 7em;
  margin: 1em 0.5em 0;
}

.notify {
  text-align: center;
}

.modal-body {
  text-align: center;
  padding: 3em !important;
}

.modal-body img {
  margin: auto;
  width: 60px;
  height: 90px;
  padding: 10px 0 20px;
}

.modal-content {
  width: 80% !important;
  margin: auto;
  border-radius: 2em !important;
  box-shadow: 0px 3px 13px #00000061 !important;
}

.modal-dialog {
  top: 30% !important;
}

.notify-header {
  font-size: 2.5em;
  font-family: BoldRoboto;
  color: #034ea2;
}
</style>
