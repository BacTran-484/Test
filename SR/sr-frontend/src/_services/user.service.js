import http from "@/plugin/http-common";

export const userService = {
  login,
  logout
};

function login(id, password) {
  return http
    .post(
      process.env.VUE_APP_BACKEND_PATH + "/login",
      JSON.stringify({ id, password })
    )
    .then(response => {
      return response.data;
    })
    .then(user => {
      // login successful if there's a jwt token in the response
      if (user.token) {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem("user", JSON.stringify(user));
      }
      return user;
    })
    .catch(error => {
      // server reject
      // location.reload(true)
      logout();
      if (error.response.status === 403 || error.response.status === 401) {
        return Promise.reject("Sorry, your ID or password is incorrect.");
      }
    });
}

function logout() {
  // remove user from local storage to log user out
  localStorage.removeItem("user");
}
