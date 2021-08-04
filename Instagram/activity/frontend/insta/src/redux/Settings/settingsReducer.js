// import { state } from "../../../../../backend/model/db";
import { FETCH_USER } from "./settingsType"

const initialState = {
    user: {},
    disabled:true
}

const userReducer = (state = initialState, action) => {
    console.log("Inside user reducer");
    switch (action.type) {
        case FETCH_USER:
            // // function addPost() {
            // //     console.log("inside addPost function")
            // //     state.posts.push(action.payload);
            // //     return state.posts;
            // // }
            console.log("inside case")
            console.log("action.payload",action.payload)

            return {
                // posts: state.posts.push(action.payload)
                // ...state,
                // posts:[...state.posts , action.payload]
                ...state,
                user:action.payload
            }
        case "UPDATE_DISABLE":return{
            ...state,
            disabled:action.payload
        }
        default: return state
    }
}


export default userReducer;