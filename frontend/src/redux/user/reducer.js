import * as actionTypes from './actionTypes';

const initialState = {
  user: null,
  error: null,
  loading: false,
  resetPasswordLoading: false,
  role: null,
  otpGenerated: false,
};

const user = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_USER_INFO_LOADING:
      return {
        ...state,
        loading: true,
      };
    case actionTypes.SET_RESET_PASSWORD_LOADING:
      return {
        ...state,
        resetPasswordLoading: true,
      };
    case actionTypes.GET_USER_INFO_SUCCESS:
      return {
        ...state,
        user: action.payload,
        loading: false,
        role: action.role,
      };
    case actionTypes.GET_USER_INFO_FAIL:
      return {
        ...state,
        data: action.error,
        loading: false,
      };

    case actionTypes.LOGOUT_USER_SUCCESS:
      return {
        ...state,
        user: null,
        role: null,
      };

    case actionTypes.LOGOUT_USER_FAIL:
      return {
        ...state,
      };
    case actionTypes.GENERATE_RESET_PASSWORD_OTP_SUCCESS:
      return {
        ...state,
        resetPasswordLoading: false,
        otpGenerated: true,
      };
    case actionTypes.GENERATE_RESET_PASSWORD_OTP_FAIL:
      return {
        ...state,
        resetPasswordLoading: false,
        otpGenerated: false,
      };

    default:
      return state;
  }
};

export default user;
