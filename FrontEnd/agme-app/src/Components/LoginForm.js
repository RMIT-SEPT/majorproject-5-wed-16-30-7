import React from 'react';

class LoginForm extends React.Component {

    render() {
        return (
            <div className="loginForm">
                <form>
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
                </form>
            </div>
        );
    }
}


export default LoginForm;