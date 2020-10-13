import React from 'react';
import Image from './Images/hairdresser.jpg';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Timer from './Timer.js';
class TodayBookings extends React.Component {

    render() {
        return (
            <React.Fragment>
                <div className="grid-wrapper">
                    <div className="today-booking-details">
                        <div className="booking-service-image">
                            <img src={Image} alt="Service"></img>
                        </div>
                        <div className="details-wrapper">
                            <div className="icon-wrapper">
                                <span className="service-name">Service Name</span>
                            </div>
                            <div className="icon-wrapper">
                                <span><FontAwesomeIcon icon="user-circle" /></span>
                                <span className="employee-name">Employee Name</span>
                            </div>
                            <div className="icon-wrapper">
                                <span><FontAwesomeIcon icon="calendar" /></span>
                                <span className="appointment-time">Time of Appointement</span>
                            </div>
                        </div>
                    </div>

                    <Timer />
                </div>


            </React.Fragment>
        )
    }
}

export default TodayBookings;