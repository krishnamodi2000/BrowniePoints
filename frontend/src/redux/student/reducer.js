import * as actionTypes from './actionTypes';

const initialState = {
  subjectInfo: [],
  error: null,
  loading: false,
};

const student = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_STUDENT_DETAILS_LOADING:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case actionTypes.GET_ENROLLED_COURSES_INFO_SUCCESS:
      return {
        ...state,
        subjectInfo: action.payload,
        loading: false,
      };
    case actionTypes.GET_ENROLLED_COURSES_INFO_FAIL:
      return {
        ...state,
        subjectInfo: [],
        loading: false,
        error: true,
      };

    default:
      return state;
  }
};

export default student;
