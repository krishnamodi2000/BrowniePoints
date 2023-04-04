import * as actionTypes from './actionTypes';

export const getEnrolledCourseInfo = bannerId => ({
  type: actionTypes.GET_ENROLLED_COURSES_INFO,
  bannerId,
});
