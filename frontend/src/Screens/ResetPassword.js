import React, {useState} from 'react';
import Wrapper from '../wrapper/Wrapper';
import {
  Button,
  Center,
  FormControl,
  Heading,
  Spinner,
  Stack,
} from 'native-base';
import {InputType1} from '../components/Commons/Input';
import {useDispatch, useSelector} from 'react-redux';
import {generateResetPasswordOTP} from '../redux/user/actions';

export default function ResetPassword() {
  const dispatch = useDispatch();
  const {resetPasswordLoading, otpGenerated} = useSelector(state => state.user);
  console.log(resetPasswordLoading);
  const [emailId, setEmailId] = useState('');
  const [OTP, setOTP] = useState('');
  const [step, setStep] = useState(0);

  const generateOTP = () => {
    dispatch(generateResetPasswordOTP(emailId));
  };

  //   const matchOTP = () => {
  //     dispatch(matchOTP(emailId, OTP));
  //   };

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
        <Stack p="4" space={3} width="100%">
          {!otpGenerated && (
            <>
              <Stack space={2}>
                <FormControl>
                  <InputType1
                    placeholder="Email ID"
                    type="text"
                    name="emailId"
                    onChangeText={value => setEmailId(value)}
                  />
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
          )}
          <Stack space={2}>
            <FormControl>
              <InputType1
                placeholder="OTP"
                type="text"
                keyboardType="numeric"
                name="otp"
                onChangeText={value => setOTP(value)}
              />
            </FormControl>
          </Stack>
        </Stack>
      </Center>
    </Wrapper>
  );
}
