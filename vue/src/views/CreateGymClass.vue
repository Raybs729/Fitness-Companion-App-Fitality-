<template>
  <div>
    <h3>Create a New Gym Class</h3>
    <form @submit.prevent="submitForm">
      <label id="class-name">Class Name:</label>
      <input id="class-input" v-model="newGymClass.class_name" required />
      <label id="date-start">Date Start:</label>
      <input id="date-input" type="date" v-model="newGymClass.dateStart" required />
      <label>Date End:</label>
      <input type="date" v-model="newGymClass.dateEnd" required />
      <label>Time Start:</label>
      <input type="time" v-model="newGymClass.timeStart" required />
      <label>Time End:</label>
      <input type="time" v-model="newGymClass.timeEnd" required />
      <label>Signed Up:</label>
      <input type="number" v-model="newGymClass.signedUp" required />
      <button type="submit">Submit</button>
    </form>
    <button v-if="!showUpdateForm" @click="toggleUpdateForm">Update Gym Class</button>
    <button v-else @click="toggleUpdateForm">Close Update Gym Class</button>
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
        this.$router.push({ name: "home" });
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




/* Create a new gym class */

h3 {

position: absolute;
width: 393px;
height: 114px;
left: 5px;
top: 15px;



font-family: Font -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
font-style: normal;
font-weight: 400;
font-size: 27px;
line-height: 54px;
color: #000000;

}

/* Class Name */

#class-name {

position: absolute;
width: 195px;
height: 32px;
left: 24px;
top: 195px;

font-family: 'Open Sans';
font-style: normal;
font-weight: 400;
font-size: 20px;
line-height: 27px;
color: #000000;

}

/* Class Input */

#class-input {


position: absolute;
width: 386px;
height: 50px;
left: 17px;
top: 219px;
background: #FFFFFF;

}

/* Date Start */

#date-start {


}

/* Date Input */

#date-input {


}

/* Date End */

#date-end {
  
}



</style>