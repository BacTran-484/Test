<template>
  <div id="app">
    <div class="crud-form">
      <div class="row">
        <div class="col-xl-6 margin-bottom-30">
          <CustomInput
            labelCol="2"
            inputCol="10"
            customClass
            id="txtName"
            label="Name"
            v-model="userSelected"
          />
        </div>
        <div class="col-xl-1 margin-bottom-30 "></div>
        <div class="col-xl-4 margin-bottom-30 align-left">
          <CustomButton btnName="Search" :onClickFunc="userSearch" />
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
          :page="page"
          :selectable="selectable"
          @selection-change="selectionChanged"
          export-name="test"
        >
        </vue-ads-table>
      </perfect-scrollbar>
    </div>
  </div>
</template>
<script>
import { VueAdsTable } from "vue-ads-table-tree";
import CustomButton from "@/components/elements/CustomButton";
import { TableMethod } from "@/components/elements/TableConfig.js";
import api from "@/plugin/user-search-api";
import mixin from "@/mixin/common-mixin.js";
import CustomInput from "@/components/elements/CustomInput";

let fields = [
  {
    property: "no",
    title: "No.",
    direction: null,
    filterable: true
  },
  {
    property: "id",
    title: "Code",
    direction: null,
    filterable: true,
    collapseIcon: true
  },
  {
    property: "name",
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
    property: "expDate",
    title: "Exp. Date",
    direction: null,
    filterable: true
  }
];

export default {
  name: "TableContainerApp",
  mixins: [mixin],
  components: {
    VueAdsTable,
    CustomButton,
    CustomInput
  },
  data() {
    return {
      userSelected: "",
      userModel: {
        companyId: "",
        companyName: "",
        expDate: "",
        id: "",
        name: "",
        regDate: "",
        type: ""
      },
      options: [],
      fields,
      tableRowData: [],
      filter: "",
      page: 0,
      selectable: true,
      selectedRowIds: []
    };
  },
  mounted: function() {
    this.getUserSelectData();
    this.getAllUser();
  },
  methods: {
    ...TableMethod,
    selectionChanged(selectedRows) {
      if (selectedRows.length != 0) {
        this.userModel.id = selectedRows[0].id;
        this.userModel.name = selectedRows[0].name;
        this.userModel.expDate = selectedRows[0].expDate;
        this.userModel.regDate = selectedRows[0].regDate;
      } else {
        this.userModel.id = "";
        this.userModel.name = "";
        this.userModel.expDate = "";
        this.userModel.regDate = "";
      }
    },
    getUserSelectData: function() {
      let data = api.getData("/users/dropdown");
      data.then(data => {
        let arr = this.formatSelect(data);
        this.options = arr;
      });
    },
    getAllUser: function() {
      let dataPromise = api.getData("/users");
      dataPromise.then(arr => {
        this.tableRowData = this.clean(arr);
        return this.addNumberingToArr(this.tableRowData);
      });
    },
    // User Search
    userSearch: function() {
      let config = {
        params: {
          companyId: "",
          id: "",
          name: this.userSelected
        }
      };
      let dataPromise = api.getDataWithParams("/users/search", config);
      dataPromise.then(data => {
        this.tableRowData = this.clean(data);
        return this.addNumberingToArr(this.tableRowData);
      });
    }
  }
};
</script>
<style>
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
  background: #edf2f9 0% 0% no-repeat padding-box !important;
  border-right: 4px solid #fff;
  border-bottom: 4px solid #fff;
}

.table.b-table > tbody > .table-active,
.table.b-table > tbody > .table-active > th,
.table.b-table > tbody > .table-active > td {
  background: #dae9fd 0% 0% no-repeat padding-box !important;
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
  font-size: 1.9em;
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
</style>
