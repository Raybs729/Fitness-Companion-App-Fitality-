<template>
  <div class="update">
    <div class="update-title">
      <h3>Your Exercises</h3>
    </div>
      <div class="form">
        <form @submit.prevent="submitForm" class="box">
        <label> Select a Date </label>
        <input type="date" v-model="checkIn"/>
      </form>
      </div>
      
      <button type="submit"> Generate Report </button>
    <div v-if="exercises.length > 0" class="list">
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
.update{
  background-color: #e7e6e9;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.update-title {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  margin-bottom:10px;
  width: 100%;
}
.form{
  display: flex;
  align-items: center;
  background-color: white;
  margin-bottom: 10px;
  width: 95%;
  border-radius: 5px;
}
.box{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  padding: 10px;
  padding-left: 20px;
  border-radius: 5px;
  width: 95%;
}
button{
  background-color: red;
  border-radius: 20px;
  width: 200px;
  padding: 15px;
  border:none;
  color: white;
  font-size: large ;
  margin-bottom: 10px;
}
input{
  width: 60%;
  border-radius: 5px;
  border: 1px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
}
.list{
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
  width: 95%;
  border-radius: 5px;
  margin-bottom:10px;
}
ul{
  list-style-type: none;
  padding: 10px;
  width: 95%;
   
}


</style>
