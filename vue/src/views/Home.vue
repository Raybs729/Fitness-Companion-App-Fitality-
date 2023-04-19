<template>

  <div class="home" >
    <div>
  </div>
    <button id="start-workout"  v-if="!workoutStarted" @click="startWorkout" class="start-workout-btn">
      Start Workout
    </button>
    <button id="end-workout" v-else @click="endWorkout" class="end-workout-btn">
      End Workout
    </button>
    
    <div v-if="user && workoutStarted">
      <h2>Welcome, {{ this.name }} {{user.authorities[0].name }}!</h2>
      <div>
        <router-link :to = "{name:'UserExercises', params: {userId:user.id}}" tag = button class = "exercise-view-button">
          View Exercises 
        </router-link>
      </div>
        <router-link :to = "{name:'UpcomingGymClassesView'}" tag = button class = "upcoming-gym-classes">
            Upcoming Classes 
        </router-link>
        <div v-if="user.authorities[0].name === 'ROLE_ADMIN'">
          
          <router-link :to = "{name:'MachineMetrics'}" tag = button class = "machine-metrics">
            Machine Metrics
          </router-link>
        </div>
      
    </div>
    
  </div>
</template>

<script>
import { mapState } from 'vuex';
import WorkoutService from '../services/WorkoutService';
import AccountService from '../services/AccountService';

export default {
  name: "home",

  data() {
    return {
      workoutStarted: false,
      latestWorkout: null,
      name: ""
    };
  },
  computed: {
    ...mapState(['user'])
  },
  created() {
    if (this.user.authorities[0].name == 'ROLE_USER') {
      AccountService.getNameByUserId(this.user.id).then(response => {
        this.name = response.data;
      });
    } else {
      this.name = this.user.username;
    }
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

/* Fitality Header */

#fitality-header {

position: absolute;
width: 269px;
height: 57px;
left: 149px;
top: 83px;

font-family: 'Open Sans';
font-style: normal;
font-weight: 400;
font-size: 40px;
line-height: 54px;
color: #000000;

}



#start-workout {

position: absolute;
width: 314px;
height: 79px;
left: 58px;
top: 645px;

font-family: 'Font -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;';
font-style: normal;
font-weight: 400;
font-size: 40px;
line-height: 54px;
color: #FFFFFF;
background:black;

}

#end-workout {


  position: absolute;
width: 314px;
height: 79px;
left: 58px;
top: 645px;

font-family: 'Font -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;';
font-style: normal;
font-weight: 400;
font-size: 40px;
line-height: 54px;
color: #FFFFFF;
background:black;


}

.upcoming-gym-classes {

position: absolute;
width: 314px;
height: 79px;
left: 58px;
top: 345px;

font-family: 'Font -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;';
font-style: normal;
font-weight: 400;
font-size: 35px;
line-height: 54px;
color: #FFFFFF;
background:black;

}


.exercise-view-button {

position: absolute;
width: 314px;
height: 79px;
left: 58px;
top: 445px;

font-family: 'Font -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;';
font-style: normal;
font-weight: 400;
font-size: 35px;
line-height: 54px;
color: #FFFFFF;
background:black;

}

button {
  color: black;

}

</style>

