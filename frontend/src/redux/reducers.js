import user from './user/reducer';
import points from './points/reducer';
import course from './course/reducer';
import student from './student/reducer';

const {combineReducers} = require('redux');

const rootReducer = combineReducers({
  user,
  points,
  course,
  student,
});

export default rootReducer;
