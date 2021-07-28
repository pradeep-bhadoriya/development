import Reacy from "react";
import {useDispatch, useSelector} from "react-redux";
import { buyCake } from "../redux";

function HooksCakeContainer(){
    const numOfCakes = useSelector(state=>state.cake.numOfCakes);
    const dispatch = useDispatch();
    return(
        <div>
            <h1>Hi welcome to HooksCakeContainer {numOfCakes} </h1>
            <button onClick={()=> dispatch(buyCake())} > Buy Cake</button>
        </div>
    )
}

export default HooksCakeContainer;