import React from 'react';
import './Dashboard.scss';

class Dashboard extends React.Component {
    render() {
        return (
            <div>
                <div className="TopContainer">
                    <div className="TodayBookings"></div>
                    <div className="UpcomingBookings"></div>
                </div>
                <div className="PastBookings"></div>
            </div>
        )
    }
}

export default Dashboard;