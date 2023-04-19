<template>
  <div id="app" class ="app">
    <div class="controlls" id="nav" v-if="$store.state.token != ''">
      <div class="control active-btn" data-id="home">
        <router-link class="ehh" v-bind:to="{ name: 'home' }">
          <div class="eh">
          <font-awesome-icon icon="home" class="icon"/>
          </div>  
          <div class="eh">
            <span>Home</span>
          </div>
        </router-link> 
       
    </div>
    <div class="control control1" data-id="create">
          <router-link class="ehh" v-bind:to="{ name: 'createExercise' }">
            <div>
              <font-awesome-icon icon="dumbbell" class="icon"/> 
            </div>
            <div>
              <span>Workouts</span>
            </div>
          </router-link>
         
    </div>
    <div class="control control2" data-id="createclass" v-if="user.authorities[0].name === 'ROLE_ADMIN'">
          <router-link  class="ehh" v-bind:to="{ name: 'CreateGymClass' }">
              <div>
              <font-awesome-icon icon="bolt" class="icon" />  
            </div>
             <div>
              <span>Classes </span>  
             </div>
          </router-link>        
    </div>
    <!-- <div class="control control2" data-id="createclass" v-if="user.authorities[0].name === 'ROLE_ADMIN'">
      <router-link v-bind:to="{ name: 'UpdateGymClass' }">
        <font-awesome-icon icon="edit" /> Update Class
      </router-link>
    </div> -->
    <div class="control control4" data-id="profile">
            <router-link class="ehh" v-bind:to="{ name: 'Profile' }">
              <div>
              <font-awesome-icon icon="user" class="icon" /> 
            </div>
            <div>
              <span>Profile</span> 
            </div>
            </router-link>
            
    </div>
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

<style scoped>

.ehh {
   display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  text-decoration: none; 
} 
.app {
background-color: #c8c7c6 ;
width: 100%;
top: 0;
left:0;
right: 0;
bottom: 0;
position: relative;
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
        border: 3px solid #d1b40cce;
        background: #e4e1d5;
        transition: all .6s ease-in-out;
 
  }
  .controlls .active-btn {
  background-color: rgb(255, 255, 255);
  transition: all .4s ease-in-out;

}

.icon {
  padding: 10px;
  font-size: 1.5rem;
  display: flex;
  color:#675326;
  justify-content: center;
  align-items: center;
}

/*** PHONE DISPLAY ****/

@media (max-width: 480px) {

  .app {
 background-color: #eae8e5 ;
width: 100%;
top: 0;
left:0;
right: 0;
bottom: 0;

}
span {
  color:#734a1e
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
    background-color: rgb(255, 254, 252);
}

.controlls .control {
   
    margin: .5rem 0;
    cursor: pointer;
    width: 45vh;
    height: 55px;
    /* border-radius: 15%; */
    display: flex;
    justify-content: center;
   
    box-sizing: border-box;
    background-color: #eeebe8;
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