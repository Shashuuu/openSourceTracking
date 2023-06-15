import React from 'react';
import Home from './component/Home'
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Register from './component/Register';
import Login from './component/Login';
import './App.css';
import ClientRegistrationFailure from './component/ClientRegistrationFailure';
import AddOSProject from './component/AddOSProject';
import ViewAllOSProjects from './component/ViewAllOSProjects';
import EditOSProject from './component/EditOSProject';
import ViewOSProject from './component/ViewOSProject';
import BookFailure from './component/BookFailure';

function App() {
  return (
    <div>
      
      <Route exact path="/" component={Home} />
      <Route exact path="/login" component={Login} />
      <Route exact path="/register" component={Register} />
      <Route exact path='/ClientRegistrationFailure' component={ClientRegistrationFailure} />
      <Route exact path="/addOSProject" component={AddOSProject} />
      <Route exact path="/editOSProject" component={EditOSProject} />
      <Route exact path='/ViewAllOSProjects' component={ViewAllOSProjects} />
      <Route exact path='/ViewOSProject' component={ViewOSProject} />
      <Route exact path='/bookFailure' component={BookFailure} />
    </div>
  );

}

export default App;
