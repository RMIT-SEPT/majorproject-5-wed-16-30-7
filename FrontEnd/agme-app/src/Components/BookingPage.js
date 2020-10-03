import React, { useState } from 'react';
import './BookingPage.scss';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


class BookingPage extends React.Component {


    render() {

        return (
            <div >
                <div className="booking-container">
                    <div className="booking-form">
                        <form>
                            <DatePicker
                                placeholderText="Select a day"
                                dateFormat='dd/MM/yyyy'
                                isValidDate={true} />
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

export default BookingPage;