import logo from './logo.svg';
import './App.css';
import CakeContainer from './components/CakeContainer';
import React from "react";
import {Provider} from "react-redux";
import store from "./redux/cake/store"
import HooksCakeContainer from './components/HooksCakeContainer';
import IcecreamContainer from './components/IcecreamContainer';
import { Usercomponent } from './components/UserComponent';

function App() {
  return (
    <Provider store={store}>
      <div className="App">
        <HooksCakeContainer></HooksCakeContainer>
        <CakeContainer></CakeContainer>
        <IcecreamContainer></IcecreamContainer>
        <Usercomponent></Usercomponent>
      </div>
    </Provider>
  );
}

export default App;
