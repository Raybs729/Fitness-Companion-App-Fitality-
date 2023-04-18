<template>
  <div id="app" class ="app">
    <div class ="twotone">

    </div>
    <div class="controlls" id="nav" v-if="$store.state.token != ''">
      <div class="control active-btn" data-id="home">
        <div>
            <router-link v-bind:to="{ name: 'home' }"></router-link>
        </div>
        <font-awesome-icon icon="home" class="icon"/> Home
    </div>
    <div class="control control1" data-id="create">
        <div>
            <router-link v-bind:to="{ name: 'createExercise' }"></router-link>
        </div>
        <font-awesome-icon icon="dumbbell" class="icon"/> Workouts
    </div>
    <div class="control control2" data-id="createclass" v-if="user.authorities[0].name === 'ROLE_ADMIN'">
        <div>
            <router-link v-bind:to="{ name: 'createGymClass' }"></router-link>
        </div>
          <font-awesome-icon icon="bolt" class="icon" /> Class  
    </div>
   
    <div class="control control4" data-id="profile">
        <div>
            <router-link v-bind:to="{ name: 'Profile' }"></router-link>
        </div>  
        <font-awesome-icon icon="user" class="icon" /> Profile
    </div>
    <div class="control control5" data-id="logout">
        <div>
            <router-link v-bind:to="{ name: 'logout' }"></router-link>
        </div>
        <font-awesome-icon icon="sign-out-alt" class="icon"/> Logout
    </div>  
    
  </div>
 
    <router-view />
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  
  computed: {
    ...mapState(['user']),
  },

};
</script>

<style scoped>


.app {
min-height: 100vh;
width: 100%;
left:0;
top: 0;
padding: 0.5rem 1rem;
box-sizing: border-box;

}
.controlls {
 
  position: fixed;
    z-index: 10;
    top: 50%;
    right: 3%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
 
    transform: translateY(-50%);


}

.controlls .control {
   
     
        cursor: pointer;
        background-color: var(--color-grey-4);
        width: 65px;
        height: 65px;
        border-radius: 30%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        position: relative;
        align-items: center;

        margin: 0.5rem 0;
        box-shadow: var(--box-shadow-1);
        font-size: 15px;

        border-radius: 10px;
        overflow-x: hidden;
        border: 3px solid #ba9330;
        background: #e4e1d5;
        transition: all .6s ease-in-out;
 
  }
  .controlls .active-btn {
  background-color: rgb(255, 255, 255);
  transition: all .4s ease-in-out;
}

.icon {
  font-size: 1.5rem;
  display: flex;
  color:#675326;
  justify-content: center;
  align-items: center;
}

/*** PHONE DISPLAY ****/

@media (max-width: 414px) {

  .app {
min-height: 100vh;
width: 100%;
left:0;
top: 0;
padding: 0.5rem 1rem;
box-sizing: border-box;

}
.controlls {
 
  position: fixed;
  
    top: auto;
    bottom: 0;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    left: 50%;
    transform: translateX(-50%);
    width: 100%;
    transition: all .4s ease-in-out;
    background-color: rgb(236, 233, 228);
}

.controlls .control {
   
    margin: .5rem 0;
    cursor: pointer;
    width: 75px;
    height: 55px;
    /* border-radius: 15%; */
    display: flex;
    justify-content: center;
   
    box-sizing: border-box;
    background-color: #cdcac8;
    font-size: 15px;
   
    
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
 
  }
  .controlls .active-btn {
  background-color: rgb(255, 255, 255);
  transition: all .4s ease-in-out;
}

}
</style>