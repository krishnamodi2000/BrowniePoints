import {
  Alert,
  Box,
  CloseIcon,
  Collapse,
  HStack,
  IconButton,
  Text,
  VStack,
} from 'native-base';

export const CustomAlert = ({status, message, open, onClose}) => (
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
        </HStack>
      </Alert>
    )}
  </>
);
