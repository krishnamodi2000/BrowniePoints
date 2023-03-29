import * as actionTypes from './actionTypes';

const initialState = {
  addedPointDetails: null,
  error: null,
  loading: false,
};

const points = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SET_POINTS_LOADING:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case actionTypes.ADD_POINTS_SUCCESS:
      return {
        ...state,
        addedPointDetails: action.payload,
        loading: false,
      };
    case actionTypes.ADD_POINTS_FAIL:
      return {
        ...state,
        addedPointDetails: null,
        loading: false,
        error: true,
      };

    default:
      return state;
  }
};

export default points;
