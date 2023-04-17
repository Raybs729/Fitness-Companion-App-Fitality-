<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <img id="logoimg" src="img\fitalitylogo.png" alt="Fitality Logo">
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

/* Logo */

#logoimg { 
 position: absolute;
  width: 200px;
  height: 192.16px;
  left: 140px;
  top: 128px;
}

/* Welcome */

#welcome-header {

  position: absolute;
  width: 164px;
  height: 41px;
  left: 150px;
  top: 217px;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 32px;
  line-height: 44px;
  color: #000000;

}

/* Create an account today to start using Fitality */

#welcome-message {

  position: absolute;
  width: 259px;
  height: 40px;
  left: 90px;
  top: 248px;

  font-family: 'Open Sans';
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 18px;
  color: #000000;

}

/* Username */

#username {

  position: absolute;
  width: 309px;
  height: 32px;
  left: 59px;
  top: 319px;
  background: #FFFFFF;
}

/* Password */

#password {

position: absolute;
width: 310px;
height: 32px;
left: 59px;
top: 363px;
background: #FFFFFF;
}

/* Confirm Password */

#confirmPassword {

  position: absolute;
  width: 310px;
  height: 32px;
  left: 57px;
  top: 408px;
  background: #FFFFFF;
 }
 
 /* Create Account button */

 #create-button {

  position: absolute;
  width: 262px;
  height: 49px;
  left: 82px;
  top: 463px;
  background: #000000;
}

 /* Already have an account? */

#login-router {

  position: absolute;
  width: 295px;
  height: 25px;
  left: 90px;
  top: 536px;

  font-family: Georgia, 'Times New Roman', Times, serif;
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 20px;
  color: #000000;
}

/* Login link */

#login-link {

position: absolute;
width: 295px;
height: 25px;
left: 264px;
top: 536px;

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
</style>
