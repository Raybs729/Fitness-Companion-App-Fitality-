<template>
  <div class="container">
    <div class="title">
      <h3>Profile</h3>
      
          <button class="logout" @click="$router.push({ name: 'logout' })">
            <font-awesome-icon icon="sign-out-alt" /> Logout
          </button>
   
    </div>
    
      <div class="profile" v-if="currentProfile">
          <div class="table1" v-if="!showUpdateForm">
            <div class="memberinfo">
              
            </div>
            <div class="memberinfo profile-photo">
              <div>
                <img :src="currentProfile.photo || defaultPhotoUrl " class="photo-preview" />
              </div>
            </div>
            <div class="memberinfo">
              <div>
                <p class="infos"> {{ currentProfile.firstName }} {{ currentProfile.lastName }}</p>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.email }}</p>
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
                <h4 class="info"> Goals: </h4>
              </div>
              <div>
                <p class="infos"> {{ currentProfile.goals }} </p>
              </div>
            </div>

            <div>
              <button class="edit" v-if="!showUpdateForm" @click="showUpdateForm = true">
                Edit
              </button>
            </div>
          </div>
          <div class="table2" div v-if="showUpdateForm">
            <div class="memberinfo update-title">
              <h3 >Update Profile</h3>
            </div>
            
            <form @submit.prevent="submitForm">
            <div class="memberinfo">  
              <label>Email:</label>
              <input type="email" v-model="profileData.email" />
            </div>
            <div class = "memberinfo">
              <label>First Name:</label>
              <input type="text" v-model="profileData.firstName" />
            </div>
            <div class = "memberinfo">
              <label>Last Name:</label>
              <input type="text" v-model="profileData.lastName" />
            </div>
            <div class = "memberinfo">
              <label>Phone:</label>
              <input type="text" v-model="profileData.phone" />
            </div>
            <div class = "memberinfo">
              <label>Height:</label>
              <input type="number" v-model="profileData.height" />
            </div>
            <div class = "memberinfo">
              <label>Weight:</label>
              <input type="number" v-model="profileData.weight" />
            </div>
            <div class = "memberinfo">
              <label>Age:</label>
              <input type="number" v-model="profileData.age" />
            </div>
            <div class = "memberinfo">
              <label>Upload Photo:</label>
              <input type="file" accept="image/*" @change="onFileChange" />
            </div>
            <!-- <label>Photo URL:</label>
            <input type="url" v-model="profileData.photo" /> -->
            <div class="memberinfo">
              <label>Goals:</label>
              <textarea v-model="profileData.goals" ></textarea>
            </div>
            <button type="submit" class="confirm">Confirm Changes</button>
            </form>
            
          </div>
        
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
      defaultPhotoUrl: "https://cdn.dribbble.com/users/6142/screenshots/5679189/media/052967c305a8f96a4b40b79ce5e61b0d.png",
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
  onFileChange(e) {
      const file = e.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.profileData.photo = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },
  },
};
</script>
<style scoped>
.container{
  position:absolute;
  width: 100vw;
  display: flex;
  flex-direction: column;
  background-color: #e7e6e9;
  align-items: center;
}
.title{
  display: flex;
  justify-content: space-between;
  background-color: white;
  margin-bottom: 10px;
  text-align: center;
  width: 100%;
  font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  color:rgb(37, 34, 34);
}
.title button{
  margin: 0px;
  padding: 10px;
  border-radius: 0;
}
.title h3{
  margin: 0px;
  padding: 10px;
}
.profile{
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
  margin-bottom: 90px;
  border-radius: 5px;
  width: 95%;
}
.table1, .table2{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding: 10px;
}
form{
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  
}
.memberinfo{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f6f5f8;
  margin-bottom: 10px;
  padding: 0px 10px;
  border-radius: 5px;
  width: 92%;
}
.profile-photo{
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}
img{
  width: 150px;
  height: 150px;
  border-radius: 50%;
}
button{
  background-color: red;
  border-radius: 20px;
  width: 150px;
  padding: 15px;
  border:none;
  color: white;
  font-size: large ;
}
button.confirm{
  width: 230px;
}
.update-title{
  justify-content: center;
}
form .memberinfo{
  padding: 10px;
}
input{
  width: 60%;
  border-radius: 5px;
  border: 1px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
}
textarea{
  width: 60%;
  border-radius: 5px;
  border: 1px solid #ccc;
}


</style>