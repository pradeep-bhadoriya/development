const redux=require("redux");
const reduxLogger=require("redux-logger");

const logger=reduxLogger.createLogger();
const applyMiddleware=redux.applyMiddleware;

const createStore=redux.createStore;
const combineReducers=redux.combineReducers;


console.log("from index.js")

const BUY_CAKE = "BUY_CAKE";
const BUY_ICECREAM = "BUY_ICECREAM";


function buyCake() {
    return {
        type: BUY_CAKE,
        info: "first redux app"
    }
}
function buyIcecream() {
    return {
        type: BUY_ICECREAM,
    }
}
const cakeState = {
    cakeNo: 10
}
const icecreamState = {
    icecreamNo: 20
}


// const reducer = (state = preState, action) => {
//     switch (action.type) {
//         case BUY_CAKE: return {
//             ... state,
//             no_of_cake : state.no_of_cake-1
//         }
//         default:return state
//     }
// }

const cakeReducer = (state = cakeState, action) => {
    switch (action.type) {
        case BUY_CAKE: return {
            ... state,
            cakeNo : state.cakeNo-1
        }
        default:return state
    }
}
const icecreamReducer = (state = icecreamState, action) => {
    switch (action.type) {
        case BUY_ICECREAM: return {
            ... state,
            icecreamNo : state.icecreamNo-1
        }
        default:return state
    }
}
// combining cake and icecream reducers
const rootReducer = combineReducers({
    cake:cakeReducer,
    icecream:icecreamReducer
});
const store=createStore(rootReducer , applyMiddleware(logger));
console.log("Initi State : " , store.getState());
const unsubscribe = store.subscribe(()=> console.log("updated state : " , store.getState()));
store.dispatch(buyCake());
store.dispatch(buyCake());
store.dispatch(buyCake());
store.dispatch(buyIcecream());
store.dispatch(buyIcecream());
store.dispatch(buyIcecream());
unsubscribe();

