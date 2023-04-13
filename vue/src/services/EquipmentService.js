import axios from 'axios';

export default {
  getEquipment() {
    return axios.get('/equipments');
  },
};
