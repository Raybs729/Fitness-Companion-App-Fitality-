<template>
  <div class="update">
    <div class="update-title">
      <h3 class="title">Update a Gym Class</h3>
    </div>
    <form @submit.prevent="submitForm">
      <div class="input">
        <div class="box">
          <label>Class ID:</label>
          <input v-model="updateGymClass.classId" required />
        </div>
        <div class="box">
          <label>Class Name:</label>
          <input v-model="updateGymClass.class_name" required />
        </div>
        <div class="box">
          <label>Date Start:</label>
          <input type="date" v-model="updateGymClass.dateStart" required />
        </div>
        <div class="box">
          <label>Date End:</label>
          <input type="date" v-model="updateGymClass.dateEnd" required />
        </div>
        <div class="box">
          <label>Time Start:</label>
          <input type="time" v-model="updateGymClass.timeStart" required />
        </div>
        <div class="box">
          <label>Time End:</label> 
          <input type="time" v-model="updateGymClass.timeEnd" required />
        </div>
        <div class="box">
          <label>Signed Up:</label>
          <input type="number" v-model="updateGymClass.signedUp" required />
        </div>         
        <div>
          <button class="submit" type="submit">Submit</button>
        </div>
      </div>
    </form>
    <h3 class="title">All Gym Classes</h3>
    <table class="table">
      <div class="headtitle">
        <thead>
          <tr class="main">
            <div class="h">
              <th class="id"> ID</th>
            </div>
            <div class="h">
              <th class="class">Class Name</th>
            </div>
            <div class="h">
              <th class="date">Date Start</th>
            </div>
            
            <div class="h">
              <th class="time">Time Start</th>
              </div> 
            <div class="h">
              <th class="time">Time End</th>
            </div>
          <div class="h">
              <th class="sign">Signed Up</th>
          </div>
          </tr>
        </thead>
      </div>
      <div class="data">
        <tbody class="2">
        <div class="eee">
          <tr class="main2" v-for="gymClass in allGymClasses" :key="gymClass.classId">
            <div class="h">
              <td class="id" >{{ gymClass.classId }}</td>
            </div>
            <div class="h">
              <td class="class">{{ gymClass.class_name }}</td>
            </div>
            <div class="h">
              <td class="date">{{ gymClass.dateStart }}</td>
            </div>
            <!-- <div class="h">
              <td class="date">{{ gymClass.dateEnd }}</td>
            </div>   -->
            <div class="h">
              <td class="time">{{ gymClass.timeStart }}</td>
            </div>
            <div class="h">
              <td class="time">{{ gymClass.timeEnd }}</td>
            </div>
            <div class="h">
              <td class="sign"> {{ gymClass.signedUp }}</td>
            </div>
            </tr>
          </div>
        </tbody>
      </div>
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
.update{
  background-color: #e7e6e9;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.update-title {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  margin-top:10px;
  width: 100%;
}
.box{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  margin-bottom: 10px;
  
  border-radius: 5px;
  width: 100%;
}
form{
  width: 95%;
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
  padding:20px
}


/**TABLE **/
.data{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  overflow: auto;
}
.eee{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  overflow: auto;
}
table {
  border-collapse: collapse;
  width: 100%;
  margin-top: 10px;
  margin-bottom: 10px;
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 5px  25px rgba(0,0,0,.5);
  transition: all .6s ease-in-out;
  margin-bottom:90px;
}
.input {
  width: 100%;
  height: 90%;
  padding-top: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  
}


tr.main {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  
  border: 2px solid #715f45;
  border-radius: 5px;
  transition: all .6s ease-in-out;
  font-family: Georgia, 'Times New Roman', Times, serif;
}
.headtitle {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.eee {
  width: 100%;
display: flex;
flex-direction: column;
justify-content: space-between;
align-items: center;
} 
.main2 {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: flex-start;
  transition: all .6s ease-in-out;
  font-family: 'Times New Roman', Times, serif
 
}
.data {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  border: 2px solid #715f45;
  border-radius: 5px;
}

.id {
  width: 5vh;
  text-align: start;  
}
.class {
  width: 30vh;
  text-align: center;
}
.date {
  width: 30vh;
  text-align: center;
}
.time {
  width: 30vh;
  text-align: center;
}
.sign {
  width: 10vh;
  text-align: center;
}
</style>