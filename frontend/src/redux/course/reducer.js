import * as actionTypes from './actionTypes';

const initialState = {
  courses: [],
  courseDetails: null,
  error: null,
  loading: false,
  students: [],
  studentsAdded: [],
  errorMessage: '',
};

const courses = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_COURSE_LOADING:
      return {
        ...state,
        loading: true,
        error: false,
        errorMessage: '',
        studentAdded: [],
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

    case actionTypes.ADD_STUDENTS_TO_COURSE_SUCCESS:
      return {...state, loading: false, studentAdded: action.payload};

    case actionTypes.ADD_STUDENTS_TO_COURSE_FAIL:
      return {...state, studentAdded: [], loading: false, error: true};

    case actionTypes.REMOVE_STUDENT_FROM_COURSE_SUCCESS:
      return {...state, loading: false};

    case actionTypes.REMOVE_STUDENT_FROM_COURSE_FAIL:
      return {...state, loading: false, error: true};

    case actionTypes.DELETE_COURSE_SUCCESS:
    case actionTypes.UPDATE_COURSE_SUCCESS:
      return {...state, loading: false};

    case actionTypes.DELETE_COURSE_FAIL:
    case actionTypes.UPDATE_COURSE_FAIL:
      return {
        ...state,
        loading: false,
        error: true,
        errorMessage: action.error,
      };

    default:
      return state;
  }
};

export default courses;
