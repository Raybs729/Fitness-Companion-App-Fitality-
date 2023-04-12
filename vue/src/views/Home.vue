<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div v-if="user">
      <h2>Welcome, {{ user.username }}!</h2>
      <div v-if="exercises.length > 0">
        <h3>Your Exercises:</h3>
        <ul>
          <li v-for="exercise in exercises" :key="exercise.id">
            <div>Equipment Name: {{ exercise.equipmentName }}</div>
            <div>Exercise Name: {{ exercise.exerciseName }}</div>
            <div>Set: {{ exercise.set }}</div>
            <div>Rep: {{ exercise.rep }}</div>
            <div>Weight Lifted: {{ exercise.weightLifted }}</div>
            <div>Single Workout Duration: {{ exercise.singleWorkoutDuration }}</div>
            <div>Equipment Usage Date Time: {{ exercise.equipmentUsageDateTime }}</div>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>You don't have any exercises yet.</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import exerciseService from "../services/ExerciseService";

export default {
  name: "home",
  data() {
    return {
      exercises: [],
    };
  },
  computed: {
    ...mapState(['user'])
  },
  created() {
    if (this.user && this.user.id) {
      exerciseService.getExercise(this.user.id).then((response) => {
        this.exercises = response.data;
      });
    }
  },
};
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f8f9fa;
  font-family: Arial, sans-serif;
}

.home h1 {
  font-size: 2rem;
  font-weight: bold;
  color: #212529;
  margin-bottom: 1rem;
}

.home p {
  font-size: 1.125rem;
  color: #495057;
}

.home ul {
  list-style-type: none;
  padding: 0;
}

.home li {
  font-size: 1rem;
  color: #495057;
  margin-bottom: 0.5rem;
}
</style>