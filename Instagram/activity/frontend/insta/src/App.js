import React from "react";
import { Provider } from "react-redux";
import store from "./redux/store";
import Navbar from "./components/navbar/navbar";
import "./App.css"
import Feeds from "./components/feeds/feeds";
import ProfileView from "./components/profileView/profileView";

function App() {
  return (
    <Provider store={store}>
      <Navbar></Navbar>
      <div class="homepage">
        <Feeds></Feeds>
        <ProfileView></ProfileView>
      </div>
    </Provider>
  );

}

export default App;
