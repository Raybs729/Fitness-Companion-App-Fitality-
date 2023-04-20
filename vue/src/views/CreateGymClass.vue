<template>
  <div class="full">
    <div class="title">
      <h3>Create a New Gym Class</h3>
    </div>
    
      <form @submit.prevent="submitForm">
        <div class="table">
          <div class="box">
            <label id="class-name">Class Name:</label>
            <input id="class-input" v-model="newGymClass.class_name" required />
          </div>
          <div class="box">
            <label id="date-start">Date Start:</label>
            <input id="date-input" type="date" v-model="newGymClass.dateStart" required />
          </div>
          <div class="box">
            <label>Date End:</label>
            <input type="date" v-model="newGymClass.dateEnd" required />
          </div>
          <div class="box" >
            <label>Time Start:</label> 
            <input type="time" v-model="newGymClass.timeStart" required />
          </div>
          <div class="box">
            <label>Time End:</label>
            <input type="time" v-model="newGymClass.timeEnd" required />
          </div>
          <div class="box">
            <label>Signed Up:</label>
            <input type="number" v-model="newGymClass.signedUp" required />
          </div>
          <div class="buttons">
            <button class="button1" type="submit">Submit</button>
            <button class="button2" type="button" v-if="!showUpdateForm" @click="toggleUpdateForm">Update Gym Class</button>
            <button class="button2" type="button" v-else @click="toggleUpdateForm">Close Update </button>
          </div>
          
        </div>
      </form>
   
    <UpdateGymClass v-if="showUpdateForm" @submit="toggleUpdateForm" />
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService";
import UpdateGymClass from "./UpdateGymClass.vue";
export default {
  name: "CreateGymClass",
  components: {
    UpdateGymClass,
   
  },
  data() {
    return {
      newGymClass: {
        class_name: "",
        dateStart: "",
        timeStart: "",
        dateEnd: "",
        timeEnd: "",
        signedUp: "",
      },
      showUpdateForm: false,
    };
  },
  methods: {
    formatTime(time) {
      const [hours, minutes, seconds] = time.split(':');
      return `${hours}:${minutes}:${seconds || '00'}`;
    },
    toggleUpdateForm() {
      this.showUpdateForm = !this.showUpdateForm;
    },
    
    submitForm() {
      const gymClassData = {
        ...this.newGymClass,
        timeStart: this.formatTime(this.newGymClass.timeStart),
        timeEnd: this.formatTime(this.newGymClass.timeEnd),
      };
      
      WorkoutService.createGymClass(gymClassData).then(() => {
        this.newGymClass = {
          class_name: "",
          dateStart: "",
          timeStart: "",
          dateEnd: "",
          timeEnd: "",
          signedUp: "",
        };
        this.$emit('update');
      });
    },
  },
};
</script>

<style scoped>
.full{
  position:absolute;
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #e7e6e9;
  align-items: center;
}
.title{
  display: flex;
  justify-content: center;
  background-color: white;
  margin-bottom: 10px;
  text-align: center;
  width: 100%;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  color:rgb(37, 34, 34);
}
form{
  width: 95%;
  border-radius: 5px;
}
.table{
  width: 100%;
}
.box{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  margin-bottom: 10px;
  padding: 10px;
  padding-left: 20px;
  border-radius: 5px;
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
  box-sizing: border-box;
}
.button2{
  width: 200px;
}
.buttons{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}


</style>