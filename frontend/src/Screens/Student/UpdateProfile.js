import React from 'react';
import {Box, Center, Heading, Text, Button, Input} from 'native-base';
import Wrapper from '../../wrapper/Wrapper';
import {useNavigation} from '@react-navigation/native';

export default function UpdateProfile() {
  const navigation = useNavigation();

  const handleUpdateProfile = () => {
    alert('User information updated successfully');
    navigation.goback();
  };

  return (
    <Wrapper>
      <Box>
        <Center>
          <Heading size="xl" fontweight="bold" mb={12} color="secondary.200">
            Update Profile
          </Heading>
        </Center>
      </Box>
    </Wrapper>
  );
}
