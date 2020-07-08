import http from "./http-common";
import router from "@/router/router";

export default {
  getData: path => {
    //TODO: code duplicate
    // need to move to common header
    let user = JSON.parse(localStorage.getItem("user"));
    let token = user && user.token ? user.token : "";
    http.defaults.headers["Authorization"] = "Bearer " + token;
    return http
      .get(path)
      .then(response => response.data)
      .catch(error => {
        if (error.response.status === 401) {
          router.push("/login");
        }
        return Promise.reject(error);
      });
  },
  postRequest: (path, payload) => {
    //TODO: code duplicate
    // need to move to common header
    let user = JSON.parse(localStorage.getItem("user"));
    let token = user && user.token ? user.token : "";
    http.defaults.headers["Authorization"] = "Bearer " + token;
    return http.post(path, payload).catch(error => {
      if (error.response.status === 401) {
        router.push("/login");
      }
      return Promise.reject(error);
    });
  },
  putRequest: (path, payload) => {
    //TODO: code duplicate
    // need to move to common header
    let user = JSON.parse(localStorage.getItem("user"));
    let token = user && user.token ? user.token : "";
    http.defaults.headers["Authorization"] = "Bearer " + token;
    return http.put(path, payload).catch(error => {
      if (error.response.status === 401) {
        router.push("/login");
      }
      return Promise.reject(error);
    });
  },
  deleteRequest: path => {
    //TODO: code duplicate
    // need to move to common header
    let user = JSON.parse(localStorage.getItem("user"));
    let token = user && user.token ? user.token : "";
    http.defaults.headers["Authorization"] = "Bearer " + token;
    return http.delete(path).catch(error => {
      if (error.response.status === 401) {
        router.push("/login");
      }
      return Promise.reject(error);
    });
  }
};
