import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { fetchUser, updateDisable } from '../../redux/Settings/settingsAction';
import "./setting.css"
const Setting = () => {
    let user = useSelector((state) => state.updateUser.user)
    // const [userState, updateState]=useState([]);
    let var1 = 0;
    console.log(user)
    let disabled = useSelector((state) => state.updateUser.disabled)
    console.log(disabled)
    const dispatch = useDispatch();
    useEffect(() => {
        console.log("sending axios request")
        axios.get("/user/259f44f0-b76b-428c-9a98-bbe7694ae518").then(data => {
            console.log(data.data.data[0], "Inside setting axios");
            var1 = data;
            dispatch(fetchUser(data.data.data[0]));
            // dispatch(updateDisable(disabled))

        }).catch((error) => {
            console.log(error);
        })
    }, [user[0] , disabled])

    let fileInput = React.createRef();

    let onSaveHandeler=()=>{
        console.log(fileInput.current.files)
        let formData=new FormData();
        if(fileInput.current.files.length){
            formData.append("user" , fileInput.current.files[0])
        }
        formData.append("name" , user.name);
        formData.append("username" , user.username);
        formData.append("email" , user.email);
        formData.append("pw" , user.pw);
        formData.append("bio" , user.bio);

        axios.patch("/user/891c7120-b769-4604-ac78-af7502b2b148" , formData).then((obj)=>{
            console.log(obj);
            // useEffect();
            dispatch(updateDisable(true))
        })
    }

    let onEditHandeler = () => {
        dispatch(updateDisable(false))

    }

    let onChangeHandeler = (e) => {
        e.preventDefault();
        let key = e.target.id;
        let newUser={...user}
        newUser[key] = e.target.value;
        dispatch(fetchUser(newUser));
    }

    let onCancelHandeler = () => {
        console.log("Inside Cancel Handeler")
        axios.get("/user/891c7120-b769-4604-ac78-af7502b2b148").then(data => {
            // console.log(data.data.data[0], "Inside setting axios");
            dispatch(fetchUser(data.data.data[0]));
            console.log("going to call updateDisable action")
            dispatch(updateDisable(true))
            console.log(user)
        }).catch((error) => {
            console.log(error);
        })

    }


    console.log(user, "Inside Setting !");
    return (
        <div className="settings">
            <div className="left">
                <div className="profile-photo">
                    <img src={user.pimage} alt="user.jpg" />
                    {!disabled && <input type="file" ref={fileInput} />}
                </div>
            </div>
            <div className="right">
                <div className="name">
                    <label htmlFor="">Name  </label>
                    <input type="text" id="name" value={user.name} disabled={disabled} onChange={(e) => { onChangeHandeler(e) }} />
                </div>
                <div className="username">
                    <label htmlFor="">Username  </label>
                    <input type="text" id="username" value={user.username} disabled={disabled} onChange={(e) => { onChangeHandeler(e) }} />
                </div>
                <div className="email">
                    <label htmlFor="">Email </label>
                    <input type="text" id="email" value={user.email} disabled={disabled} onChange={(e) => { onChangeHandeler(e) }} />
                </div>
                <div className="password">
                    <label htmlFor="">Password  </label>
                    <input type="text" id="pw" value={user.pw} disabled={disabled} onChange={(e) => { onChangeHandeler(e) }} />
                </div>
                <div className="bio">
                    <label htmlFor="">Bio   </label>
                    <input type="text" id="bio" value={user.bio} disabled={disabled} onChange={(e) => { onChangeHandeler(e) }} />
                </div>
                <div className="isPublic">
                    <label htmlFor="">Account Type  </label>
                    <select name="" id="">
                        <option value={user.isPublic ? 1 : 0}>Public</option>
                        <option value={user.isPublic ? 0 : 1}>Private</option>
                    </select>
                </div>
                {disabled ? <button className="edit" onClick={onEditHandeler}>Edit</button> :
                    <React.Fragment>
                        <button className="cancel" onClick={onCancelHandeler} >Cancel</button>
                        <button className="save" onClick={onSaveHandeler} > Save</button>
                    </React.Fragment>
                }

            </div>

        </div>
    );
}

export default Setting;