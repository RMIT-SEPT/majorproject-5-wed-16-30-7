import React from 'react';
import './BookingPage.scss';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Select from 'react-select';

class BookingPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            selectedOption1: null,
            selectedOption2: null,
            selectedDate: null
        };
    };

    handleChange1 = selectedOption1 => {
        this.setState({ selectedOption1 });
        console.log(`Option selected:`, selectedOption1);
    };

    handleChange2 = selectedOption2 => {
        this.setState({ selectedOption2 });
        console.log(`Option selected:`, selectedOption2);
    };

    handleDateChange = selectedDate => {
        this.setState({ selectedDate });
    }

    render() {
        const options1 = [
            { value: 'hairdresser', label: 'Hairdresser' },
            { value: 'gym', label: 'Gym' },
            { value: 'dentist', label: 'Dentist' },
        ];

        const options2 = [
            { value: 'provider1', label: 'Provider1' },
            { value: 'provider2', label: 'Provider2' },
            { value: 'provider3', label: 'Provider3' },
        ];

        const { selectedOption1, selectedOption2, selectedDate } = this.state;

        return (
            <div >
                <div className="booking-container">
                    <div className="booking-form">

                        <div className="booking-left">
                            <h2>Make Your Reservation</h2>
                            <p>We provide the best services available by liscened experts in their field.</p>
                        </div>


                        <div className="booking-right">
                            <form>
                                <div className="service">
                                    <h3>Service:</h3>
                                    <Select
                                        name="selectedOption1"
                                        options={options1}
                                        value={selectedOption1}
                                        onChange={this.handleChange1}
                                        placeholder="Choose a Service..." />
                                </div>

                                <div className="provider">
                                    <h3>Provider:</h3>
                                    <Select
                                        options={options2}
                                        value={selectedOption2}
                                        onChange={this.handleChange2}
                                        placeholder="Choose a Provider..." />

                                </div>


                                <div className="date-time">
                                    <h3>Date/Time:</h3>
                                    <DatePicker
                                        selected={selectedDate}
                                        onChange={this.handleDateChange}
                                        showTimeSelect
                                        placeholderText="Select a day"
                                        dateFormat='MMMM d, yyyy h:mm aa'
                                        isValidDate={true}
                                    />
                                </div>

                                <div className="button-group">
                                    <span ><button className="submit-button"><p>Make Booking</p></button></span>
                                    <span><button className="cancel-button"><p>Cancel</p></button></span>
                                </div>

                            </form>
                        </div>

                    </div>
                </div>
            </div>
        )
    }
}

export default BookingPage;