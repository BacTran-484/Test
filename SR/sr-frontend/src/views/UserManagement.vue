<template>
  <div id="app">
    <CustomNotify
      :modalRef="successNoti"
      :modalType="successNoti"
      :content="content"
      @handleOk="GetReturnValue(successNoti, true)"
      @handleCancel="GetReturnValue(successNoti, false)"
    ></CustomNotify>
    <CustomNotify
      :modalRef="errorNoti"
      :modalType="errorNoti"
      :content="content"
      @handleOk="GetReturnValue(errorNoti, true)"
      @handleCancel="GetReturnValue(errorNoti, false)"
    ></CustomNotify>
    <div class="crud-form">
      <div class="row">
        <div class="col-xl-2 margin-bottom-30">
          <CustomInput
            labelCol="2"
            inputCol="10"
            customClass
            id="userIdSearch"
            label="ID"
            v-model="userSearchModel.id"
          />
        </div>
        <div class="col-xl-4 margin-bottom-30">
          <CustomInput
            labelCol="2"
            inputCol="10"
            id="userNameSearch"
            label="Name"
            v-model="userSearchModel.name"
          />
        </div>
        <div class="col-xl-4 margin-bottom-30 align-left ">
          <div class="row align-left">
            <div class="col-sm-3">
              <label class="code-name-label roboto-medium" for="code-name"
                >Company</label
              >
            </div>
            <div class="col-sm-9">
              <v-select
                :options="companyList"
                v-model="userSearchModel.companyName"
                label="text"
              />
            </div>
          </div>
        </div>
        <div class="col-xl-2 align-right">
          <div>
            <CustomButton
              class="margin-bottom-30"
              btnName="Search"
              :onClickFunc="userSearch"
            />
          </div>
        </div>
      </div>
    </div>
    <div class="table-wrapper">
      <perfect-scrollbar id="table-scroll">
        <vue-ads-table
          class="my-common-code-table"
          :columns="fields"
          :rows="tableRowData"
          :filter="filter"
          :selectable="selectable"
          @selection-change="selectionChanged"
          export-name="test"
        >
        </vue-ads-table>
      </perfect-scrollbar>
    </div>
    <div class="crud-form">
      <div class="row">
        <div class="col-xl-10 margin-bottom-30">
          <div class="row">
            <div class="col-xl-4 margin-bottom-30">
              <CustomInput
                labelCol="4"
                inputCol="8"
                customClass
                id="userId"
                label="ID"
                v-model="userModel.id"
                :validate="submitted && !$v.userModel.id.required"
              />
            </div>
            <div class="col-xl-4 margin-bottom-30">
              <CustomInput
                labelCol="4"
                inputCol="8"
                id="userName"
                label="Name"
                v-model="userModel.name"
                :validate="submitted && !$v.userModel.name.required"
              />
            </div>
            <div class="col-xl-4 margin-bottom-30 align-left ">
              <div class="row align-left">
                <div class="col-sm-4">
                  <label class="code-name-label roboto-medium">Type</label>
                </div>
                <div class="col-sm-8">
                  <v-select
                    :options="typeList"
                    v-model="userModel.typeName"
                    label="text"
                  />
                  <div
                    class="invalid-feedback d-block"
                    v-if="submitted && !$v.userModel.typeName.required"
                  >
                    Type is required
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-4 margin-bottom-30">
              <CustomInput
                labelCol="4"
                inputCol="8"
                customClass
                id="userPassword"
                label="Password"
                type="password"
                v-model="userModel.password"
                :validate="submitted && !$v.userModel.password.required"
              />
            </div>
            <div class="col-xl-4 margin-bottom-30 align-left ">
              <div class="row align-left">
                <div class="col-sm-4">
                  <label class="code-name-label roboto-medium">Company</label>
                </div>
                <div class="col-sm-8">
                  <v-select
                    :options="companyList"
                    v-model="userModel.companyName"
                    label="text"
                  />
                  <div
                    class="invalid-feedback d-block"
                    v-if="submitted && !$v.userModel.companyName.required"
                  >
                    Company name is required
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-4 margin-bottom-30">
              <div class="row align-left">
                <div class="col-sm-4">
                  <label class="code-name-label roboto-medium">Exp. Date</label>
                </div>
                <div class="col-sm-8">
                  <b-form-input
                    id="txtDateInput"
                    v-model="userModel.expDate"
                    type="text"
                    placeholder="YYYY-MM-DD"
                    autocomplete="off"
                  >
                  </b-form-input>
                  <b-form-datepicker
                    id="datepicker-dateformat2"
                    :date-format-options="{
                      day: '2-digit',
                      month: '2-digit',
                      year: 'numeric'
                    }"
                    button-only
                    v-model="userModel.expDate"
                    :min="minDate"
                  >
                  </b-form-datepicker>
                  <div
                    class="invalid-feedback d-block"
                    v-if="submitted && !$v.userModel.expDate.required"
                  >
                    Exp.Date is required
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-2 align-right">
          <div>
            <CustomButton
              class="margin-bottom-30"
              btnName="Create"
              :onClickFunc="createUser"
            />
          </div>
          <div>
            <CustomButton
              class="margin-bottom-30"
              btnName="Update"
              :onClickFunc="updateUser"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { VueAdsTable } from "vue-ads-table-tree";
import CustomInput from "@/components/elements/CustomInput";
import CustomButton from "@/components/elements/CustomButton";
import api from "@/plugin/common-code-api";
import http from "@/plugin/user-management-api";
import { mapState } from "vuex";
import mixin from "@/mixin/common-mixin.js";
import { TableMethod } from "@/components/elements/TableConfig.js";
import { required } from "vuelidate/lib/validators";
import CustomNotify from "@/components/elements/CustomNotify";

let fields = [
  {
    property: "no",
    title: "No.",
    direction: null
  },
  {
    property: "id",
    title: "ID",
    direction: null,
    collapseIcon: true
  },
  {
    property: "name",
    title: "Name",
    direction: null
  },
  {
    property: "companyName",
    title: "Company",
    direction: null
  },
  {
    property: "regDate",
    title: "Reg. Date",
    direction: null
  },
  {
    property: "expDate",
    title: "Exp. Date",
    direction: null
  },
  {
    property: "typeName",
    title: "Type",
    direction: null
  }
];

export default {
  name: "TableContainerApp",
  mixins: [mixin],
  components: {
    VueAdsTable,
    CustomButton,
    CustomInput,
    CustomNotify
  },
  data() {
    return {
      minDate: new Date(),
      userTypeSelected: "",
      userCompanySelected: "",
      userCompanySearch: "",
      // Data model
      userModel: {
        companyId: "",
        companyName: "",
        expDate: "",
        id: "",
        name: "",
        regDate: "",
        password: "",
        typeName: "",
        typeId: ""
      },
      // Search model
      userSearchModel: {
        companyId: "",
        companyName: "",
        expDate: "",
        id: "",
        name: "",
        regDate: "",
        type: ""
      },
      typeList: [],
      companyList: [],
      fields,
      filter: "",
      tableRowData: [],
      page: 0,
      selectable: true,
      selectedRowIds: [],
      submitted: false,
      noti: "",
      content: "",
      callback: Function,
      successNoti: "success",
      errorNoti: "error"
    };
  },
  // validation obj from vuelidate
  validations: {
    userModel: {
      password: { required },
      id: { required },
      name: { required },
      companyName: { required },
      expDate: { required },
      typeName: { required }
    }
  },
  mounted: function() {
    //load data when mounted component
    this.getCompanyDropdownList();
    this.getUserTypeDropdownList();
    this.getAllUser();
  },
  methods: {
    ...TableMethod,
    ...{
      // Binding data when select
      selectionChanged(selectedRows) {
        // console.log(selectedRows)
        if (selectedRows.length != 0) {
          this.userModel.companyId = selectedRows[0].companyId;
          this.userModel.companyName = selectedRows[0].companyName;
          this.userModel.expDate = selectedRows[0].expDate;
          this.userModel.id = selectedRows[0].id;
          this.userModel.name = selectedRows[0].name;
          this.userModel.regDate = selectedRows[0].regDate;
          this.userModel.typeName = selectedRows[0].typeName;
          this.userModel.typeId = selectedRows[0].typeId;
        } else {
          this.userModel.companyId = "";
          this.userModel.companyName = "";
          this.userModel.expDate = "";
          this.userModel.id = "";
          this.userModel.name = "";
          this.userModel.regDate = "";
          this.userModel.typeName = "";
          this.userModel.typeId = "";
        }
      },
      // Get data for 'Company' dropdown
      getCompanyDropdownList: function() {
        let dataPromise = api.getData("/common-codes/company-codes");
        dataPromise.then(data => {
          this.companyList = data;
        });
      },
      // Get data for 'Type' dropdown
      getUserTypeDropdownList: function() {
        let dataPromise = api.getData("/common-codes/type-codes");
        dataPromise.then(data => {
          this.typeList = data;
        });
      },
      // Get all User
      getAllUser: function() {
        let dataPromise = api.getData("/users");
        dataPromise.then(arr => {
          this.tableRowData = this.clean(arr);
          return this.addNumberingToArr(this.tableRowData);
        });
      },
      // Create a new user
      createUser: function() {
        this.submitted = true;
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        let dataPromise = api.postRequest(
          "/users",
          JSON.stringify(this.userModel)
        );
        dataPromise
          .then(response => {
            if (response.status === 201) {
              this.content = "Create Successful !!!";
              this.showModal(this.successNoti, this.successNoti);
              this.tableRowData.push(response.data);
              return this.addNumberingToArr(this.tableRowData);
            }
          })
          .catch(error => {
            this.content = "Create failed !!!";
            this.showModal(this.errorNoti, this.errorNoti);
            return Promise.reject(error);
          });
      },
      // Update existing user
      updateUser: function() {
        this.submitted = true;
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }
        let dataPromise = api.putRequest(
          `/users/${this.userModel.id}`,
          JSON.stringify(this.userModel)
        );
        dataPromise
          .then(response => {
            if (response.status == 200) {
              this.content = "Update Successful !!!";
              this.showModal(this.successNoti, this.successNoti);
              this.getAllUser();
            }
          })
          .catch(error => {
            this.content = "Update failed !!!";
            this.showModal(this.errorNoti, this.errorNoti);
            return Promise.reject(error);
          });
      },
      // User Search
      userSearch: function() {
        // console.log('Search Model ::: ' + JSON.stringify(this.userSearchModel));
        let companyId = this.userSearchModel.companyName
          ? this.userSearchModel.companyName.id
          : "";
        let config = {
          params: {
            companyId: companyId,
            id: this.userSearchModel.id,
            name: this.userSearchModel.name
          }
        };
        let dataPromise = http.getDataWithParams("/users/search", config);
        dataPromise.then(data => {
          this.tableRowData = this.clean(data);
          return this.addNumberingToArr(this.tableRowData);
        });
      },
      showModal(modalRef, type, callback) {
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
    }
  },
  computed: {
    ...mapState({
      // active props hover button on navbar
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
.b-form-datepicker {
  position: absolute;
  top: 0;
  right: 16px;
  z-index: 100;
}
</style>
