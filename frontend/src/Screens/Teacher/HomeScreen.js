import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import CustomCard from '../../components/Commons/CustomCard';
import Header from '../../components/Header/Header';
import {logoutAction} from '../../redux/user/actions';
import Wrapper from '../../wrapper/Wrapper';

export default function HomeScreen({navigation}) {
  const {user} = useSelector(state => state.user);
  const dispatch = useDispatch();

  const logout = () => {
    dispatch(logoutAction());
  };

  return (
    <Wrapper>
      <Header
        title={'TEACHER'}
        menuItemsList={[
          {name: 'Profile', onPress: () => console.log('Profile Clicked')},
          {name: 'Logout', onPress: () => logout()},
        ]}
      />
    </Wrapper>
  );
}
