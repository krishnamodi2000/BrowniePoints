import AsyncStorage from '@react-native-async-storage/async-storage';
import axios from 'axios';

const AxiosInstance = axios.create({
  //baseURL: 'http://192.168.2.189:8080/api/',
  // baseURL: 'http://10.0.2.2:8080/api/',
  baseURL: 'http://csci5308vm3@csci5308vm3.research.cs.dal.ca:8080/api/',
});

AxiosInstance.interceptors.request.use(async request => {
  if (request.url.endsWith('login')) return request;

  if (request.url.endsWith('register')) return request;
  if (request.url.endsWith('reset-password')) return request;
  if (request.url.endsWith('reset-password-matchotp')) return request;

  const token = await AsyncStorage.getItem('token');
  request.headers.Authorization = `Bearer ${token}`;
  return request;
});

export default AxiosInstance;
