import React from 'react';
import {useSelector} from 'react-redux';
import {Box, Center, Heading, Text, Button, Avatar} from 'native-base';
import {useNavigation} from '@react-navigation/native';
import Wrapper from '../../wrapper/Wrapper';

export default function UserProfile() {
  const {user} = useSelector(state => state.user);
  const navigation = useNavigation();

  const generateInitials = () => {
    return user.firstName[0].toUpperCase() + user.lastName[0];
  };

  const handleUpdateProfile = () => {
    alert('User information will be changed');
  };

  if (!user) {
    return (
      <Wrapper>
        <Center>
          <Heading color="red.500" size="md">
            Error: User information not found!
          </Heading>
        </Center>
      </Wrapper>
    );
  }
  return (
    <Wrapper>
      <Box>
        <Center>
          <Heading size="xl" fontweight="bold" mb={12} color="secondary.200">
            User Profile
          </Heading>
          <Avatar bg="secondary.300" mr={1} size="xl">
            {generateInitials()}
          </Avatar>
          <Box
            bg="#373737"
            p={9}
            borderRadius={8}
            borderWidth={1}
            borderColor="white"
            mt={5}>
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
            <Button onPress={handleUpdateProfile}>
              <Text color="white" fontweight="bold">
                Update Profile
              </Text>
            </Button>
          </Box>
        </Center>
      </Box>
    </Wrapper>
  );
}
