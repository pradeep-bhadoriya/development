import React from "react";
import {useDispatch, useSelector} from "react-redux";
import { buyIcecream } from "../redux/index";
function IcecreamContainer(){
    const numOfIcecreams=useSelector(state =>state.icecream.numOfIcecreams);
    const dispatch = useDispatch();
    return <>
        <h1>Hi I am Icecream IcecreamContainer {numOfIcecreams}</h1>
        <button onClick={()=> dispatch(buyIcecream())}>Buy Icecream</button>
    </>
}

export default IcecreamContainer;