<template>
  <div class="home">
    <div v-if="user">
      <h2>Welcome, {{ user.username }} {{user.authorities[0].name }}!</h2>
      <UserExercise :userId="user.id" />
      <UserCreateExercise :userId="user.id" @exercise-created="refreshExercises" />
      <UpcomingGymClasses />
      <MachineUsageVue v-if="user.authorities[0].name === 'ROLE_ADMIN'"/>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import UserExercise from '../components/UserExercise.vue';
import UserCreateExercise from '../components/UserCreateExercise.vue';
import UpcomingGymClasses from '../components/UpcomingGymClasses.vue';
import MachineUsageVue from '../components/MachineUsage.vue';

export default {
  name: "home",
  components: {
    UserExercise,
    UserCreateExercise,
    UpcomingGymClasses,
    MachineUsageVue,
  },
  computed: {
    ...mapState(['user'])
  },
  methods: {
    refreshExercises() {
      this.$refs.userExercise.refreshExercises();
    },
  },
};
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f8f9fa;
  font-family: Arial, sans-serif;
}

.home h1 {
  font-size: 2rem;
  font-weight: bold;
  color: #212529;
  margin-bottom: 1rem;
}

.home p {
  font-size: 1.125rem;
  color: #495057;
}

.home ul {
  list-style-type: none;
  padding: 0;
}

.home li {
  font-size: 1rem;
  color: #495057;
  margin-bottom: 0.5rem;
}
</style>