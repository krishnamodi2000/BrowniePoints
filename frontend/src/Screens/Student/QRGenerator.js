import {Button, Box, Text, VStack, Fab, Icon, View, Center} from 'native-base';
import {useDispatch, useSelector} from 'react-redux';
import Header from '../../components/Header/Header';
import Wrapper from '../../wrapper/Wrapper';
import Qrcode from 'react-native-qrcode-svg';
import React from 'react';
import CustomCard from '../../components/Commons/CustomCard';

export default function QRGenerator() {
  //Get the Banner ID
  return (
    <Wrapper>
      <Header title="QR Code" />
      <CustomCard>
        <Center style={{height: '90%'}}>
          <Box p={6} background="white" borderRadius={16}>
            <Qrcode value={'B00917345'} size={320} />
          </Box>
        </Center>
      </CustomCard>
    </Wrapper>
  );
}
