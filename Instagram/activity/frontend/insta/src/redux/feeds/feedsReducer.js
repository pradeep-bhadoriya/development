import { FETCH_POST } from "./feedsType"

const initialState = {
    posts: [
        // {
        //     uid: "248624af-b1bb-4c12-b54a-136494fb1971",
        //     name: "ripu",
        //     email: "ripudamansb92@gmail.com",
        //     pw: "123456789",
        //     pimage: "../post2.png",
        //     username: "ripu3232",
        //     bio: "I am pradeep",
        //     isPublic: 0,
        // }
        // {
        //     uid: "248624af-b1bb-4c12-b54a-136494fb1971",
        //     name: "ripu",
        //     email: "ripudamansb92@gmail.com",
        //     pw: "123456789",
        //     pimage: "../post2.png",
        //     username: "ripu3232",
        //     bio: "I am pradeep",
        //     isPublic: 0,
        // },
        // {
        //     uid: "248624af-b1bb-4c12-b54a-136494fb1971",
        //     name: "ripu",
        //     email: "ripudamansb92@gmail.com",
        //     pw: "123456789",
        //     pimage: "../post2.png",
        //     username: "ripu3232",
        //     bio: "I am pradeep",
        //     isPublic: 0,
        // }

    ]
}
let newposts=[]
// function addPost(state ,action) {
//                 console.log("inside addPost function")
//                 state.posts.push(action.payload);
//                 return state.posts;
//             }
const postReducer = (state = initialState, action) => {
    // console.log("Inside reducer");
    // let newposts=addPost(state , action)
    console.log(action.payload , "hii helloo")
    

    switch (action.type) {
        case FETCH_POST:
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
                posts:action.payload
            }
        default: return state
    }
}


export default postReducer;