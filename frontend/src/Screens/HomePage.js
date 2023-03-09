import {Text, View} from 'react-native';
import React, {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {getUserInfoAction} from '../redux/user/actions';

export default function HomePage({navigator}) {
  const {user} = useSelector(state => state.user);
  const dispatch = useDispatch();
  console.log(user);

  useEffect(() => {
    dispatch(getUserInfoAction());
  }, [dispatch]);

  return (
    <View>
      <Text>Welcome User</Text>
    </View>
  );
}
