export const validateEmail = emailId => {
  if (!emailId) {
    return false;
  }
  return /^[A-Za-z0-9._%+-]+@dal\.ca$/.test(emailId);
};
