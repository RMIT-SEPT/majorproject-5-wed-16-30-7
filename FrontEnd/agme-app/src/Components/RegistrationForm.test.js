import React from 'react';
import { render, unmountComponentAtNode } from 'react-dom';
import { fireEvent } from '@testing-library/react';
import { act } from 'react-dom/test-utils';


import RegistrationForm from './RegistrationForm.js';


let container = null;
beforeEach(() => {
    // setup a DOM element as a render target
    container = document.createElement("div");
    document.body.appendChild(container);
});

afterEach(() => {
    // cleanup on exiting
    unmountComponentAtNode(container);
    container.remove();
    container = null;
});

describe("Render form", () => {
    it("Should render registration form", () => {
        act(() => {
            render(<RegistrationForm />, container);
        });
        expect(container).toBeTruthy();
    });
})

describe("Input Values", () => {
    const onChange = jest.fn();
    const testNames = ["name", "address", "phone", "username", "password", "confirmPass"];

    for (let i = 0; i < testNames.length; ++i) {
        it("Updates " + testNames[i] + " field on change", () => {
            act(() => {
                render(<RegistrationForm onChange={onChange} />, container);
            });
            var input = document.querySelector("[data-testid=" + CSS.escape(testNames[i].toString()) + "]");
            fireEvent.change(input, { target: { value: testNames[i] } });
            expect(input.value).toBe(testNames[i]);
        });
    }

});

