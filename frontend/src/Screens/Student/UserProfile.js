import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {Box, Center, Button, Avatar, FormControl, Spinner} from 'native-base';
import Wrapper from '../../wrapper/Wrapper';
import Header from '../../components/Header/Header';
import {InputType1} from '../../components/Commons/Input';
import {updateProfile} from '../../redux/user/actions';
import CustomModal from '../../components/Commons/CustomModal';

const UserProfile = () => {
  const dispatch = useDispatch();

  const {user, updateProfileLoading} = useSelector(state => state.user);
  const [profile, setProfile] = useState({firstName: '', lastName: ''});
  const [showSuccessModal, setShowSuccessModal] = useState(false);

  const generateInitials = () =>
    user.firstName[0].toUpperCase() + user.lastName[0];

  const handleUpdateProfile = () => {
    dispatch(
      updateProfile(user.email, profile.firstName, profile.lastName, () => {
        setShowSuccessModal(true);
      }),
    );
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
      <CustomModal
        showModal={showSuccessModal}
        setShowModal={setShowSuccessModal}
        heading="Profile Updated"
        body={
          <Center>
            Successfully updated the profile
            <Button
              mt="2"
              minWidth="100"
              onPress={() => setShowSuccessModal(false)}>
              Back
            </Button>
          </Center>
        }
      />
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
            {updateProfileLoading ? (
              <Spinner color="secondary.500" size="lg" />
            ) : (
              <Button
                onPress={handleUpdateProfile}
                _pressed={{backgroundColor: 'secondary.400'}}>
                Update Profile
              </Button>
            )}
          </Box>
        </Center>
      </Box>
    </Wrapper>
  );
};

export default UserProfile;
