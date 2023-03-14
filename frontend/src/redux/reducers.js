const {combineReducers} = require('redux');
import user from './user/reducer';

const rootReducer = combineReducers({
  user,
});

export default rootReducer;
