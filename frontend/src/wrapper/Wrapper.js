import {SafeAreaView} from 'react-native';
import React from 'react';
import {View} from 'native-base';

const Wrapper = ({children}) => (
  <SafeAreaView style={{flex: 1}}>
    <View bg="primary.800" height="100%">
      {children}
    </View>
  </SafeAreaView>
);

export default Wrapper;
