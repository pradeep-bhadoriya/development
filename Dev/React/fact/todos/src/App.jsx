import React, { Component } from 'react';
import TodoList from './components/todoList';
import InputBox from './components/inputBox';
class App extends Component {

  state = { 
    todos:[{id:1,todo:"Learn JSX"},{id:1,todo:"Learn JSX"},{id:2,todo:"Learn React"},{id:3,todo:"Learn JavaScript"},{id:4,todo:"Learn CSS"}]
   }
  render() { 
    return ( 
      <React.Fragment>
        <InputBox></InputBox>
        <TodoList></TodoList>

      </React.Fragment>
     );
  }
}
 
export default App;

