import {View} from 'react-native';
import React, {useState} from 'react';
import QRCodeScanner from 'react-native-qrcode-scanner';
import Wrapper from '../../wrapper/Wrapper';
import {Text, Box, Center} from 'native-base';
import {useDispatch} from 'react-redux';
import {addPoints} from '../../redux/points/actions';

export default function Scanner({navigation}) {
  const [showScanner, setShowScanner] = useState(true);
  const dispatch = useDispatch();

  const onRead = e => {
    dispatch(addPoints('CSCI5100', e.data));
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
