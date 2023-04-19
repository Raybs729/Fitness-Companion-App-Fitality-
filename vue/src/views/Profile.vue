<template>
  <div>
    <h2>Current Profile</h2>
    <div v-if="currentProfile">
      <p>Email: {{ currentProfile.email }}</p>
      <p>First Name: {{ currentProfile.firstName }}</p>
      <p>Last Name: {{ currentProfile.lastName }}</p>
      <p>Phone: {{ currentProfile.phone }}</p>
      <p>Height: {{ currentProfile.height }}</p>
      <p>Weight: {{ currentProfile.weight }}</p>
      <p>Age: {{ currentProfile.age }}</p>
      <p>Photo URL: {{ currentProfile.photo }}</p>
      <p>Goals: {{ currentProfile.goals }}</p>
    </div>

    <button v-if="!showUpdateForm" @click="showUpdateForm = true">
      Update Profile
    </button>
    <div v-if="showUpdateForm">
      <h2>Update Profile</h2>
        <form @submit.prevent="submitForm">
          <label>Email:</label>
          <input type="email" v-model="profileData.email" />
          <label>First Name:</label>
          <input type="text" v-model="profileData.firstName" />
          <label>Last Name:</label>
          <input type="text" v-model="profileData.lastName" />
          <label>Phone:</label>
          <input type="text" v-model="profileData.phone" />
          <label>Height:</label>
          <input type="number" v-model="profileData.height" />
          <label>Weight:</label>
          <input type="number" v-model="profileData.weight" />
          <label>Age:</label>
          <input type="number" v-model="profileData.age" />
          <label>Photo URL:</label>
          <input type="url" v-model="profileData.photo" />
          <label>Goals:</label>
          <input type="text" v-model="profileData.goals" />
          <button type="submit">Confirm Changes</button>
        </form>
    </div>
    <div>
      <button @click="$router.push({ name: 'logout' })">
        <font-awesome-icon icon="sign-out-alt" /> Logout
      </button>
    </div> 
  </div>
</template>

<script>
import AccountService from "../services/AccountService";
import { mapState } from 'vuex';

export default {
  name: "Profile",
  data() {
    return {
      showUpdateForm: false,
      currentProfile: null,
      profileData: {
        userId: this.userId,
        email: "",
        firstName: "",
        lastName: "",
        phone: "",
        height: 0,
        weight: 0,
        age: 0,
        photo: "",
        goals: "",
      },
    };
  },
  computed: {
    ...mapState(["user"]),
  },
  async created() {
    try {
      const response = await AccountService.getAccount(this.user.id);
      this.currentProfile = response.data;
      this.profileData = { ...this.currentProfile };
    } catch (error) {
      console.error("Error fetching profile data:", error);
    }
  },
  methods: {
    async submitForm() {
      try {
        await AccountService.updateProfile(this.profileData.userId, this.profileData);
        this.currentProfile = { ...this.profileData };
        this.showUpdateForm = false;
        console.log("Profile updated successfully.");
      } catch (error) {
        console.error("Error updating profile:", error);
      }
    },
  },
};
</script>