<template>
  <div class="home">
    <button v-if="!workoutStarted" @click="startWorkout" class="start-workout-btn">
      Start Workout
    </button>
    <button v-else @click="endWorkout" class="end-workout-btn">
      End Workout
    </button>
    <div v-if="user && workoutStarted">
      <h2>Welcome, {{ user.username }} {{user.authorities[0].name }}!</h2>
      <UpcomingGymClasses/>
      <MachineUsageVue v-if="user.authorities[0].name === 'ROLE_ADMIN'"/>
    </div>
    <div>
      <UserExercise/>
      <UserCreateExercise/>
      <button class="user-exercises">
        Exercises
      </button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import UpcomingGymClasses from '../components/UpcomingGymClasses.vue';
import MachineUsageVue from '../components/MachineUsage.vue';
import WorkoutService from '../services/WorkoutService';
import UserExercise from '../components/UserExercise.vue';
import UserCreateExercise from '../components/UserCreateExercise.vue';

export default {
  name: "home",
  components: {
    UpcomingGymClasses,
    MachineUsageVue,
    UserExercise,
    UserCreateExercise
  },
  data() {
    return {
      workoutStarted: false,
    };
  },
  computed: {
    ...mapState(['user'])
  },
  methods: {
    startWorkout() {
      this.workoutStarted = true;
      const currentTimestamp = new Date().toISOString().slice(0, 19).replace('T', ' ');
      const workout = {
        userId: this.user.id,
        timeOfEntry: currentTimestamp,
      };
      WorkoutService.createWorkout(workout)
        .then(response => {
          console.log('Workout created:', response);
        })
        .catch(error => {
          console.error('Error creating workout:', error);
        });
    },
    endWorkout() {
      this.workoutStarted = false;
    },
    refreshExercises() {
      this.$refs.userExercise.refreshExercises();
    },
  },
};
</script>