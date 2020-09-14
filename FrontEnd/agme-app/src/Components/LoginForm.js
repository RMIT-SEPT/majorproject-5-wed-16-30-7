import React from 'react';
import './LoginForm.scss';
import RegistartionForm from './RegistrationForm.js'
class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.getCustomerLogin = this.getCustomerLogin.bind(this);
    }


    register() {
        document.getElementById("login").style.left = "-400px";
        document.getElementById("register").style.left = "50px";
        document.getElementById("btn").style.left = "110px";
        document.getElementById("form-box").style.height = "700px";
    }

    login() {
        document.getElementById("login").style.left = "50px";
        document.getElementById("register").style.left = "450px";
        document.getElementById("btn").style.left = "0px";
        document.getElementById("form-box").style.height = "450px";
    }

    getCustomerLogin = (e) => {
        e.preventDefault();
        let customer = { username: this.state.username, password: this.state.password };
        console.log('customerLogin =>' + JSON.stringify(customer));
    }

    handleChange = (event) => {
        event.preventDefault();
        const { name, value } = event.target;

        this.setState({ [name]: value }, () => {
            // console.log(errors)
        })
    }

    render() {
        return (
            <div className="main">
                <div id="form-box" className="form-box">
                    <div className="button-box">
                        <div id="btn"></div>
                        <button type="button" className="toggle-btn" onClick={this.login}>Login</button>
                        <button type="button" className="toggle-btn" onClick={this.register}>Register</button>
                    </div>
                    <form id="login" className="input-group" onSubmit={this.getCustomerLogin}>
                        <input type="text" className="input-field" placeholder="User Name" name='username' value={this.state.username} onChange={this.handleChange} required />
                        <input type="password" className="input-field" placeholder="Password" name='password' value={this.state.password} onChange={this.handleChange} required />
                        <input type="checkbox" className="check-box" /><span id="rememberMe">Remember Me</span>
                        <button type="submit" className="submit-btn" >Login</button>
                    </form>
                    <RegistartionForm />
                </div>
            </div>
        );
    }
}


export default LoginForm;