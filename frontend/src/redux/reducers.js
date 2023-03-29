const {combineReducers} = require('redux');
import user from './user/reducer';
import points from './points/reducer';

const rootReducer = combineReducers({
  user,
  points,
});

export default rootReducer;
