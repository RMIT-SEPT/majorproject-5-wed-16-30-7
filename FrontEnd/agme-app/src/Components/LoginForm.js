import React from 'react';
import './LoginForm.scss';
class LoginForm extends React.Component {
    
    constructor(props){
        super(props);
        this.state = {
            username: null,
            password: null,

            errors: {
                username: '',
                password: '',
            }
        };
    }

    handleChange = (event) => {
        event.preventDefault();
        const {name, value} = event.target;
        let errors = this.state.errors;

        switch (name){
            case 'username':
                errors.username = 
                value.length < 5
                ? 'Please enter a valid username'
                :'';
            break;

            case 'password':
                errors.password = 
                value.length < 3
                ? 'Please enter a valid password'
                :'';
            break;

            default:
            break;
        }

        this.setState({errors, [name]: value}, ()=> {
            console.log(errors)
        })
    }

    render() {
        const {errors} = this.state;
        return (
            <div>
                <form className="login-form">
                    <h1>Login</h1>
                    <div className="form-input-material">
                        <label className="label" for="username">Username</label>
                        <input type='text' name='username' id='username' placeholder="Enter Username " className="form-control-material" onChange = {this.handleChange} required />
                    </div>

                    <div className="form-input-material">
                        <label className="label" for="password">Password</label>
                        <input type='password' name='password' placeholder=" " className="form-control-material" onChange = {this.handleChange} required />
                    </div>
                    <button type="submit" class="btn btn-primary btn-ghost" >Login</button>
                </form>
            </div>
        );
    }
}


export default LoginForm;