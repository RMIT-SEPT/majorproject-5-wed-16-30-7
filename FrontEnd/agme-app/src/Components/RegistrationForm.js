import React from 'react';
import './LoginForm.scss';
class LoginForm extends React.Component {

    render() {
        <div>
            <form className="login-form">
                <h1>Registration</h1>
                <div className="form-input-material">
                    <label className="label" for="name">Name</label>
                    <input type='text' name='name' id='name' placeholder="Enter Name " className="form-control-material"
                        value={this.state.username} onChange={this.handleChange} required />
                </div>

                <div className="form-input-material">
                    <label className="label" for="email">Email</label>
                    <input type='email' name='email' placeholder=" " className="form-control-material"
                        value={this.state.password} onChange={this.handleChange} required />
                </div>

                <div className="form-input-material">
                    <label className="label" for="password">Password</label>
                    <input type='password' name='password' placeholder=" " className="form-control-material"
                        value={this.state.password} onChange={this.handleChange} required />
                </div>

                <div className="form-input-material">
                    <label className="label" for="password-confirm">Confirm Password</label>
                    <input type='password' name='password-confirm' placeholder=" " className="form-control-material"
                        value={this.state.password} onChange={this.handleChange} required />
                </div>
                <button type="submit" className="btn btn-primary btn-ghost" onClick={this.getCustomerLogin}>Login</button>
            </form>
        </div>
    }
}