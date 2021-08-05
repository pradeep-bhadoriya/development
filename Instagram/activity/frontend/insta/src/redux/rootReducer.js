import { combineReducers } from "redux";
import postReducer from "./feeds/feedsReducer";
import onePostReducer from "./Post/postReducer";
import userReducer from "./Settings/settingsReducer";

const rootReducer=combineReducers({
    post:postReducer,
    updateUser:userReducer,
    onePost:onePostReducer
})

export default rootReducer;