import React from 'react';
import './NavBar.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
class NavBar extends React.Component {
    render() {
        return (
            <section className="navigation">
                <ul>
                    <li>
                        <div>
                            <span className="icon"><FontAwesomeIcon icon="home" /></span>
                            <span className="title">Home</span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="tachometer-alt" /></span>
                            <span className="title">Dashboard</span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="user" /></span>
                            <span className="title">Profile</span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="cog" /></span>
                            <span className="title">Settings</span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span className="icon"><FontAwesomeIcon className="fontAwesome" icon="sign-out-alt" /></span>
                            <span className="title">Logout</span>
                        </div>
                    </li>
                </ul>
            </section>
        )
    }
}

export default NavBar;