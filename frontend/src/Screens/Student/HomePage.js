import {Button, Box, Text, VStack, Spinner} from 'native-base';
import React, {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import CustomCard from '../../components/Commons/CustomCard';
import Header from '../../components/Header/Header';
import {logoutAction} from '../../redux/user/actions';
import Wrapper from '../../wrapper/Wrapper';
import {getEnrolledCourseInfo} from '../../redux/student/actions';

export default function HomePage({navigation}) {
  const dispatch = useDispatch();

  const {user} = useSelector(state => state.user);
  const {subjectInfo, loading} = useSelector(state => state.student);

  const logout = () => {
    dispatch(logoutAction());
  };

  const handleProfilePress = () => {
    navigation.navigate('UserProfile');
  };

  const handleQRGenerator = () => {
    navigation.navigate('QRgenerator');
  };

  const reloadPoints = () => {
    dispatch(getEnrolledCourseInfo(user.userId));
  };

  useEffect(() => {
    dispatch(getEnrolledCourseInfo(user.userId));
  }, [dispatch]);

  return (
    <Wrapper>
      <Header
        title={`Hello, ${user.firstName}`}
        menuItemsList={[
          {name: 'Profile', onPress: () => handleProfilePress()},
          {name: 'Logout', onPress: () => logout()},
          {name: 'Reload Points', onPress: () => reloadPoints()},
        ]}
      />
      {loading ? (
        <Spinner color="secondary.500" size="lg" mt={4} />
      ) : (
        <VStack space="2">
          {subjectInfo.length === 0 && (
            <Text
              color="white"
              fontWeight={600}
              mt={5}
              textAlign="center"
              fontSize={18}>
              Not enrolled to any course
            </Text>
          )}
          {subjectInfo.map((subject, key) => (
            <CourseDetailCard {...subject} key={key} />
          ))}
        </VStack>
      )}

      <Button
        style={{
          backgroundColor: '#fcd12a',
          position: 'absolute',
          bottom: 15,
          right: 6,
          height: 50,
          width: 50,
          borderRadius: 25,
        }}
        floating
        onMouseOver={e => {
          e.target.style.backgroundColor = '#f5b800';
          e.target.style.transform = 'translateY(-3px)';
          e.target.style.boxShadow = '0px 5px 15px rgba(0, 0, 0, 0.4)';
        }}
        onMouseOut={e => {
          e.target.style.backgroundColor = '#fcd12a';
          e.target.style.transform = 'none';
          e.target.style.boxShadow = '0px 2px 10px rgba(0, 0, 0, 0.3)';
        }}
        onPress={() => handleQRGenerator()}
        _pressed={{backgroundColor: 'secondary.400'}}>
        <Text color="black" fontWeight={600}>
          QR
        </Text>
      </Button>
    </Wrapper>
  );
}

const CourseDetailCard = ({courseId, courseName, points}) => (
  <CustomCard>
    <Box
      px="4"
      py="5"
      borderRadius={8}
      bg="#373737"
      borderWidth={1}
      borderColor="gray.300"
      shadow={2}
      transition="all 0.2s"
      _hover={{
        transform: 'translateY(-8px)',
        shadow: '2xl',
      }}>
      <Text fontSize={22} fontWeight="bold" color="white" mb="2">
        {courseId} - {courseName}
      </Text>

      <Box
        borderRadius={5}
        bg="secondary.100"
        px="3"
        py="1"
        alignSelf="flex-start">
        <Text fontSize={16} fontWeight="bold" color="black">
          Points: {points}
        </Text>
      </Box>
    </Box>
  </CustomCard>
);
