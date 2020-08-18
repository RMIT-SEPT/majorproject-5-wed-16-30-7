import React from 'react';
import './LoginForm.scss';
class LoginForm extends React.Component {

    render() {
        return (
            <div>
                <form className="login-form">
                    <h1>Login</h1>
                    <div className="form-input-material">
                        <label className="label" for="username">Username</label>
                        <input type="text" name="username" id="username" placeholder="Enter Username " className="form-control-material" required />
                    </div>

                    <div className="form-input-material">
                        <label className="label" for="password">Password</label>
                        <input type="password" name="password" placeholder=" " className="form-control-material" required />
                    </div>
                    <button type="submit" class="btn btn-primary btn-ghost" >Login</button>
                </form>
            </div>
        );
    }
}


export default LoginForm;