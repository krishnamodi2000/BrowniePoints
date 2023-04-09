import React, {useState} from 'react';
import {
  Button,
  Center,
  FormControl,
  Heading,
  Spinner,
  Stack,
  WarningOutlineIcon,
} from 'native-base';
import {useDispatch, useSelector} from 'react-redux';
import {useNavigation} from '@react-navigation/native';
import Wrapper from '../wrapper/Wrapper';
import {InputType1} from '../components/Commons/Input';
import {
  generateResetPasswordOTP,
  changePassword,
  resetPasswordChangeFlow,
} from '../redux/user/actions';
import {CustomAlert} from '../components/Commons/CustomAlert';
import CustomModal from '../components/Commons/CustomModal';
import {validateEmail} from '../helpers/functions';

export default function ResetPassword() {
  const dispatch = useDispatch();
  const navigation = useNavigation();

  const {resetPasswordLoading, otpGenerated, error, errorMessage} = useSelector(
    state => state.user,
  );

  const [emailId, setEmailId] = useState('');
  const [OTP, setOTP] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  const [errors, setErrors] = useState({
    email: '',
    OTP: '',
    newPassword: '',
  });

  const isEmailValid = () => {
    const errorObj = {
      email: '',
      OTP: '',
      newPassword: '',
    };
    let isValid = true;

    if (!validateEmail(emailId)) {
      isValid = false;
      errorObj.email = 'Please enter valid email address';
    }

    setErrors(errorObj);
    return isValid;
  };

  const generateOTP = () => {
    if (isEmailValid()) dispatch(generateResetPasswordOTP(emailId));
  };

  const isOTPandPasswordValid = () => {
    const errorObj = {
      email: '',
      OTP: '',
      newPassword: '',
    };
    let isValid = true;

    if (OTP.length !== 6) {
      isValid = false;
      errorObj.OTP = 'Please enter valid OTP';
    }

    if (newPassword.length < 6) {
      isValid = false;
      errorObj.newPassword = 'Please enter valid password';
    }

    setErrors(errorObj);
    return isValid;
  };

  const handleChangePassword = () => {
    if (isOTPandPasswordValid())
      dispatch(
        changePassword(emailId, newPassword, OTP, () => {
          setShowSuccessModal(true);
        }),
      );
  };

  const handleNavigateToLogin = () => {
    navigation.navigate('Login');
    setEmailId('');
    setOTP('');
    setNewPassword('');
    dispatch(resetPasswordChangeFlow());
  };

  return (
    <Wrapper>
      <Center height="100%">
        <Heading
          size="lg"
          fontSize="36"
          color="secondary.300"
          textAlign="center"
          mt="5"
          mb="5">
          Reset Password
        </Heading>
        <CustomAlert
          message={errorMessage}
          open={error}
          status="error"
          noClose={true}
        />
        <Stack p="4" space={3} width="100%">
          {!otpGenerated ? (
            <>
              <Stack space={2}>
                <FormControl isInvalid={Boolean(errors.email)}>
                  <InputType1
                    placeholder="Email ID"
                    type="text"
                    name="emailId"
                    onChangeText={value => setEmailId(value)}
                  />
                  <FormControl.ErrorMessage
                    leftIcon={<WarningOutlineIcon size="xs" />}>
                    {errors.email}
                  </FormControl.ErrorMessage>
                </FormControl>
              </Stack>
              <Stack space={2}>
                {resetPasswordLoading ? (
                  <Spinner color="secondary.500" size="lg" />
                ) : (
                  <Button
                    size="lg"
                    background="secondary.400"
                    onPress={() => generateOTP()}
                    _pressed={{backgroundColor: 'secondary.500'}}>
                    Generate OTP
                  </Button>
                )}
              </Stack>
            </>
          ) : (
            <>
              <Stack space={2}>
                <FormControl isInvalid={Boolean(errors.OTP)}>
                  <InputType1
                    placeholder="OTP"
                    type="text"
                    keyboardType="numeric"
                    name="otp"
                    onChangeText={value => setOTP(value)}
                  />
                  <FormControl.ErrorMessage
                    leftIcon={<WarningOutlineIcon size="xs" />}>
                    {errors.OTP}
                  </FormControl.ErrorMessage>
                </FormControl>
              </Stack>
              <Stack space={2}>
                <FormControl isInvalid={Boolean(errors.newPassword)}>
                  <InputType1
                    placeholder="New Password"
                    type="password"
                    secureTextEntry={true}
                    name="confirmPassword"
                    onChangeText={value => setNewPassword(value)}
                  />
                  <FormControl.ErrorMessage
                    leftIcon={<WarningOutlineIcon size="xs" />}>
                    {errors.newPassword}
                  </FormControl.ErrorMessage>
                </FormControl>
              </Stack>
              <Stack space={2}>
                {resetPasswordLoading ? (
                  <Spinner color="secondary.500" size="lg" />
                ) : (
                  <Button
                    size="lg"
                    background="secondary.400"
                    onPress={() => handleChangePassword()}
                    _pressed={{backgroundColor: 'secondary.500'}}>
                    Submit
                  </Button>
                )}
              </Stack>
            </>
          )}
        </Stack>
      </Center>
      <CustomModal
        showModal={showSuccessModal}
        setShowModal={setShowSuccessModal}
        heading="Success"
        body={
          <Center>
            Passoword has been reset. Please Login
            <Button mt="2" minWidth="100" onPress={handleNavigateToLogin}>
              Login
            </Button>
          </Center>
        }
      />
    </Wrapper>
  );
}
