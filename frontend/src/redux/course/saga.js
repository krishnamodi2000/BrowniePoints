const {takeLatest, all, put} = require('redux-saga/effects');
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

function* getCoursesSaga({}) {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});

    //Make sure to remove hardcoded teacher ID ie 12345
    const {data} = yield AxiosInstance.get(`teachers/courses`);
    if (data) {
      yield put({
        type: actionTypes.GET_COURSES_SUCCESS,
        payload: data.courseRequestList,
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

function* addCouseSaga({courseDetails, onSuccess}) {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});

    const {data} = yield AxiosInstance.post('/teachers/courses/addCourse', {
      ...courseDetails,
    });

    if (data) {
      yield put({
        type: actionTypes.ADD_COURSE_SUCCESS,
        payload: data.data,
      });
      onSuccess();
    } else {
      yield put({
        type: actionTypes.ADD_COURSE_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error.message, JSON.stringify(error));
    yield put({
      type: actionTypes.ADD_COURSE_FAIL,
      error: 'Something went wrong',
    });
  }
}
function* getStudentsByCourseSaga({courseId}) {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});

    const {data} = yield AxiosInstance.get(
      `/teachers/courses/points/${courseId}`,
    );

    if (data.success) {
      yield put({
        type: actionTypes.GET_STUDENTS_BY_COURSE_SUCCESS,
        payload: data.data,
      });
    } else {
      yield put({
        type: actionTypes.GET_STUDENTS_BY_COURSE_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error.message, JSON.stringify(error));
    yield put({
      type: actionTypes.GET_STUDENTS_BY_COURSE_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* addStudentsToCourseSaga({courseId, bannerIds, successCallBack}) {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});
    const {data} = yield AxiosInstance.post(`/teachers/courses/addStudents`, {
      courseId,
      bannerIds,
    });

    if (data.status) {
      yield put({
        type: actionTypes.ADD_STUDENTS_TO_COURSE_SUCCESS,
        payload: data.data,
      });

      successCallBack();
    } else {
      yield put({
        type: actionTypes.ADD_STUDENTS_TO_COURSE_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error.message, JSON.stringify(error));
    yield put({
      type: actionTypes.ADD_STUDENTS_TO_COURSE_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* courseSaga() {
  yield all([
    yield takeLatest(actionTypes.GET_COURSES, getCoursesSaga),
    yield takeLatest(actionTypes.ADD_COURSE, addCouseSaga),
    yield takeLatest(
      actionTypes.GET_STUDENTS_BY_COURSE,
      getStudentsByCourseSaga,
    ),
    yield takeLatest(
      actionTypes.ADD_STUDENTS_TO_COURSE,
      addStudentsToCourseSaga,
    ),
  ]);
}

export default courseSaga;
