import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchPost } from '../../redux/feeds/feedAction';
import Post from '../post/post';
import "./feeds.css"

class Feeds extends Component {
    render() {
        return (<div className="feeds">
            {this.props.posts.map((post)=>{
                return <Post name={post.username} userImage={post.postImage} id={post.id} key={post.id}></Post>
            })}

        </div>);
    }
}

const mapStorageToProps = state => {
    return {
        posts: state.post.posts
    }
}

const mapDispatchToProps = dispatch => {
    return {
        fetchPost: () => dispatch(fetchPost())
    }
}



export default connect(mapStorageToProps, mapDispatchToProps)(Feeds);