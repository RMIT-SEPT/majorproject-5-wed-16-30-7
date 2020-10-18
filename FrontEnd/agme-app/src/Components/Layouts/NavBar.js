import React from 'react';
import './NavBar.scss';
import { NavLink } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Cookies from 'js-cookie';
class NavBar extends React.Component {

    constructor(props) {
            super(props);
            this.logout = this.logout.bind(this);
        }
    logout = (e) =>{
        e.preventDefault();
       Cookies.set('id', '-1');
       Cookies.set('logged_in', 'false');
    }

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

                            <div>
                                <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="tachometer-alt" onChange={this.   logout}  /></span>
                                <span className="title">Dashboard</span>
                            </div>

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
                    <li onClick={this.logout}>
                        <NavLink to="/login" exact activeClassName="current-link"  >
                            <div>
                                <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="sign-out-alt" onChange={this.logout}/></span>
                                <span className="title" onChange={this.logout}>Logout</span>
                            </div>
                        </NavLink>
                    </li>
                </ul>
            </section>
        )
    }
}

export default NavBar;