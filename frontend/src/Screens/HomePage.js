import {Text} from 'native-base';
import React from 'react';
import {useSelector} from 'react-redux';
import Header from '../components/Header/Header';
import Wrapper from '../wrapper/Wrapper';

export default function HomePage({navigator}) {
  const {user} = useSelector(state => state.user);
  console.log(user);
  return (
    <Wrapper>
      <Header title={user.firstName} />
    </Wrapper>
  );
}
