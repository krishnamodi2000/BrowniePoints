import {
  Center,
  HStack,
  Spinner,
  VStack,
  Box,
  Text,
  Heading,
  FlatList,
  Spacer,
  Stack,
} from 'native-base';
import React, {useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import Header from '../../components/Header/Header';
import {getStudentsByCourseId} from '../../redux/course/actions';
import Wrapper from '../../wrapper/Wrapper';

export default function CourseInfo({route}) {
  const dispatch = useDispatch();
  const {students, loading} = useSelector(state => state.course);

  useEffect(() => {
    dispatch(getStudentsByCourseId(route.params.courseId));
  }, [dispatch]);

  return (
    <Wrapper>
      <Header title={`${route.params.courseId} Info`} />
      {loading ? (
        <Center height="100%">
          <Spinner color="secondary.500" size="lg" />
        </Center>
      ) : (
        <>
          <Stack p="2" space={3} width="100%">
            <Stack space={1}>
              <Text fontSize="18" color="white">
                Code : {route.params.courseId}
              </Text>
            </Stack>
            <Stack space={1}>
              <Text fontSize="18" color="white">
                Name : {route.params.courseName}
              </Text>
            </Stack>
            <Stack space={1}>
              <Text fontSize="16" color="white">
                Description : {route.params.courseDescription}
              </Text>
            </Stack>
          </Stack>
          <Heading color="white" m={2}>
            Student details
          </Heading>
          <Box
            borderColor="white"
            borderWidth={2}
            borderRadius={5}
            padding={3}
            margin={2}>
            <Box
              borderBottomWidth="1"
              borderColor="secondary.300"
              pl={['0', '4']}
              pr={['0', '5']}
              py="2">
              <HStack space={[2, 3]} justifyContent="space-between">
                <VStack>
                  <Text color="white" bold fontSize={16}>
                    Student Info
                  </Text>
                </VStack>
                <Spacer />
                <Text fontSize="lg" color="yellow.200" alignSelf="flex-start">
                  Points
                </Text>
              </HStack>
            </Box>

            <FlatList
              data={students}
              renderItem={({item, key}) => (
                <Box
                  key={key}
                  borderBottomWidth="1"
                  borderColor="secondary.300"
                  pl={['0', '4']}
                  pr={['0', '5']}
                  py="2">
                  <HStack
                    space={[2, 3]}
                    justifyContent="space-between"
                    key={key}>
                    <VStack>
                      <Text color="white" bold>
                        {item.bannerId}
                      </Text>
                      <Text
                        color="white"
                        _dark={{
                          color: 'warmGray.200',
                        }}>
                        {item.studentName}
                      </Text>
                    </VStack>
                    <Spacer />
                    <Text
                      fontSize="lg"
                      color="yellow.200"
                      alignSelf="flex-start"
                      pr={3}>
                      {item.points}
                    </Text>
                  </HStack>
                </Box>
              )}
              keyExtractor={item => item.id}
            />
          </Box>
        </>
      )}
    </Wrapper>
  );
}
