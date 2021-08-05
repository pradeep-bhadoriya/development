import React, { Component } from 'react';
import { Link } from "react-router-dom"
import "./navbar.css"
const Navbar = () => {
    return <>
        <div className="navbar">
            <Link to="/">
                <div className="nav logo">
                    <img src="./logo.png" alt="" />
                </div>
            </Link>
            <div className="nav search">
                <input type="text" class="searchText" placeholder="search" />
            </div>
            <div className="nav links">
                <Link to="/">
                    <div className="home">Home</div>
                </Link>
                <Link to="profile">
                    <div className="profile">Profile</div>
                </Link>
                <Link to="settings">
                    <div className="setting">Settings</div>
                </Link>

            </div>
        </div>
    </>
}

export default Navbar;