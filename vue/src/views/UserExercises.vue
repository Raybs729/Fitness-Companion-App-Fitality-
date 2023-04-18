<template>
    <div>
     <UserExercise :userId="user.id"/>
     <UserCreateExercise :userId="user.id" @exercise-created="refreshExercises"/>
    </div>
</template>

<script>
import UserExercise from '../components/UserExercise.vue';
import UserCreateExercise from '../components/UserCreateExercise.vue';


export default {
  name: "home",
  components: {
    UserExercise,
    UserCreateExercise,
  }
}
</script>