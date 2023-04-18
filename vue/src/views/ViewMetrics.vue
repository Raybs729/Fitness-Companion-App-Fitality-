<template>
  <div>
      <!-- <h3> View Equipment Metrics </h3>
      <form @submit.prevent="submitForm">
          <label> Equipment Name: </label>
          <select v-model="equipmentName">
              <option v-for="e in equipment" :key="e.equipmentId" :value="equipment.equipmentName"> {{equipment.equipmentName}} </option>
          </select>
          <label> Sort By: </label>
          <select v-model="intervalChoice" required>
              <option v-for="(option, index) in intervalOptions" v-bind:key="index"> {{intervalChoice}} </option>
          </select>
          <label> Choose A {{intervalChoice}}: </label>
          <input type="year" v-model="equipmentUsageDateTime" v-show="intervalChoice == 'By Year'"/>
          <input type="month"  v-model="equipmentUsageDateTime" v-show="intervalChoice == 'By Month'"/>
          <input type="date" v-model="equipmentUsageDateTime" v-show="intervalChoice == 'By Date'"/>  
          <button type="submit"> Submit </button>
      </form> -->
    <machine-usage/>
  </div>
</template>

<script>
import machineUsage from "../components/MachineUsage.vue";
export default {
    name: "ViewMetrics",
    components: {
        machineUsage
    },
    data() {
        return {
            machineMetrics: [],
            equipmentName: "",
            equipmentUsageDateTime: "",
            equipment: [],
            intervalOptions: ["Year", "Month", "Date"],
            intervalChoice: ""
        }
    },
            
    }

</script>

<style>

</style>