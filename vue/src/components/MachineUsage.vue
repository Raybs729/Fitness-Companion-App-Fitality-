<template>
  <div>
    <h3> View Equipment Metrics </h3>
      <form @submit.prevent="submitForm">
          <label> Equipment Name: </label>
          <select v-model="equipmentName">
              <option v-for="e in equipment" :key="e.equipmentId" :value="e.equipmentName"> {{e.equipmentName.toUpperCase()}} </option>
          </select>
          <label> Sort By: </label>
          <select v-model="intervalChoice" required>
              <option v-for="(option, index) in intervalOptions" v-bind:key="index"> {{option}} </option>
          </select>
          <label v-if="intervalChoice != ''"> Choose A {{intervalChoice}}: </label> &nbsp;
          <select v-show="intervalChoice == 'Year'" v-model="equipmentUsageDateTime"> &nbsp;
            <option v-for="(option, index) in years" v-bind:key="index"> {{option}} </option>
          </select>
          <input type="month"  v-model="equipmentUsageDateTime" v-show="intervalChoice == 'Month'"/> &nbsp;
          <input type="date" v-model="equipmentUsageDateTime" v-show="intervalChoice == 'Date'"/> &nbsp;
          <button type="submit"> Generate Report </button>
      </form>
    <div v-if="machineMetrics.length > 0">
      <div>
      <ul>
        <li v-for="machine in machineMetrics" :key="machine.equipmentName">
          <div>Equipment Name: {{ machine.equipmentName }}</div>
          <div>Total Usages: {{ machine.totalUsage }}</div>
        </li>
      </ul>
      </div>
      </div>
    <div v-else>
      <p>No machine usage</p>
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