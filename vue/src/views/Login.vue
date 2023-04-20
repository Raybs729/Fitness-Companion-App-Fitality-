<template>
  
  <div id="login" class="text-center">
    <div id="twotone">
    </div>
    <form class="form-signin" @submit.prevent="login">
      <img id="logoimg" src="../img/fitalitylogo.png" alt="Fitality Logo">
      <!-- <h1 class="h3 mb-3 font-weight-normal">LOGIN</h1> -->
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
#twotone {
  /*****polygon****/
transition: all .4s ease-in-out;
            width: 100%;
            height: 100%;
            background-color: #818080;
            position: absolute;
            left: 0;
            top: 0;
            z-index: 0;
            /*You can generate clip- path : https://www.cssportal.com/css-clip-path-generator/*/
            clip-path: polygon(0 0, 25% 0, 85% 100%, 0% 100%);
            

}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  object-fit: cover;
  
}
/*Logo Image */

#logoimg {

  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  width: auto;
  height: auto;
  margin-bottom: 10px;
  left: center;
  top: 5%;
  z-index: 1;
 
}

/*Login*/
h1 {
  position: absolute;
  width: 106px;
  height: 44px;
  left: center;
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
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  width: 310px;
  height: 34px;
  left: center;
  top: 45vh; 
 
  /***/
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #474645;
  align-items: center;
   /***/
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
  
}

/*Password*/

#password {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  width: 310px;
  height: 32px;
  left: center;
  top: 52vh; 
  background: #FFFFFF;
   /***/
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #474645;
  align-items: center;
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
   /***/
}

/* Submit button */

button {

  position: absolute;
  width: 218px;
  height: 57px;
  left: center;
  top: 60vh;
  
  
  font-family: fantasy;
  font-style:normal;
  font-size: 20px;
  line-height: 27px;
  color: #FFFFFF;
  background: #000000;
  border-radius: 10px;
  overflow-x: hidden;
  margin-top: 20px;
}

#loginOptions {

  position: absolute;
  width: 134px;
  height: 26px;
  left: center;
  top: 70vh;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 27px;
  color: #000000;
  
}
/* Don't have an account? */

#noaccount {
 position: fixed;
 display: center;
 text-align: center;

 width: 300px;
 top: 87vh;
 left: center;
 font-family: Georgia, 'Times New Roman', Times, serif;
 font-style: normal;
 font-weight: 400;
 font-size: 20px;
 line-height: 27px;

} 

/* Sign-Up */
 #register {
 position: fixed;
 text-align: center;
 width: 293px;
 left: center;
 
 top: 90vh;
 font-family: Georgia, 'Times New Roman', Times, serif;
 font-style: normal;
 font-weight: 400;
 font-size: 20px;
 line-height: 27px;

} 

/* Mobile */
@media (max-width: 414px) {
  form {
  display: flex;
  flex-direction: column;
  align-items: center;
  object-fit: cover;
  
}
/*Logo Image */

#logoimg {

  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 150px;
  height: 150px;
  margin-bottom: 10px;
  left: center;
  top: 5%;
 
 
}
h1 {
  position: absolute;
  width: 106px;
  height: 44px;
  left: center;
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
  position: fixed;
  display: flex;
  align-items: center;
  width: 200px;
  height: 34px;
  left: center;
  top: 40vh; 
 
  /***/
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
   /***/
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
  
}

/*Password*/

#password {
  position: fixed;
  display: flex;
  align-items: center;
  width: 200px;
  height: 32px;
  left: center;
  top: 48vh;
  background: #FFFFFF;
   /***/
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
   /***/
}

/* Submit button */

button {

  position: absolute;
  width: 118px;
  height: 35px;
  left: center;
  top: 52vh;
  
  
  font-family: fantasy;
  font-style:normal;
  font-size: 15px;
  line-height: 27px;
  color: #FFFFFF;
  background: #000000;
  border-radius: 10px;
  overflow-x: hidden;
  margin-top: 20px;
}

#loginOptions {

  position: absolute;
  text-align: center;
  width: 134px;
  height: 26px;
  left: center;
  top: 63vh;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 20px;
  color: #000000;
  
}
/* Don't have an account? */

#noaccount {
 position: fixed;
 display: center;
 text-align: center;
 width: 220px;
 top: 80vh;
 left: center;
 font-family: Georgia, 'Times New Roman', Times, serif;
 font-style: normal;
 font-weight: 400;
 font-size: 15px;
 line-height: 25px;

} 

/* Sign-Up */
 #register {
 position: fixed;
 text-align: center;
 width: 293px;
 left: center;
 top: 83vh;
 font-family: Georgia, 'Times New Roman', Times, serif;
 font-style: normal;
 font-weight: 400;
 font-size: 15px;
 line-height: 25px;

} 
  .sr-only {
    display: grid;
  }
}

</style>





