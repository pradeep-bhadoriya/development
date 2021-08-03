import axios from 'axios';
import React, { Component, useEffect } from 'react'
import { useSelector , useDispatch } from 'react-redux';
import { fetchUser } from '../../redux/Settings/settingsAction';
import "./setting.css"
const Setting = () => {
    let user=useSelector((state)=>state.updateUser.user )
    let disabled=useSelector((state)=>state.updateUser.disabled )
    const dispatch=useDispatch();
    useEffect(()=>{
        axios.get("/user/891c7120-b769-4604-ac78-af7502b2b148").then(data=>{
            console.log(data.data.data[0] , "Inside setting axios");
            dispatch(fetchUser(data.data.data[0]));
        })
    })
    // console.log(user, "Inside Setting !");
    return ( 
        <div className="settings">
            <div className="left">
                <div className="profile-photo">
                    <img src={user.pimage} alt="user.jpg" />
                </div>
            </div>
            <div className="right">
                <div className="name">
                    <label htmlFor="">Name  </label>
                    <input type="text" value={user.name} disabled={disabled} />
                </div>
                <div className="username">
                    <label htmlFor="">Username  </label>
                    <input type="text" value={user.username} disabled={disabled} />
                </div>
                <div className="name">
                    <label htmlFor="">Email </label>
                    <input type="text" value={user.email} disabled={disabled} />
                </div>
                <div className="username">
                    <label htmlFor="">Password  </label>
                    <input type="text" value={user.pw} disabled={disabled} />
                </div>
                <div className="name">
                    <label htmlFor="">Bio   </label>
                    <input type="text" value={user.bio} disabled={disabled} />
                </div>
                <div className="username">
                    <label htmlFor="">Account Type  </label>
                    <select name="" id="">
                        <option value={user.isPublic?1:0}>Public</option>
                        <option value={user.isPublic?0:1}>Private</option>
                    </select>
                </div>
                {disabled?<button className="edit">Edit</button> :
                <React.Fragment>
                <button className="cancel">Cancel</button>
                <button className="save">Save</button>
                </React.Fragment>
                }
                
            </div>
            
        </div>
     );
}
 
export default Setting;