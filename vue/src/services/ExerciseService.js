import axios from 'axios';

export default {
  getExercises() {
    return axios.get('/exercises');
  },
  getExercise(userId) {
    return axios.get(`/exercises/users/${userId}`);
  },
  createExerciseInfo(data) {
    return axios.post("exercises/info", data);
  }
};
