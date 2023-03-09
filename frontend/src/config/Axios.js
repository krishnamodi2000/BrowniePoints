import axios from 'axios';

const AxiosInstance = axios.create({
  // baseURL: 'http://192.168.2.189:8080/api/',
  baseURL: 'http://10.0.2.2:8080/api/',
});

export default AxiosInstance;
