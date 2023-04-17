
<template>
  <div id="app">
    <div id="nav" v-if="$store.state.token != ''">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" >Logout</router-link> &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'createExercise' }" >Create Exercise</router-link> &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'createGymClass' }" >Create Gym Class</router-link> &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'updateGymClass' }" >Update Gym Class</router-link>
    </div>
    <router-view />
  </div>
</template>
