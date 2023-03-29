import * as actionTypes from './actionTypes';

export const getCourses = () => ({
  type: actionTypes.GET_COURSES,
});

export const addCourse = courseDetails => ({
  type: actionTypes.ADD_COURSE,
  courseDetails,
});

export const updateCourse = courseDetails => ({
  type: actionTypes.UPDATE_COURSE,
  courseDetails,
});

export const deleteCourse = courseId => ({
  type: actionTypes.DELETE_COURSE,
  courseId,
});
