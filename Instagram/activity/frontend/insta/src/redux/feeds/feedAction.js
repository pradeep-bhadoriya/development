import { FETCH_POST } from "./feedsType"

export const fetchPost=(data)=>{
    console.log(data)
    return{
        type:FETCH_POST,
        payload:data
    }
}