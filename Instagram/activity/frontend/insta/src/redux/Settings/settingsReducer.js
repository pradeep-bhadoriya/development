import { FETCH_USER } from "./settingsType"

const initialState = {
    user: {},
    disabled:false
}

const userReducer = (state = initialState, action) => {
    // console.log("Inside reducer");
    switch (action.type) {
        case FETCH_USER:
            // // function addPost() {
            // //     console.log("inside addPost function")
            // //     state.posts.push(action.payload);
            // //     return state.posts;
            // // }
            console.log("inside case")
            // console.log(newposts)

            return {
                // posts: state.posts.push(action.payload)
                // ...state,
                // posts:[...state.posts , action.payload]
                ...state,
                user:action.payload
            }
        default: return state
    }
}


export default userReducer;