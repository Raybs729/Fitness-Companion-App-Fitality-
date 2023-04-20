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
                <label>Date End:</label>
              </div>
              <div>
                <input type="date" v-model="updateGymClass.dateEnd" required />
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
            <div class="h">
              <td class="time">{{ gymClass.timeStart }}</td>
            </div>
            <div class="h">
              <td class="time">{{ gymClass.timeEnd }}</td>
            </div>
            <div class="h">
              <td class="sign"> {{ gymClass.signedUp }}</td>
            </div>   
            <!-- <td>{{ gymClass.dateEnd }}</td> -->
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
  width: 100px;
  height: 30px;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #47433a;
  overflow-x: hidden;
  border: 2px solid #8b7f74;
  background: #f2c382;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-size: 15px;
  box-shadow: 0 3px 5px rgba(119, 105, 105, 0.3);
  transition: all .6s ease-in-out;
  text-transform: uppercase;
}
/**TABLE **/
.input {
  width: 100%;
  height: 90%;
  padding-top: 10px;
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
.table {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-bottom: 80px;
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