import { combineReducers } from "redux";
import postReducer from "./feeds/feedsReducer";

const rootReducer=combineReducers({
    post:postReducer
})

export default rootReducer;