<template>
  <div id="app">
    <CustomNotify
      :modalRef="errorNoti"
      :modalType="errorNoti"
      :content="content"
      @handleOk="GetReturnValue(errorNoti, true)"
      @handleCancel="GetReturnValue(errorNoti, false)"
    ></CustomNotify>
    <CustomNotify
      :modalRef="successNoti"
      :modalType="successNoti"
      :content="content"
      @handleOk="GetReturnValue(successNoti, true)"
      @handleCancel="GetReturnValue(successNoti, false)"
    ></CustomNotify>
    <CustomNotify
      :modalRef="warningNoti"
      :notifyHeader="notifyHeader"
      :modalType="warningNoti"
      :content="content"
      @handleOk="GetReturnValue(warningNoti, true)"
      @handleCancel="GetReturnValue(warningNoti, false)"
    ></CustomNotify>
    <CustomNotify
      :modalRef="insertNoti"
      :notifyHeader="notifyHeader"
      :modalType="warningNoti"
      :content="content"
      @handleOk="GetReturnValue(insertNoti, true)"
      @handleCancel="GetReturnValue(insertNoti, false)"
    ></CustomNotify>

    <div class="parent-code">
      <label class="code-name-label roboto-medium" for="code-name"
        >Code Name</label
      >
      <div class="selectbox">
        <v-select
          :options="options"
          v-model="commonCodeSelected"
          label="text"
          :select-on-key-codes="[13]"
        />
      </div>
      <CustomButton btnName="Search" :onClickFunc="searchCommonMainCode" />
    </div>
    <div class="table-wrapper">
      <perfect-scrollbar id="table-scroll">
        <transition name="fade">
          <div class="loading" v-show="loading">
            <span class="fa fa-spinner fa-spin"></span> Loading
          </div>
        </transition>
        <vue-ads-table
          class="my-common-code-table"
          :columns="fields"
          :rows="tableRowData"
          :filter="filter"
          :page="page"
          :selectable="selectable"
          @selection-change="selectionChanged"
          export-name="test"
        ></vue-ads-table>
      </perfect-scrollbar>
    </div>
    <div class="crud-form">
      <div class="row">
        <div class="col-md-8">
          <div class="row">
            <div class="col-md-5 margin-bottom-30">
              <CustomInput
                labelCol="4"
                inputCol="8"
                customClass
                id="txtCode"
                label="Code"
                v-model="commonCodeModel.dtlCdId"
                :validate="submitted && !$v.commonCodeModel.dtlCdId.required"
              />
            </div>
            <div class="col-md-1 margin-bottom-30"></div>
            <div class="col-md-6 margin-bottom-30">
              <CustomInput
                labelCol="4"
                inputCol="8"
                id="txtName"
                label="Name"
                v-model="commonCodeModel.dtlCdNm"
                :validate="submitted && !$v.commonCodeModel.dtlCdNm.required"
              />
            </div>
            <div class="col-md-5 margin-bottom-30">
              <div class="row align-left">
                <div class="col-sm-4">
                  <label for="txtExpDate" class="roboto-medium">Exp.Date</label>
                </div>
                <div class="col-sm-8">
                  <b-form-input
                    id="txtDateInput"
                    v-model="commonCodeModel.dlDt"
                    type="text"
                    placeholder="YYYY-MM-DD"
                    autocomplete="off"
                  ></b-form-input>
                  <b-form-datepicker
                    id="datepicker-dateformat2"
                    :date-format-options="{
                      day: '2-digit',
                      month: '2-digit',
                      year: 'numeric'
                    }"
                    button-only
                    v-model="commonCodeModel.dlDt"
                    :min="minDate"
                  ></b-form-datepicker>
                  <div
                    class="invalid-feedback d-block"
                    v-if="submitted && !$v.commonCodeModel.dlDt.required"
                  >
                    Date is required
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4 align-right">
          <div>
            <CustomButton
              class="margin-bottom-30"
              btnName="Save"
              :onClickFunc="createOrUpdateCommonCode"
            />
          </div>
          <div>
            <CustomButton
              class="margin-bottom-30"
              btnName="Delete"
              :onClickFunc="deleteCommonCode"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CustomInput from "@/components/elements/CustomInput";
import CustomButton from "@/components/elements/CustomButton";
import CustomNotify from "@/components/elements/CustomNotify";
import api from "@/plugin/common-code-api";
import { mapState } from "vuex";
import { VueAdsTable } from "vue-ads-table-tree";
import mixin from "@/mixin/common-mixin.js";
import { TableMethod } from "@/components/elements/TableConfig.js";
import { required } from "vuelidate/lib/validators";
import * as CONFIG from "@/common/constants/Config.js";

let fields = [
  {
    property: "no",
    title: "No.",
    direction: true,
    filterable: true
  },
  {
    property: "cdId",
    title: "cdId",
    direction: null,
    filterable: true,
    visible: false
  },
  {
    property: "dtlCdId",
    title: "Code",
    direction: null,
    collapseIcon: true,
    filterable: true
  },
  {
    property: "dtlCdNm",
    title: "Name",
    direction: null,
    filterable: true
  },
  {
    property: "regDate",
    title: "Reg. Date",
    direction: null,
    filterable: true
  },
  {
    property: "dlDt",
    title: "Exp. Date",
    direction: null,
    filterable: true
  }
];

export default {
  name: "UserManagement",
  mixins: [mixin],
  components: {
    CustomInput,
    CustomButton,
    VueAdsTable,
    CustomNotify
    // ValidationProvider
    // JqxGrid,
  },
  data() {
    return {
      loading: false,
      minDate: new Date(),
      submitted: false,
      filter: "",
      page: 0,
      commonCodeSelected: "",
      commonCodeModel: {
        cdId: "", // id
        dtlCdId: "", // detail code id
        dtlCdNm: "", // name
        regDate: "", // registerd date
        dlDt: "9999-12-31" //expired date
      },
      options: [],
      fields: fields,
      tableRowData: [],
      selectedRow: [],
      isExistCode: null,
      selectable: true,
      selectedRowIds: [],
      parentSelected: null,
      content: "This code id is exist! Are you want to update?",
      returnResult: true,
      callback: Function,
      successNoti: "success",
      errorNoti: "error",
      warningNoti: "warning",
      insertNoti: "insert",
      notifyHeader: ""
    };
  },
  validations: {
    commonCodeModel: {
      dtlCdId: { required },
      dtlCdNm: { required },
      dlDt: { required }
    }
  },
  mounted: function() {
    this.getCommonCodeSelectData();
    this.getAllCommonCode();
    // const lstElm = document.querySelector('#table-scroll');
    // lstElm.addEventListener('scroll' , () => {
    //   if(lstElm.scrollTop + lstElm.clientHeight >= lstElm.scrollHeight){
    //     this.loadMore();
    //   }
    // })
  },
  methods: {
    ...TableMethod,
    selectionChanged(selectedRows) {
      if (selectedRows.length > 0) {
        this.commonCodeModel.dtlCdId = selectedRows[0].dtlCdId;
        this.commonCodeModel.dtlCdNm = selectedRows[0].dtlCdNm;
        this.commonCodeModel.dlDt = selectedRows[0].dlDt;
        this.commonCodeModel.cdId = selectedRows[0].cdId;
        this.commonCodeModel.regDate = selectedRows[0].regDate;
        // reset validate
        this.submitted = false;
        // determine parent or child
        if (selectedRows[0].cdId === selectedRows[0].dtlCdId) {
          this.parentSelected = true;
        } else {
          this.parentSelected = false;
        }
      } else {
        this.commonCodeModel.dtlCdId = "";
        this.commonCodeModel.dtlCdNm = "";
        this.commonCodeModel.dlDt = "";
        this.commonCodeModel.cdId = "";
        this.commonCodeModel.regDate = "";
      }
    },
    getCommonCodeSelectData: function() {
      let data = api.getData("/codes/selectbox");
      data.then(data => {
        let arr = this.formatSelect(data);
        this.options = arr;
      });
    },
    getAllCommonCode: function() {
      let dataPromise = api.getData("/common-codes");
      dataPromise.then(arr => {
        this.tableRowData = this.clean(arr);
        return this.addNumberingToArr(this.tableRowData);
      });
    },
    createOrUpdateCommonCode: function() {
      //validate data
      this.submitted = true;
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      //CREATE OR UPDATE PARENT
      if (!this.commonCodeSelected.id) {
        this.commonCodeModel.cdId = this.commonCodeModel.dtlCdId;
        //check code existed
        let dataPromise = api.getData(
          `/common-codes/${this.commonCodeModel.cdId}/${this.commonCodeModel.dtlCdId}/exist`
        );
        dataPromise.then(data => {
          let jsonObj = JSON.stringify(this.commonCodeModel);
          if (data) {
            this.showModal(this.warningNoti, this.warningNoti, () => {
              api
                .putRequest(
                  `/common-codes/${this.commonCodeModel.cdId}/${this.commonCodeModel.dtlCdId}`,
                  jsonObj
                )
                .then(res => {
                  this.hideModal(this.warningNoti, this.warningNoti);
                  if (res.status == 200) {
                    this.content = CONFIG.SuccessMessage;
                    this.showModal(this.successNoti, this.successNoti);
                  } else {
                    this.showModal(this.errorNoti, this.errorNoti);
                  }

                  // refresh select
                  this.getCommonCodeSelectData();
                  // refresh table
                  this.getAllCommonCode();
                });
            });
          } else {
            this.showModal(this.insertNoti, this.warningNoti, () => {
              api.postRequest("/common-codes", jsonObj).then(res => {
                this.hideModal(this.warningNoti, this.warningNoti);
                if (res.status == 201) {
                  this.content = CONFIG.SuccessMessage;
                  this.showModal(this.successNoti, this.successNoti);
                } else {
                  this.showModal(this.errorNoti, this.errorNoti);
                }

                // refresh select
                this.getCommonCodeSelectData();
                // refresh table
                this.getAllCommonCode();
              });
            });
          }
        });
      } else {
        //CREATE OR UPDATE CHILD
        this.commonCodeModel.cdId = this.commonCodeSelected.id;
        let dataPromise = api.getData(
          `/common-codes/${this.commonCodeModel.cdId}/${this.commonCodeModel.dtlCdId}/exist`
        );
        dataPromise.then(data => {
          let jsonObj = JSON.stringify(this.commonCodeModel);
          if (data) {
            this.showModal(
              this.warningNoti,
              this.warningNoti + "_sub_update",
              () => {
                api
                  .putRequest(
                    `/common-codes/${this.commonCodeModel.cdId}/${this.commonCodeModel.dtlCdId}`,
                    jsonObj
                  )
                  .then(res => {
                    this.hideModal(this.warningNoti, this.warningNoti);
                    if (res.status == 200) {
                      this.content = CONFIG.SuccessMessage;
                      this.showModal(this.successNoti, this.successNoti);
                    } else {
                      this.showModal(this.errorNoti, this.errorNoti);
                    }
                    // refresh select
                    this.getCommonCodeSelectData();
                    // refresh table
                    this.searchCommonMainCode();
                  });
              }
            );
          } else {
            this.showModal(this.insertNoti, this.warningNoti + "_sub", () => {
              api.postRequest("/common-codes", jsonObj).then(res => {
                this.hideModal(this.warningNoti, this.warningNoti);
                if (res.status == 201) {
                  this.content = CONFIG.SuccessMessage;
                  this.showModal(this.successNoti, this.successNoti);
                } else {
                  this.showModal(this.errorNoti, this.errorNoti);
                }
                // refresh select
                this.getCommonCodeSelectData();
                // refresh table
                this.searchCommonMainCode();
              });
            });
          }
        });
      }
    },
    deleteCommonCode: function() {
      api
        .deleteRequest(
          `/common-codes/${this.commonCodeModel.cdId}/${this.commonCodeModel.dtlCdId}`
        )
        .then(() => {
          this.content = "Delete Success";
          this.showModal(this.successNoti, "delete");
        })
        .then(() => {
          if (this.parentSelected) {
            this.getCommonCodeSelectData();
            this.getAllCommonCode();
            this.commonCodeSelected = "";
          } else {
            this.getCommonCodeSelectData();
            this.searchCommonMainCode();
            this.commonCodeSelected = "";
          }
        });
    },
    searchCommonMainCode: function() {
      let url = "";
      if (this.commonCodeSelected && this.commonCodeSelected.id) {
        url = `/common-codes/main/${this.commonCodeSelected.id}`;
      } else {
        url = "/common-codes/";
      }
      let dataPromise = api.getData(url);
      dataPromise.then(data => {
        if (data.length > 0) {
          this.tableRowData = this.clean(data);
          this.addNumberingToArr(this.tableRowData);
        } else {
          this.tableRowData = [this.clean(data)];
          this.addNumberingToArr(this.tableRowData);
        }
      });
    },
    showModal(modalRef, type, callback) {
      switch (type) {
        case this.errorNoti:
          // this.content - Set modal content message
          this.content = CONFIG.ErrorMessage;
          break;
        case this.successNoti:
          this.content = CONFIG.SuccessMessage;
          break;
        case this.warningNoti + "_sub": // check if this modal is insert sub id
          // this.notifyHeader - Set modal header title
          this.notifyHeader = "Create Sub!!!";
          this.content = CONFIG.InsertMessage;
          // re-set modal type = Warning noti
          type = this.warningNoti;
          break;
        case "delete":
          type = this.successNoti;
          break;
        case this.warningNoti + "_sub_update": // check if this modal is update sub id
          this.notifyHeader = "Update Sub!!!";
          this.content = CONFIG.UpdateMessage;
          // re-set modal type = Warning noti
          type = this.warningNoti;
          break;
        default:
          // default is insert/update parent id
          // modal type : Warning noti
          // modal ref : insert/update ref
          if (modalRef == this.insertNoti) {
            this.notifyHeader = "Create!!!";
            this.content = CONFIG.InsertMessage;
          } else {
            this.notifyHeader = "Update!!!";
            this.content = CONFIG.UpdateMessage;
          }
          break;
      }
      this.$root.$emit("showModal", modalRef, type);
      this.callback = callback;
    },
    hideModal(modalRef, type) {
      this.$root.$emit("hideModal", modalRef, type);
    },
    GetReturnValue(modalRef, params) {
      params == true ? this.callback() : "";
      this.hideModal(modalRef);
    }
    // loadMore : function(){
    //   this.loading = true;
    //   let dataPromise = api.getData("/common-codes");
    //   dataPromise.then((arr) => {
    //     setTimeout(() => {
    //       let data = this.clean(arr)
    //       for(let i = 0; i < 10; i++){
    //         this.tableRowData.push(data[i]);
    //       }
    //       this.addNumberingToArr(this.tableRowData);
    //       this.loading = false;
    //     }, 200);
    //     return this.tableRowData;
    //   });
    // }
  },
  computed: {
    ...mapState({
      status: state => state.login
    })
  }
};
</script>

<style scoped>
#codeManagement label {
  font-size: 22px;
}
.table-margin {
  margin-bottom: 58px;
}

.margin-right-70 {
  margin-right: 70px !important;
}
.table-margin td {
  border-right: 4px solid #fff;
  border-bottom: 4px solid #fff;
}

.b-calendar .b-calendar-inner {
  margin: auto;
}

.code-name-label,
.btn-search {
  margin: 0;
  margin-right: 24px;
}

.select2-container--default .select2-selection--single {
  height: 40px;
}

.selectbox {
  width: 535px;
  height: 39px;
  display: table-cell;
  margin-right: 114px;
}

label {
  margin-bottom: 0px;
}

.screen-title {
  height: 50px;
  width: 100%;
  margin-bottom: 4rem;
}

.col {
  padding: 0px;
}

.vue-ads-text-sm {
  font-size: 1.9em !important;
}

.vs__dropdown-toggle {
  padding: 0;
}

.crud-form {
  font-size: 2.2em;
}

.table-wrapper {
  margin-bottom: 62px;
}
.margin-bottom-30 {
  margin-bottom: 30px;
}
.loading {
  text-align: center;
  position: absolute;
  color: #fff;
  z-index: 9;
  background: slateblue;
  padding: 8px 18px;
  border-radius: 5px;
  left: calc(50% - 45px);
  right: calc(50% - 18px);
}
/* #table-scroll{
  overflow : scroll;
  height: 450px;
} */
.b-form-datepicker {
  position: absolute;
  top: 0;
  right: 16px;
  z-index: 100;
}
</style>
