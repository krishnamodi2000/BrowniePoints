import {useState} from 'react';
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
import Header from '../../components/Header/Header';
import {InputType1} from '../../components/Commons/Input';
import axios from 'axios';

const UserProfile = () => {
  const {user} = useSelector(state => state.user);
  const navigation = useNavigation();
  const [firstName, setFirstName] = useState(user.firstName);
  const [lastName, setLastName] = useState(user.lastName);

  const generateInitials = () =>
    user.firstName[0].toUpperCase() + user.lastName[0];

  const handleUpdateProfile = () => {
    Alert.alert('Are you sure you want to change your information');
    navigation.navigate('UpdateProfile', {user});
  };

  const inputFields = [
    {placeholder: 'First Name', type: 'text', name: 'firstName'},
    {placeholder: 'Last Name', type: 'text', name: 'lastName'},
  ];

  return (
    <Wrapper>
      <Header title="User Profile" />
      <Box>
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
            {inputFields.map(({placeholder, type, name}) => (
              <FormControl key={name} isDisabled>
                <FormControl.Label
                  _text={{
                    color: 'secondary.100',
                    fontSize: 18,
                    fontWeight: 'bold',
                  }}>
                  {placeholder}:
                </FormControl.Label>
                <FormControl.Input
                  value={user[name]}
                  borderColor="white"
                  _focus={{borderColor: 'secondary.200'}}
                />
              </FormControl>
            ))}
          </Box>
          <Box mt={5}>
            <Button
              onPress={handleUpdateProfile}
              _pressed={{backgroundColor: 'secondary.400'}}>
              <Text color="white" fontWeight="bold">
                Update Profile
              </Text>
            </Button>
          </Box>
        </Center>
      </Box>
    </Wrapper>
  );
};

export default UserProfile;
