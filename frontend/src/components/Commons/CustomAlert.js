import React from 'react';
import {Alert, CloseIcon, HStack, IconButton, Text} from 'native-base';

export const CustomAlert = ({status, message, open, onClose, noClose}) => (
  <>
    {open && (
      <Alert maxW="400" status={status} colorScheme={status}>
        <HStack
          flexShrink={1}
          space={2}
          alignItems="center"
          justifyContent="space-between">
          <HStack flexShrink={1} space={2} alignItems="center">
            <Alert.Icon />
            <Text fontSize="md" fontWeight="medium" color="coolGray.800">
              {message}
            </Text>
          </HStack>
          {!noClose && (
            <IconButton
              variant="unstyled"
              _focus={{
                borderWidth: 0,
              }}
              onPress={onClose}
              icon={<CloseIcon size="3" />}
              _icon={{
                color: 'coolGray.600',
              }}
            />
          )}
        </HStack>
      </Alert>
    )}
  </>
);
