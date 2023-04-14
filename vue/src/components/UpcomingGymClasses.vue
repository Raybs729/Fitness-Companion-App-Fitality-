<template>
  <div>
    <h3>Upcoming Gym Classes:</h3>
    <div v-if="gymClasses.length > 0">
      <ul>
        <li v-for="gymClass in gymClasses" :key="gymClass.classId">
          <div>Class Name: {{ gymClass.class_name }}</div>
          <div>Date Start: {{ gymClass.dateStart }}</div>
          <div>Time Start: {{ gymClass.timeStart }}</div>
          <div>Date End: {{ gymClass.dateEnd }}</div>
          <div>Time End: {{ gymClass.timeEnd }}</div>
          <div>Signed Up: {{ gymClass.signedUp }}</div>
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