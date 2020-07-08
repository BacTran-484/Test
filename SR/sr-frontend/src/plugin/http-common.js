import axios from "axios";

let config = {
  baseURL: process.env.VUE_APP_BACKEND_PATH,
  headers: {
    "Content-type": "application/json",
    "Access-Control-Allow-Origin": process.env.VUE_APP_BACKEND
  }
};
let http = axios.create(config);

// Add a response interceptor
http.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    return Promise.reject(error);
  }
);

export default http;
