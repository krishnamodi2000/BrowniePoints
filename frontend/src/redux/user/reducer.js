import * as actionTypes from './actionTypes';

const initialState = {
  user: null,
  error: null,
  loading: false,
};

const user = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_USER_INFO_LOADING:
      return {
        ...state,
        loading: true,
      };
    case actionTypes.GET_USER_INFO_SUCCESS:
      return {
        ...state,
        user: action.payload,
      };
    case actionTypes.GET_USER_INFO_FAIL:
      return {
        ...state,
        data: action.error,
      };

    default:
      return state;
  }
};

export default user;
