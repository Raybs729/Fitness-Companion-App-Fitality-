<template>
  <div>
    <h3>Add Exercise Info</h3>
    <form @submit.prevent="submitForm">
      <label>Workout ID:</label>
      <select v-model.number="exerciseInfo.workoutId" required>
        <option >{{ latestWorkout }}</option>
      </select>
      <label>Equipment Name:</label>
      <select v-model="exerciseInfo.equipmentName" required>
        <option v-for="equipment in equipments" :key="equipment.equipmentId" :value="equipment.equipmentName">{{ equipment.equipmentName }}</option>
      </select>
      <label>Exercise Name:</label>
      <select v-model="exerciseInfo.exerciseName" required>
        <option v-for="exercise in exercises" :key="exercise.exerciseId" :value="exercise.exerciseName">{{ exercise.exerciseName }}</option>
      </select>
      <label>Set:</label>
      <input v-model.number="exerciseInfo.set" />
      <label>Rep:</label>
      <input v-model.number="exerciseInfo.rep" />
      <label>Weight Lifted:</label>
      <input v-model.number="exerciseInfo.weightLifted" />
      <label>Single Workout Duration:</label>
      <input v-model="exerciseInfo.singleWorkoutDuration" />
      <label>Equipment Usage Date Time:</label>
      <input type="datetime-local" v-model="exerciseInfo.equipmentUsageDateTime" required/>
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
        this.$emit("exercise-created");
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