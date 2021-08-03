import { combineReducers } from "redux";
import postReducer from "./feeds/feedsReducer";
import userReducer from "./Settings/settingsReducer";

const rootReducer=combineReducers({
    post:postReducer,
    updateUser:userReducer
})

export default rootReducer;