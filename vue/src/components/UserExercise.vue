<template>
  <div>
    <div v-if="exercises.length > 0">
      <h3>Your Exercises:</h3>
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
      <p>You don't have any exercises yet.</p>
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
  },
};
</script>