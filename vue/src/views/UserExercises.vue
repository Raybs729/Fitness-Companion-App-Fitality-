<template>
    <div>
     <UserExercise :userId="userId"/>
     <UserCreateExercise :userId="userId" @exercise-created="refreshExercises"/>
    </div>
</template>

<script>
import UserExercise from '../components/UserExercise.vue';
import UserCreateExercise from '../components/UserCreateExercise.vue';


export default {
  name: "UserExercises",
  components: {
    UserExercise,
    UserCreateExercise,
  },
  props: {
    userId: {
      type: Number, 
      required: true,
    }
  },
  methods: {
    refreshExercises(){
      this.$refs.UserExercise.refreshExercises();
    }
  }
}
</script>