import React, { Component } from 'react';
import { connect } from 'react-redux';
import { fetchPost } from '../../redux/feeds/feedAction';
import Post from '../post/post';
import "./feeds.css"
import axios from 'axios';

class Feeds extends Component {
    componentDidMount(){
        axios.get("/post").then(data=>{
            console.log("-------------------------------")
            // console.log(data.data.data);
            console.log("-------------------------------")
            this.props.fetchPost(data.data.data);
            // console.log("-------------------------------")
            console.log(this.props.posts , "Inside Feeds")
            console.log("-------------------------------")
        })
    }
    render() {
        

        return (<div className="feeds">
            {this.props.posts.map((post)=>{
                 return <Post post={post} key={post.pid}></Post>
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
        fetchPost: (data)=>dispatch(fetchPost(data))
    }
}



export default connect(mapStorageToProps, mapDispatchToProps)(Feeds);