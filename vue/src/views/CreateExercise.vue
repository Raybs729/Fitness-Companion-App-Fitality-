<template>
  <div>
    <h3>Create a New Exercise</h3>
    <form @submit.prevent="submitForm">
      <label>Exercise Name:</label>
      <input v-model="newExercise.exerciseName" required />
      <label>Equipment Name:</label>
      <select v-model="newExercise.equipmentName" required>
        <option v-for="equipment in equipments" :key="equipment.equipmentId" :value="equipment.equipmentName">{{ equipment.equipmentName }}</option>
      </select>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService";
import equipmentService from "../services/EquipmentService"

export default {
  name: "CreateExercise",
  data() {
    return {
      newExercise: {
        exerciseName: "",
        equipmentName: "",
      },
      equipments: [],
    };
  },

  mounted() {
    equipmentService.getEquipment().then((response) => {
      this.equipments = response.data;
    });
  },

  methods: {
    submitForm() {
      exerciseService.makeNewExercise(this.newExercise).then(() => {
        this.$router.push({ name: "home" });
        this.newExercise = {
          exerciseName: "",
          equipmentName: "",
        };
      });
    },
  },
};
</script>