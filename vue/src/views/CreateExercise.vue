<template>
  <div class="nav">
    <div class="title">
      <h1>Fitality</h1>
      <h2> <span> Exercise</span><span class="bg-text">New</span></h2>
    </div>
  
    <!-- <img id="inverted-logo" src="../img/invertedlogo.png" alt="inverted logo"> -->
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

/* Inverted Logo  */

#inverted-logo {
position: absolute;
left: 10px;
top: 28px;
z-index: -2;
width: 20%;
height: 10%;
background: #ffffff;

} 

/* Fitality Header */

.title {
  text-align: center;
  font-family: 'Poppins', serif;
}

h1 {
  position: absolute;
  top: -1%;
  left: 5%;
  
  transform: translate(50%, -50%);
  font-size: 7rem;
  z-index: -1;
  color: rgba(255, 255, 255, 0.5); 
  text-shadow: 0 0 10px rgba(0, 0, 0, 0.5); 
  animation: rotate 3s linear forwards;
}

@keyframes rotate {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(320deg);
  }
}

form {
  display: flex;
  align-items: center;
  justify-content: center;

}
h2 {
position: relative;
text-transform: uppercase;
font-size: 4rem;
font-weight: 950;
padding-left: 10px;
}
.bg-text{
  font-family: 'Poppins', sans-serif;
  position: absolute;
  top: 10%;
  left: 15%;
  color: #76736d;
  transition: all  .4s ease-in-out;
       z-index: -1;
       transform: translate(-50%, -50%);
       font-weight: 700;
       font-size: 3rem;
}

span {
  color: #c9952c;
}

/* Exercise Name */

#exercise-name {

position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 350px;
height: 32px;

top: 35%;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: 'Poppins', sans-serif;
font-weight: 600;
font-size: 24px;
line-height: 29px;
color: #c9952c;


}

/* Exercise Input */

input {
position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 260px;
height: 40px;
top: 40%;

background: #FFFFFF;
box-shadow: 0 3px  15px rgba(0,0,0,.3);
border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
  transition: all .6s ease-in-out;
}

/* Equipment Name */

label {

  position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 260px;
height: 40px;
top: 50%;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: 'Poppins', serif;
font-weight: 600;
font-size: 24px;
line-height: 29px;
color: #ba9330;

}


/* Equipment Options */

select {

 position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 260px;
height: 45px;
font-size: 1rem;
text-transform: uppercase;
top: 55%;


box-shadow: 0 3px  15px rgba(0,0,0,.3);

border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
  transition: all .6s ease-in-out;
}


/* Add an exercise */

button {
position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 247px;
height: 52px;

top: 70%;
background: #986f1e;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style:  'Open Sans';
font-weight: 400;
font-size: 24px;
line-height: 29px;
color: #0a0a09;
box-shadow: 0 3px  15px rgba(0,0,0,.3);
border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #593f0d;
  align-items: center;
  transition: all .6s ease-in-out;

}


/* Background Img black/white */

#background-img {

position: absolute;
  width: 100%;
  height: 90%;
  filter: blur(15px); 
  z-index: -5;

}

@media (max-width: 414px) {
/* Exercise Name */

#exercise-name {

position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 350px;
height: 32px;

top: 35%;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: 'Poppins', sans-serif;
font-weight: 600;
font-size: 24px;
line-height: 29px;
color: #c9952c;


}

/* Exercise Input */

input {
position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 260px;
height: 40px;
top: 40%;

background: #FFFFFF;
box-shadow: 0 3px  15px rgba(0,0,0,.3);
border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
  transition: all .6s ease-in-out;
}

/* Equipment Name */

label {

  position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 260px;
height: 40px;
top: 50%;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: 'Poppins', serif;
font-weight: 600;
font-size: 24px;
line-height: 29px;
color: #ba9330;

}


/* Equipment Options */

select {

 position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 260px;
height: 45px;
font-size: 1rem;
text-transform: uppercase;
top: 55%;


box-shadow: 0 3px  15px rgba(0,0,0,.3);

border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
  transition: all .6s ease-in-out;
}


/* Add an exercise */

button {
position: fixed;
display: flex;
justify-content: center;
align-items: center;
text-align: center;
left: center;
width: 150px;
height: 52px;

top: 70%;
background: #986f1e;

font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style:  'Open Sans';
font-weight: 400;
font-size: 24px;
line-height: 29px;
color: #0a0a09;
box-shadow: 0 3px  15px rgba(0,0,0,.3);
border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #593f0d;
  align-items: center;
  transition: all .6s ease-in-out;

}

}

</style>