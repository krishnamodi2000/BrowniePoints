import {Box, Text, VStack} from 'native-base';
import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import CustomCard from '../../components/Commons/CustomCard';
import Header from '../../components/Header/Header';
import {logoutAction} from '../../redux/user/actions';
import Wrapper from '../../wrapper/Wrapper';

export default function HomePage({navigation}) {
  const {user} = useSelector(state => state.user);
  const dispatch = useDispatch();

  const logout = () => {
    dispatch(logoutAction());
  };

  return (
    <Wrapper>
      <Header
        title={user.firstName}
        menuItemsList={[
          {name: 'Profile', onPress: () => console.log('Profile Clicked')},
          {name: 'Logout', onPress: () => logout()},
        ]}
      />
      <CustomCard>
        <VStack space="2">
          <Box px="2">
            <Text fontSize={20} fontWeight="bold" color="white">
              CSCI 5308 - Adv. Topic in software Development
            </Text>
          </Box>
          <Box px="2">
            <Text color={'secondary.500'}> Points : 5</Text>
          </Box>
        </VStack>
      </CustomCard>
    </Wrapper>
  );
}
