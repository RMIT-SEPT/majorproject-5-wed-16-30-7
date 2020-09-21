import React from 'react';
import LoginForm from './LoginForm.js';
import { shallow, mount } from 'enzyme';
import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });

const simulateChangeOnInput = (wrapper, inputSelector, inputName, newValue) => {
    let input = wrapper.find(inputSelector);
    input.simulate('change', {
        preventDefault: () => { },
        target: {
            name: inputName,
            value: newValue
        }

    });
    return wrapper.find(inputSelector);
};


describe("Login Form Unit Tests", () => {

    let wrapper = null;
    beforeEach(() => {
        wrapper = shallow(<LoginForm />);
    });

    afterEach(() => {
        wrapper.unmount();
        wrapper = null;
    });

    it("Should render the form component correctly", () => {
        expect(wrapper).toHaveLength(1);
    });

    describe("Check if input values are initially empty", () => {

        it("Has default input value for username as empty", () => {
            const userName = wrapper.find('#username');
            expect(userName.props().value).toEqual('');
        });

        it("Has default input value for password as empty", () => {
            const userName = wrapper.find('#password');
            expect(userName.props().value).toEqual('');
        });
    });

    describe("Check if input value changes after user input", () => {

        it("Changes input value for username", () => {
            const usernameInput = simulateChangeOnInput(
                wrapper,
                '#username',
                'username',
                'TestUsername'
            );
            expect(usernameInput.props().value).toEqual('TestUsername');

        });

        it("Changes input value for password", () => {
            const usernameInput = simulateChangeOnInput(
                wrapper,
                '#password',
                'password',
                'TestPassword'
            );
            expect(usernameInput.props().value).toEqual('TestPassword');
        });
    });

});