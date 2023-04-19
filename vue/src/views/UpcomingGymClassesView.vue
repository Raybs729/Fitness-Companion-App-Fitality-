<template class = "whole-page-container">
    <div class="upcoming-classes-container">
      <UpcomingGymClasses/>
    </div>
</template>

<script>
import UpcomingGymClasses from "../components/UpcomingGymClasses.vue";


export default {
  name: "UpcomingGymClassesView",
  components: {
    UpcomingGymClasses,
  },
}
</script>

<style>
.upcoming-classes-container {
  background-color: #ffc400cc;
  background-image: linear-gradient(rgba(255, 217, 0, 0.911), rgb(255, 255, 255) );
  padding: 20px;
  margin: 50px;
  color: rgb(0, 0, 0);
  font-size: 1.5rem;
  font-family: baskerville;
  border-style: solid;
  border-color: rgb(0, 0, 0);
}

.whole-page-container {
  background-image: linear-gradient(rgba(255, 217, 0, 0.911), rgb(255, 255, 255) );
}
</style>




