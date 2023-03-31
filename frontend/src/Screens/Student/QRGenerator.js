import {Button, Box, Text, VStack, Fab, Icon, View} from 'native-base';
import {useDispatch, useSelector} from 'react-redux';
import Header from '../../components/Header/Header';
import Wrapper from '../../wrapper/Wrapper';
import Qrcode from 'react-native-qrcode-svg';
import React, {useState} from 'react';
import axios from 'axios';
import {TextInput} from 'react-native';

export default function QRGenerator() {
  const [qrCodeText, setQrCodeText] = useState('');

  return (
    <Wrapper>
      <Header title="QR Code" />
      <View style={{flex: 1, backgroundColor: 'white'}}>
        <View
          style={{
            flex: 1,
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          <Qrcode
            value={'B0012324'}
            size={300}
            //  logo={require('../../assets/img/logo.png')}
          />
        </View>
      </View>
    </Wrapper>
  );
}
