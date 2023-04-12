<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <p>Users:</p>
    <ul>
      <li v-for="user in users" :key="user.id">{{user.username}} {{ user.id }}{{ user.authorities[0].name }}</li>
    </ul>

  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  name: "home",
  data(){
    return{
      users: [],
    }
  },
  created(){
    authService.getAllUsers().then((response)=>{
      this.users = response.data;
    })
  }
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