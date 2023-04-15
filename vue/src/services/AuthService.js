import axios from "axios";

let currentUser = null;

export default {
  login(user) {
    return axios.post("/login", user).then((response) => {
      currentUser = response.data;
      return response;
    });
  },

  register(user) {
    return axios.post("/register", user).then((response) => {
      currentUser = response.data;
      return response;
    });
  },

  getCurrentUser() {
    return currentUser;
  },

  getAllUsers() {
    return axios.get("/");
  },
};
