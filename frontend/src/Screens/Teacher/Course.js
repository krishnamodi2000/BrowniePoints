import {View, Text} from 'react-native';
import React from 'react';
import Wrapper from '../../wrapper/Wrapper';
import Header from '../../components/Header/Header';

const inputFields = [
  {placeholder: 'Course Name', type: 'text', name: 'courseName'},
  {
    placeholder: 'Password',
    type: 'password',
    name: 'password',
  },
];

export default function Course() {
  return (
    <Wrapper>
      <Header title="Add Course" />
    </Wrapper>
  );
}
