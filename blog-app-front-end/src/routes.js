import React from 'react';
import {Route, IndexRoute} from 'react-router';

import App from './components/app';
import PostIndex from './components/post-index';
import PostNew from './components/post-new';
import PostsShow from './components/posts-show';

export default (
	<Route path="/" component={App}>
		<IndexRoute component={PostIndex} />
		<Route path="posts/new" component={PostNew} />
		<Route path="posts/:id" component={PostsShow} />
	</Route>
);