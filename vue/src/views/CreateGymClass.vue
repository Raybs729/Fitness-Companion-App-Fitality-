<template>
  <div class="full">
    <h2>Create<span> a New</span><span class="bg-text"> Gym Class</span></h2>
    <div class="input">
      <form @submit.prevent="submitForm">
        <div class="table">
          <div class="info1">
            <div class="information1 ">
              <div class="box">
                <label id="class-name">Class Name:</label>
              </div>
              <div>
                <input id="class-input" v-model="newGymClass.class_name" required />
              </div>
            </div>
          </div>
          <div class="info1">
            <div class="information2">
              <div class="box">
                <label id="date-start">Date Start:</label>
              </div>
              <div>
                <input id="date-input" type="date" v-model="newGymClass.dateStart" required />
              </div>
            </div>
          </div> 
          <div class="info1">
            <div class="information2">
              <div class="box">
                <label>Date End:</label>
              </div>
              <div>
                <input type="date" v-model="newGymClass.dateEnd" required />
              </div>
            </div>
          </div>   
          <div class="info1">
            <div class="information3">
              <div class="box" >
                <label>Time Start:</label> 
              </div>
              <div>
                <input type="time" v-model="newGymClass.timeStart" required />
              </div>
           </div>
          </div>  
          <div class="info1">
            <div class="information4 ">
              <div class="box">
                <label>Time End:</label>
              </div>
              <div>
                <input type="time" v-model="newGymClass.timeEnd" required />
              </div>
            </div>
          </div>
          <div class="info1">
            <div class="information5 ">
              <div class="box">
                <label>Signed Up:</label>
              </div>
              <div>
                <input type="number" v-model="newGymClass.signedUp" required />
              </div>
            </div>
          </div>
        </div>
        <div class="button">
        <div >
          <button class="button1" type="submit">Submit</button>
        </div>
        <div >
          <button class="button2" v-if="!showUpdateForm" @click="toggleUpdateForm">Update Gym Class</button>
          <button class="button2" v-else @click="toggleUpdateForm">Close Update Gym Class</button>
        </div>
      </div>
      </form>
      
    </div>
   
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

.full {
  background-color: #e1dfdd;
  width: 100%;
  height: 100%;
  
}


/* Create a new gym class */

h2 {
position: relative;
text-transform: uppercase;
font-size: 1.8rem;
font-weight: 950;
padding-left: 45px;
font-style: italic;
z-index: 0;
left: center;
}

.bg-text{
  font-family: 'Poppins', serif;
  
  position: absolute;
  top: 50%;
  left: center;
  color: #76736d;
  transition: all  .4s ease-in-out;
       z-index: -1;
       transform: translate(-50%, -50%);
       font-weight: 900;
       font-size: 3rem;
    
}
span {
  color: #c9952c;
  font-weight: 400;
  font-size: 2.5rem;
  
}

.time {
  display: flex ;
  justify-content: space-evenly;
  padding-top: 10px;
}
.information2, .information3, .information4, .information5 {
  background-color: rgb(255, 255, 255);
  display: flex;
  align-items: center;
  justify-content: space-between;
  box-shadow: 0 5px  25px rgba(0,0,0,.5);
  border-radius: 5px;
  overflow-x: hidden;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-size: 20px;
  transition: all .6s ease-in-out;
  width: 100%;
  padding: 15px 25px 15px 25px;
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

.info2 {
  padding-bottom: 10px;
}
.table {
  width: 100%;
  height: 80%;
  padding-top: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
  
  
}
.button {
  padding : 0px 25px;
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
  
}

.button1  {
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
.button2 {
  width:200px;
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
}

.table input {
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
</style>