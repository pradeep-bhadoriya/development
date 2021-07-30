import React, { Component } from 'react';
import "./profileView.css"
const ProfileView = () => {
    return (
        <div className="profile-view">
            <div className="user">
                <div className="user-image">
                    <img src="../post3.png" alt="" />
                </div>
                <div className="user-detail">
                    <div className="username">Pradeep</div>
                    <div className="fullname">Pradeep Singh</div>
                </div>
            </div>
            <div className="suggestions">
                <div className="suggestion-user">
                    <div className="suggestion-user-image">
                        <img src="../post5.png" alt="" />
                    </div>
                    <div className="suggestion-user-name">Ripu</div>
                    <button className="suggestion-user-follow">Follow</button>
                </div>
            </div>
        </div>
    )
}

export default ProfileView;