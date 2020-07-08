<template>
  <div id="loginPage">
    <div class="sds-title">
      <img
        src="@/assets/images/common/SAMSUNG-SDS_White.png"
        alt="SAMSUNG SDS"
      />
    </div>
    <div class="title-wrap">
      <h1 class="sr-title roboto-bold">SR Allocation<br />Management System</h1>
    </div>
    <div class="login-form">
      <form @submit.prevent="handleSubmit">
        <div
          v-if="alert.message"
          :class="'alert ' + alert.type"
          style="height:20%;"
        >
          {{ alert.message }}
        </div>
        <CustomInput
          txtStyle="background: #EDF2F9; margin-bottom:1.6em"
          labelCol="3"
          inputCol="9"
          id="userID"
          label="ID"
          v-model="id"
        />

        <!-- <label for="id">id</label>
                <input type="text" v-model="id" name="id" class="form-control" :class="{ 'is-invalid': submitted && !id }" />
                <div v-show="submitted && !id" class="invalid-feedback">
                    id is required
                </div> -->
        <CustomInput
          txtStyle="background: #EDF2F9; margin-bottom:1.1em"
          labelCol="3"
          inputCol="9"
          id="userPassword"
          label="Password"
          type="password"
          v-model="password"
        />

        <!-- <label htmlFor="password">Password</label>
                <input type="password" v-model="password" name="password" class="form-control" :class="{ 'is-invalid': submitted && !password }" />
                <div v-show="submitted && !password" class="invalid-feedback">
                    Password is required
                </div> -->

        <b-row class="align-left">
          <b-col :sm="3"> </b-col>
          <b-col :sm="9">
            <b-form-checkbox
              value="accepted"
              unchecked-value="not_accepted"
              style="margin-bottom:1.1em"
              >Remember my ID on this computer</b-form-checkbox
            >
          </b-col>
        </b-row>
        <b-row class="align-left">
          <b-col :sm="3"> </b-col>
          <b-col :sm="9">
            <button class="btn-custom" :disabled="loggingIn">Log in</button>
            <img
              v-show="loggingIn"
              src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA=="
            />
          </b-col>
        </b-row>
        <!-- <div class="form-group">
 
                <button class="btn btn-primary" :disabled="loggingIn">Login</button>
                <img v-show="loggingIn" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
            </div> -->
      </form>
    </div>
  </div>
</template>
<script>
import CustomInput from "@/components/elements/CustomInput";
import CustomButton from "@/components/elements/CustomButton";
export default {
  components: {
    // eslint-disable-next-line
    CustomButton,
    CustomInput
  },
  data() {
    return {
      id: "",
      password: "",
      submitted: false
    };
  },
  computed: {
    loggingIn() {
      return this.$store.state.authentication.status.loggingIn;
    },
    alert() {
      return this.$store.state.alert;
    }
  },
  // watch:{
  //     $route (to, from){
  //         // clear alert on location change
  //         this.$store.dispatch('alert/clear');
  //     }
  // } ,
  created() {
    // reset login status
    this.$store.dispatch("authentication/logout");
  },
  methods: {
    handleSubmit() {
      this.submitted = true;
      const { id, password } = this;
      const { dispatch } = this.$store;
      if (id && password) {
        dispatch("authentication/login", {
          id,
          password
        });
      }
    }
  }
};
</script>

<style scoped>
#loginPage {
  text-align: center;
}

.sds-title,
.sr-title {
  color: #fff;
}

.sr-title {
  padding: 4px 0;
}

.title-wrap {
  margin-bottom: 2.6em;
}

.sds-title img {
  width: 13.2em;
  height: 1.6em;
  margin: auto;
  /* margin-top: 2.9em; */
  margin-top: -7em;
  margin-bottom: 7.8em;
}

.login-form {
  width: 53.5em;
  margin: auto;
  background: #ffffff 0% 0% no-repeat padding-box;
  border: 1px solid #034ea2;
  border-radius: 23px;
  padding: 5.2em 3em;
}

.login-form form {
  font-size: 1.8em;
}
.btn-custom {
  border-radius: 5px;
  background-color: #034ea2;
  color: white;
  font-family: Roboto;
  font-size: 1em;
  padding: 0.525em 1.65em;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: 1.23;
  letter-spacing: normal;
  text-align: center;
  border-width: 1px;
  border-style: solid;
  border-color: #0000001a;
}
.btn-custom:hover {
  transition: 0.3s;
  background: #0060cc;
}
.btn-custom:active {
  background: #033975;
}
</style>
