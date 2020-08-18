import React from 'react';

class LoginForm extends React.Component {

    render() {
        return (
            <div className="loginForm">

                <div>
                    <label>
                        User Name:
                    <input type="text" name="userName" />
                    </label>
                </div>

                <div>
                    <label>
                        Password:
                    <input type="text" name="userName" />
                    </label>
                </div>
                <input type="submit" value="Submit" />

            </div>
        );
    }
}


export default LoginForm;