import React, {useState} from 'react';
import {Stack, Heading, Input, Button, Center} from 'native-base';
import Wrapper from '../wrapper/Wrapper';
import {InputType1} from '../components/Commons/Input';

const inputFields = [
  {placeholder: 'Banner ID', type: 'text', name: 'bannerId'},
  {
    placeholder: 'Password',
    type: 'password',
    secureTextEntry: true,
    name: 'password',
  },
];

const Login = () => {
  const [formData, setFormData] = useState({
    bannerId: '',
    password: '',
  });

  const handleTextChange = (name, value) => {
    setFormData(current => ({...current, [name]: value}));
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
              onPress={() => console.log(formData)}
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
