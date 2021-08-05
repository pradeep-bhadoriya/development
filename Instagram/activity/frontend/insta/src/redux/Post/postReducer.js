import { FETCH_USER_FOR_POST } from "./postType"

const initialState = {
    post:
        {
            uid: "",
            name: "",
            pimage: "",
            username: "",
            postimage:"",
            caption:"",

        }  
}

const onePostReducer = (state = initialState, action) => {

    console.log(action.payload , "hii helloo Inside Post reducer");    

    switch (action.type) {
        case FETCH_USER_FOR_POST:
            return {
                post:action.payload
            }
        default: return state
    }
}


export default onePostReducer;