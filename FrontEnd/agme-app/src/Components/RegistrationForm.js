import React from 'react';
import './LoginForm.scss';

class RegistrationForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            address: '',
            phone: '',
            username: '',
            password: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.getCustomerRegistration = this.getCustomerRegistration.bind(this);
    }

    getCustomerRegistration = (e) => {
        e.preventDefault();
        let customer = {
            name: this.state.name,
            address: this.state.address,
            phone: this.state.phone,
            username: this.state.username,
            password: this.state.password
        };
        console.log('customerLogin =>' + JSON.stringify(customer));
    }

    handleChange = (event) => {
        event.preventDefault();
        const { name, value } = event.target;

        this.setState({ [name]: value }, () => {
        })
    }

    render() {
        return (
            <form id="register" className="input-group">
                <input type="text" className="input-field" name='name' placeholder="Full Name" value={this.state.name} onChange={this.handleChange} required />
                <input type="text" className="input-field" name='address' placeholder="Address" value={this.state.address} onChange={this.handleChange} required />
                <input type="text" className="input-field" name='phone' placeholder="Phone Number" value={this.state.phone} onChange={this.handleChange} required />
                <input type="text" className="input-field" name='username' placeholder="User Name" value={this.state.username} onChange={this.handleChange} required />
                <input type="password" className="input-field" placeholder="Password" required />
                <input type="password" className="input-field" name='password' placeholder="Confirm Password" value={this.state.password} onChange={this.handleChange} required />
                <button type="submit" className="submit-btn" onClick={this.getCustomerRegistration}>Register</button>
            </form>
        );
    }
}

export default RegistrationForm;