import {extendTheme} from 'native-base';

const theme = extendTheme({
  colors: {
    primary: {
      50: '#42597c',
      100: '#3c4d67',
      200: '#354153',
      300: '#2d3440',
      400: '#222831',
      500: '#1c2025',
      600: '#16181b',
      700: '#101012',
      800: '#252525',
      900: '#010101',
    },
    secondary: {
      50: '#ffef9f',
      100: '#ffe877',
      200: '#ffe250',
      300: '#fcd12a',
      400: '#ffd400',
      500: '#e1bc07',
      600: '#c3a50c',
      700: '#a88f11',
      800: '#8e7913',
      900: '#756515',
    },

    config: {
      initialColorMode: 'dark',
    },
  },
});

export default theme;
