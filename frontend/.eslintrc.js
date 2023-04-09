module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: ['plugin:react/recommended', 'airbnb-base', 'prettier'],

  parserOptions: {
    ecmaVersion: 12,
    sourceType: 'module',
  },
  plugins: ['react', 'prettier'],
  rules: {
    'react/prop-types': ['off'],
    'no-param-reassign': ['off'],
    'no-plusplus': ['off'],
    'default-param-last': 0,
    'prettier/prettier': 0,
    'no-debugger': 'warn',
    'import/no-extraneous-dependencies': 0,
    'no-underscore-dangle': 0,
    'no-alert': 0,
    'arrow-body-style': 'warn',
    'no-nested-ternary': 0,
    'no-unused-vars': 'warn',
    'import/prefer-default-export': 0,
  },
};
