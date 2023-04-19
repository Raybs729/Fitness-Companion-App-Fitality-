<template>
  <div class="container">
    
    <div class="title">
      <h1>Fitality</h1>
      <h2>My<span> Profile</span><span class="bg-text"> My Info</span></h2>
    </div>
    
      <div class="profile" v-if="currentProfile">
        <div class="table">
          <div class="table1">
            <div class="memberinfo">
              <div>
                <h3>Member Info</h3>
              </div>
              <div>
                <button class="edit" v-if="!showUpdateForm" @click="showUpdateForm = true">
                Edit
                </button>
              </div>
            </div>
            <div class="memberinfo">
              <div>
                <h4 class="info">Email: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.email }}</p>
              </div>
            </div>  
            <div class="memberinfo">
              <div>
                <h4 class="info">First Name: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.firstName }}</p>
              </div>
            </div>  
            <div class="memberinfo">
              <div>
                <h4 class="info">Last Name: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.lastName }}</p>
              </div>
            </div>
            <div class="memberinfo">
              <div>
                <h4 class="info">Phone: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.phone }}</p>
              </div>
            </div>   
            <div class="memberinfo">
              <div>
                <h4 class="info">Height: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.height }}</p>
              </div>
          </div>
          <div class="memberinfo">
              <div>
                <h4 class="info">Weight: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.weight }} </p>
              </div>
            </div>
            <div class="memberinfo">
              <div>
                <h4 class="info">Age: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.age }} </p>
              </div>
            </div>
            <div class="memberinfo">
              <div>
                <h4 class="info">Photo URL: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.photo }} </p>
              </div>
            </div>
            
            <div class="memberinfo">
              <div>
                <h4 class="info"> Goals: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.goals }} </p>
              </div>
            </div>
          </div>
          <div>
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
          </div>
        </div>
     </div>
   
     <div>
          <button class="logout" @click="$router.push({ name: 'logout' })">
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
<style scoped>

h1 {
  position: absolute;
  top: 15%;
  left: 15%;
  bottom: 1%;
  transform: rotate(300deg);
  font-size: 11rem;
  z-index: -1;
  color: rgba(241, 239, 239, 0.5); 
  text-shadow: 0 0 10px rgba(196, 191, 191, 0.5); 
  animation: rotate 3s linear forwards;
  z-index: -4;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  
}
.profile, .profile2 {
  border: 1px solid black;
  display: inline-block;
  overflow-x: hidden;
  box-shadow: 0 5px  25px rgba(0,0,0,.5);
  border-radius: 5px;
  overflow-x: hidden;
  border: 3px solid #593f0d;
  align-items: center;
  transition: all .6s ease-in-out;
  width: 70%;
  padding-left: 20px;
  
}
.profile2 {
  padding-top: 10px;
  top: 65%;
}

.memberinfo {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-bottom: -20px;
  
}
.table {
  display: inline;
  justify-content: center;

  width: 100%;
}

.table1{
  padding-bottom: 10px;
}
h3 {
  color: #c98f23;
  font-size: 1.5rem;
}

.edit  {
  color:#554005;
  border: 1px solid rgb(156, 123, 24);
}
.logout{
  font-style: 'Poppins', sans-serif;
  color:#6f4d09;
  
  font-size: 15px;
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  left: 70%;
  top: 65%;

  box-shadow: 0 3px  15px rgba(0,0,0,.3);
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #593f0d;
  align-items: center;
  transition: all .6s ease-in-out;

}

.info {
  display: flex;
  position: relative;
  display: flex;
  flex-direction: row;
}

.title {
  text-align: center;
  font-family: 'Poppins', serif;
}

h2 {
position: relative;
text-transform: uppercase;
font-size: 2.5rem;
font-weight: 950;
padding-left: 5px;
 
}

.bg-text{
  font-family: 'Poppins', serif;
  position: absolute;
  top: 10%;
  left: 35%;
  color: #76736d;
  transition: all  .4s ease-in-out;
       z-index: -1;
       transform: translate(-50%, -50%);
       font-weight: 700;
       font-size: 2.5rem;
       filter: blur(3px)
}
span {
  color: #c9952c;
  font-weight: 400;
  font-size: 3rem;
}

@media (max-width: 414px) { 
  h1 {
  position: absolute;
  top: 15%;
  left: 15%;
  bottom: 1%;
  transform: rotate(300deg);
  font-size: 11rem;
  z-index: -1;
  color: rgba(241, 239, 239, 0.5); 
  text-shadow: 0 0 10px rgba(196, 191, 191, 0.5); 
  animation: rotate 3s linear forwards;
  z-index: -4;
}
  .profile, .profile2 {
  border: 1px solid black;
  display: inline-block;
  box-shadow: 0 5px  25px rgba(0,0,0,.5);
  border-radius: 10px;
  overflow-x: hidden;
  border: 3px solid #593f0d;
  align-items: center;
  transition: all .6s ease-in-out;
  width: 70%;
  padding-right: 25px;
  }
}

</style>