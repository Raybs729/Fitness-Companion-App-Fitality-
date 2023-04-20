<template>
  <div class="nav">
    <div class="title">
      <h1>Fitality</h1>
      <h2> <span> Exercise</span><span class="bg-text">New</span></h2>
    </div>
  
    <!-- <img id="inverted-logo" src="../img/invertedlogo.png" alt="inverted logo"> -->
     <!-- <img id="background-img" src="../img/createExerciseVue.jpeg">  -->
    <div class="input">
      <form @submit.prevent="submitForm">
        <div class="table">
          <div class="info1">
                <div class="information1 ">
                  <div class="box">
                    <label id="exercise-name">Exercise Name:</label>
                  </div>
                  <div>
                    <input v-model="newExercise.exerciseName" required />
                  </div>
                </div>  
            </div>
            <div class="info1">
                <div class="information1 ">
                  <div class="box">
                    <label>Equipment Name:</label>
                  </div>
                  <div>
                    <select v-model="newExercise.equipmentName" required>
                      <option v-for="equipment in equipments" :key="equipment.equipmentId" :value="equipment.equipmentName">{{ equipment.equipmentName }}</option>
                    </select>
                  </div>
                </div>
          </div>
        </div>
        <div class="button">
          <button type="submit">Submit</button>
        </div> 
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
  z-index: 0;
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

/* form {
  display: flex;
  align-items: center;
  justify-content: center;

} */
h2 {
position: relative;
text-transform: uppercase;
font-size: 4rem;
font-weight: 950;
padding-left: 10px;
z-index: 1;
}
.bg-text{
  font-family: 'Poppins', sans-serif;
  position: absolute;
  top: 10%;
  left: 15%;
  color: #76736d;
  transition: all  .4s ease-in-out;
       z-index: 0;
       transform: translate(-50%, -50%);
       font-weight: 700;
       font-size: 3rem;
}

span {
  color: #d8950e;
  z-index: 2;
}

.table {
  width: 100%;
  height: 80%;
  padding-top: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 2;
}

.info1 { 
  height: 100%;
  width: 90%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 10px;
  z-index: 2;
}

/* Exercise Input */

.information1 {
  display: flex;
  left: 50%;
  justify-content: space-between;
  align-items: center;
  border: 1px solid black;
  border-radius: 5px;
  background-color: rgb(255, 255, 255);
  box-shadow: 0 5px  25px rgba(0,0,0,.5);
  transition: all .6s ease-in-out;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-size: 15px;
  width: 100%;
  padding: 15px 25px 15px 25px;
  
}
.nav {
  background-color: #f7f3f0;
  width: 100%;
  height: 100%;
  z-index: -1;
}

.button  {
  display: flex;
  justify-content: center;
}
button {
  width: 200px;
  height: 30px;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #47433a;
  overflow-x: hidden;
  border: 2px solid #333231;
  background: #b4afac;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-size: 15px;
  box-shadow: 0 3px 5px rgba(119, 105, 105, 0.3);
  transition: all .6s ease-in-out;
  text-transform: uppercase;
  
} 
.table input , select {
  padding-left: 5px;
  margin-left: 50px;
  height: 30px;
  width: 180px;
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #a19f99;
  align-items: center;
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
}

/*** PHONE DISPLAY ***/
@media (max-width: 480px) {
 

 select {
  text-transform: uppercase;
 }
}

</style>