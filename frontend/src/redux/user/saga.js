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
    console.log(error, JSON.stringify(error));
    yield put({
      type: actionTypes.GENERATE_RESET_PASSWORD_OTP_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* resetPasswordSaga({email, otp, newPassword}) {
  try {
    yield put({type: actionTypes.SET_RESET_PASSWORD_LOADING});
    const {data} = yield AxiosInstance.post('/auth/reset-password-matchotp', {
      email,
      otp,
      newPassword,
    });
    console.log(data);
    if (data) {
      yield put({
        type: actionTypes.RESET_PASSWORD_SUCCESS,
      });
    } else {
      yield put({
        type: actionTypes.RESET_PASSWORD_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error);
    yield put({
      type: actionTypes.RESET_PASSWORD_FAIL,
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
    yield takeLatest(actionTypes.RESET_PASSWORD, resetPasswordSaga),
  ]);
}

export default userSaga;
