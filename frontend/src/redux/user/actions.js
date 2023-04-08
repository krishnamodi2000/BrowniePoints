import * as actionTypes from './actionTypes';

export const getUserInfoAction = () => ({
  type: actionTypes.GET_USER_INFO,
});

export const logoutAction = () => ({
  type: actionTypes.LOGOUT_USER,
});

export const generateResetPasswordOTP = email => ({
  type: actionTypes.GENERATE_RESET_PASSWORD_OTP,
  email,
});
