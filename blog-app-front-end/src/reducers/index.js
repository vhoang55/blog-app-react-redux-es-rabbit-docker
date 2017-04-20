import { combineReducers } from 'redux';
import PostsReducer from './reducer-post'
import {reducer as formReducer} from 'redux-form';

const rootReducer = combineReducers({
  posts: PostsReducer,
  form: formReducer
});

export default rootReducer;