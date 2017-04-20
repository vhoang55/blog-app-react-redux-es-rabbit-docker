import { FETCH_POSTS, FETCH_POST, SEARCH_POSTS } from '../actions/index';

const INITIAL_STATE = {
	all: [],
	post: null
};

export default function(state = INITIAL_STATE, action) {
  switch(action.type) {
  case FETCH_POST:
    return { ...state, post: action.payload.data };
  case FETCH_POSTS:
    return { ...state, all: action.payload.data };
  case SEARCH_POSTS:
    return { ...state, all: action.payload.data };
  default:
    return state;
  }
}