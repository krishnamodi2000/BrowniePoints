import AsyncStorage from '@react-native-async-storage/async-storage';
import {Text} from 'native-base';
import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import CustomCard from '../components/Commons/CustomCard';
import Header from '../components/Header/Header';
import {logoutAction} from '../redux/user/actions';
import Wrapper from '../wrapper/Wrapper';

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
      <CustomCard subjectName="CSCI5308 - Adv. Software Engineering" />
      <CustomCard subjectName="CSCI5408 - Adv. Software Engineering" />
    </Wrapper>
  );
}
