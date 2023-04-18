<template>
  <div>
    <h3 class="title">Update a Gym Class</h3>
    <form @submit.prevent="submitForm">
      <div class="input">
        <label>Class ID:</label>
        <input v-model="updateGymClass.classId" required />
        <div class="name">
          <label>Class Name:</label>
          <input v-model="updateGymClass.class_name" required />
        </div>
        <div class= "datestart">
          <label>Date Start:</label>
          <input type="date" v-model="updateGymClass.dateStart" required />
        </div>
        <div class="dateend">
          <label>Date End:</label>
          <input type="date" v-model="updateGymClass.dateEnd" required />
        </div>  
        <div class="timestart">
          <label>Time Start:</label>
          <input type="time" v-model="updateGymClass.timeStart" required />
        </div>
        <div class="timeend">
          <label>Time End:</label>
          <input type="time" v-model="updateGymClass.timeEnd" required />
        </div>
        <label>Signed Up:</label>
        <input type="number" v-model="updateGymClass.signedUp" required />

        <button class="submit" type="submit">Submit</button>
    </div>
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
        this.newGymClass = {
          class_id: "",
          class_name: "",
          dateStart: "",
          timeStart: "",
          dateEnd: "",
          timeEnd: "",
          signedUp: "",
        };
        this.fetchAllGymClasses();
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
<style scoped>

.input input {
  padding-left: 5px;
  margin-left: 50px;
  font-family: 'Poppins', sans-serif;
  font-size: 15px;
  margin-bottom: 10px;
  display: fixed;
  justify-content: center;
  flex-basis: 100%;

  /****MAKE round shadow box*/
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
   /***/
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
 
}
.input label {
  flex-basis: 100%;
}
h3.title {
  font-family: 'Poppins', sans-serif;
  position: relative;
  text-transform: uppercase;
  font-size: 1rem;
  font-weight: 700;
  color: #d08412;
  text-align: center;
  
}

.submit {
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
   /***/
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
}
</style>