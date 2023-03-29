import * as actionTypes from './actionTypes';

const initialState = {
  courses: [],
  error: null,
  loading: false,
};

const courses = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_COURSE_LOADING:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case actionTypes.GET_COURSES_SUCCESS:
      return {
        ...state,
        courses: action.payload,
        loading: false,
      };
    case actionTypes.GET_COURSES_FAIL:
      return {
        ...state,
        courses: [],
        loading: false,
        error: true,
      };

    default:
      return state;
  }
};

export default courses;
