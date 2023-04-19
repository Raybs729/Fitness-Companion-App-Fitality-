<template>
  <div id="register" class="text-center">
    <div class="twotone"></div>
    <form class="form-register" @submit.prevent="register">
      <img id="logoimg" src="../img/fitalitylogo.png" alt="Fitality Logo">
      <div id="welcome-header">Welcome</div>
      <h3 id="welcome-message">Create an account today to start using Fitality.</h3>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <div id="login-router">Already have an account?</div>
      <router-link id="login-link" :to="{ name: 'login' }">Login</router-link>
      <button id="create-button" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.twotone{
    /*****polygon****/
transition: all .4s ease-in-out;
            width: 100%;
            height: 100%;
            background-color: #ba9330;
            position: absolute;
            left: 0;
            top: 0;
            z-index: 0;
            /*You can generate clip- path : https://www.cssportal.com/css-clip-path-generator/*/
              clip-path: polygon(25% 0%, 100% 0%, 75% 100%, 0% 100%); 
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  object-fit: cover;
  
}
/* Logo */

#logoimg { 

  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 250px;
  height: 250px;
  margin-bottom: 10px;
  left: center;
  top: 1vh;
}

/* Welcome */

#welcome-header {

  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 200px;
  height: auto;
  left: center;
  top: 30vh;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 44px;
  color: #000000;

}

/* Create an account today to start using Fitality */

#welcome-message {

  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  width: 259px;
  height: 40px;
  left: center;
  top: 32vh;

  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 18px;
  color: #000000;

}

/* Username */

#username {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 40vh;
  width: 280px;
  height: 32px;
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

/* Password */

#password {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 46vh;
  width: 280px;
  height: 32px;
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

/* Confirm Password */

#confirmPassword {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 52vh;
  width: 280px;
  height: 32px;
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
 
 /* Create Account button */

 #create-button {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;
  left: center;
  top: 58vh;
  width: 200px;
  height: 52px;
  

  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
  background: #1b1414;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
}

 /* Already have an account? */

#login-router {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 90vh;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 20px;
  color: #000000;
}

/* Login link */

#login-link {

  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 92vh;

/* Font */

font-family: Georgia, 'Times New Roman', Times, serif;
font-style: normal;
font-weight: 400;
font-size: 15px;
line-height: 20px;
color: #000000;
}

.form-register .form-control {
  
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
  border-radius: 4px;
  margin-bottom: 10px;
}

.form-register button[type="submit"] {
  font-size: 18px;
  font-weight: bold;
  padding: 8px 16px;
  border: none;
  cursor: pointer;
  background-color: #007bff;
  color: #fff;
  border-radius: 4px;
  transition: background-color 0.2s ease-in-out;
}

.form-register button[type="submit"]:hover {
  background-color: #0056b3;
}

.form-register .alert {
  margin-bottom: 15px;
}

.router-link-exact-active {
  color: #007bff;
  text-decoration: underline;
  font-size: 14px;
  display: inline-block;
  margin-bottom: 15px;
}

/* Mobile */
@media (max-width: 414px) {
  .twotone{
    /*****polygon****/
  transition: all .4s ease-in-out;
   width: 100%;
   height: 100%;
   background-color: #ba9330;
   position: absolute;
   left: 0;
   top: 0;
   z-index: 0;
  /*You can generate clip- path : https://www.cssportal.com/css-clip-path-generator/*/ 
  clip-path: polygon(51% 0, 0 96%, 100% 0, 100% 30%, 0 100%, 0 100%, 100% 57%, 100% 64%, 100% 0);
  
}           
  form {
  display: flex;
  flex-direction: column;
  align-items: center;
  object-fit: cover;
  
}
#logoimg { 

  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 150px;
  height: 150px;
  margin-bottom: 10px;
  left: center;
  top: 1vh;
}

#welcome-header {

  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 150px;
  height: auto;
  left: center;
  top: 25vh;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 30px;
  line-height: 40px;
  color: #000000;

}

#welcome-message {

  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  width: 259px;
  height: 40px;
  left: center;
  top: 28vh;

  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 18px;
  color: #000000;

}

/* Username */

#username {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 38vh;
  width: 220px;
  height: 32px;
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

/* Password */

#password {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 45vh;
  width: 220px;
  height: 32px;
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

/* Confirm Password */

#confirmPassword {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 52vh;
  width: 220px;
  height: 32px;
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
 
 /* Create Account button */

 #create-button {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;
  left: center;
  top: 60vh;
  width: 150px;
  height: 52px;
  background: #1e1a0f;
  font-size: 15px;

  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
  background: #1b1414;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
}

 /* Already have an account? */

#login-router {
  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 90vh;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 20px;
  color: #000000;
}

/* Login link */

#login-link {

  position: fixed;
  display: flex;
  align-items: center;
  text-align: center;
  left: center;
  top: 93vh;

/* Font */

font-family: Georgia, 'Times New Roman', Times, serif;
font-style: normal;
font-weight: 400;
font-size: 15px;
line-height: 20px;
color: #000000;
}

}
</style>
