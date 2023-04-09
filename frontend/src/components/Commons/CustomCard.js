import React from 'react';
import {Box} from 'native-base';

export default function CustomCard({children}) {
  return (
    <Box
      padding="2"
      margin={2}
      marginTop={5}
      bgGradient="linear(to-b, gray.200, gray.300)"
      shadow="lg"
      _hover={{
        shadow: '2xl',
        transform: 'translateY(-2px)',
      }}
      _active={{
        bgGradient: 'linear(to-b, gray.300, gray.400)',
        transform: 'translateY(0)',
      }}>
      {children}
    </Box>
  );
}
