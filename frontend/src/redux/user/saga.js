import AsyncStorage from '@react-native-async-storage/async-storage';
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

const {takeLatest, all, put} = require('redux-saga/effects');

function* getUserInfoSaga() {
  try {
    yield put({type: actionTypes.SET_USER_INFO_LOADING});
    const {data} = yield AxiosInstance.get('/user');
    if (data.role) {
      yield put({
        type: actionTypes.GET_USER_INFO_SUCCESS,
        payload: data,
        role: data.role,
      });
    } else {
      yield put({
        type: actionTypes.GET_USER_INFO_FAIL,
        error: data.message,
      });
    }
  } catch (error) {
    yield put({
      type: actionTypes.GET_USER_INFO_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* generateResetPasswordOTPSaga({email}) {
  try {
    yield put({type: actionTypes.SET_RESET_PASSWORD_LOADING});
    const {data} = yield AxiosInstance.post('/auth/reset-password', {email});
    if (data.status) {
      yield put({
        type: actionTypes.GENERATE_RESET_PASSWORD_OTP_SUCCESS,
      });
    } else {
      yield put({
        type: actionTypes.GENERATE_RESET_PASSWORD_OTP_FAIL,
        error: data.message,
      });
    }
  } catch (error) {
    yield put({
      type: actionTypes.GENERATE_RESET_PASSWORD_OTP_FAIL,
      error: error?.response?.data?.message || 'Something went wrong',
    });
  }
}

function* resetPasswordSaga({email, otp, newPassword, success}) {
  try {
    yield put({type: actionTypes.SET_RESET_PASSWORD_LOADING});
    const {data} = yield AxiosInstance.post('/auth/reset-password-matchotp', {
      email,
      otp,
      newPassword,
    });
    if (data.status) {
      yield put({
        type: actionTypes.RESET_PASSWORD_SUCCESS,
      });
      success();
    } else {
      yield put({
        type: actionTypes.RESET_PASSWORD_FAIL,
        error: data.message,
      });
    }
  } catch (error) {
    yield put({
      type: actionTypes.RESET_PASSWORD_FAIL,
      error: error?.response?.data?.message || 'Something went wrong',
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

function* updateProfileSaga({email, firstName, lastName, successCallBack}) {
  try {
    yield put({type: actionTypes.UPDATE_PROFILE_LOADING});
    const {data} = yield AxiosInstance.put(`/user/${email}`, {
      firstName,
      lastName,
    });
    if (data) {
      yield put({
        type: actionTypes.UPDATE_PROFILE_SUCCESS,
        payload: {firstName, lastName},
      });
      successCallBack();
    } else {
      yield put({
        type: actionTypes.UPDATE_PROFILE_FAIL,
        error: data.message,
      });
    }
  } catch (error) {
    yield put({
      type: actionTypes.UPDATE_PROFILE_FAIL,
      error: error?.response?.data?.message || 'Something went wrong',
    });
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
    yield takeLatest(actionTypes.UPDATE_PROFILE, updateProfileSaga),
  ]);
}

export default userSaga;
