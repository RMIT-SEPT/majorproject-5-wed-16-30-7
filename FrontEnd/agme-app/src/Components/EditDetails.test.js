import React from 'react';
import UserService from '../Services/UserService';
import { Dropdown, DropdownMenu, DropdownToggle, DropdownItem, ButtonGroup, DropdownButton } from 'react-bootstrap';
export default class EditDetails extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            address: '',
            phone: '',
            username: '',
            password: '',
            confirmPass: '',
            accountType: 'c',
            persons: [],
            currentId: '',
            errors: {
                name: '',
                address: '',
                phone: '',
                username: '',
                password: '',
                confirmPass: '',
            }
        };


        UserService.getCustomer()
            .then(res => {

                this.setState({ ...this.state, persons: res.data });
            })



        this.handleChange = this.handleChange.bind(this);
        this.editdetails = this.editdetails.bind(this);
        this.selectUser = this.selectUser.bind(this);
    }


    componentDidMount() {
        UserService.getCustomer()
            .then(res => {
                const persons = res.data;
                this.setState({ persons });
                console.log(this.state.persons);
            })


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


    editdetails = (e) => {
        e.preventDefault();

        //Check if form contains any errors
        const validateForm = (errors) => {
            let valid = true;
            Object.values(errors).forEach(
                (val) => val.length > 0 && (valid = false)
            );
            return valid;
        }

        //Submit form if valid
        if (validateForm(this.state.errors)) {
            console.info('Valid Form');
            let customer = {
                //ADD ID
                name: this.state.name,
                address: this.state.address,
                phone: this.state.phone,
                username: this.state.username,
                password: this.state.password,
                accountType: this.state.accountType
            };
            console.log('customerLogin =>' + JSON.stringify(customer));
            console.log(this.state.currentId);
            UserService.editCustomer(customer, this.state.currentId);
            // UserService.createCustomer(customer).then(res => {
            //     this.props.history.push('/dashboard');
            // });
        } else {
            console.error('Invalid Form')
        }

    }

    selectUser = (e) => {
        const person = this.state.persons[(e.target.value) - 1];
        this.setState({ currentId: e.target.value })
        this.setState({ name: person.name });
        this.setState({ address: person.address });
        this.setState({ phone: person.phone });
        this.setState({ username: person.username });
        this.setState({ password: person.password });


    }


    render() {
        return (
            <ul>
                <div>

                </div>


                <form id="editdetails" className="input-group">
                    <select id="dropdown" onChange={this.selectUser} >
                        {this.state.persons.map(person => (
                            <option value={person.id}>{person.id}</option>
                        ))}
                    </select>
                    <input type="text" className="input-field" name='name' placeholder="Full Name" value={this.state.name} onChange={this.handleChange} required />
                    <input type="text" className="input-field" name='address' placeholder="Address" value={this.state.address} onChange={this.handleChange} required />
                    <input type="text" className="input-field" name='phone' placeholder="Phone Number" value={this.state.phone} onChange={this.handleChange} required />
                    <input type="text" className="input-field" id="usernamefield" name='username' placeholder="User Name" value={this.state.username} onChange={this.handleChange} required />
                    <input type="password" className="input-field" id="password" name='password' placeholder="Password" value={this.state.password} onChange={this.handleChange} required />
                    <button type="submit" className="submit-btn" onClick={this.editdetails}>Edit</button>
                </form>
            </ul>
        )
    }
}