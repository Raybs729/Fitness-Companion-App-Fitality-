<template>
   
  <div class="home" >
    <div>
    
  </div>
    <button v-if="!workoutStarted" @click="startWorkout" class="start-workout-btn">
      Start Workout
    </button>
    <button v-else @click="endWorkout" class="end-workout-btn">
      End Workout
    </button>
    <div v-if="user && workoutStarted">
      <div>
      <router-link :to = "{name:'UserExercises', params: {userId:user.id}}" tag = button class = "exercise-view-button">
        View Exercises 
      </router-link>
    </div>
      <h2>Welcome, {{ user.username }} {{user.authorities[0].name }}!</h2>
      <router-link :to = "{name:'UpcomingGymClassesView'}" tag = button class = "upcoming-gym-classes">
        Upcoming Classes 
      </router-link>
      <MachineUsageVue v-if="user.authorities[0].name === 'ROLE_ADMIN'"/>
    </div>
    
  </div>
</template>

<script>
import { mapState } from 'vuex';
import MachineUsageVue from '../components/MachineUsage.vue';
import WorkoutService from '../services/WorkoutService';

export default {
  name: "home",
  components: {
    MachineUsageVue,
   
  },
  data() {
    return {
      workoutStarted: false,
      latestWorkout: null
    };
  },
  computed: {
    ...mapState(['user'])
  },
  methods: {
    async startWorkout() {
      this.workoutStarted = true;
      const currentTimestamp = new Date().toISOString().slice(0, 19).replace('T', ' ');
      const workout = {
        userId: this.user.id,
        timeOfEntry: currentTimestamp,
      };
      try {
        await WorkoutService.createWorkout(workout);
        console.log('Workout created');

        // Fetch the latest workout
        const response = await WorkoutService.getLatestWorkoutByUser(this.user.id);
        this.latestWorkout = response.data;
        console.log('Latest workout:', this.latestWorkout);
      } catch (error) {
        console.error('Error:', error);
      }
    },
    async endWorkout() {
      this.workoutStarted = false;
      
      if (this.latestWorkout) {
        const currentTime = new Date();
        const startTime = new Date(this.latestWorkout.timeOfEntry);
        const durationMs = currentTime - startTime;

        const durationHours = Math.floor(durationMs / (1000 * 60 * 60));
        const durationMinutes = Math.floor((durationMs % (1000 * 60 * 60)) / (1000 * 60));
        const durationSeconds = Math.floor((durationMs % (1000 * 60)) / 1000);

        const duration = `${durationHours.toString().padStart(2, '0')}:${durationMinutes.toString().padStart(2, '0')}:${durationSeconds.toString().padStart(2, '0')}`;

        const currentDate = currentTime.toISOString().slice(0, 10);
        
        const workoutTimeData = {
          workoutId: this.latestWorkout.workoutId,
          date: currentDate,
          duration: duration,
        };

        try {
          await WorkoutService.createWorkoutTime(workoutTimeData);
          console.log('Workout time created');
        } catch (error) {
          console.error('Error creating workout time:', error);
        }
      }
    },
    refreshExercises() {
      this.$refs.userExercise.refreshExercises();
    },
  },
};
</script>
<style scoped>

.home{
 top : auto;
 bottom: 0;
}
</style>