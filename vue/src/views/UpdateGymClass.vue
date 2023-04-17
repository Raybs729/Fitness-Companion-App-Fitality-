<template>
  <div>
    <h3>Update a Gym Class</h3>
    <form @submit.prevent="submitForm">
      <label>Class ID:</label>
      <input v-model="updateGymClass.classId" required />
      <label>Class Name:</label>
      <input v-model="updateGymClass.class_name" required />
      <label>Date Start:</label>
      <input type="date" v-model="updateGymClass.dateStart" required />
      <label>Date End:</label>
      <input type="date" v-model="updateGymClass.dateEnd" required />
      <label>Time Start:</label>
      <input type="time" v-model="updateGymClass.timeStart" required />
      <label>Time End:</label>
      <input type="time" v-model="updateGymClass.timeEnd" required />
      <label>Signed Up:</label>
      <input type="number" v-model="updateGymClass.signedUp" required />
      <button type="submit">Submit</button>
    </form>
    <h3>All Gym Classes</h3>
    <table>
      <thead>
        <tr>
          <th>Class ID</th>
          <th>Class Name</th>
          <th>Date Start</th>
          <th>Time Start</th>
          <th>Date End</th>
          <th>Time End</th>
          <th>Signed Up</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="gymClass in allGymClasses" :key="gymClass.classId">
          <td>{{ gymClass.classId }}</td>
          <td>{{ gymClass.class_name }}</td>
          <td>{{ gymClass.dateStart }}</td>
          <td>{{ gymClass.timeStart }}</td>
          <td>{{ gymClass.dateEnd }}</td>
          <td>{{ gymClass.timeEnd }}</td>
          <td>{{ gymClass.signedUp }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService";

export default {
  name: "UpdateGymClass",
  data() {
    return {
      updateGymClass: {
        classId: "",
        class_name: "",
        dateStart: "",
        timeStart: "",
        dateEnd: "",
        timeEnd: "",
        signedUp: "",
      },
      allGymClasses: [],
    };
  },
  methods: {
    formatTime(time) {
      const [hours, minutes, seconds] = time.split(':');
      return `${hours}:${minutes}:${seconds || '00'}`;
    },

    submitForm() {
      const gymClassData = {
        ...this.updateGymClass,
        timeStart: this.formatTime(this.updateGymClass.timeStart),
        timeEnd: this.formatTime(this.updateGymClass.timeEnd),
      };

      WorkoutService.updateGymClass(gymClassData).then(() => {
        this.$router.push({ name: "home" });
        this.newGymClass = {
          class_id: "",
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
  created() {
    WorkoutService.getAllGymClasses().then((response) => {
      this.allGymClasses = response.data;
    });
  },
};
</script>