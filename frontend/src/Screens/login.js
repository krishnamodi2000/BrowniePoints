import React, {useState} from 'react';
import Wrapper from '../wrapper/Wrapper';
import {InputType1} from '../components/Commons/Input';
import {
  Stack,
  Heading,
  Input,
  Button,
  Center,
  Spinner,
  FormControl,
  WarningOutlineIcon,
  ScrollView,
} from 'native-base';
import axios from 'axios';

const inputFields = [
  {placeholder: 'Email ID', type: 'text', name: 'emailId'},
  {
    placeholder: 'Password',
    type: 'password',
    secureTextEntry: true,
    name: 'password',
  },
];

const Login = () => {
  const [formData, setFormData] = useState({
    emailId: '',
    password: '',
  });
  const [loader, setLoader] = useState(false);
  const [errors, setErrors] = useState({

    emailId: '',
    password: '',
  
  });
  const [alert, setAlert] = useState('');

  const isValidated = () => {
    const errorObj = {
      emailId: '',
      password: '',
    };
    let isValid = true;

    if (formData.emailId.length <= 0) {
      isValid = false;
      errorObj.emailId = 'Please enter valid email address';
      //Enter email validation using regex
    }

    if (formData.password.length < 6) {
      isValid = false;
      errorObj.password = 'Please enter valid password';
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
        .post('http://api/auth/authenticate', {
          email: formData.emailId,
          password: formData.password,
        })
        .then(res => {
          if (res.data) {
          }
        })
        .catch(e => {
          setAlert('Unable to Login the user.');
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
        <Heading
          size="lg"
          fontSize="36"
          color="secondary.300"
          textAlign="center"
          mt="5"
          mb="5">
          Login
        </Heading>

        <Stack p="4" space={3} width="100%">
          {inputFields.map((inputField, key) => (
            <Stack space={2} key={key}>
              <InputType1
                {...inputField}
                onChangeText={value => handleTextChange(inputField.name, value)}
              />
            </Stack>
          ))}

          <Stack space={2}>
            <Button
              size="lg"
              background="secondary.400"
              onPress={() => handleSubmit()}
              _pressed={{backgroundColor: 'secondary.500'}}>
              login
            </Button>
          </Stack>
        </Stack>
      </Center>
    </Wrapper>
  );
};
export default Login;
