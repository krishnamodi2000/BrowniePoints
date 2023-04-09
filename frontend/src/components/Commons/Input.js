import {React} from 'react';

const {Input} = require('native-base');

export const InputType1 = ({...rest}) => (
  <Input
    size="lg"
    color="white"
    borderColor="white"
    _focus={{borderColor: 'white'}}
    {...rest}
  />
);
