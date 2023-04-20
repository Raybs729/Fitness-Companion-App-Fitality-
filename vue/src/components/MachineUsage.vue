<template>
  <div class="update">
    <div class="update-title">
      <h3> View Equipment Metrics </h3>
    </div>
    <div class="section">
      <form @submit.prevent="submitForm">
        <div class="box">
          <label> Equipment Name: </label>
          <select v-model="equipmentName">
              <option v-for="e in equipment" :key="e.equipmentId" :value="e.equipmentName"> {{e.equipmentName.toUpperCase()}} </option>
          </select>
        </div>
        <div class="box">
          <label> Sort By: </label>
          <select v-model="intervalChoice" required>
              <option v-for="(option, index) in intervalOptions" v-bind:key="index"> {{option}} </option>
          </select>
          <label v-if="intervalChoice != ''"> Choose a {{intervalChoice}}: </label> 
          <select v-show="intervalChoice == 'Year'" v-model="equipmentUsageDateTime"> 
            <option v-for="(option, index) in years" v-bind:key="index"> {{option}} </option>
          </select>
          <input type="month"  v-model="equipmentUsageDateTime" v-show="intervalChoice == 'Month'"/> 
          <input type="date" v-model="equipmentUsageDateTime" v-show="intervalChoice == 'Date'"/> 
        </div>
          
          <button type="submit"> Generate Report </button>
      </form>
      <div class="report" v-if="machineMetrics.length > 0">
        <ul>
          <li class="box" v-for="machine in machineMetrics" :key="machine.equipmentName">
            <div>Equipment Name: {{ machine.equipmentName }}</div>
            <div>Total Usages: {{ machine.totalUsage }}</div>
          </li>
        </ul>
        </div>
      <div class="report" v-else>
        <p>No machine usage</p>
      </div>
    </div>
      
  </div>
</template>

<script>
import equipmentService from "../services/EquipmentService";

export default {
  name: "MachineUsage",
  data() {
    return {
      machineMetrics: [],
      years: [],
      equipmentName: "",
      equipmentUsageDateTime: 0,
      equipment: [],
      intervalOptions: ["Year", "Month", "Date"],
      intervalChoice: ""
    };
  },
  created() {
    equipmentService.getEquipment().then((response) => {
      this.equipment = response.data;
    });
    equipmentService.getMachineMetrics(this.equipmentUsageDateTime).then((response) => {
      this.machineMetrics = response.data;
    });
    equipmentService.getYears().then((response) => {
      this.years = response.data;
    })
    
  },

  methods: {
    submitWithName() {
      equipmentService.getMachineMetricsByName(this.equipmentUsageDateTime, this.equipmentName).then((response) => {
        this.machineMetrics = response.data;
      });
    },
    submitWithoutName() {
      equipmentService.getMachineMetrics(this.equipmentUsageDateTime).then((response) => {
        this.machineMetrics = response.data;
      })
    },
    submitForm() {
      if (this.equipmentName == "") {
        this.submitWithoutName();
      } else {
        this.submitWithName();
      }
    }
  }
  
};
</script>

<style scoped>
.update{
  background-color: #e7e6e9;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: -10px;
  min-height: 100vh;
}
.update-title {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  margin-top:10px;
  margin-bottom:10px ;
  width: 100%;
}
.form{
  display: flex;
  flex-direction: column;
  align-items: center;

  width: 100%;
}
ul{
  list-style-type: none;
  width: 95%;
  padding: 0;
  margin: 0;
}
.box{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: white;
  margin-bottom: 10px;
  padding: 10px;
  
  border-radius: 5px;
  width: 95%;
}
button{
  background-color: red;
  border-radius: 20px;
  width: 200px;
  padding: 15px;
  border:none;
  color: white;
  font-size: large ;
  margin-bottom: 10px;
}
.section{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom:10px ;
  width: 95%;
}
form{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 95%;
}
input{
  width: 60%;
  border-radius: 5px;
  border: 1px solid #ccc;
  padding: 10px;
  box-sizing: border-box;
}
select{
  width: 60%;
  border: none;
  padding: 10px;
}
.report{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 95%;
}
ul{
  list-style-type: none;
  width: 100%;
  padding: 0;
  margin: 0;
}
</style>