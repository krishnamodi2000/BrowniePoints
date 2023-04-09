import React, {useState} from 'react';
import {
  Button,
  Center,
  FormControl,
  Spinner,
  Stack,
  WarningOutlineIcon,
} from 'native-base';
import {useDispatch, useSelector} from 'react-redux';
import Wrapper from '../../wrapper/Wrapper';
import Header from '../../components/Header/Header';
import {InputType1} from '../../components/Commons/Input';
import {addCourse, getCourses} from '../../redux/course/actions';
import CustomModal from '../../components/Commons/CustomModal';

const inputFields = [
  {
    placeholder: 'Course Code',
    type: 'text',
    name: 'courseId',
  },
  {placeholder: 'Course Name', type: 'text', name: 'courseName'},
  {placeholder: 'Course Description', type: 'text', name: 'courseDescription'},
];

const initialState = {
  courseId: '',
  courseCode: '',
  courseDescription: '',
};

export default function Course({navigation}) {
  const dispatch = useDispatch();
  const {courseDetails: courseDetailsStore, loading} = useSelector(
    state => state.course,
  );

  const [courseDetails, setCourseDetails] = useState({
    ...initialState,
  });
  const [errors, setErrors] = useState({
    courseName: '',
    courseCode: '',
  });
  const [showSuccessModal, setShowSuccessModal] = useState(false);

  const handleTextChange = (field, value) => {
    setCourseDetails(current => ({...current, [field]: value}));
  };

  const isFieldsValid = () => {
    let isValid = true;
    const error = {
      courseName: '',
      courseCode: '',
    };

    if (courseDetails.courseName.length < 6) {
      isValid = false;
      error.courseName = 'Course name must be 6 characters long.';
    }

    if (courseDetails.courseId.length !== 8) {
      isValid = false;
      error.courseCode = 'Course code is invalid.';
    }

    setErrors(error);
    return isValid;
  };

  const onSuccess = () => {
    setCourseDetails({...initialState});
    setShowSuccessModal(true);
  };

  const handleSubmit = () => {
    if (isFieldsValid()) {
      dispatch(addCourse(courseDetails, onSuccess));
    }
  };

  const handleBack = () => {
    navigation.goBack();
    dispatch(getCourses());
  };

  return (
    <Wrapper>
      <Header title="Add Course" />
      <Center height="100%">
        <CustomModal
          showModal={showSuccessModal}
          setShowModal={setShowSuccessModal}
          heading="Successfully"
          body={
            <Center>
              Course {courseDetailsStore?.courseId} was added successfully.
              <Button mt="2" minWidth="100" onPress={handleBack}>
                Back
              </Button>
            </Center>
          }
        />
        <Stack p="4" space={3} width="100%">
          {inputFields.map((inputField, key) => (
            <Stack space={2} key={key}>
              <FormControl isInvalid={errors[inputField.name]}>
                <InputType1
                  {...inputField}
                  onChangeText={value =>
                    handleTextChange(inputField.name, value)
                  }
                />
                <FormControl.ErrorMessage
                  leftIcon={<WarningOutlineIcon size="xs" />}>
                  {errors[inputField.name]}
                </FormControl.ErrorMessage>
              </FormControl>
            </Stack>
          ))}

          <Stack>
            {loading ? (
              <Spinner color="secondary.400" size="lg" />
            ) : (
              <Button
                size="lg"
                background="secondary.400"
                onPress={() => handleSubmit()}
                _pressed={{backgroundColor: 'secondary.400'}}>
                Add Course
              </Button>
            )}
          </Stack>
        </Stack>
      </Center>
    </Wrapper>
  );
}
