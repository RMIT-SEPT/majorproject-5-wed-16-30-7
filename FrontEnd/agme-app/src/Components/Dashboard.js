import React from 'react';
import './Dashboard.scss';
import NavBar from './Layouts/NavBar.js'
import PastBookings from './PastBookingsTable.js';

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
                                <div className="upcoming-bookings-list">
                                    <ul>
                                        <li>
                                            <div className="booking-content">
                                                <span className="booking-details">
                                                    <div className="service-name">Service Name</div>
                                                    <div className="worker-name">Worker Name</div>
                                                </span>
                                                <span className="booking-date">Date Booked</span>
                                                <span className="cancel-button"><button>Cancel</button></span>
                                            </div>
                                        </li>
                                        <li>
                                            <div className="booking-content">
                                                <span className="booking-details">
                                                    <div className="service-name">Service Name</div>
                                                    <div className="worker-name">Worker Name</div>
                                                </span>
                                                <span className="booking-date">Date Booked</span>
                                                <span className="cancel-button"><button>Cancel</button></span>
                                            </div>
                                        </li>
                                        <li>
                                            <div className="booking-content">
                                                <span className="booking-details">
                                                    <div className="service-name">Service Name</div>
                                                    <div className="worker-name">Worker Name</div>
                                                </span>
                                                <span className="booking-date">Date Booked</span>
                                                <span className="cancel-button"><button>Cancel</button></span>
                                            </div>
                                        </li>
                                        <li>
                                            <div className="booking-content">
                                                <span className="booking-details">
                                                    <div className="service-name">Service Name</div>
                                                    <div className="worker-name">Worker Name</div>
                                                </span>
                                                <span className="booking-date">Date Booked</span>
                                                <span className="cancel-button"><button>Cancel</button></span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

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