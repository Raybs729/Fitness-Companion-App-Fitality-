<template>
  <div id="app" class ="app">
    <div class="controlls" id="nav" v-if="$store.state.token != ''">

      <div class="control active-btn" data-id="home">
      <router-link v-bind:to="{ name: 'home' }">
        <font-awesome-icon icon="home" /> Home
      </router-link>
    </div>
    <div class="control control1" data-id="create">
      <router-link v-bind:to="{ name: 'createExercise' }">
        <font-awesome-icon icon="dumbbell" /> Workouts
      </router-link>
    </div>
    <div class="control control2" data-id="createclass" v-if="user.authorities[0].name === 'ROLE_ADMIN'">
      <router-link v-bind:to="{ name: 'createGymClass' }">
        <font-awesome-icon icon="bolt" /> Gym Class
      </router-link>
    </div>
    <div class="control control4" data-id="profile">
      <router-link v-bind:to="{ name: 'Profile' }">
        <font-awesome-icon icon="user" /> Profile
      </router-link>
    </div>
    <div class="control control5" data-id="logout">
      <router-link v-bind:to="{ name: 'logout' }">
        <font-awesome-icon icon="sign-out-alt" /> Logout
      </router-link>
    </div>  
    
  </div>
 
    <router-view />
  </div>
</template>
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
  
    top: auto;
    bottom: 0;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    left: 50%;
    transform: translateX(-50%);
    width: 100%;
    transition: all .4s ease-in-out;
    background-color: rgb(236, 233, 228);
}

.controlls .control {
   
    margin: .5rem 0;
    cursor: pointer;
    width: 55px;
    height: 55px;
    /* border-radius: 15%; */
    display: flex;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    background-color: #cdcac8;
    font-size: 15px;
    flex: 1;
    text-align: center;
    
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #ba9330;
  align-items: center;
   /***/
  background: #FFFFFF;
  box-shadow: 0 3px 15px rgba(0, 0, 0, 0.3);
  transition: all .6s ease-in-out;
 
  }
  .controlls .active-btn {
  background-color: rgb(255, 255, 255);
  transition: all .4s ease-in-out;
}

</style>