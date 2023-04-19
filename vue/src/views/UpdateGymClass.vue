<template>
  <div>
    <h3 class="title">Update a Gym Class</h3>
    <form @submit.prevent="submitForm">
      <div class="input">
          <div class="info1">
            <div class="information1 ">
              <div class="box">
                <label>Class ID:</label>
              </div>
              <div>
                <input v-model="updateGymClass.classId" required />
              </div>
            </div>
          </div>
          <div class="info1">
            <div class="information1 ">
              <div class="box">
                <label>Class Name:</label>
              </div>
              <div>
                <input v-model="updateGymClass.class_name" required />
              </div>
            </div>
          </div>  
          <div class="info1">
            <div class="information1 ">
              <div class="box">
                <label>Date Start:</label>
              </div>
              <div>
                <input type="date" v-model="updateGymClass.dateStart" required />
              </div>
            </div>
          </div>  
          <div class="info1">
            <div class="information1 ">
              <div class="box">
                <label>Time Start:</label>
              </div>
              <div>
                <input type="time" v-model="updateGymClass.timeStart" required />
              </div>
            </div>
          </div>  
          <div class="info1">
            <div class="information1 ">
              <div class="box">
                <label>Time End:</label> 
              </div>
              <div>
                <input type="time" v-model="updateGymClass.timeEnd" required />
              </div>
            </div>
          </div>  
          <div class="info1">
            <div class="information1 ">
              <div class="box">
                <label>Signed Up:</label>
              </div>
              <div>
                <input type="number" v-model="updateGymClass.signedUp" required />
              </div>
            </div>
          </div>

        <!-- <div class="dateend">
          <label>Date End:</label>
          <input type="date" v-model="updateGymClass.dateEnd" required />
        </div>   -->
        <div>
          <button class="submit" type="submit">Submit</button>
        </div>
       
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
    fetchGymClasses() {
      WorkoutService.getAllGymClasses().then((response) => {
        this.allGymClasses = response.data;
      });
    },

    submitForm() {
      const gymClassData = {
        ...this.updateGymClass,
        timeStart: this.formatTime(this.updateGymClass.timeStart),
        timeEnd: this.formatTime(this.updateGymClass.timeEnd),
      };

      WorkoutService.updateGymClass(gymClassData).then(() => {
        this.updateGymClass = {
          classId: "",
          class_name: "",
          dateStart: "",
          timeStart: "",
          dateEnd: "",
          timeEnd: "",
          signedUp: "",
        };
        this.fetchGymClasses();
        this.$emit('update');
      });
    },
  },
  created() {
    this.fetchGymClasses();
  },
};
</script>
<style scoped>

.input input {
  padding-left: 5px;
  margin-left: 50px;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-size: 15px;
  margin-bottom: 10px;
  display: fixed;
  justify-content: center;
  flex-basis: 100%;
  height: 30px;
  width: 180px;
  /****MAKE round shadow box*/
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #a19f99;
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
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
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
/**TABLE **/
.input {
  width: 100%;
  height: 80%;
  padding-top: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
  
}
.info1 { 
  height: 100%;
  width: 90%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  padding-bottom: 10px;
}
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
  font-size: 20px;
  width: 100%;
  padding: 15px 25px 15px 25px;
  
}
</style>