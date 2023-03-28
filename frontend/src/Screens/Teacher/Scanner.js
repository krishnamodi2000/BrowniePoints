import {View, Text} from 'react-native';
import React from 'react';
import QRCodeScanner from 'react-native-qrcode-scanner';

export default function Scanner({navigation}) {
  const onRead = e => {
    console.log(e);
  };

  return (
    <>
      <QRCodeScanner
        reactivate={true}
        showMarker={true}
        onRead={e => onRead(e)}
        topContent={
          <Text>Please move your camera {'\n'} over the QR Code</Text>
        }
        bottomContent={<View></View>}
      />
    </>
  );
}
