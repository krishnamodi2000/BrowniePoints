import React from 'react';
import {Box, Divider, VStack} from 'native-base';
import {Text} from 'native-base';

export default function CustomCard({children}) {
  return (
    <Box
      padding="2"
      borderRadius="8"
      borderColor="white"
      borderWidth={2}
      margin={2}
      marginTop={5}>
      {children}
    </Box>
  );
}
