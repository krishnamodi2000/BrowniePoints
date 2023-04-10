import React, {useState} from 'react';
import QRCodeScanner from 'react-native-qrcode-scanner';
import {Text, Box, Center, Button, Spinner} from 'native-base';
import {useDispatch, useSelector} from 'react-redux';
import Wrapper from '../../wrapper/Wrapper';
import {addPoints} from '../../redux/points/actions';
import {addStudentsToCourse} from '../../redux/course/actions';

export default function Scanner({navigation, route}) {
  const dispatch = useDispatch();
  const {addedPointDetails, loading} = useSelector(state => state.points);

  const [showScanner, setShowScanner] = useState(true);

  const handleBack = () => {
    navigation.goBack();
  };

  const onRead = e => {
    if (route.params.addStudent) {
      dispatch(
        addStudentsToCourse(route.params.courseCode, [e.data], () => {
          handleBack();
        }),
      );
    } else {
      dispatch(addPoints(route.params.courseCode, e.data));
    }
    setShowScanner(false);
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
                <>
                  {!route.params.addStudent && (
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
            </>
          )}
        </Box>
      </Center>
    </Wrapper>
  );
}
