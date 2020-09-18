import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import "./Components/FontAwesomeIcons/FontAwesomeIcons"
import LoginForm from './Components/LoginForm';
import Dashboard from './Components/Dashboard';

function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/login" component={LoginForm}></Route>
          <Route path="/dashboard" component={Dashboard}></Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
