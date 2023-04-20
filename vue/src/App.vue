<template>
  <div id="app" class ="app">
    <div class="controlls" id="nav" v-if="$store.state.token != ''">
      <button class="menu-btn" @click="toggleMenu">
        <font-awesome-icon icon="bars" class="icon" />
      </button>
      <div class="menu" v-show="menuVisible">
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
     </div> 
  </div>
    <router-view />
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      menuVisible: false,
    };
  },
  computed: {
    ...mapState(['user']),
  },
  methods: {
    toggleMenu() {
      this.menuVisible = !this.menuVisible;
      console.log('Menu Visible:', this.menuVisible);
    },
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
    z-index: 11;
    
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    text-align: center;
}

.controlls .control {
        cursor: pointer;
        background-color: white !important;
        width: 100px;
        height: 58px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        position: relative;
        align-items: center;

        font-size: 15px;

        
  }

.icon {
  font-size: 1.5rem;
  display: flex;
  color:black;
  justify-content: center;
  align-items: center;
}
.menu-btn {
    position: fixed;
    top: 0px;
    right: 0px;
    background-color: transparent;
    border: none;
    cursor: pointer;
    z-index: 20;
    padding: 18px 30px;
    border: 2px solid black ;
    height: 58px;
    border-radius: 5px;
  }

  .menu {
    position: fixed;
    top: 0px;
    right: 80px;
    background-color: #f7f7f7;
    padding: 0px;
    border-radius: 5px;
    z-index: 15;
    display: flex;
    
  }

/*** PHONE DISPLAY ****/

@media (max-width: 768px) {

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
    font-size: 15px;
 
  }
}
</style>