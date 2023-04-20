<template>
    <div>
     <UpcomingGymClasses/>
    </div>
</template>

<script>
import UpcomingGymClasses from "../components/UpcomingGymClasses.vue";


export default {
  name: "UpcomingGymClassesView",
  data(){
    return{
      showUpcoming: false,
    }
  },
  components: {
    UpcomingGymClasses,
  },
}
</script>
