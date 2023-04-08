const {takeLatest, all, put} = require('redux-saga/effects');
import AsyncStorage from '@react-native-async-storage/async-storage';
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

function* getUserInfoSaga() {
  try {
    yield put({type: actionTypes.SET_USER_INFO_LOADING});

    const {data} = yield AxiosInstance.get('/user');
    if (data) {
      yield put({
        type: actionTypes.GET_USER_INFO_SUCCESS,
        payload: data,
        role: data.role,
      });
    } else {
      yield put({
        type: actionTypes.GET_USER_INFO_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actionTypes.GET_USER_INFO_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* generateResetPasswordOTPSaga({email}) {
  try {
    console.log('HERE');
    yield put({type: actionTypes.SET_RESET_PASSWORD_LOADING});
    const {data} = yield AxiosInstance.post('/auth/reset-password', {email});
    if (data) {
      yield put({
        type: actionTypes.GENERATE_RESET_PASSWORD_OTP_SUCCESS,
      });
    } else {
      yield put({
        type: actionTypes.GENERATE_RESET_PASSWORD_OTP_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actionTypes.GENERATE_RESET_PASSWORD_OTP_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* validateOTPSaga({email, otp}) {
  try {
    yield put({type: actionTypes.SET_RESET_PASSWORD_LOADING});
    const {data} = yield AxiosInstance.post('/auth/reset-password-matchotp', {
      email,
      otp,
    });
    if (data) {
      yield put({
        type: actionTypes.VALIDATE_RESET_PASSWORD_OTP_SUCCESS,
      });
    } else {
      yield put({
        type: actionTypes.VALIDATE_RESET_PASSWORD_OTP_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actionTypes.VALIDATE_RESET_PASSWORD_OTP_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* logoutUserSaga() {
  try {
    yield AsyncStorage.clear();
    yield put({type: actionTypes.LOGOUT_USER_SUCCESS});
  } catch (error) {
    yield put({type: actionTypes.LOGOUT_USER_FAIL});
  }
}

function* userSaga() {
  yield all([
    yield takeLatest(actionTypes.GET_USER_INFO, getUserInfoSaga),
    yield takeLatest(actionTypes.LOGOUT_USER, logoutUserSaga),
    yield takeLatest(
      actionTypes.GENERATE_RESET_PASSWORD_OTP,
      generateResetPasswordOTPSaga,
    ),
    yield takeLatest(actionTypes.VALIDATE_RESET_PASSWORD_OTP, validateOTPSaga),
  ]);
}

export default userSaga;
