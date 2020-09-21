import React from 'react';

class UpcomingBookings extends React.Component {


    constructor(props) {
        super(props);

        this.state = {
            urgency: [{
                high: "#E0404C"
            }],

            upcomingBookings: [{
                id: 0,
                service_name: "Service Name",
                worker_name: "Worker Name",
                date_booked: "Date Booked",
            },
            {
                id: 1,
                service_name: "Service Name",
                worker_name: "Worker Name",
                date_booked: "Date Booked"
            },
            {
                id: 2,
                service_name: "Service Name",
                worker_name: "Worker Name",
                date_booked: "Date Booked"
            },
            {
                id: 3,
                service_name: "Service Name",
                worker_name: "Worker Name",
                date_booked: "Date Booked"
            }
            ]
        }
    }


    render() {
        return (
            <React.Fragment>
                <div className="upcoming-bookings-list">
                    <ul>
                        {this.state.upcomingBookings.map(bookings => (
                            <li key={bookings.id}>
                                <div className="booking-content">
                                    {this.state.urgency.map(urgency => (
                                        <div className="vertical-bar" key={urgency.id} style={{ background: urgency.high }}> </div>
                                    ))}
                                    <span className="booking-details">
                                        <div className="service-name">{bookings.service_name}</div>
                                        <div className="worker-name">{bookings.worker_name}</div>
                                    </span>
                                    <span className="booking-date">{bookings.date_booked}</span>
                                    <span className="cancel-button"><button><p>Cancel</p></button></span>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            </React.Fragment >
        )
    }
}

export default UpcomingBookings;