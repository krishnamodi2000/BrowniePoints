const {takeLatest, all, put} = require('redux-saga/effects');
import AxiosInstance from '../../config/Axios';
import * as actionTypes from './actionTypes';

function* getCoursesSaga() {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});
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
    if (data.status) {
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

function* removeStudentFromCourseSaga({courseId, bannerId, successCallback}) {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});

    const {data} = yield AxiosInstance.delete(
      `/teachers/courses/removeStudent`,
      {
        data: {courseId, bannerId},
      },
    );

    if (data.status) {
      yield put({
        type: actionTypes.REMOVE_STUDENT_FROM_COURSE_SUCCESS,
        payload: data.data,
      });

      yield put({type: actionTypes.GET_STUDENTS_BY_COURSE, courseId});
      successCallback(bannerId);
    } else {
      yield put({
        type: actionTypes.REMOVE_STUDENT_FROM_COURSE_FAIL,
        error: 'Message from backend',
      });
    }
  } catch (error) {
    console.log(error.message, JSON.stringify(error));
    yield put({
      type: actionTypes.REMOVE_STUDENT_FROM_COURSE_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* deleteCourseSaga({courseId, success}) {
  try {
    yield put({type: actionTypes.SET_COURSE_LOADING});

    const {data} = yield AxiosInstance.delete(
      `/teachers/courses/removeCourse`,
      {
        data: {courseId},
      },
    );
    console.log(data);
    if (data.status) {
      yield put({
        type: actionTypes.DELETE_COURSE_SUCCESS,
      });
      yield put({type: actionTypes.GET_COURSES});
      success();
    } else {
      yield put({
        type: actionTypes.DELETE_COURSE_FAIL,
        error: 'Unable to delete course',
      });
    }
  } catch (error) {
    console.log(error, JSON.stringify(error));
    yield put({
      type: actionTypes.DELETE_COURSE_FAIL,
      error: 'Something went wrong',
    });
  }
}

function* courseSaga() {
  yield all([
    yield takeLatest(actionTypes.GET_COURSES, getCoursesSaga),
    yield takeLatest(actionTypes.ADD_COURSE, addCouseSaga),
    yield takeLatest(
      actionTypes.REMOVE_STUDENT_FROM_COURSE,
      removeStudentFromCourseSaga,
    ),
    yield takeLatest(
      actionTypes.GET_STUDENTS_BY_COURSE,
      getStudentsByCourseSaga,
    ),
    yield takeLatest(
      actionTypes.ADD_STUDENTS_TO_COURSE,
      addStudentsToCourseSaga,
    ),
    yield takeLatest(actionTypes.DELETE_COURSE, deleteCourseSaga),
  ]);
}

export default courseSaga;
