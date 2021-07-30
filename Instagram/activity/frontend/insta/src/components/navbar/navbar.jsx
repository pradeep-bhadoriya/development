import React, { Component } from 'react';
import "./navbar.css"
const Navbar = () => {
    return <>
        <div className="navbar">
            <div className="nav logo">
                <img src="./logo.png" alt="" />
            </div>
            <div className="nav search">
                <input type="text" class="searchText" placeholder="search"/>
            </div>
            <div className="nav links">
                <div className="home">Home</div>
                <div className="home">Profile</div>
            </div>
        </div>
    </>
}
 
export default Navbar;