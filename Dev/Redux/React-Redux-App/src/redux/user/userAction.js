import { FETCH_USER_FAILURE, FETCH_USER_REQUEST, FETCH_USER_SUCCESS ,  } from "./userType";
import axios from "axios";
export const userRequest=()=>{
    return{
        type:FETCH_USER_REQUEST
    }
}
export const userSuccess=(user)=>{
    return{
        type:FETCH_USER_SUCCESS,
        payload:user
    }
}
export const userFailure=(error)=>{
    return{
        type:FETCH_USER_FAILURE,
        payload:error
    }
}

export const fetchUsers=()=>{
    return async (dispatch)=>{
        try {
            dispatch(userRequest);
            console.log("hi baba ko");
            const userList=await axios.get("https://jsonplaceholder.typicode.com/users");
            console.log("ya allah iske aage bhi hota hai kya");
            console.log(userList.data);
            dispatch(userSuccess(userList.data))
            
        } catch (error) {
            console.log("error aa gyi hai");
            dispatch(userFailure(error.message))
        }
    }
}