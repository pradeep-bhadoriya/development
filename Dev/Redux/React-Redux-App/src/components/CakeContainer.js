import React from "react"
import {connect} from "react-redux";
import { buyCake } from "../redux";

function CakeContainer(props) {
    return <>
        <h1>Number of cakes {props.numOfCakes}</h1>
        <button onClick={props.buyCake} >Buy Cake</button>
    </>
}

const mapStateToProps = state => {
    return {
        numOfCakes: state.cake.numOfCakes
    }
}

const mapDispatchToProps = dispatch => {
    return{
        buyCake: ()=> dispatch(buyCake())
    }
}
export default connect(mapStateToProps , mapDispatchToProps)(CakeContainer);