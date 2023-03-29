import * as actionTypes from './actionTypes';

const initialState = {
  courses: [],
  courseDetails: null,
  error: null,
  loading: false,
  students: [],
};

const courses = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_COURSE_LOADING:
      return {
        ...state,
        loading: true,
        error: false,
        success: false,
        students: [],
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

    case actionTypes.ADD_COURSE_SUCCESS:
      return {
        ...state,
        courseDetails: action.payload,
        loading: false,
      };

    case actionTypes.ADD_COURSE_FAIL:
      return {
        ...state,
        courseDetails: null,
        loading: false,
        error: false,
      };

    case actionTypes.GET_STUDENTS_BY_COURSE_SUCCESS:
      return {...state, students: action.payload, loading: false};

    case actionTypes.GET_STUDENTS_BY_COURSE_FAIL:
      return {...state, students: [], loading: false, error: true};

    default:
      return state;
  }
};

export default courses;
