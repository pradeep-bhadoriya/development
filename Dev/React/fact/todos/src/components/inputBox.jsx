import React, { Component } from 'react';
class InputBox extends Component {
    state = {
        todo:""
    }

    onChangeHandler=(e)=>{
        console.log(e.target.value);
        this.setState({
            todo:e.target.value
        })
    }



    render() { 
        let addTodoHandler=this.props.addTodoHandler;
        return ( 
            
            <div className="input-group mt-5 mb-5">
                
                <input type="text" className="form-control" value={this.state.todo} onChange={(e)=>{this.onChangeHandler(e)}}></input>
                <div className="input-group-append">
                    <button className="btn btn-primary" onClick={ () => { addTodoHandler(this.state.todo);this.setState({todo:""}) }}>Add Todo</button>
                </div>
            </div>
         );
    }
}
 
export default InputBox;