import React from 'react';
import {Box, HStack, Menu, Pressable, Text} from 'native-base';
import Icon from 'react-native-vector-icons/Entypo';

export default function Header({title, menuItemsList}) {
  return (
    <Box bg="secondary.300" paddingY="3" paddingX="2" roundedBottom="lg">
      <HStack justifyContent="space-between" alignItems="center">
        <Text color="blueGray.900" fontSize="18" fontWeight="bold">
          {title}
        </Text>

        <Box alignItems="center">
          {menuItemsList && (
            <Menu
              w="190"
              trigger={triggerProps => (
                  <Pressable
                    accessibilityLabel="More options menu"
                    {...triggerProps}>
                    <Icon
                      name="dots-three-vertical"
                      color="#0f172a"
                      size={28}
                    />
                  </Pressable>
                )}>
              {menuItemsList.map((item, key) => (
                <Menu.Item key={key} onPress={() => item.onPress()}>
                  {item.name}
                </Menu.Item>
              ))}
            </Menu>
          )}
        </Box>
      </HStack>
    </Box>
  );
}
