import React from 'react';
import './Dashboard.scss';
import NavBar from './Layouts/NavBar.js'

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
                            </div>
                            <div className="upcoming-bookings">
                                <h2>Upcoming Bookings</h2>
                            </div>
                        </div>

                        <div className="past-bookings">
                            <div className="table-top">
                                <h2>Past Bookings</h2>
                                <div className="headings">
                                    <span>Service Provider</span>
                                    <span>Worker</span>
                                    <span>Date Booked</span>
                                </div>
                            </div>
                            <div className="divider"></div>
                            <div className="past-booking-details">
                                <div className="past-booking-content">
                                    <span className="service-provider">Service Provider Name</span>
                                    <span className="worker-details">Worker Name</span>
                                    <span className="date">Date</span>
                                </div>
                            </div>
                            <div className="divider"></div>
                            <div className="past-booking-details">
                                <div className="past-booking-content"></div>
                            </div>
                            <div className="divider"></div>
                            <div className="past-booking-details">
                                <div className="past-booking-content"></div>
                            </div>
                            <div className="divider"></div>
                            <div className="past-booking-details">
                                <div className="past-booking-content"></div>
                            </div>
                            <div className="divider"></div>
                            <div className="past-booking-details">
                                <div className="past-booking-content">
                                    <span>Service Provider</span>
                                </div>
                            </div>

                        </div>
                    </section>
                </div>
            </div>
        )
    }
}

export default Dashboard;