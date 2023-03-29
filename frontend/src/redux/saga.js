import {all} from 'redux-saga/effects';
import pointsSaga from './points/saga';
import userSaga from './user/saga';

export default function* rootSaga() {
  yield all([userSaga(), pointsSaga()]);
}
