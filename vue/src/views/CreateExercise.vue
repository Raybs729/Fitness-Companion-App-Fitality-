<template>
  <div>
    <h1>FITALITY</h1>
    <h3>Create a new exercise</h3>
    <img id="inverted-logo" src="../img/invertedlogo.png" alt="inverted logo">
    <img id="background-img" src="../img/createExerciseVue.jpeg">
    <form @submit.prevent="submitForm">
      <label id="exercise-name">Exercise Name:</label>
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

<style scoped>

/* Inverted Logo  */

#inverted-logo {

position: absolute;
width: 97px;
height: 56px;
left: 10px;
top: 28px;

position: absolute;
width: 150px;
height: 113px;
left: 0px;
top: 0px;
background: #000000;

} 

/* Fitality Header */

h1 {

position: absolute;
width: 200px;
height: 50px;
left: 162px;
top: 34px;

font-family:  -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: normal;
font-weight: 400;
font-size: 36px;
line-height: 49px;

color: #FFFFFF;
position: absolute;
width: 430px;
height: 113px;
left: 0px;
top: 0px;
background: #000000;

}



/* Create a new exercise */

h3 {
position: absolute;
width: 379px;
height: 52px;
left: 9px;
top: 85px;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: normal;
font-weight: 400;
font-size: 50px;
line-height: 54px;
color: #000000;
}

/* Exercise Name */

#exercise-name {

position: absolute;
width: 215px;
height: 32px;
left: 64px;
top: 327px;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: normal;
font-weight: 400;
font-size: 24px;
line-height: 29px;
color: #000000;

}

/* Exercise Input */

input {
position: absolute;
width: 292px;
height: 25px;
left: 68px;
top: 366px;
background: #FFFFFF;
}

/* Equipment Name */

label {

position: absolute;
width: 215px;
height: 32px;
left: 64px;
top: 471px;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: normal;
font-weight: 400;
font-size: 24px;
line-height: 29px;
color: #000000;

}


/* Equipment Options */

select {

position: absolute;
width: 292px;
height: 28px;
left: 68px;
top: 506px;

}


/* Add an exercise */

button {

position: absolute;
width: 247px;
height: 52px;
left: 90px;
top: 680px;
background: #000000;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: normal;
font-weight: 400;
font-size: 24px;
line-height: 29px;
color: #FFFFFF;


}


/* Background Img black/white */

#background-img {

position: fixed;
width: 100%;
height: 100%;
left: 0px;
top: 137px;
mix-blend-mode: luminosity;
opacity: 0.5;

}

</style>