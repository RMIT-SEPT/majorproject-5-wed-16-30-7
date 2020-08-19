import React from 'react';
import './LoginForm.scss';
class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',

            errors: {
                username: '',
                password: '',
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.getCustomerLogin = this.getCustomerLogin.bind(this);
    }

    getCustomerLogin = (e) => {
        e.preventDefault();
        let customer = { username: this.state.username, password: this.state.password };
        console.log('customerLogin =>' + JSON.stringify(customer));
    }

    handleChange = (event) => {
        event.preventDefault();
        const { name, value } = event.target;
        let errors = this.state.errors;

        switch (name) {
            case 'username':
                errors.username =
                    value.length < 5
                        ? 'Please enter a valid username'
                        : '';
                break;

            case 'password':
                errors.password =
                    value.length < 3
                        ? 'Please enter a valid password'
                        : '';
                break;

            default:
                break;
        }

        this.setState({ errors, [name]: value }, () => {
            // console.log(errors)
        })
    }

    render() {
        const { errors } = this.state;
        return (
            <div>
                <form className="login-form">
                    <h1>Login</h1>
                    <div className="form-input-material">
                        <label className="label" for="username">Username</label>
                        <input type='text' name='username' id='username' placeholder="Enter Username " className="form-control-material"
                            value={this.state.username} onChange={this.handleChange} required />
                    </div>

                    <div className="form-input-material">
                        <label className="label" for="password">Password</label>
                        <input type='password' name='password' placeholder=" " className="form-control-material"
                            value={this.state.password} onChange={this.handleChange} required />
                    </div>
                    <button type="submit" className="btn btn-primary btn-ghost" onClick={this.getCustomerLogin}>Login</button>
                </form>
            </div>
        );
    }
}


export default LoginForm;