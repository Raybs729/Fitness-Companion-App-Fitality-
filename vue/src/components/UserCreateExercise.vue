<template>
  <div class="update">
    <div class="update-title">
      <h3>Add Exercise Info</h3>
    </div>
    
    <form @submit.prevent="submitForm">
      <div class="upper-box">
      <div class="box">
        <label id="workout-id">Workout ID:</label>
        <select id="option1" v-model.number="exerciseInfo.workoutId" required>
          <option >{{ latestWorkout }}</option>
        </select>
      </div>
      <div class="box">
        <label id="equipment-name">Equipment Name:</label>
        <select id="option2" v-model="exerciseInfo.equipmentName" required>
          <option v-for="equipment in equipments" :key="equipment.equipmentId" :value="equipment.equipmentName">{{ equipment.equipmentName.toUpperCase() }}</option>
        </select>
      </div>
      <div class="box">
        <label id="exercise-name">Exercise Name:</label>
        <select id="option3" v-model="exerciseInfo.exerciseName" required>
          <option v-for="exercise in exercises" :key="exercise.exerciseId" :value="exercise.exerciseName">{{ exercise.exerciseName.toUpperCase() }}</option>
        </select>
      </div>
      <div class="box">
        <label id="set">Set:</label>
        <input id="input1" v-model.number="exerciseInfo.set" />
      </div>
      <div class="box">
        <label id="rep">Rep:</label>
        <input id="input2" v-model.number="exerciseInfo.rep" />
      </div>
      <div class="box">
        <label id="weight-lifted">Weight Lifted:</label>
        <input id="input3" v-model.number="exerciseInfo.weightLifted" />
      </div>
      <div class="box">
        <label id="single-workout-duration">Single Workout Duration:</label>
        <input id="input4" v-model="exerciseInfo.singleWorkoutDuration" />
      </div>
      <div class="box">
        <label id="equipment-usage-date-time">Equipment Usage Date Time:</label>
        <input id="input5" type="datetime-local" v-model="exerciseInfo.equipmentUsageDateTime" required/>
      </div>
      </div>
        <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService";
import equipmentService from "../services/EquipmentService"
import workoutService from "../services/WorkoutService";

export default {
  name: "UserCreateExercise",
  props: {
    userId: {
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      exerciseInfo: {
        userId: this.userId,
        workoutId: null,
        equipmentName: "",
        exerciseName: "",
        set: null,
        rep: null,
        weightLifted: null,
        singleWorkoutDuration: "",
        equipmentUsageDateTime: "",
      },
      exercises: [],
      equipments: [],
      workouts: [],
      latestWorkout: 0
    };
  },

  mounted() {
    exerciseService.getExercises().then((response) => {
      this.exercises = response.data;
    });
    equipmentService.getEquipment().then((response) => {
      this.equipments = response.data;
    });
    workoutService.getWorkoutByUserId(this.userId).then((response) => {
      this.workouts = response.data;
      this.findLatest();
    });
  },

  methods: {
    submitForm() {
      exerciseService.createExerciseInfo(this.exerciseInfo).then(() => {
        this.exerciseInfo = {
          userId: this.userId,
          workoutId: null,
          equipmentName: "",
          exerciseName: "",
          set: null,
          rep: null,
          weightLifted: null,
          singleWorkoutDuration: "",
          equipmentUsageDateTime: "",
        };
      });
    },
    findLatest() {
      let len = this.workouts.length;
      for (let i = 0; i < len; i++) {
        if (this.workouts[i].workoutId > this.latestWorkout) {
          this.latestWorkout = this.workouts[i].workoutId;
        }
      }
    }
  },
};
</script>

<style scoped>
.update{
  background-color: #e7e6e9;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-bottom:90px;
}
.update-title {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  margin-bottom:10px;
  width: 100%;
}

form{
  width:95%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.box{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  padding: 10px 0px;
  
  border-radius: 5px;
  width: 100%;
  margin-bottom: 10px;
}
label{
  padding-left: 20px;
}
input{
  width: 60%;
  border-radius: 5px;
  border: 1px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
  margin-right: 20px
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
select{
  width: 60%;
  border: none;
  padding: 10px;
  margin-right: 20px
}
.upper-box{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

</style>