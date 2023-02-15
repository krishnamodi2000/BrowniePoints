import {NativeBaseProvider, Text} from 'native-base';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import theme from './theme';
import Signup from './Screens/Signup';
import Home from './Screens/Home';
import Login from './Screens/Login';

const Stack = createNativeStackNavigator();

const components = [
  {name: 'Home', component: Home},
  {name: 'Signup', component: Signup},
  {name: 'Login', component: Login},
  
];

export default function App() {
  return (
    <NativeBaseProvider theme={theme}>
      <NavigationContainer>
        <Stack.Navigator>
          {components.map((component, key) => (
            <Stack.Screen
              key={key}
              name={component.name}
              component={component.component}
              options={{headerShown: false}}
            />
          ))}
        </Stack.Navigator>
      </NavigationContainer>
    </NativeBaseProvider>
  );
}
