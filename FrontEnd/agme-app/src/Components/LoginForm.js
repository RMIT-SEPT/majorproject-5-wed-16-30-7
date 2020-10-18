import React from 'react';
import './LoginForm.scss';
import RegistartionForm from './RegistrationForm.js'
import UserService from '../Services/UserService';
import history from '../Services/history';
import Cookies from 'js-cookie'
class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            persons: [],
            error: ''
        };
        if(Cookies.get('logged_in') == 'true')
        {
        history.push('./dashboard')
        }
        this.handleChange = this.handleChange.bind(this);
        this.getCustomerLogin = this.getCustomerLogin.bind(this);
    }

    componentDidMount() {
            UserService.getCustomer()
                .then(res => {
                    const persons = res.data;
                    this.setState({ persons });
                    console.log(this.state.persons);
                });

    }



    //Change form style for register when register button is clicked
    register() {
        document.getElementById("login").style.left = "-400px";
        document.getElementById("register").style.left = "50px";
        document.getElementById("btn").style.left = "110px";
        document.getElementById("form-box").style.height = "700px";
    }

    //Change form style for login when login button is clicked
    login() {
        document.getElementById("login").style.left = "50px";
        document.getElementById("register").style.left = "450px";
        document.getElementById("btn").style.left = "0px";
        document.getElementById("form-box").style.height = "450px";
    }

    //Get customer username and password from input fields
    //Pass input field values onto back end for validation
    getCustomerLogin = (e) => {
        e.preventDefault();

        //Login out to console to check if correct values from input fields are captured
        let customer = { username: this.state.username, password: this.state.password };
        console.log('customerLogin =>' + JSON.stringify(customer));

        //Login the user given correct pass and username
        UserService.loginCustomer(this.state.username, this.state.password).then(res => {
            //If login succesful redirect to dashboard
            if (res.status === 202) {

                 Cookies.set('logged_in', 'true');
                 Cookies.set('password', this.state.password);
                 {this.state.persons.map(person => {
                                         console.log(person.id);
                                        if(person.username  === this.state.username)
                                        {
                                            console.log(person.id);
                                             Cookies.set('id', person.id);
                                            Cookies.set('name', person.name)
                                        }
                                     })}
                console.log(this.state.error);
                this.setState({ error: '' });
                history.push('./dashboard');
            }
            //Else stay on login page
        }).catch(() => {
            this.setState({ error: '*Username or Password is invalid*' });
        });

    }

    //Capture user input values
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
                        <span className='error'>{this.state.error}</span>
                        <input type="text" id="username" className="input-field" placeholder="User Name" name='username' value={this.state.username} onChange={this.handleChange} required />
                        <input type="password" id="password" className="input-field" placeholder="Password" name='password' value={this.state.password} onChange={this.handleChange} required />
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