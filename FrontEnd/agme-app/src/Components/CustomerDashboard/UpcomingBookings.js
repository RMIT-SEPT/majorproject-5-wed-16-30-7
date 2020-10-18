import React from 'react';
import UserService from '../../Services/UserService.js';
import 'moment-timezone';
import moment from 'moment';

class UpcomingBookings extends React.Component {


    constructor(props) {
        super(props);

        this.state = {
            urgency: [{
                id: 0,
                high: "#E0404C"
            }],

            //Mock data to be used incase for testing
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
            ],

            ongoingBookings: []
        }
    }

    //Get ongoing bookings of a user from backend
    async getOngoingBookings() {
        //Call axios function to retrieve data
        const res = await UserService.getCustomerOngoingBookings(1);
        const data = res.data;

        //Map data
        const bookings = data.map(d => ({
            "id": d.booking_id,
            "service_name": d.service_name,
            "provider_name": d.provider_name,
            "date_booked": moment(d.booking_date).format('MMMM Do YYYY, h:mm:ss a')
        }))

        //Set state for ongoingBookings
        this.setState({ ongoingBookings: bookings });

    }

    //Call getOngoingBookings 
    componentDidMount() {
        this.getOngoingBookings();
    }


    render() {
        return (
            <React.Fragment>
                <div className="upcoming-bookings-list">
                    <ul>
                        {this.state.ongoingBookings.map(bookings => (
                            <li key={bookings.id}>
                                <div className="booking-content">
                                    {this.state.urgency.map(urgency => (
                                        <div className="vertical-bar" key={urgency.id} style={{ background: urgency.high }}> </div>
                                    ))}
                                    <span className="booking-details">
                                        <div className="service-name">{bookings.service_name}</div>
                                        <div className="worker-name">{bookings.provider_name}</div>
                                    </span>
                                    <span className="booking-date">{bookings.date_booked}</span>
                                    <span className="cancelButton"><button><p>Cancel</p></button></span>
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