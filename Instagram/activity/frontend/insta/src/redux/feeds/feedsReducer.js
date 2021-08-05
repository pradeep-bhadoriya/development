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
    ]
}

const postReducer = (state = initialState, action) => {

    console.log(action.payload , "hii helloo");    

    switch (action.type) {
        case FETCH_POST:
            return {
                posts:action.payload
            }
        default: return state
    }
}


export default postReducer;