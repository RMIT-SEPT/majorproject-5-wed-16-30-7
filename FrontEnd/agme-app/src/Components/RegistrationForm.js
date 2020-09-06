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
            confirmPass: '',

            errors: {
                name: '',
                address: '',
                phone: '',
                username: '',
                password: '',
                confirmPass: '',
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.getCustomerRegistration = this.getCustomerRegistration.bind(this);
    }


    getCustomerRegistration = (e) => {
        e.preventDefault();

        //Check if form contains any errors
        const validateForm = (errors) => {
            let valid = true;
            Object.values(errors).forEach(
                (val) => val.length > 0 && (valid = false)
            );
            return valid;
        }

        //Submit form if form is valid
        if (validateForm(this.state.errors)) {
            console.info('Valid Form');
            let customer = {
                name: this.state.name,
                address: this.state.address,
                phone: this.state.phone,
                username: this.state.username,
                password: this.state.password
            };
            console.log('customerLogin =>' + JSON.stringify(customer));
        } else {
            console.error('Invalid Form')
        }

    }


    handleChange = (event) => {
        event.preventDefault();
        const { name, value } = event.target;
        let errors = this.state.errors;

        switch (name) {
            case 'name':
                errors.name =
                    value.length > 25
                        ? 'Name too long!'
                        : '';
                break;
            case 'username':
                errors.username =
                    value.length < 5
                        ? 'Username too short!'
                        : '';
                break;
            case 'password':
                errors.password =
                    value.length < 8
                        ? 'Password must be atleast 8 characters long!'
                        : '';
                break;
            case 'confirmPass':
                errors.confirmPass =
                    value !== this.state.password
                        ? 'Password does not match!'
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
            <form id="register" className="input-group">
                <input type="text" className="input-field" name='name' placeholder="Full Name" value={this.state.name} onChange={this.handleChange} required />
                {errors.name.length > 0 &&
                    <span className='error'>{errors.name}</span>}
                <input type="text" className="input-field" name='address' placeholder="Address" value={this.state.address} onChange={this.handleChange} required />
                <input type="text" className="input-field" name='phone' placeholder="Phone Number" value={this.state.phone} onChange={this.handleChange} required />
                <input type="text" className="input-field" name='username' placeholder="User Name" value={this.state.username} onChange={this.handleChange} required />
                {errors.username.length > 0 &&
                    <span className='error'>{errors.username}</span>}
                <input type="password" className="input-field" name='password' placeholder="Password" value={this.state.password} onChange={this.handleChange} required />
                {errors.password.length > 0 &&
                    <span className='error'>{errors.password}</span>}
                <input type="password" className="input-field" name='confirmPass' placeholder="Confirm Password" value={this.state.confirmPass} onChange={this.handleChange} required />
                {errors.confirmPass.length > 0 &&
                    <span className='error'>{errors.confirmPass}</span>}
                <button type="submit" className="submit-btn" onClick={this.getCustomerRegistration}>Register</button>
            </form>
        );
    }
}

export default RegistrationForm;