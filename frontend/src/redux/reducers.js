const {combineReducers} = require('redux');
import user from './user/reducer';
import points from './points/reducer';
import course from './course/reducer';

const rootReducer = combineReducers({
  user,
  points,
  course,
});

export default rootReducer;
