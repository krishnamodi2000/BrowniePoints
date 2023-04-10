import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

const {takeLatest, all, put} = require('redux-saga/effects');

function* addPointsSaga({courseId, bannerId}) {
  try {
    yield put({type: actionTypes.SET_POINTS_LOADING});

    const {data} = yield AxiosInstance.put(
      `/teachers/points/${courseId}/${bannerId}`,
    );

    if (data) {
      yield put({
        type: actionTypes.ADD_POINTS_SUCCESS,
        payload: data,
      });
    } else {
      yield put({
        type: actionTypes.ADD_POINTS_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    yield put({
      type: actionTypes.ADD_POINTS_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* pointsSaga() {
  yield all([yield takeLatest(actionTypes.ADD_POINTS, addPointsSaga)]);
}

export default pointsSaga;
