<template>
  <div class="exercise">
    <div class="title">
        <h3>CREATE EXERCISE</h3>
      </div>
    <div class="input">
      <form @submit.prevent="submitForm">
        <div class="section">
          <label id="exercise-name">Exercise Name</label>
          <input v-model="newExercise.exerciseName" required />
        </div>
        <div class="section">
          <label>Equipment Name</label>
          <select v-model="newExercise.equipmentName" required>
            <option v-for="equipment in equipments" :key="equipment.equipmentId" :value="equipment.equipmentName">{{ equipment.equipmentName }}</option>
          </select>
        </div>  
          <button type="submit">Submit</button>
      </form>
    </div>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService";
import equipmentService from "../services/EquipmentService"

export default {
  name: "CreateExercise",
  data() {
    return {
      titleStyles: {
        animation: 'rotate 3s linear infinite',
        transformOrigin: 'center center',
        animationPlayState: 'paused',
      },
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
    startAnimation() {
      this.titleStyles.animationPlayState = 'running';
    },
    stopAnimation() {
      this.titleStyles.animationPlayState = 'paused';
    },
  },
};
</script>

<style scoped>

.exercise {
  height: 100vh;
  background-color: #e7e6e9;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.input{
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
form{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 95%;
}
label {
  display: inline-block;
  margin: 0.5em 0;
}
option {
  margin: 0.5em 0;
}
.section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  margin-bottom: 10px;
  border-radius: 5px;
  width: 100%;
}
.title{
  background-color: white;
  margin-bottom: 10px;
  text-align: center;
  width: 100%;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  color:rgb(37, 34, 34);
}
button{
  background-color: red;
  border-radius: 20px;
  width: 150px;
  padding: 15px;
  border:none;
  color: white;
  font-size: large ;
}
input{
  width: 60%;
  border-radius: 5px;
  border: 1px solid #ccc;
  padding: 10px;
  margin-right:20px;
  box-sizing: border-box;
}
label{
  padding: 20px;
}
select{
  width: 60%;
  border: none;
  padding: 10px;
  margin-right: 20px;
}

</style>