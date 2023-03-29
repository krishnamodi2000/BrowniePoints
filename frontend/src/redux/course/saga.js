const {takeLatest, all, put} = require('redux-saga/effects');
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

function* getCoursesSaga({}) {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});

    //Make sure to remove hardcoded teacher ID ie 12345
    const {data} = yield AxiosInstance.get(`teachers/courses/12345`);

    if (data) {
      yield put({
        type: actionTypes.GET_COURSES_SUCCESS,
        payload: data,
      });
    } else {
      yield put({
        type: actionTypes.GET_COURSES_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error.message, JSON.stringify(error));
    yield put({
      type: actionTypes.GET_COURSES_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* courseSaga() {
  yield all([yield takeLatest(actionTypes.GET_COURSES, getCoursesSaga)]);
}

export default courseSaga;
