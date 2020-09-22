import React from 'react';
import './App.css';
import { Router, Route, Switch } from 'react-router-dom';
import history from './Services/history';
import "./Components/FontAwesomeIcons/FontAwesomeIcons";
// import 'bootstrap/dist/css/bootstrap.min.css';
import LoginForm from './Components/LoginForm';
import Dashboard from './Components/CustomerDashboard/Dashboard';
import Home from './Components/Home';
import ProfilePage from './Components/ProfilePage.js';
import EditDetails from './Components/EditDetails.js';

function App() {
  return (
    <Router history={history}>
      <div className="App">

        <Switch>
          <Route path="/login" component={LoginForm}></Route>
          <Route path="/dashboard" component={Dashboard}></Route>
          <Route path="/profile" component={ProfilePage}></Route>
          <Route path="/edit" component={EditDetails}></Route>
          <Route path="/" component={Home}></Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
