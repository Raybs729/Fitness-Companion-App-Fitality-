<template>
  <div>
    <h3>Add Exercise Info</h3>
    <form @submit.prevent="submitForm">
      <label id="workout-id">Workout ID:</label>
      <select id="option1" v-model.number="exerciseInfo.workoutId" required>
        <option >{{ latestWorkout }}</option>
      </select>
      <label id="equipment-name">Equipment Name:</label>
      <select id="option2" v-model="exerciseInfo.equipmentName" required>
        <option v-for="equipment in equipments" :key="equipment.equipmentId" :value="equipment.equipmentName">{{ equipment.equipmentName }}</option>
      </select> 
      <label id="exercise-name">Exercise Name:</label>
      <select id="option3" v-model="exerciseInfo.exerciseName" required>
        <option v-for="exercise in exercises" :key="exercise.exerciseId" :value="exercise.exerciseName">{{ exercise.exerciseName }}</option>
      </select>
      <label id="set">Set:</label>
      <input id="input1" v-model.number="exerciseInfo.set" />
      <label id="rep">Rep:</label>
      <input id="input2" v-model.number="exerciseInfo.rep" />
      <label id="weight-lifted">Weight Lifted:</label>
      <input id="input3" v-model.number="exerciseInfo.weightLifted" />
      <label id="single-workout-duration">Single Workout Duration:</label>
      <input id="input4" v-model="exerciseInfo.singleWorkoutDuration" />
      <label id="equipment-usage-date-time">Equipment Usage Date Time:</label>
      <input id="input5" type="datetime-local" v-model="exerciseInfo.equipmentUsageDateTime" required/>
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

<style scoped>

/* Add Exercise Info */

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

#workout-id {

position: absolute;
top: 700px;
left: 10px;

}

#option1 {
position: absolute;
top: 700px;
left: 95px;

}

#equipment-name {

  position:absolute;
  top: 730px;
  left: 10px;
}

#option2 {

  position: absolute;
  top:730px;
  left: 130px;
}

#exercise-name {

  position: absolute;
  top:760px;
  left: 10px;

}

#option3 {

  position: absolute;
  top:760px;
  left: 115px;

}

#set {

  position: absolute;
  top:790px;
  left: 10px;
}

#input1 {

  position: absolute;
  top:790px;
  left: 40px;
}

#rep {
  position: absolute;
  top:820px;
  left: 10px;
}

#input2 {

  position: absolute;
  top:820px;
  left: 40px;
}

#weight-lifted {

  position: absolute;
  top:700px;
  left: 275px;
}

#input3 {
  position: absolute;
  top:725px;
  left: 275px;

}

#single-workout-duration {

  position: absolute;
  top:750px;
  left: 275px;
}

#input4 {

  position: absolute;
  top:775px;
  left: 275px;
}



#equipment-usage-date-time {

  position: absolute;
  top:800px;
  left: 275px;
}

#input5 {
  position: absolute;
  top:830px;
  left: 275px;
}


/* Submit */

button {

  position: absolute;
  top: 855px;
  left: 230px;
}




</style>