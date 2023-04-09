import React from 'react';
import {Center, Image, Spinner, Stack, Text} from 'native-base';
import Wrapper from '../wrapper/Wrapper';
import Logo from '../assets/Logo.png';

export default function Loading() {
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
        <Text color="white" fontSize="28">
          Brownie Point
        </Text>
      </Center>
      <Stack space={2}>
        <Spinner color="secondary.500" size="lg" />
      </Stack>
    </Wrapper>
  );
}
