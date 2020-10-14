import React from 'react';
import './BookingPage.scss';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import Select from 'react-select';
import UserService from '../Services/UserService.js';
import 'moment-timezone';
import moment from 'moment';
import history from '../Services/history';


class BookingPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            selectedOption1: null,
            selectedOption2: null,
            selectedDate: null,
            selectServices: [],
            providerIDs: [],
            selectProvider: [],
            serviceName: null,
            provider_id: '',
            service_id: '',
            booking_date: ''

        };
    };

    handleChangeService = selectedOption1 => {
        this.setState({ selectedOption1 });
        this.setState({ providerIDs: [] });


        console.log(`Service selected:`, selectedOption1);

        this.setState({ service_id: selectedOption1.value })
        // console.log(this.state.service_id);
        UserService.getProviders(selectedOption1.label).then(res => {

            const data = res.data;
            const options = data.map(d => ({
                "value": d.provider_id,
                "label": d.provider_name
            }))

            this.setState({ selectProvider: options })

        })

        console.log("PROVIDER:", this.state.selectProvider);

    };

    handleChangeProvider = selectedOption2 => {
        this.setState({ selectedOption2 });
        console.log(`Provider selected:`, selectedOption2);

        this.setState({ provider_id: selectedOption2.value })

    };



    handleDateChange = selectedDate => {
        this.setState({ selectedDate });
        console.log(`Selected Date:`, selectedDate);
        const date = moment(selectedDate).format();

        this.setState({ booking_date: date })
    }

    async getServiceOptions() {
        const res = await UserService.getServices();
        const data = res.data;

        console.log(data);
        const options = data.map(d => ({
            "value": d.service_id,
            "label": d.service_name
        }))

        this.setState({ selectServices: options });

    }

    cancelBooking() {
        history.push('/');
    }


    makeNewBooking = (e) => {
        e.preventDefault();

        let newBooking = {
            user_id: 1,
            service_id: this.state.service_id,
            provider_id: this.state.provider_id,
            status: "ongoing",
            booking_date: this.state.booking_date
        };

        UserService.makeNewBooking(newBooking).then(history.push('/dashboard'));

        console.log(newBooking);
    }

    componentDidMount() {
        this.getServiceOptions();
    }

    render() {

        const { selectedDate } = this.state;

        return (
            <div >
                <div className="booking-container">
                    <div className="booking-form">

                        <div className="booking-left">
                            <h2>Make Your Reservation</h2>
                            <p>We provide the best services available by liscened experts in their field.</p>
                        </div>


                        <div className="booking-right">
                            <form onSubmit={this.makeNewBooking}>
                                <div className="service">
                                    <h3>Service:</h3>
                                    <Select
                                        name="selectedOption1"
                                        options={this.state.selectServices}
                                        // value={selectedOption1}
                                        onChange={this.handleChangeService.bind(this)}
                                        placeholder="Choose a Service..." />
                                </div>

                                <div className="provider">
                                    <h3>Provider:</h3>
                                    <Select
                                        options={this.state.selectProvider}
                                        // value={selectedOption2}
                                        onChange={this.handleChangeProvider.bind(this)}
                                        placeholder="Choose a Provider..." />

                                </div>


                                <div className="date-time">
                                    <h3>Date/Time:</h3>
                                    <DatePicker
                                        selected={selectedDate}
                                        minDate={new Date()}
                                        onChange={this.handleDateChange}
                                        showTimeSelect
                                        placeholderText="Select a day and time"
                                        dateFormat='MMMM d, yyyy h:mm aa'
                                        // dateFormat='yyyy-MM-dd, HH:mm:ss'
                                        isValidDate={true}
                                    />
                                </div>

                                <div className="button-group">
                                    <span ><button type="submit" className="submit-button"><p>Make Booking</p></button></span>
                                    <span><button className="cancel-button" onClick={() => { if (window.confirm('Return to home page?')) { this.cancelBooking() } }}><p>Cancel</p></button></span>
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