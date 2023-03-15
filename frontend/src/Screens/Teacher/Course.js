import {View, Text} from 'react-native';
import React, {useState} from 'react';
import Wrapper from '../../wrapper/Wrapper';
import Header from '../../components/Header/Header';
import {
  Button,
  Center,
  FormControl,
  Stack,
  WarningOutlineIcon,
} from 'native-base';
import {InputType1} from '../../components/Commons/Input';

const inputFields = [
  {
    placeholder: 'Course Code',
    type: 'text',
    name: 'courseCode',
  },
  {placeholder: 'Course Name', type: 'text', name: 'courseName'},
];

export default function Course() {
  const [courseDetails, setCourseDetails] = useState({
    courseName: '',
    courseCode: '',
  });

  const [errors, setErrors] = useState({
    courseName: '',
    courseCode: '',
  });

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

    if (courseDetails.courseCode.length !== 8) {
      isValid = false;
      error.courseCode = 'Course code is invalid.';
    }

    setErrors(error);
    return isValid;
  };

  const handleSubmit = () => {
    if (isFieldsValid()) {
      console.log('VALID');
    }
  };

  return (
    <Wrapper>
      <Header title="Add Course" />
      <Center height="100%">
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
            <Button
              size="lg"
              background="secondary.400"
              onPress={() => handleSubmit()}
              _pressed={{backgroundColor: 'secondary.400'}}>
              Add Course
            </Button>
          </Stack>
        </Stack>
      </Center>
    </Wrapper>
  );
}
