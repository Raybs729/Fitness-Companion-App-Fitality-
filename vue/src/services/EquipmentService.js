import axios from 'axios';

export default {
  getEquipment() {
    return axios.get('/equipments');
  },
  getMachineMetrics(){
    return axios.get('/equipments/usage');
  }
};
