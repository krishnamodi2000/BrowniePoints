import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {Box, Center, Heading, Text, Button, FormControl} from 'native-base';
import {useNavigation} from '@react-navigation/native';
import Wrapper from '../../wrapper/Wrapper';
import Header from '../../components/Header/Header';
import {InputType1} from '../../components/Commons/Input';
import axios from 'axios';

export default function UpdateProfile() {
  const dispatch = useDispatch();
  const {user} = useSelector(state => state.user);
  const navigation = useNavigation();

  const [firstName, setFirstName] = useState(user.firstName);
  const [lastName, setLastName] = useState(user.lastName);
  const [bannerId, setBannerId] = useState(user.bannerId);
  const [emailId, setEmailId] = useState(user.emailId);

  const handleSaveChanges = () => {
    dispatch({
      type: 'UPDATE_USER_INFO',
      payload: {
        firstName,
        lastName,
        bannerId,
        emailId,
      },
    });
    alert('Profile updated successfully!');
    navigation.goBack();
  };

  const inputFields = [
    {placeholder: 'First Name', type: 'text', name: 'firstName'},
    {placeholder: 'Last Name', type: 'text', name: 'lastName'},
    {placeholder: 'Banner ID', type: 'text', name: 'bannerId'},
    {placeholder: 'Email ID', type: 'email', name: 'emailId'},
  ];

  return (
    <Wrapper>
      <Header title="Update Profile" />
      <Box>
        <Center>
          <Box
            bg="#373737"
            p={10}
            borderRadius={8}
            borderWidth={1}
            borderColor="white"
            mt={10}>
            {inputFields.map(({placeholder, type, name}) => (
              <FormControl key={name}>
                <FormControl.Label
                  _text={{
                    color: 'secondary.100',
                    fontSize: 18,
                    fontWeight: 'bold',
                  }}>
                  {placeholder}:
                </FormControl.Label>
                <InputType1
                  type={type}
                  value={user[name]}
                  onChangeText={value => {
                    switch (name) {
                      case 'firstName':
                        setFirstName(value);
                        break;
                      case 'lastName':
                        setLastName(value);
                        break;
                      case 'bannerId':
                        setBannerId(value);
                        break;
                      case 'emailId':
                        setEmailId(value);
                        break;
                    }
                  }}
                />
              </FormControl>
            ))}
          </Box>
          <Box mt={5}>
            <Button
              onPress={() => handleSaveChanges()}
              _pressed={{backgroundColor: 'secondary.400'}}>
              <Text color="white" fontWeight="bold">
                Save Changes
              </Text>
            </Button>
          </Box>
        </Center>
      </Box>
    </Wrapper>
  );
}
