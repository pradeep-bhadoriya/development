import React, { Component } from 'react';
import TodoList from './components/todoList';
import InputBox from './components/inputBox';
class App extends Component {

  state = {
    todos: [{ id: 1, todo: "Learn JSX" }, { id: 2, todo: "Learn React" }, { id: 3, todo: "Learn JavaScript" }, { id: 4, todo: "Learn CSS" }]
  }

  deleteTodos = (id) => {
    let filteredTodo = this.state.todos.filter(todoObject => {
      // console.log(todoObject.id+"-----"+id);
      return todoObject.id != id;
    });
    console.log(filteredTodo);

    this.setState({
      todos: filteredTodo
    })
  }

  addTodo = (todo) => {
    this.setState({
      todos: [...this.state.todos , {id:this.state.todos.length+1,todo:todo}]
    })
  }

  render() {
    return (
      <React.Fragment>
        <div>
          <InputBox addTodoHandler={this.addTodo}></InputBox>
          <TodoList todos={this.state.todos} handleDelete={this.deleteTodos}></TodoList>
        </div>
      </React.Fragment>

    );
  }
}

export default App;

