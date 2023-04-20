<template>
  <div id="app" class ="app">
    <div class="controlls" id="nav" v-if="$store.state.token != ''">
      <router-link class="ehh" v-bind:to="{ name: 'home' }">
        <div class="control active-btn" data-id="home">
          <div class="eh">
          <font-awesome-icon icon="home" class="icon"/>
          </div>  
          <div class="eh">
            <span>Home</span>
        </div>
      </div>

    </router-link>
    <router-link class="ehh" v-bind:to="{ name: 'createExercise' }">
      <div class="control control1" data-id="create">  
        <div>
          <font-awesome-icon icon="dumbbell" class="icon"/> 
        </div>
        <div>
          <span>Workouts</span>
        </div>
      </div>
    </router-link>
    
    <router-link  class="ehh" v-bind:to="{ name: 'CreateGymClass' }" v-if="user.authorities[0].name === 'ROLE_ADMIN'">
      <div class="control control2" data-id="createclass" >
        <div>
          <font-awesome-icon icon="bolt" class="icon" />  
        </div>
        <div>
          <span>Classes </span>  
        </div>
      </div>
    </router-link>        
    
    <!-- <div class="control control2" data-id="createclass" v-if="user.authorities[0].name === 'ROLE_ADMIN'">
      <router-link v-bind:to="{ name: 'UpdateGymClass' }">
        <font-awesome-icon icon="edit" /> Update Class
      </router-link>
    </div> -->
    
    <router-link class="ehh" v-bind:to="{ name: 'Profile' }">
      <div class="control control4" data-id="profile">
        <div>
        <font-awesome-icon icon="user" class="icon" /> 
        </div>
        <div>
          <span>Profile</span> 
        </div>
      </div>
    </router-link>           
    
    <!-- <div class="control control5" data-id="logout">
      <router-link v-bind:to="{ name: 'logout' }">
        <font-awesome-icon icon="sign-out-alt" /> Logout
      </router-link>
    </div>   -->
    
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
<style>
  body{
    display: block;
    margin: 0;
  }
</style>
<style scoped>

.ehh {
   display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  text-decoration: none; 
} 
.app {
width: 100%;
top: 0;
left:0;
right: 0;
bottom: 0;
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
        background: #f7f7f7;
        transition: all .6s ease-in-out;
  }

.icon {
  font-size: 1.5rem;
  display: flex;
  color:black;
  justify-content: center;
  align-items: center;
}

/*** PHONE DISPLAY ****/

@media (max-width: 480px) {

  .app {
    background-color: #f7f7f7 ;
width: 100%;
top: 0;
left:0;
right: 0;
bottom: 0;
}
span {
  color:black
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
    background-color: #f7f7f7;
}
.ehh{
  width: calc(100% / 4);
}
.control {
   
    margin: .5rem 0;
    cursor: pointer;
    width: 100%;
    height: 55px;
    /* border-radius: 15%; */
    display: flex;
    justify-content: center;
    
    box-sizing: border-box;
    background-color: red;
    font-size: 15px;
 
  }
}
</style>