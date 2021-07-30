import { FETCH_POST } from "./feedsType"

const initialState={
    posts:[
        {
        id:1, 
        username:"ripu",
        userImage:"../icon.png",
        postImage:"../post5.png",
        likes:11213
        },
        {
            id:2, 
            username:"papa",
            userImage:"../icon.png",
            postImage:"../post4.png",
            likes:11213
        },
        {
            id:3, 
            username:"pradeep",
            userImage:"../icon.png",
            postImage:"../post3.png",
            likes:11213
        },
        {
            id:4, 
            username:"mumma",
            userImage:"../icon.png",
            postImage:"../post2.png",
            likes:11213
        } 
    ]
}

const postReducer=(state=initialState , action)=>{
    switch(action.type){
        case FETCH_POST:return{
            ...state,
            posts:action.payload
        }
        default:return state
    }
}

export default postReducer;