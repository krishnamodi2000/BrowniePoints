import {View} from 'react-native';
import React, {useState} from 'react';
import QRCodeScanner from 'react-native-qrcode-scanner';
import Wrapper from '../../wrapper/Wrapper';
import {Text, Box, Center} from 'native-base';

export default function Scanner({navigation}) {
  const [showScanner, setShowScanner] = useState(true);

  const onRead = e => {
    console.log(e);
    setShowScanner(false);
  };

  return (
    <Wrapper>
      <Center>
        <Box>
          {showScanner && (
            <QRCodeScanner
              reactivate={true}
              showMarker={true}
              onRead={e => onRead(e)}
              topContent={
                <Text color="white">
                  Please move your camera {'\n'} over the QR Code
                </Text>
              }
              bottomContent={<View></View>}
            />
          )}
        </Box>
      </Center>
    </Wrapper>
  );
}
