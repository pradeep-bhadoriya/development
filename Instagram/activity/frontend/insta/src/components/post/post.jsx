import React, { Component, useEffect } from 'react';
import "./post.css"
import axios from "axios";
import { useSelector , useDispatch } from 'react-redux';
import { fetchUserForPost } from '../../redux/Post/postAction';

const Post = (props) => {
    let onePost=useSelector((state)=>state.onePost.post)
    let postInfo=props.post;
    console.log(postInfo , "Inside One pOst");
    let dispatch=useDispatch();

    useEffect(() => {
        axios.get("/user/"+postInfo.uid).then((data)=>{
            console.log(data.data.data[0]);
            let newOnePost={... onePost};
            newOnePost.uid=postInfo.uid;
            // newOnePost.name=data.data.data[0].name;
            postInfo.name=data.data.data[0].name
            // newOnePost.pimage=data.data.data[0].pimage;
            postInfo.pimage=data.data.data[0].pimage
            newOnePost.postimage=postInfo.postimage;
            newOnePost.caption=postInfo.caption;
            console.log("printing newOnePost" , newOnePost)
            dispatch(fetchUserForPost(newOnePost));
        })
    }, [postInfo])

    return <>
        <div class="post">
            <div class="post-username">
                <img src={postInfo.pimage}  alt="" />
                <div class="username">{postInfo.name}</div>
            </div>
            <div class="post-image">
                <img src={postInfo.postimage} alt="" />
            </div>
            <div class="post-actions">
                <div class="like" >
                    <i class="far fa-heart"></i>
                </div>
                <div class="comment">
                    <i class="far fa-comment"></i>
                </div>
            </div>
            <div class="post-like-count">like count</div>
            <div class="post-comments">{postInfo.caption}</div>
            <div class="post-add-comment">
                <input type="text" placeholder="Add a comment..." />
                <button>Post</button>
            </div>
        </div>
    </>
}

export default Post;