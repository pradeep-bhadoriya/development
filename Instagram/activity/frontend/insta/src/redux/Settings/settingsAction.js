import { FETCH_USER } from "./settingsType"

export const fetchUser=(data)=>{
    console.log(data)
    return{
        type:FETCH_USER,
        payload:data
    }
}
export const updateDisable=(data)=>{
    return{
        type:"UPDATE_DISABLE",
        payload:data
    }
}