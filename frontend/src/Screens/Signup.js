import React, {useState} from 'react';
import {
  Stack,
  Heading,
  Input,
  Button,
  Center,
  Spinner,
  FormControl,
  WarningOutlineIcon,
} from 'native-base';
import Wrapper from '../wrapper/Wrapper';
import {InputType1} from '../components/Commons/Input';
import axios from 'axios';
import {CustomAlert} from '../components/Commons/CustomAlert';

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

const Signup = () => {
  const [formData, setFormData] = useState({
    bannerId: '',
    emailId: '',
    password: '',
    contactNumber: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
  });
  const [loader, setLoader] = useState(false);
  const [errors, setErrors] = useState({
    bannerId: '',
    emailId: '',
    password: '',
    contactNumber: '',
    confirmPassword: '',
    firstName: '',
    lastName: '',
  });
  const [alert, setAlert] = useState('');

  const isValidated = () => {
    const errorObj = {
      bannerId: '',
      emailId: '',
      password: '',
      contactNumber: '',
      confirmPassword: '',
      firstName: '',
      lastName: '',
    };
    let isValid = true;

    if (formData.bannerId.length <= 0) {
      isValid = false;
      errorObj.bannerId = 'Please enter valid banner Id';
    }

    if (formData.emailId.length <= 0) {
      isValid = false;
      errorObj.emailId = 'Please enter valid email address';
      //Enter email validation using regex
    }

    if (formData.password.length < 6) {
      isValid = false;
      errorObj.password = 'Please enter valid password';
    }

    if (formData.password === formData.confirmPassword) {
      isValid = false;
      errorObj.confirmPassword = 'Password do not match';
    }

    if (formData.firstName.length <= 3) {
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
      axios
        .post('http://10.0.2.2:8080/api/auth/register', {
          email: formData.emailId,
          firstName: formData.firstName,
          lastName: formData.lastName,
          contactNumber: Math.random(0) * 1020,
          password: formData.password,
          role: 'ROLE_STUDENT',
          isAuthenticated: true,
        })
        .then(res => {
          if (res.data) {
          }
        })
        .catch(e => {
          setAlert('Unable to register the user.');
          console.log(JSON.stringify(e));
        })
        .finally(() => {
          setLoader(false);
        });
    }
  };

  return (
    <Wrapper>
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
                Reigister
              </Button>
            )}
          </Stack>
        </Stack>
      </Center>
    </Wrapper>
  );
};

export default Signup;