import React from 'react';
import EditDetails from './EditDetails.js';
import {shallow, mount} from 'enzyme';
import Enzyme from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });

const simulate = (wrapper, inputSelector, newValue) => {
    let input = wrapper.find(inputSelector);
    input.simulate('change', {
        preventDefault: () => { },
        target: { value: newValue}
    });

    console.log(newValue);
    console.log(inputSelector);

    return wrapper.find(inputSelector);

};

describe("Edit Details form Unit Test", () => {

    it("Should render the form component", () => {
        const component = shallow(<EditDetails />);
        expect(component).toHaveLength(1);
    });

    describe("Check if input values are not empty", () => {
        it("Has default input value for username is not empty", () => {

            setTimeout(() => {
                 const wrapper = shallow(<EditDetails />);
                 const username = wrapper.find('#usernamefield');
                 expect(username.props().value).toEqual('s3723189');
                                 done();

                }, 400);

        });
        it("Has default input value for password is not  empty", () => {
                    const wrapper = shallow(<EditDetails />);
                    setTimeout(() => {
                      const password = wrapper.find('#password');

                     expect(password.props().value).toEqual('12345');
                     done();

                    }, 400);

                });
    });




})
