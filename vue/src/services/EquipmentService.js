import axios from 'axios';

export default {
  getEquipment() {
    return axios.get('/equipments');
  },
  getMachineMetrics(equipmentUsageDateTime){
    return axios.get(`/equipments/usage/${equipmentUsageDateTime}`);
  },
  getMachineMetricsByName(equipmentUsageDateTime, equipmentName) {
    return axios.get(`/equipments/usage/${equipmentUsageDateTime}/${equipmentName}`);
  },
  getYears() {
    return axios.get('/equipments/years');
  }
};
