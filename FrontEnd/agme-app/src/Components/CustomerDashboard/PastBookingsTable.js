import React from 'react';
import ReactTable from 'react-table-6';
import 'react-table-6/react-table.css';
import UserService from '../../Services/UserService';
// import data from './MOCK_DATA.json';
import 'moment-timezone';
import moment from 'moment';

class PastBookingsTable extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            pastBookings: []
        }
    }

    //Get past bookings of a user from the backend
    async getPastBookings() {
        //Use axios function to retrieve data
        const res = await UserService.getCustomerPastBooking(1);
        const data = res.data;

        //Map data
        const bookings = data.map(d => ({
            "id": d.booking_id,
            "service_name": d.service_name,
            "provider_name": d.provider_name,
            "date_booked": moment(d.booking_date).format('MMMM Do YYYY, h:mm:ss a'),
            "status": d.status
        }))

        //Set state for pastBookings
        this.setState({ pastBookings: bookings });

    }

    //Call get past booking
    componentDidMount() {
        this.getPastBookings();
    }

    render() {

        const columns = [
            {
                Header: "Service Provider",
                accessor: "provider_name",
                sortable: false,
                style: {
                    textAlign: "center"
                }
            },
            {
                Header: "Service",
                accessor: "service_name",
                sortable: false,
                style: {
                    textAlign: "center",
                }
            },
            {
                Header: "Status",
                accessor: "status",
                sortable: false,
                style: {
                    textAlign: "center",
                }
            },

            {
                Header: "Date",
                accessor: "date_booked"

            }
        ]

        return (
            <div>
                <ReactTable
                    columns={columns}
                    data={this.state.pastBookings}
                    defaultPageSize={5}
                    noDataText={"You have not made any bookings in the past"}
                />
            </div>
        )
    }
}

export default PastBookingsTable;
