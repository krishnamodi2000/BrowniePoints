import React, {useState} from 'react';
import QRCodeScanner from 'react-native-qrcode-scanner';
import Wrapper from '../../wrapper/Wrapper';
import {Text, Box, Center, Button, Spinner} from 'native-base';
import {useDispatch, useSelector} from 'react-redux';
import {addPoints} from '../../redux/points/actions';

export default function Scanner({navigation, route}) {
  const [showScanner, setShowScanner] = useState(true);
  const dispatch = useDispatch();
  const {addedPointDetails, loading} = useSelector(state => state.points);

  console.log(route);

  const onRead = e => {
    dispatch(addPoints('CSCI5100', e.data));
    setShowScanner(false);
  };

  const handleBack = () => {
    console.log(navigation.goBack());
  };

  return (
    <Wrapper>
      <Center height="100%">
        <Box>
          {showScanner ? (
            <QRCodeScanner
              reactivate={true}
              showMarker={true}
              onRead={e => onRead(e)}
              topContent={
                <Text color="white">
                  Please move your camera over the QR Code
                </Text>
              }
              bottomContent={
                <Text color="white" fontSize={20}>
                  Course: {route.params.courseCode}
                </Text>
              }
            />
          ) : (
            <>
              {loading ? (
                <Spinner color="secondary.400" size="lg" />
              ) : (
                <Box>
                  <Text fontSize={20} fontWeight={500} color="white" mb={5}>
                    Point added!
                  </Text>
                  <Text color="white">
                    Name: {addedPointDetails?.student.user?.firstName}{' '}
                    {addedPointDetails?.student?.user?.lastName}
                  </Text>
                  <Text color="white">
                    Banner ID: {addedPointDetails?.student?.bannerId}
                  </Text>
                  <Button
                    size="lg"
                    marginTop={5}
                    fontWeight={500}
                    backgroundColor="secondary.300"
                    _pressed={{backgroundColor: 'secondary.400'}}
                    onPress={() => handleBack()}>
                    Back
                  </Button>
                </Box>
              )}
            </>
          )}
        </Box>
      </Center>
    </Wrapper>
  );
}
