<template>
  <div>
      <h3>Your Exercises:</h3>
      <form @submit.prevent="submitForm">
        <label> Select A Date: </label>
        <input type="date" v-model="checkIn"/> &nbsp;
        <button type="submit"> Generate Report </button>
      </form>
    <div v-if="exercises.length > 0">
      <ul>
        <li v-for="exercise in exercises" :key="exercise.id">
          <div>Equipment Name: {{ exercise.equipmentName }}</div>
          <div>Exercise Name: {{ exercise.exerciseName }}</div>
          <div v-if="exercise.set > 0">Set: {{ exercise.set }}</div>
          <div v-if="exercise.rep > 0">Rep: {{ exercise.rep }}</div>
          <div v-if="exercise.weightLifted > 0">Weight Lifted: {{ exercise.weightLifted }}</div>
          <div v-if="exercise.singleWorkoutDuration > 0">Single Workout Duration: {{ exercise.singleWorkoutDuration }}</div>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>You don't have any exercises.</p>
    </div>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService";
export default {
  name: "UserExercise",
  props: {
    userId: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      exercises: [],
      checkIn: ""
    };
  },
  created() {
    this.refreshExercises();
  },
  methods: {
    refreshExercises() {
      exerciseService.getExercise(this.userId).then((response) => {
        this.exercises = response.data;
      });
    },
    submitForm() {
      exerciseService.getExercisesByDateAndUserId(this.checkIn, this.userId).then(response => {
        this.exercises = response.data;
      })
    }
  },
};
</script>

<style scoped>

/* Your Exercises */

h3 {
position: relative;
text-transform: uppercase;
font-size: 1.8rem;
font-weight: 950;
padding-left: 45px;
font-style: italic;
z-index: 0;
left: center;
}


label {

    display: flex;
    left: 50%;
    justify-content: space-between;
    align-items: center;
    border: 1px solid black;
    border-radius: 5px;
    background-color: rgb(255, 255, 255);
    box-shadow: 0 5px 25px rgba(0,0,0,.5);
    transition: all .6s ease-in-out;
    font-family: Georgia, 'Times New Roman', Times, serif;
    font-size: 20px;
    width: 100%;
    padding: 15px 25px 15px 25px;

}
.information1 {
  display: flex;
  left: 50%;
  justify-content: space-between;
  align-items: center;
  border: 1px solid black;
  border-radius: 5px;
  background-color: rgb(255, 255, 255);
  box-shadow: 0 5px  25px rgba(0,0,0,.5);
  transition: all .6s ease-in-out;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-size: 20px;
  width: 100%;
  padding: 15px 25px 15px 25px;
}

input {

    padding-left: 5px;
    margin-left: 50px;
    height: 30px;
    width: 180px;
    border-radius: 10px;
    overflow-x: hidden;
    border: 3px solid #a19f99;
    align-items: center;
    background: #FFFFFF;
    box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
    transition: all .6s ease-in-out;
    position: absolute;
    left: 250px;
    top: 100px;
}

button {

  position: absolute;
  top: 160px;
  left: 380px;



}


</style>
