<template>
  <div class="update">
    <div class="update-title">
      <h3>Upcoming Gym Classes:</h3>
    </div>
    
    <div v-if="gymClasses.length > 0" class="form">
      <ul>
        <li v-for="gymClass in gymClasses" :key="gymClass.classId" class="box">
          <div>
            <div>Class Name: {{ gymClass.class_name }}</div>
            <div>Date Start: {{ gymClass.dateStart }}</div>
            <div>Date End: {{ gymClass.dateEnd }}</div>
          </div>
          <div>
            <div>Time Start: {{ gymClass.timeStart }}</div>
            <div>Time End: {{ gymClass.timeEnd }}</div>
            <div>Signed Up: {{ gymClass.signedUp }}</div>
          </div>
          
        </li>
      </ul>
    </div>
    <div v-else>
      <p>No upcoming gym classes found.</p>
    </div>
  </div>
</template>

<script>
import WorkoutService from "../services/WorkoutService";

export default {
  name: "UpcomingGymClasses",
  data() {
    return {
      gymClasses: [],
    };
  },
  created() {
    this.fetchUpcomingGymClasses();
  },
  methods: {
    fetchUpcomingGymClasses() {
      WorkoutService.getUpcomingGymClass().then((response) => {
        this.gymClasses = response.data;
      });
    },
  },
};
</script>

<style scoped>
.update{
  background-color: #e7e6e9;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: -10px;
  min-height: 100vh;
}
.update-title {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  margin-top:10px;
  margin-bottom:10px ;
  width: 100%;
}
.form{
  display: flex;
  flex-direction: column;
  align-items: center;

  width: 100%;
}
ul{
  list-style-type: none;
  width: 95%;
  padding: 0;
  margin: 0;
}
.box{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  margin-bottom: 10px;
  padding: 10px;
  
  border-radius: 5px;
  width: 95%;
}
</style>