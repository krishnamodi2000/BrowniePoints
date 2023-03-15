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
  const handleProfilePress = () => {
    navigation.navigate('UserProfile');
  };

  return (
    <Wrapper>
      <Header
        title={user.firstName}
        menuItemsList={[
          {name: 'Profile', onPress: () => handleProfilePress()},
          {name: 'Logout', onPress: () => logout()},
        ]}
      />
      <CustomCard>
        <VStack space="2">
          <Box
            px="4"
            py="5"
            borderRadius={8}
            bg="#373737"
            borderWidth={1}
            borderColor="gray.300"
            shadow={2}
            transition="all 0.2s"
            _hover={{
              transform: 'translateY(-8px)',
              shadow: '2xl',
            }}>
            <Text fontSize={22} fontWeight="bold" color="white" mb="2">
              CSCI 5308 - Advanced Topics in Software Development
            </Text>

            <Box
              borderRadius={5}
              bg="secondary.100"
              px="3"
              py="1"
              alignSelf="flex-start"
              mb="2">
              <Text fontSize={16} fontWeight="bold" color="black">
                Points: 5
              </Text>
            </Box>
          </Box>
          <Box
            px="4"
            py="5"
            borderRadius={8}
            bg="#373737"
            borderWidth={1}
            borderColor="gray.300"
            shadow={2}
            transition="all 0.2s"
            _hover={{
              transform: 'translateY(-8px)',
              shadow: '2xl',
            }}>
            <Text fontSize={22} fontWeight="bold" color="white" mb="2">
              CSCI 5309 - Data Management Warehousing Analysis
            </Text>
            <Box
              borderRadius={5}
              bg="secondary.100"
              px="3"
              py="1"
              alignSelf="flex-start"
              mb="2">
              <Text fontSize={16} fontWeight="bold" color="black">
                Points: 2
              </Text>
            </Box>
          </Box>
        </VStack>
      </CustomCard>
    </Wrapper>
  );
}
