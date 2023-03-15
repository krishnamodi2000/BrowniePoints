import {View, Text} from 'react-native';
import React from 'react';
import {Box, VStack} from 'native-base';
import Wrapper from '../../wrapper/Wrapper';
import {
  Stack,
  Heading,
  Button,
  Center,
  Spinner,
  FormControl,
  WarningOutlineIcon,
  Avatar,
} from 'native-base';
import Header from '../../components/Header/Header';
import {useSelector} from 'react-redux';

export default function UserProfile() {
  const {user} = useSelector(state => state.user);

  const generateInitials = () => {
    return user.firstName[0].toUpperCase() + user.lastName[0];
  };

  return (
    <Wrapper>
      <Center height="30%">
        <Heading
          size="xl"
          fontSize="36"
          color="secondary.50"
          textAlign="center"
          mt="5"
          mb="5">
          User Profile
        </Heading>
        <Avatar bg="secondary.300" mr="1" size="xl">
          {generateInitials()}
        </Avatar>
        <Box pt={2}></Box>
      </Center>
    </Wrapper>
  );
}
