import { FETCH_USER_FOR_POST } from "./postType"

export const fetchUserForPost=(data)=>{
    console.log(data)
    return{
        type:FETCH_USER_FOR_POST,
        payload:data
    }
}