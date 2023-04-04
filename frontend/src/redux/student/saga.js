const {takeLatest, all, put} = require('redux-saga/effects');
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

function* getEnrolledCoursesInfoSaga({bannerId}) {
  try {
    yield put({type: actionTypes.SET_STUDENT_DETAILS_LOADING});

    const {data} = yield AxiosInstance.get(
      `/teachers/courses/studentPoints/${bannerId}`,
    );
    if (data) {
      yield put({
        type: actionTypes.GET_ENROLLED_COURSES_INFO_SUCCESS,
        payload: data,
      });
    } else {
      yield put({
        type: actionTypes.GET_ENROLLED_COURSES_INFO_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error.message, JSON.stringify(error));
    yield put({
      type: actionTypes.GET_ENROLLED_COURSES_INFO_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* studentSaga() {
  yield all([
    yield takeLatest(
      actionTypes.GET_ENROLLED_COURSES_INFO,
      getEnrolledCoursesInfoSaga,
    ),
  ]);
}

export default studentSaga;
