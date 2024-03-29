import React, { Component } from 'react';

class TodoList extends Component {
    state = {};
    render() {
        let todos = this.props.todos;
        let handleDelete = this.props.handleDelete;
        // console.log(handleDelete);
        return (
            <ul className="list-group">
                {todos.map((todoObject) => {
                    return (
                        <li className="list-group-item d-flex justify-content-between"
                            key={todoObject.id}>
                            {todoObject.todo}
                            <button
                                className="btn btn-danger"
                                onClick={ () => { handleDelete(todoObject.id) }}>
                                Delete
                            </button>
                        </li>
                    );
                })}
            </ul>
        );

    }
}

export default TodoList;