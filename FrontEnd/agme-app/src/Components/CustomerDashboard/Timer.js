import React from 'react';

class Timer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            time: new Date().toLocaleTimeString()
        };

    }

    componentDidMount() {
        setInterval(() => {
            this.setState({ time: new Date().toLocaleTimeString() });
        }, 1000);
    }

    render() {
        return (
            <div className="display-time">
                <h1>Current Time</h1>
                <h2>{this.state.time}</h2>
            </div>
        )
    }

}

export default Timer;