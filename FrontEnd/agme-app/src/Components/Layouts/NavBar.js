import React from 'react';
import './NavBar.scss';
import { NavLink } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

class NavBar extends React.Component {
    render() {
        return (
            <section className="navigation">
                <ul>
                    <li>
                        <NavLink to="/" exact activeClassName="current-link">
                            <div>
                                <span className="icon"><FontAwesomeIcon icon="home" /></span>
                                <span className="title">Home</span>
                            </div>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/dashboard" exact activeClassName="current-link">
                            <div>
                                <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="tachometer-alt" /></span>
                                <span className="title">Dashboard</span>
                            </div>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/profile" exact activeClassName="current-link">
                            <div>
                                <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="user" /></span>
                                <span className="title">Profile</span>
                            </div>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="" exact activeClassName="current-link">
                            <div>
                                <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="cog" /></span>
                                <span className="title">Settings</span>
                            </div>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/login" exact activeClassName="current-link">
                            <div>
                                <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="sign-out-alt" /></span>
                                <span className="title">Logout</span>
                            </div>
                        </NavLink>
                    </li>
                </ul>
            </section>
        )
    }
}

export default NavBar;