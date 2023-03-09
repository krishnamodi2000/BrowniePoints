import {Text} from 'native-base';
import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import Wrapper from '../wrapper/Wrapper';

export default function HomePage({navigator}) {
  const {user} = useSelector(state => state.user);
  console.log(user);
  return (
    <Wrapper>
      <Text color="white">Hi {user.firstName},</Text>
    </Wrapper>
  );
}
