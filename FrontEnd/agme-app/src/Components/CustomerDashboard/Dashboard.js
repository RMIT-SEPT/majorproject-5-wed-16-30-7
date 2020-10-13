import React from 'react';
import './Dashboard.scss';
import NavBar from '../Layouts/NavBar.js'
import PastBookings from './PastBookingsTable.js';
import UpcomingBookings from './UpcomingBookings.js';
import TodayBookings from './TodayBookings.js';

class Dashboard extends React.Component {

    render() {
        return (
            <div>
                <section className="Header">
                    <h1>My Dashboard</h1>
                </section>


                <div className="container">
                    <NavBar />
                    <section className="content-area">
                        <div className="top-container">
                            <div className="todays-bookings">
                                <h2>Today's Schedule</h2>
                                <TodayBookings />
                            </div>
                            <div className="upcoming-bookings">
                                <h2>Upcoming Bookings</h2>
                                <UpcomingBookings />
                            </div>
                        </div>

                        <div className="past-bookings">
                            <div className="table-top">
                                <h2>Past Bookings</h2>
                            </div>
                            <PastBookings />
                        </div>
                    </section>
                </div>
            </div>
        )
    }
}

export default Dashboard;