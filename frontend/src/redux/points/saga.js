const {takeLatest, all, put} = require('redux-saga/effects');
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

function* addPointsSaga({courseId, bannerId}) {
  try {
    yield put({type: actionTypes.SET_POINTS_LOADING});

    const {data} = yield AxiosInstance.put(
      `teachers/courses/${courseId}/${bannerId}`,
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
    console.log(error.message, JSON.stringify(error));
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
