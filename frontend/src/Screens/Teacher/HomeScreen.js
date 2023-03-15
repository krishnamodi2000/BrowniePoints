import {Button, ScrollView, Text, VStack} from 'native-base';
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

  const navigateToCouse = () => {
    navigation.navigate('Course');
  };

  return (
    <Wrapper>
      <Header
        title={user.firstName}
        menuItemsList={[
          {name: 'Profile', onPress: () => console.log('Profile Clicked')},
          {name: 'Add Course', onPress: () => navigateToCouse()},
          {name: 'Logout', onPress: () => logout()},
        ]}
      />
      <ScrollView>
        <CustomCard>
          <CardContent
            courseCode="5308"
            courseName="Adv. Topic in software development"
          />
        </CustomCard>
      </ScrollView>
    </Wrapper>
  );
}

const CardContent = ({courseCode, courseName}) => {
  return (
    <VStack>
      <Text fontSize={20} fontWeight="bold" color="white">
        {courseCode} - {courseName}
      </Text>
      <Button
        mt={3}
        background="secondary.400"
        _pressed={{backgroundColor: 'secondary.600'}}>
        Scan QR
      </Button>
      <Button
        mt={3}
        background="primary.200"
        _pressed={{backgroundColor: 'primary.600'}}>
        Edit Information
      </Button>
    </VStack>
  );
};
