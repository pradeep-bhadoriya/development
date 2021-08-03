import React from "react";
import { Provider } from "react-redux";
import store from "./redux/store";
import "./App.css"
import Navbar from "./components/navbar/navbar";
import Feeds from "./components/feeds/feeds";
import ProfileView from "./components/profileView/profileView";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Setting from "./components/setting/setting";
import Profile from "./components/profile/profile";


function App() {
  return (
    <Router>
      <Provider store={store}>
        <Navbar></Navbar>
        <Route path="/" exact>
        <div class="homepage">
          <Feeds></Feeds>
          <ProfileView></ProfileView>
        </div>
        </Route>
        <Route path="/profile" exact>
        <Profile></Profile>
        </Route>
        <Route path="/settings" exact>
        <Setting></Setting>
        </Route>

      </Provider>
    </Router>
  ); y

}

export default App;
