import {
  Box,
  Button,
  Center,
  Divider,
  FlatList,
  FormControl,
  HStack,
  Spacer,
  Spinner,
  Stack,
  Text,
  VStack,
} from 'native-base';
import {useEffect, useState} from 'react';
import {InputType1} from '../../components/Commons/Input';
import Header from '../../components/Header/Header';
import Wrapper from '../../wrapper/Wrapper';
import {useDispatch, useSelector} from 'react-redux';
import {
  addStudentsToCourse,
  deleteCourse,
  getStudentsByCourseId,
  removeStudentFromCourse,
} from '../../redux/course/actions';
import CustomModal from '../../components/Commons/CustomModal';
import {useNavigation} from '@react-navigation/native';
import DocumentPicker from 'react-native-document-picker';
import Papa from 'papaparse';
import {readFile} from 'react-native-fs';

const inputFields = [
  {
    placeholder: 'Course Code',
    type: 'text',
    name: 'courseId',
  },
  {placeholder: 'Course Name', type: 'text', name: 'courseName'},
];

const bannerIdField = {
  placeholder: 'Banner ID',
  type: 'text',
  name: 'bannerId',
};

export default function EditCourse({route}) {
  const dispatch = useDispatch();
  const navigation = useNavigation();
  const {loading, students, studentAdded} = useSelector(state => state.course);

  const [courseInformation, setCourseInformation] = useState({
    ...route.params.courseDetails,
  });
  const [bannerId, setBannerId] = useState();
  const [showAddStudents, setShowAddStudents] = useState(false);
  const [disabled, setDisabled] = useState(true);
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  const [showRemoveStudentModal, setShowRemoveStudentModal] = useState(false);
  const [showRemoveStudent, setShowRemoveStudent] = useState(false);
  const [showDeleteCourseSuccessModal, setShowDeleteCourseSuccessModal] =
    useState(false);
  const [removedStudentBannerId, setRemovedStudentBannerId] = useState('');

  const handleEdit = () => {
    setDisabled(false);
  };

  const handleTextChange = (field, value) => {
    setCourseInformation(current => ({...current, [field]: value}));
  };

  const handleSave = () => {
    setDisabled(true);
  };

  const addBannerIdField = () => {
    setShowAddStudents(true);
  };

  const handleBannerIdChange = value => {
    setBannerId(value);
  };

  const handleAddBannerId = () => {
    dispatch(
      addStudentsToCourse(
        (courseCode = courseInformation.courseId),
        [bannerId],
        () => {
          setShowSuccessModal(true);
        },
      ),
    );
  };

  const handleCloseModal = () => {
    setShowSuccessModal(false);
    setShowAddStudents(false);
    setBannerId(null);
  };

  const removeStudent = () => {
    setShowRemoveStudent(true);
  };

  const selectFile = async () => {
    try {
      const res = await DocumentPicker.pickSingle({
        type: [DocumentPicker.types.allFiles],
      });

      readFile(res.uri, 'ascii').then(res => {
        const data = res
          .split(',')
          .map(item => item.replace(/(\r\n|\n|\r)/gm, ''));
        dispatch(
          addStudentsToCourse(
            (courseCode = courseInformation.courseId),
            data,
            () => {
              setShowSuccessModal(true);
            },
          ),
        );
      });
    } catch (err) {}
  };

  const handleStudentRemove = studentId => {
    dispatch(
      removeStudentFromCourse(
        courseInformation.courseId,
        studentId,
        bannerId => {
          setRemovedStudentBannerId(bannerId);
          setShowRemoveStudentModal(true);
          setShowRemoveStudent(false);
        },
      ),
    );
  };

  const handleRemoveStudentClose = () => {
    setShowRemoveStudentModal(false);
  };

  const navigateToScanner = () => {
    navigation.navigate('Scanner', {
      courseCode: courseInformation.courseId,
      addStudent: true,
    });
  };

  const deleteCourseHandler = () => {
    dispatch(
      deleteCourse(courseInformation.courseId, () => {
        setShowDeleteCourseSuccessModal(true);
      }),
    );
  };

  const handleDeleteCourseClose = () => {
    navigation.goBack();
  };

  useEffect(() => {
    dispatch(getStudentsByCourseId(route.params.courseDetails.courseId));
  }, [dispatch]);

  return (
    <Wrapper>
      <Header title="Edit Course" />
      <Stack p="4" space={3} width="100%">
        {inputFields.map((inputField, key) => (
          <Stack space={2} key={key}>
            {disabled ? (
              <Text fontSize="20" color="white">
                {inputField.placeholder} : {courseInformation[inputField.name]}
              </Text>
            ) : (
              <FormControl>
                <InputType1
                  {...inputField}
                  value={courseInformation[inputField.name]}
                  disabled={disabled}
                  onChangeText={value =>
                    handleTextChange(inputField.name, value)
                  }
                />
                {/* <FormControl.ErrorMessage
                leftIcon={<WarningOutlineIcon size="xs" />}>
                {errors[inputField.name]}
              </FormControl.ErrorMessage> */}
              </FormControl>
            )}
          </Stack>
        ))}
        <Stack>
          {disabled ? (
            <Button
              size="lg"
              backgroundColor="primary.200"
              _pressed={{backgroundColor: 'primary.400'}}
              onPress={() => handleEdit()}>
              Edit course
            </Button>
          ) : (
            <Button
              size="lg"
              backgroundColor="secondary.400"
              _pressed={{backgroundColor: 'secondary.500'}}
              onPress={() => handleSave()}>
              Save Changes
            </Button>
          )}
        </Stack>
        <Divider />
        <Stack>
          {loading ? (
            <Spinner color="secondary.500" size="lg" />
          ) : (
            <>
              {showAddStudents ? (
                <VStack>
                  <FormControl>
                    <InputType1
                      {...bannerIdField}
                      value={bannerId}
                      onChangeText={value => handleBannerIdChange(value)}
                    />
                  </FormControl>
                  <Button
                    mt={2}
                    size="md"
                    backgroundColor="secondary.400"
                    _pressed={{backgroundColor: 'secondary.500'}}
                    fontWeight={'600'}
                    onPress={() => handleAddBannerId()}>
                    Add Student
                  </Button>
                  <Text mt={2} fontSize={18} textAlign="center" color="white">
                    OR
                  </Text>
                  <Button
                    mt={2}
                    size="md"
                    backgroundColor="blue.500"
                    _pressed={{backgroundColor: 'blue.600'}}
                    onPress={() => navigateToScanner()}>
                    Scan QR
                  </Button>
                  <Button
                    mt={2}
                    size="md"
                    backgroundColor="green.500"
                    _pressed={{backgroundColor: 'green.600'}}
                    onPress={selectFile}>
                    Upload Excel
                  </Button>
                </VStack>
              ) : showRemoveStudent ? (
                <VStack>
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
                          {students.length > 0 && (
                            <Button
                              size="sm"
                              backgroundColor="red.400"
                              _pressed={{backgroundColor: 'red.500'}}
                              onPress={() => handleStudentRemove(item.bannerId)}
                              pr={3}>
                              Remove
                            </Button>
                          )}
                        </HStack>
                      </Box>
                    )}
                    keyExtractor={item => item.id}
                  />
                </VStack>
              ) : (
                <>
                  <Button
                    size="lg"
                    backgroundColor="secondary.400"
                    _pressed={{backgroundColor: 'secondary.500'}}
                    onPress={() => addBannerIdField()}>
                    Add Students
                  </Button>
                  {students.length > 0 && (
                    <Button
                      size="lg"
                      mt={5}
                      backgroundColor="red.400"
                      _pressed={{backgroundColor: 'red.500'}}
                      onPress={() => removeStudent()}>
                      Remove Student
                    </Button>
                  )}
                  <Button
                    size="lg"
                    mt={5}
                    backgroundColor="red.400"
                    _pressed={{backgroundColor: 'red.500'}}
                    onPress={() => deleteCourseHandler()}>
                    Delete Course
                  </Button>
                </>
              )}
            </>
          )}
        </Stack>
      </Stack>
      <AddStudentToCourseSuccessModal
        showModal={showSuccessModal}
        setShowModal={setShowSuccessModal}
        studentAdded={studentAdded}
        handleCloseModal={handleCloseModal}
      />
      <StudentRemovedFromCourseSuccessModal
        showModal={showRemoveStudentModal}
        setShowModal={setShowRemoveStudentModal}
        bannerId={removedStudentBannerId}
        handleCloseModal={handleRemoveStudentClose}
      />
      <DeleteCourseSuccessModal
        showModal={showDeleteCourseSuccessModal}
        setShowModal={setShowDeleteCourseSuccessModal}
        handleCloseModal={handleDeleteCourseClose}
      />
    </Wrapper>
  );
}

const StudentRemovedFromCourseSuccessModal = ({
  showModal,
  setShowModal,
  bannerId,
  handleCloseModal,
}) => (
  <CustomModal
    showModal={showModal}
    setShowModal={setShowModal}
    heading="Removed Student"
    body={
      <Center>
        Successfully removed {bannerId} from course.
        <Button mt="2" minWidth="100" onPress={handleCloseModal}>
          Okay
        </Button>
      </Center>
    }
  />
);

const AddStudentToCourseSuccessModal = ({
  showModal,
  setShowModal,
  studentAdded,
  handleCloseModal,
}) => (
  <CustomModal
    showModal={showModal}
    setShowModal={setShowModal}
    heading="Added Students"
    body={
      <Center>
        Successfully added {studentAdded.length} students.
        <Button mt="2" minWidth="100" onPress={handleCloseModal}>
          Back
        </Button>
      </Center>
    }
  />
);

const DeleteCourseSuccessModal = ({
  showModal,
  setShowModal,
  handleCloseModal,
}) => (
  <CustomModal
    showModal={showModal}
    setShowModal={setShowModal}
    heading="Added Students"
    body={
      <Center>
        Successfully removed the course.
        <Button mt="2" minWidth="100" onPress={handleCloseModal}>
          Back
        </Button>
      </Center>
    }
  />
);
