import React, { Component } from 'react';
import "./post.css"
const Post = (props) => {
    return <>
        <div class="post">
            <div class="post-username">
                <img src="../icon.png" alt="" />
                <div class="username">{props.name}</div>
            </div>
            <div class="post-image">
                <img src={props.userImage} alt="" />
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
            <div class="post-comments">comment 1</div>
            <div class="post-add-comment">
                <input type="text" placeholder="Add a comment..." />
                <button>Post</button>
            </div>
        </div>
    </>
}

export default Post;