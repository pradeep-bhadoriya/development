const redux=require("redux");
const thunkMiddleware=require("redux-thunk").default;
const axios=require("axios");
const applyMiddleware=redux.applyMiddleware;
const createStore=redux.createStore;


const initialState={
    loading:false,
    users:[],
    error:""
}

const FETCH_USERS_REQUEST ="FETCH_USERS_REQUEST";
const FETCH_USERS_SUCCESS ="FETCH_USERS_SUCCESS";
const FETCH_USERS_FAILURE ="FETCH_USERS_FAILURE";

const fetchUsersRequest=()=>{
    return {
        type:FETCH_USERS_REQUEST
    }
}
const fetchUsersSuccess=(user)=>{
    return {
        type:FETCH_USERS_SUCCESS,
        payload:user
    }
}
const fetchUsersFailure=(error)=>{
    return {
        type:FETCH_USERS_FAILURE,
        payload:error
    }
}

const reducer=(state=initialState , action)=>{
    switch(action.type){
        case FETCH_USERS_REQUEST: return {
            loading:true,
            user:[],
            error:""
        }
        case FETCH_USERS_SUCCESS: return {
            loading:false,
            user:action.payload,
            error:""
        }
        case FETCH_USERS_FAILURE: return {
            loading:false,
            user:[],
            error:action.paylaod
        }
        default:return state
    }
}

const fetchUser = () => {
    return async function(dispatch){
        try {
            dispatch(fetchUsersRequest());
            let users = await axios.get("https://jsonplaceholder.typicode.com/users");
            console.log(users);
            users = users.data.map(user => user.id);
            dispatch(fetchUsersSuccess(users));
        } catch (error) {
            dispatch(fetchUsersFailure(error.message));
        }
    }
}

const store=createStore(reducer , applyMiddleware(thunkMiddleware));
store.subscribe(() => {console.log(store.getState())});
store.dispatch(fetchUser());