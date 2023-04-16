<template>
  <div>
    <h3>Create a New Gym Class</h3>
    <form @submit.prevent="submitForm">
      <label>Class Name:</label>
      <input v-model="newGymClass.class_name" required />
      <label>Date Start:</label>
      <input type="date" v-model="newGymClass.dateStart" required />
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
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService";

export default {
  name: "CreateGymClass",
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
    };
  },
  methods: {
    formatTime(time) {
      const [hours, minutes, seconds] = time.split(':');
      return `${hours}:${minutes}:${seconds || '00'}`;
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
      });
    },
  },
};
</script>