
import React from 'react'
import { useSelector , useDispatch } from 'react-redux'
import { fetchUsers, userSuccess} from '../redux';
import { FETCH_USER_REQUEST } from '../redux/user/userType';

export function Usercomponent(props) {
    const users=useSelector(state=>state.user.data);
    const dispatch=useDispatch();

    return (
        <>
            {/* <div>{userId}</div> */}
            <div>Inside User allah {users.map(user=><p>{user.name}</p>)}</div>
            <button onClick={()=>dispatch(fetchUsers())} >Show User</button>
        </>
    )
}
