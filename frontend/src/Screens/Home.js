import {View} from 'react-native';
import React from 'react';
import Wrapper from '../wrapper/Wrapper';
import Logo from '../assets/Logo.png';
import {Center, Flex, Image, VStack, Text, Button} from 'native-base';

const Home = ({navigation}) => {
  return (
    <Wrapper>
      <Center style={{height: '100%'}}>
        <Image
          size={150}
          alt="fallback text"
          borderRadius={100}
          source={Logo}
          padding="20"
        />
        <Text color="#fbfcf8" fontSize="28">
          Brownie Point
        </Text>
      </Center>
      <VStack mt="auto" padding="30">
        <Button
          size="lg"
          mt="2"
          mb="2"
          backgroundColor="secondary.400"
          _pressed={{backgroundColor: 'secondary.500'}}
          onPress={() => navigation.navigate('Login')}>
          Login
        </Button>
        <Button
          size="lg"
          mt="2"
          mb="2"
          backgroundColor="primary.200"
          _pressed={{backgroundColor: 'primary.400'}}
          onPress={() => navigation.navigate('Signup')}>
          Register
        </Button>
      </VStack>
      <Text>home</Text>
    </Wrapper>
  );
};

export default Home;
