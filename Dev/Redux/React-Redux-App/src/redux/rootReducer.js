import { combineReducers } from "redux";
import cakeReducer from "./cake/cakeReducer";
import icecreamReducer from "./icecream/icecreanReducer";
import userReducer from "./user/userReducer";

const rootReducer = combineReducers({
    cake:cakeReducer,
    icecream:icecreamReducer,
    user:userReducer
});
 
export default rootReducer;