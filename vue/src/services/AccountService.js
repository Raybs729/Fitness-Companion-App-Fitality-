import axios from "axios";

export default {
  getAccount(userId) {
    return axios.get(`/accounts/${userId}`);
  },
  updateProfile(userId, data) {
    return axios.put(`/accounts/${userId}`, data);
  },
  getNameByUserId(userId) {
    return axios.get(`/accounts/name/${userId}`);
  }
};
