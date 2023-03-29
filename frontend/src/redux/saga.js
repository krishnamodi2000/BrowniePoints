import {all} from 'redux-saga/effects';
import courseSaga from './course/saga';
import pointsSaga from './points/saga';
import userSaga from './user/saga';

export default function* rootSaga() {
  yield all([userSaga(), pointsSaga(), courseSaga()]);
}
