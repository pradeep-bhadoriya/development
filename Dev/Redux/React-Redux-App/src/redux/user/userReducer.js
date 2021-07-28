import { FETCH_USER_FAILURE, FETCH_USER_REQUEST, FETCH_USER_SUCCESS } from "./userType"


const initialState={
    loading:false,
    data:[],
    error:""
}

const userReducer=(state=initialState , action)=>{
    switch(action.type){
        case FETCH_USER_REQUEST: return{
            ...state,
            data:"",
            loading:true,
        }
        case FETCH_USER_SUCCESS: return{
            loading:false,
            data:action.payload,
            error:""
        }
        case FETCH_USER_FAILURE: return{
            loading:false,
            data:"",
            error:action.payload
        }
        default: return state
    }
}
export default userReducer;