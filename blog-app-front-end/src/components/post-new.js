import React, { Component, PropTypes } from 'react';
import { reduxForm } from 'redux-form';
import { createPost } from '../actions/index';
import { Link } from 'react-router';

class PostsNew extends Component {
  static contextTypes = {
  	router: PropTypes.Object
  };


  onSubmit(props) {
  	this.props.createPost(props)
  	.then(() => {
  		this.context.router.push('/');
  	});
  }

  render() {
      const { fields: { title, content, author, tag }, handleSubmit } = this.props;


      return (
          <form onSubmit={handleSubmit(this.onSubmit.bind(this))}>

                <h3>Create A New Post</h3>

                <div className={`form-group ${title.touched && title.invalid ? 'has-danger' : ''}`}>
                  <label>Title</label>
                  <input type="text" className="form-control" {...title} />
                  <div className="text-help">
                    {title.touched ? title.error : ''}
                  </div>
                </div>

                <div className={`form-group ${content.touched && content.invalid ? 'has-danger' : ''}`}>
                  <label>Content</label>
                  <textarea className="form-control" {...content} />
                  <div className="text-help">
                    {content.touched ? content.error : ''}
                  </div>
                </div>

              <div className={`form-group ${author.touched && author.invalid ? 'has-danger' : ''}`}>
                  <label>Author</label>
                  <input type="text" className="form-control" {...author} />
                  <div className="text-help">
                      {author.touched ? author.error : ''}
                  </div>
              </div>

              <div className={`form-group ${tag.touched && tag.invalid ? 'has-danger' : ''}`}>
                  <label>Tag</label>
                  <input type="text" className="form-control" {...tag} />
                  <div className="text-help">
                      {tag.touched ? tag.error : ''}
                  </div>
              </div>

              <button type="submit" className="btn btn-primary">Submit</button>
              <Link to="/" className="btn btn-danger">Cancel</Link>
          </form>
        );
  }
}

function validate(values) {
	const errors = {};
	if (!values.title) {
		errors.title = 'Please Enter a title';
	}

  	if(!values.content) {
    	errors.content = 'Please Enter Some Text';
  	}

    if(!values.author) {
        errors.author = 'Please provide author';
    }

    if(!values.tag) {
        errors.tag = 'Please tag this blog';
    }
	return errors;
}

export default reduxForm({
  form: 'PostsNewForm',
    fields: ['title', 'content', 'author', 'tag'],
  validate
}, null, {createPost})(PostsNew);




