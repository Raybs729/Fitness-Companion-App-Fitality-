
<template>
  <div id="app" class ="app">
    <div class="controlls" id="nav" v-if="$store.state.token != ''">

      <div class="control active-btn" data-id="home">
        <i class="fa-solid fa-house"></i>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp; &nbsp;
     </div>
      <div class="control control1" data-id="create">
      <router-link v-bind:to="{ name: 'createExercise' }" >Workouts</router-link> &nbsp; &nbsp;
     </div>
     <div class="control control2" data-id="createclass">
      <i class="fa-solid fa-briefcase"></i>
        <router-link v-bind:to="{ name: 'createGymClass' }" >Gym Class</router-link> &nbsp; &nbsp;
     </div>
      <div class="control control3" data-id="update">
        <router-link v-bind:to="{ name: 'updateGymClass' }" >Update Gym Class</router-link> &nbsp; &nbsp;
      </div>
      <div class="control control4" data-id="profile">
        <i class="fa-solid fa-user"></i>
        <router-link v-bind:to="{ name: 'Profile' }" >Profile</router-link> &nbsp; &nbsp;
      </div>
      <router-link v-bind:to="{ name: 'logout' }" >Logout</router-link>  
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
    z-index: 10;
    top: 50%;
    right: 3%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    transform: translateY(-50%);

/*   
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
    background-color: rgb(236, 233, 228); */
}

.controlls .control {
   
     padding: 1rem;
        cursor: pointer;
        background-color: var(--color-grey-4);
        width: 55px;
        height: 55px;
        border-radius: 30%;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: .7rem 0;
        box-shadow: var(--box-shadow-1);
  
  /* margin: .5rem 0;
    cursor: pointer;
    width: 55px;
    height: 55px;
    
    display: flex;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    background-color: #cdcac8;
    font-size: 15px;
    flex: 1;
    text-align: center; */
    /* border-radius: 15%; */
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

}
</style>