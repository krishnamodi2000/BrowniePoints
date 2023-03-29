import * as actionTypes from './actionTypes';

export const addPoints = (courseId, bannerId) => ({
  type: actionTypes.ADD_POINTS,
  courseId,
  bannerId,
});
