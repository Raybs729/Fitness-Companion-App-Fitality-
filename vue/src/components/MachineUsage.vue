<template>
  <div>
    <h3>Machine Metrics:</h3>
    <div v-if="machineMetrics.length > 0">
      <ul>
        <li v-for="machine in machineMetrics" :key="machine.equipmentName">
          <div>Equipment Name: {{ machine.equipmentName }}</div>
          <div>Total Usage: {{ machine.totalUsage }}</div>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>No machine usage</p>
    </div>
  </div>
</template>

<script>
import EquipmentService from "../services/EquipmentService";

export default {
  name: "MachineUsage",
  data() {
    return {
      machineMetrics: [],
    };
  },
  created() {
    this.fetchMachineMetrics();
  },
  methods: {
    fetchMachineMetrics() {
      EquipmentService.getMachineMetrics().then((response) => {
        this.machineMetrics = response.data;
      });
    },
  },
};
</script>