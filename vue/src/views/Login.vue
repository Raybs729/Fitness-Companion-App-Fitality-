<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 id= class="h3 mb-3 font-weight-normal">Login</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Don't have an account?</router-link>
      <button type="submit">Sign up</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>


/*Login*/
h1 {
  position: absolute;
  width: 106px;
  height: 44px;
  left: 184px;
  top: 350px;
  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 44px;
  color:#000000;
}

/*Username*/

 #username {

  position: absolute;
  width: 310px;
  height: 32px;
  left: 85px;
  top: 505px;
  background: #FFFFFF;
}

/*Password*/

#password {

  position: absolute;
  width: 310px;
  height: 32px;
  left: 85px;
  top: 505px;
  background: #FFFFFF;

}

/*Don't have an Account? Sign-Up*/

register { 
  
}









/* Mobile */
@media (max-width: 450px) {

  .sr-only {
    display: grid;
    grid-template-areas: ;
  }
}







</style> 
