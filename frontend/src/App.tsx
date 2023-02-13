import {NativeBaseProvider, Text} from 'native-base';
import theme from './theme';
import Wrapper from './wrapper/Wrapper';
import Login from './components/login';

export default function App() {
  return (
    <NativeBaseProvider theme={theme}>
      <Wrapper>
        <Login />
      </Wrapper>
    </NativeBaseProvider>
  );
}
