import axios from 'axios';

export default {
  getWorkoutByUserId(userId) {
    return axios.get(`/workouts/getuserworkout/${userId}`);
  },
  getUpcomingGymClass(){
    return axios.get('/workouts/gymclass');
  },
  createGymClass(gymClass){
    return axios.post('/workouts/createclass', gymClass);
  },
  updateGymClass(gymClass){
    return axios.put('/workouts/gymclass/update', gymClass);
  },
  getAllGymClasses(){
    return axios.get('/workouts/gymclassesall');
  },
  createWorkout(workout) {
    return axios.post('/workouts/newworkout', workout);
  },
  createWorkoutTime(workoutTime) {
    return axios.post('/workouts/time', workoutTime);
  },
  getLatestWorkoutByUser(userId) {
    return axios.get(`/workouts/getuserlatestworkout/${userId}`);
  }

};
