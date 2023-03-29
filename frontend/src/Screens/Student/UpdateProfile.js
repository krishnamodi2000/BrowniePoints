import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {Box, Center, Heading, Text, Button, Input} from 'native-base';
import Wrapper from '../../wrapper/Wrapper';
import Header from '../../components/Header/Header';
import {InputType1} from '../../components/Commons/Input';

export default function UpdateProfile() {
  const dispatch = useDispatch();
  const {user} = useSelector(state => state.user);

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

  return (
    <Wrapper>
      <Header title="Update Profile" />

      <Box
        bg="#373737"
        p={10}
        borderRadius={8}
        borderWidth={1}
        borderColor="white"
        mt={10}>
        <Text color="secondary.100" fontSize={18} mb={2}>
          First Name:{' '}
          {user.firstName ? (
            <Text fontWeight="bold">{user.firstName}</Text>
          ) : (
            <Text color="red.500">Not available</Text>
          )}
        </Text>
        <Text color="secondary.100" fontSize={18} mb={2}>
          Last Name: {''}
          {user.lastName ? (
            <Text fontWeight="bold">{user.lastName}</Text>
          ) : (
            <Text color="red.500">Not available</Text>
          )}
        </Text>
        <Text color="secondary.100" fontSize={18} mb={2}>
          Banner ID: <Text fontWeight="bold">{user.bannerId}</Text>
        </Text>
        <Text color="secondary.100" fontSize={18} mb={2}>
          Email ID: <Text fontWeight="bold">{user.emailId}</Text>
        </Text>
      </Box>
      <Box mt={5}>
        <Button
          onPress={() => handleSaveChanges()}
          _pressed={{backgroundColor: 'secondary.400'}}>
          <Text color="white" fontweight="bold">
            Save Changes
          </Text>
        </Button>
      </Box>
    </Wrapper>
  );
}
