import React from 'react';
import {useSelector} from 'react-redux';
import {
  Box,
  Center,
  Heading,
  Text,
  Button,
  Avatar,
  FormControl,
} from 'native-base';
import {useNavigation} from '@react-navigation/native';
import Wrapper from '../../wrapper/Wrapper';
import UpdateProfile from './UpdateProfile';
import {Alert} from 'react-native';
import Header from '../../components/Header/Header';

export default function UserProfile() {
  const {user} = useSelector(state => state.user);
  const navigation = useNavigation();

  const generateInitials = () => {
    return user.firstName[0].toUpperCase() + user.lastName[0];
  };

  const handleUpdateProfile = () => {
    alert('Are you sure you want to change your infrormation');
    navigation.navigate('UpdateProfile');
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
        <Header title="User Profile" />
        <Center>
          <Avatar bg="secondary.300" mr={1} size="xl" mt={10}>
            {generateInitials()}
          </Avatar>
          <Box
            bg="#373737"
            p={10}
            borderRadius={8}
            borderWidth={1}
            borderColor="white"
            mt={10}>
            <FormControl isDisabled>
              <FormControl.Label
                _text={{
                  color: 'secondary.100',
                  fontSize: 18,
                  fontWeight: 'bold',
                }}>
                First Name:
              </FormControl.Label>
              <FormControl.Input
                value={user.firstName}
                borderColor="white"
                _focus={{borderColor: 'secondary.200'}}
              />
            </FormControl>
            <FormControl isDisabled>
              <FormControl.Label
                _text={{
                  color: 'secondary.100',
                  fontSize: 18,
                  fontWeight: 'bold',
                }}>
                Last Name:
              </FormControl.Label>
              <FormControl.Input
                value={user.lastName}
                borderColor="white"
                _focus={{borderColor: 'secondary.200'}}
              />
            </FormControl>
            <FormControl isDisabled>
              <FormControl.Label
                _text={{
                  color: 'secondary.100',
                  fontSize: 18,
                  fontWeight: 'bold',
                }}>
                Banner ID:
              </FormControl.Label>
              <FormControl.Input
                value={user.bannerId}
                borderColor="white"
                _focus={{borderColor: 'secondary.200'}}
              />
            </FormControl>
            <FormControl isDisabled>
              <FormControl.Label
                _text={{
                  color: 'secondary.100',
                  fontSize: 18,
                  fontWeight: 'bold',
                }}>
                Email ID:
              </FormControl.Label>
              <FormControl.Input
                value={user.emailId}
                borderColor="white"
                _focus={{borderColor: 'secondary.200'}}
              />
            </FormControl>
          </Box>
          <Box mt={5}>
            <Button
              onPress={() => handleUpdateProfile()}
              _pressed={{backgroundColor: 'secondary.400'}}>
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
