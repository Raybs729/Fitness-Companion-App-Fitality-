<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <img id="logoimg" src="img\fitalitylogo.png" alt="Fitality Logo">
      <h1 class="h3 mb-3 font-weight-normal">LOGIN</h1>
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
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <div id="noaccount">Don't have an account?</div>
      <router-link id="register" :to="{ name: 'register' }">Sign Up</router-link>
      <button type="submit">Login</button>
      <div id="loginOptions">Or login with</div>
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

/*Logo Image */

#logoimg {

  position: absolute;
  width: 200px;
  height: 192.16px;
  left: 140px;
  top: 128px;

  
}



/*Login*/
h1 {
  position: absolute;
  width: 106px;
  height: 44px;
  left: 200px;
  top: 350px;
  
  font-family:fantasy;
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
  top: 434px;
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


/* Don't have an account? */

#noaccount {

  position: absolute;
  width: 293px;
  left: 100px;
  top: 879px;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 27px;

}


/* Sign-Up */

#register {

  position: absolute;
  width: 293px;
  left: 315px;
  top: 879px;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 27px;



}

/* Submit button */

button {

  position: absolute;
  width: 218px;
  height: 57px;
  left: 122px;
  top: 570px;
  
  
  font-family: fantasy;
  font-style:normal;
  font-size: 20px;
  line-height: 27px;
  color: #FFFFFF;
  background: #000000;
 
}

#loginOptions {

  position: absolute;
  width: 134px;
  height: 26px;
  left: 179px;
  top: 654px;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 27px;
  color: #000000;
}





















/* Mobile */
@media (max-width: 450px) {


  .sr-only {
    display: grid;
  }
}














</style>





