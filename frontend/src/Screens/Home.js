import React from 'react';
import {Center, Image, VStack, Text, Button} from 'native-base';
import Wrapper from '../wrapper/Wrapper';
import Logo from '../assets/Logo.png';

const Home = ({navigation}) => (
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
          _pressed={{backgroundColor: 'secondary.400'}}
          onPress={() => navigation.navigate('Login')}>
          <Text color="#fff9e3" fontSize="19" fontWeight={800}>
            Login
          </Text>
        </Button>
        <Button
          size="lg"
          mt="2"
          mb="2"
          backgroundColor="primary.200"
          _pressed={{backgroundColor: 'primary.400'}}
          onPress={() => navigation.navigate('Signup')}>
          <Text color="#fff9e3" fontSize="18" fontWeight={700}>
            Register
          </Text>
        </Button>
      </VStack>
    </Wrapper>
  );

export default Home;
