import {Button, FormControl, Stack, Text} from 'native-base';
import {useState} from 'react';
import {InputType1} from '../../components/Commons/Input';
import Header from '../../components/Header/Header';
import Wrapper from '../../wrapper/Wrapper';

const inputFields = [
  {
    placeholder: 'Course Code',
    type: 'text',
    name: 'courseCode',
  },
  {placeholder: 'Course Name', type: 'text', name: 'courseName'},
];

export default function EditCourse({route}) {
  const [courseInformation, setCourseInformation] = useState({
    ...route.params.courseDetails,
  });
  const [disabled, setDisabled] = useState(true);

  const handleEdit = () => {
    setDisabled(false);
  };

  const handleTextChange = (field, value) => {
    setCourseInformation(current => ({...current, [field]: value}));
  };

  const handleSave = () => {
    setDisabled(true);
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
      </Stack>
    </Wrapper>
  );
}
