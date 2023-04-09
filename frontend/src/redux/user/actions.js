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

export const changePassword = (email, newPassword, otp, success) => ({
  type: actionTypes.RESET_PASSWORD,
  email,
  newPassword,
  otp,
  success,
});

export const resetPasswordChangeFlow = () => ({
  type: actionTypes.RESET_PASSWORD_CHANGE_FLOW,
});

export const updateProfile = (email, firstName, lastName, successCallBack) => ({
  type: actionTypes.UPDATE_PROFILE,
  firstName,
  lastName,
  email,
  successCallBack,
});
