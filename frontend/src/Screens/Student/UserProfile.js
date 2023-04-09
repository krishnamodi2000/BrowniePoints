import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {Box, Center, Button, Avatar, FormControl} from 'native-base';
import {useNavigation} from '@react-navigation/native';
import Wrapper from '../../wrapper/Wrapper';
import Header from '../../components/Header/Header';
import {InputType1} from '../../components/Commons/Input';
import {updateProfile} from '../../redux/user/actions';

const UserProfile = () => {
  const navigation = useNavigation();
  const dispatch = useDispatch();

  const {user} = useSelector(state => state.user);
  const [profile, setProfile] = useState({firstName: '', lastName: ''});
  console.log(user);
  const generateInitials = () =>
    user.firstName[0].toUpperCase() + user.lastName[0];

  const handleUpdateProfile = () => {
    dispatch(updateProfile(user.email, profile.firstName, profile.lastName));
  };

  const inputFields = [
    {placeholder: 'First Name', type: 'text', name: 'firstName'},
    {placeholder: 'Last Name', type: 'text', name: 'lastName'},
  ];

  const handleTextChange = (name, value) => {
    setProfile(current => ({...current, [name]: value}));
  };

  useEffect(() => {
    setProfile({firstName: user.firstName, lastName: user.lastName});
  }, [user]);

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
            p={6}
            width="80%"
            borderRadius={8}
            borderWidth={1}
            borderColor="white"
            mt={10}>
            {inputFields.map((input, key) => (
              <FormControl key={key} isDisabled>
                <FormControl.Label
                  _text={{
                    color: 'secondary.100',
                    fontSize: 18,
                    fontWeight: 'bold',
                  }}>
                  {input.placeholder}
                </FormControl.Label>
                <InputType1
                  {...input}
                  value={profile[input.name] || ''}
                  onChangeText={value => handleTextChange(input.name, value)}
                  style={{color: 'white'}}
                />
              </FormControl>
            ))}
          </Box>
          <Box mt={5}>
            <Button
              onPress={handleUpdateProfile}
              _pressed={{backgroundColor: 'secondary.400'}}>
              Update Profile
            </Button>
          </Box>
        </Center>
      </Box>
    </Wrapper>
  );
};

export default UserProfile;
