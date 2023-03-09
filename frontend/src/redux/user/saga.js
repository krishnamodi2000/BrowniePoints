const {takeLatest, all, put} = require('redux-saga/effects');
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

function* getUserInfoSaga() {
  try {
    yield put({type: actionTypes.SET_USER_INFO_LOADING});

    const {data} = yield AxiosInstance.post('/user');
    if (data) {
      yield put({type: actionTypes.GET_USER_INFO_SUCCESS, payload: data});
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

function* userSaga() {
  yield all([yield takeLatest(actionTypes.GET_USER_INFO, getUserInfoSaga)]);
}

export default userSaga;
