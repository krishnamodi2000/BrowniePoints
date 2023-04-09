import React, {useState} from 'react';
import {
  Stack,
  Heading,
  Button,
  Center,
  Spinner,
  FormControl,
  WarningOutlineIcon,
  ScrollView,
} from 'native-base';
import Wrapper from '../wrapper/Wrapper';
import {InputType1} from '../components/Commons/Input';
import {CustomAlert} from '../components/Commons/CustomAlert';
import CustomModal from '../components/Commons/CustomModal';
import {validateEmail} from '../helpers/functions';
import Axios from '../config/Axios';

const inputFields = [
  {
    placeholder: 'Banner ID',
    type: 'text',
    name: 'bannerId',
  },
  {
    placeholder: 'First Name',
    type: 'text',
    name: 'firstName',
  },
  {
    placeholder: 'Last Name',
    type: 'text',
    name: 'lastName',
  },
  {
    placeholder: 'Email',
    type: 'email',
    name: 'emailId',
  },
  {
    placeholder: 'Password',
    type: 'password',
    secureTextEntry: true,
    name: 'password',
  },
  {
    placeholder: 'Confirm Password',
    type: 'password',
    secureTextEntry: true,
    name: 'confirmPassword',
  },
];

const dataObj = {
  bannerId: '',
  emailId: '',
  password: '',
  contactNumber: '',
  confirmPassword: '',
  firstName: '',
  lastName: '',
};

const Signup = ({navigation}) => {
  const [formData, setFormData] = useState({...dataObj});
  const [loader, setLoader] = useState(false);
  const [errors, setErrors] = useState({
    ...dataObj,
  });
  const [alert, setAlert] = useState('');
  const [showSuccessModal, setShowSuccessModal] = useState(false);

  const isValidated = () => {
    const errorObj = {
      ...dataObj,
    };
    let isValid = true;

    if (formData.bannerId.length <= 0) {
      isValid = false;
      errorObj.bannerId = 'Please enter valid banner Id';
    }

    if (!validateEmail(formData.emailId)) {
      isValid = false;
      errorObj.emailId = 'Please enter valid email address';
    }

    if (formData.password.length < 6) {
      isValid = false;
      errorObj.password = 'Please enter valid password';
    }

    if (formData.password !== formData.confirmPassword) {
      isValid = false;
      errorObj.confirmPassword = 'Password do not match';
    }

    if (formData.firstName.length <= 1) {
      isValid = false;
      errorObj.firstName = 'Please enter valid first name';
    }

    if (formData.lastName.length <= 3) {
      isValid = false;
      errorObj.lastName = 'Please enter valid last name';
    }

    setErrors(errorObj);
    return isValid;
  };

  const handleTextChange = (name, value) => {
    setFormData(current => ({...current, [name]: value}));
  };

  const handleSubmit = () => {
    if (isValidated()) {
      setLoader(true);
      Axios.post('/auth/register', {
        email: formData.emailId,
        firstName: formData.firstName,
        lastName: formData.lastName,
        password: formData.password,
        role: 'ROLE_STUDENT',
        userId: formData.bannerId,
      })
        .then(res => {
          if (res.data) {
            setShowSuccessModal(true);
          }
        })
        .catch(() => {
          setAlert('Unable to register the user.');
        })
        .finally(() => {
          setLoader(false);
        });
    }
  };

  const navigateToLogin = () => {
    navigation.navigate('Login');
  };

  const resetState = () => {
    setErrors({...dataObj});
    setFormData({...dataObj});
  };

  const handleLoginPress = () => {
    navigateToLogin();
    resetState();
  };

  return (
    <Wrapper>
      <ScrollView
        contentContainerStyle={{flexGrow: 1, justifyContent: 'center'}}>
        <CustomModal
          showModal={showSuccessModal}
          setShowModal={setShowSuccessModal}
          heading="Registration Successfull"
          body={
            <Center>
              Please Login to your account.
              <Button mt="2" minWidth="100" onPress={handleLoginPress}>
                Login
              </Button>
            </Center>
          }
        />
        <Center height="100%">
          <CustomAlert
            message={alert}
            open={Boolean(alert)}
            onClose={() => setAlert('')}
            status="error"
          />
          <Heading
            size="lg"
            fontSize="36"
            color="secondary.300"
            textAlign="center"
            mt="5"
            mb="5">
            User Registration
          </Heading>

          <Stack p="4" space={3} width="100%">
            {inputFields.map((inputField, key) => (
              <Stack space={2} key={key}>
                <FormControl isInvalid={errors[inputField.name]}>
                  <InputType1
                    {...inputField}
                    isDisabled={loader}
                    isReadOnly={loader}
                    onChangeText={value =>
                      handleTextChange(inputField.name, value)
                    }
                  />
                  <FormControl.ErrorMessage
                    leftIcon={<WarningOutlineIcon size="xs" />}>
                    {errors[inputField.name]}
                  </FormControl.ErrorMessage>
                </FormControl>
              </Stack>
            ))}

            <Stack space={2}>
              {loader ? (
                <Spinner color="secondary.500" size="lg" />
              ) : (
                <Button
                  size="lg"
                  background="secondary.400"
                  onPress={handleSubmit}
                  _pressed={{backgroundColor: 'secondary.500'}}>
                  Register
                </Button>
              )}
            </Stack>
          </Stack>
        </Center>
      </ScrollView>
    </Wrapper>
  );
};

export default Signup;
