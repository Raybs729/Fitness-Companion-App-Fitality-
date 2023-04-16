import axios from 'axios';

export default {
  getWorkoutByUserId(userId) {
    return axios.get(`/workouts/getUserWorkout/${userId}`);
  },
  getUpcomingGymClass(){
    return axios.get('/workouts/gymclass');
  },
  createGymClass(gymClass){
    return axios.post('/workouts/createClass', gymClass);
  }
};
