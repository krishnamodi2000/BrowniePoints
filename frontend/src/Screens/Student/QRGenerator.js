import {Box, Center} from 'native-base';
import {useSelector} from 'react-redux';
import Qrcode from 'react-native-qrcode-svg';
import React from 'react';
import Header from '../../components/Header/Header';
import Wrapper from '../../wrapper/Wrapper';
import CustomCard from '../../components/Commons/CustomCard';

export default function QRGenerator() {
  const {user} = useSelector(state => state.user);

  return (
    <Wrapper>
      <Header title="QR Code" />
      <CustomCard>
        <Center style={{height: '90%'}}>
          <Box p={10} background="white" borderRadius={16}>
            <Qrcode value={user.userId} size={250} />
          </Box>
        </Center>
      </CustomCard>
    </Wrapper>
  );
}
