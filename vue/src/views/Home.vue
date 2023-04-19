<template>

  <div class="home" >
     <h1 class="fade-in">Fitality</h1>
    <!-- <img id="background-img" src="../img/homepageimg.png">  -->
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

h1 {
  position: absolute;
  top: 15%;
  left: 35%;
  bottom: 1%;
  transform: rotate(320deg);
  font-size: 8rem;
  z-index: -1;
  color: rgba(139, 133, 133, 0.5); 
  text-shadow: 0 0 10px rgba(200, 130, 32, 0.5); 
  animation: rotate 3s linear forwards;
  filter: blur(2px);
  z-index: -4;
}
.fade-in {
  opacity: 2;
  animation: fadeIn 3s ease-in forwards;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
.start-workout-btn {
  position: absolute;
  top:8;

  animation-name: move;
  animation-duration: 2s;
  animation-timing-function: linear;
  animation-iteration-count: initial;
}

@keyframes move {
  0% { top: 0%; }
  50% { top: 0%; }
  100% { top: 45%; }
}

#start-workout {

position: fixed;
align-items: center;
justify-content: center;
text-align: center;
width: 300px;
height: 79px;
left: 15%;
top: 45%;

font-family: 'Font -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;';
font-style: normal;
font-weight: 400;
font-size: 40px;
line-height: 54px;
color: #ec8e13;
background:rgb(105, 104, 97);

box-shadow: 0 3px  15px rgba(0,0,0,.3);
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #433d30;
  align-items: center;
  transition: all .6s ease-in-out;

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
#background-img {

position: fixed;
justify-content: center;
  width: 100%;
  height: 100%;
  filter: blur(5px); 
  z-index: -5;
}


</style>

