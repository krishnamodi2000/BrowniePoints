import React from 'react';
import {Box, Divider, VStack} from 'native-base';
import {Text} from 'native-base';

export default function CustomCard({subjectName}) {
  return (
    <Box
      padding="2"
      borderRadius="8"
      borderColor="white"
      borderWidth={2}
      margin={2}
      marginTop={5}>
      <VStack space="2">
        <Box px="2">
          <Text fontSize={20} fontWeight="bold" color="white">
            {subjectName}
          </Text>
        </Box>
        <Box px="2">
          <Text color={'secondary.500'}> Points : 5</Text>
        </Box>
      </VStack>
    </Box>
  );
}
