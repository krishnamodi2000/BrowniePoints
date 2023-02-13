import {View} from 'react-native';
import React from 'react';
import {
  useTheme,
  Center,
  Box,
  Stack,
  Heading,
  HStack,
  Input,
  Button,
} from 'native-base';
import {Image} from 'react-native-svg';

const login = () => {
  const {colors} = useTheme();

  return (
    <Center flex={1} px="3">
      <Box alignItems="center">
        <Box
          minW="350"
          minH="500"
          rounded="lg"
          overflow="hidden"
          borderColor="primary.300"
          _light={{
            backgroundColor: 'primary.400',
          }}>
          <Stack p="4" space={3}>
            <Stack space={2}>
              <Heading
                size="lg"
                fontSize="36"
                color="secondary.300"
                textAlign="center">
                Sign Up
              </Heading>
            </Stack>
            <Stack space={2}>
              <Input size="sm" placeholder="Banner ID" />
            </Stack>
            <Stack space={2}>
              <Input size="sm" placeholder="Email ID" />
            </Stack>
            <Stack space={2}>
              <Input size="sm" placeholder="Password" />
            </Stack>
            <Stack space={2}>
              <Button size="md" background="secondary.300">
                Sign up
              </Button>
            </Stack>
          </Stack>
        </Box>
      </Box>
    </Center>
  );
};

export default login;
