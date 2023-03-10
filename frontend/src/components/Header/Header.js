import React from 'react';
import {Box, HStack, Icon, Menu, Pressable, Text} from 'native-base';

export default function Header({title}) {
  return (
    <Box bg="secondary.300" paddingY="3" paddingX="2" roundedBottom="lg">
      <HStack justifyContent="space-between" alignItems="center">
        <Text color="blueGray.900" fontSize="18" fontWeight="bold">
          Hi {title},
        </Text>

        <Box alignItems="center">
          <Menu
            w="190"
            trigger={triggerProps => {
              return (
                <Pressable
                  accessibilityLabel="More options menu"
                  {...triggerProps}></Pressable>
              );
            }}>
            <Menu.Item>Arial</Menu.Item>
            <Menu.Item>Nunito Sans</Menu.Item>
            <Menu.Item>Roboto</Menu.Item>
            <Menu.Item>Poppins</Menu.Item>
            <Menu.Item>SF Pro</Menu.Item>
            <Menu.Item>Helvetica</Menu.Item>
            <Menu.Item isDisabled>Sofia</Menu.Item>
            <Menu.Item>Cookie</Menu.Item>
          </Menu>
        </Box>
      </HStack>
    </Box>
  );
}
