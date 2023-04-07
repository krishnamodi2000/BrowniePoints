import * as actionTypes from './actionTypes';

export const getCourses = () => ({
  type: actionTypes.GET_COURSES,
});

export const addCourse = (courseDetails, onSuccess) => ({
  type: actionTypes.ADD_COURSE,
  courseDetails,
  onSuccess,
});

export const updateCourse = courseDetails => ({
  type: actionTypes.UPDATE_COURSE,
  courseDetails,
});

export const deleteCourse = courseId => ({
  type: actionTypes.DELETE_COURSE,
  courseId,
});

export const getStudentsByCourseId = courseId => ({
  type: actionTypes.GET_STUDENTS_BY_COURSE,
  courseId,
});

export const addStudentsToCourse = (courseId, bannerIds, successCallBack) => ({
  type: actionTypes.ADD_STUDENTS_TO_COURSE,
  courseId,
  bannerIds,
  successCallBack,
});
