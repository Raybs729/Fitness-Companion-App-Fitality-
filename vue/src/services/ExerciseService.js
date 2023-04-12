import axios from 'axios';

export default {
  getExercise(userId) {
    return axios.get(`/exercises/users/${userId}`);
  },
};
