import {Button, Box, Text, VStack, Fab, Icon} from 'native-base';
import {useDispatch, useSelector} from 'react-redux';
import Header from '../../components/Header/Header';
import Wrapper from '../../wrapper/Wrapper';
import Qrcode from 'react-native-qrcode-svg';
import React from 'react';
import axios from 'axios';

export default function QRGenerator() {
  const [qrCodeText, setQrCodeText] = useState('');

  return (
    <Wrapper>
      <View style={{flex: 1, backgroundColor: 'black'}}>
        <View style={{alignItems: 'center', marginTop: 10}}>
          <TextInput
            placeholder="Enter Your Data"
            style={{borderWidth: 1, borderColor: 'white', width: '80%'}}
            onChangeText={text => setQrCodeText(text)}
            value={qrCodeText}
          />
        </View>

        <View
          style={{
            flex: 1,
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          <Qrcode
            value={qrCodeText ? qrCodeText : 'https://www.google.com/'}
            size={300}
            logo={require('../../assets/img/logo.png')}
          />
        </View>
      </View>
    </Wrapper>
  );
}
