import {
  Box,
  Button,
  Center,
  Column,
  Divider,
  FormControl,
  HStack,
  Spinner,
  Stack,
  Text,
  VStack,
} from 'native-base';
import {useState} from 'react';
import {InputType1} from '../../components/Commons/Input';
import Header from '../../components/Header/Header';
import Wrapper from '../../wrapper/Wrapper';
import {useDispatch, useSelector} from 'react-redux';
import {addStudentsToCourse} from '../../redux/course/actions';
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
  const {loading, studentAdded} = useSelector(state => state.course);

  const [courseInformation, setCourseInformation] = useState({
    ...route.params.courseDetails,
  });
  const [bannerId, setBannerId] = useState();
  const [showAddStudents, setShowAddStudents] = useState(false);
  const [disabled, setDisabled] = useState(true);
  const [showSuccessModal, setShowSuccessModal] = useState(false);

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

  const navigateToScanner = () => {
    navigation.navigate('Scanner', {
      courseCode: courseInformation.courseId,
      addStudent: true,
    });
  };

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
              ) : (
                <Button
                  size="lg"
                  backgroundColor="secondary.400"
                  _pressed={{backgroundColor: 'secondary.500'}}
                  onPress={() => addBannerIdField()}>
                  Add Students
                </Button>
              )}
            </>
          )}
        </Stack>
      </Stack>
      <CustomModal
        showModal={showSuccessModal}
        setShowModal={setShowSuccessModal}
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
    </Wrapper>
  );
}
