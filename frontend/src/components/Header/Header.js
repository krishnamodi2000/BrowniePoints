import React from 'react';
import {Box, HStack, Menu, Pressable, Text} from 'native-base';
import Icon from 'react-native-vector-icons/Entypo';

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
                  {...triggerProps}>
                  <Icon name="dots-three-vertical" color="#0f172a" size={28} />
                </Pressable>
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
