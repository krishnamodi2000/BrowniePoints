import {all} from 'redux-saga/effects';
import courseSaga from './course/saga';
import pointsSaga from './points/saga';
import userSaga from './user/saga';
import studentSaga from './student/saga';

export default function* rootSaga() {
  try {
    yield all([userSaga(), pointsSaga(), courseSaga(), studentSaga()]);
  } catch (error) {
    console.log(error, JSON.stringify(error));
  }
}
