import React, {Component} from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {searchPosts} from '../actions/index';
import { browserHistory } from 'react-router';


class SearchBar extends Component {
    constructor(props) {
        super(props);

        this.state = {term: ''};
        this.onInputChange = this.onInputChange.bind(this);
        this.onFormSubmit = this.onFormSubmit.bind(this);
    }

    onInputChange(event) {
        this.setState({term: event.target.value});
    }

    onFormSubmit(event) {
        event.preventDefault();
        this.props.searchPosts(this.state.term);
        this.setState({term: ''});
    }

    render() {
        return (
            <div>
                <form onSubmit={this.onFormSubmit} className="input-group">
                    <input
                        placeholder="enter your search term. try swift"
                        className="form-control"
                        value={this.state.term}
                        onChange={this.onInputChange} />
                    <div className="input-group-btn">
                            <button type="submit" className="btn btn-secondary">Submit</button>
                    </div>
                </form>
            </div>
        );
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({searchPosts}, dispatch);
}

export default connect(null, mapDispatchToProps)(SearchBar);