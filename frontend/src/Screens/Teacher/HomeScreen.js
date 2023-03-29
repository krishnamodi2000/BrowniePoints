import {Button, Center, ScrollView, Spinner, Text, VStack} from 'native-base';
import React, {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import CustomCard from '../../components/Commons/CustomCard';
import Header from '../../components/Header/Header';
import {getCourses} from '../../redux/course/actions';
import {logoutAction} from '../../redux/user/actions';
import Wrapper from '../../wrapper/Wrapper';

export default function HomeScreen({navigation}) {
  const dispatch = useDispatch();

  const {user} = useSelector(state => state.user);
  const {courses, loading} = useSelector(state => state.course);

  const logout = () => {
    dispatch(logoutAction());
  };

  const navigateToCourse = () => {
    navigation.navigate('Course');
  };

  const naviagteToEditCourse = course => {
    navigation.navigate('Edit Course', {
      courseDetails: {...course},
    });
  };

  const navigateToScanner = courseCode => {
    navigation.navigate('Scanner', {courseCode});
  };

  useEffect(() => {
    dispatch(getCourses());
  }, [dispatch]);

  return (
    <Wrapper>
      <Header
        title={user.firstName}
        menuItemsList={[
          {name: 'Profile', onPress: () => console.log('Profile Clicked')},
          {name: 'Add Course', onPress: () => navigateToCourse()},
          {name: 'Logout', onPress: () => logout()},
        ]}
      />
      {loading ? (
        <Center height="100%">
          <Spinner color="secondary.400" size="lg" />
        </Center>
      ) : (
        <ScrollView>
          {courses.map((course, key) => (
            <CustomCard key={key}>
              <CardContent
                courseCode={course.courseId}
                courseName={course.courseName}
                naviagteToEditCourse={() => naviagteToEditCourse(course)}
                navigateToScanner={navigateToScanner}
              />
            </CustomCard>
          ))}
        </ScrollView>
      )}
    </Wrapper>
  );
}

const CardContent = ({
  courseCode,
  courseName,
  naviagteToEditCourse,
  navigateToScanner,
}) => {
  return (
    <VStack>
      <Text fontSize={20} fontWeight="bold" color="white">
        {courseCode} - {courseName}
      </Text>
      <Button
        mt={3}
        background="secondary.400"
        _pressed={{backgroundColor: 'secondary.600'}}
        onPress={() => navigateToScanner(courseCode)}>
        Scan QR
      </Button>
      <Button
        mt={3}
        background="primary.200"
        _pressed={{backgroundColor: 'primary.600'}}
        onPress={() => naviagteToEditCourse()}>
        Edit Information
      </Button>
    </VStack>
  );
};
