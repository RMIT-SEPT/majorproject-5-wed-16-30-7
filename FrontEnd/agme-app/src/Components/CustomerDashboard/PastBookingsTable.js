import React from 'react';
import ReactTable from 'react-table-6';
import 'react-table-6/react-table.css';
import data from './MOCK_DATA.json';

class PastBookingsTable extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            pastBookings: []
        }
    }

    // componentDidMount() {
    //     data.map(data => {
    //         console.log("data", data.service_provider);
    //     })
    // }

    render() {

        const columns = [
            {
                Header: "Service Provider",
                accessor: "service_provider",
                sortable: false,
                style: {
                    textAlign: "left"
                }
            },
            {
                Header: "Employee",
                accessor: "worker",
                sortable: false,
                style: {
                    textAlign: "left",
                }
            },
            {
                Header: "Date",
                accessor: "date"

            }
        ]

        return (
            <div>
                <ReactTable
                    columns={columns}
                    data={data}
                    defaultPageSize={5}
                    noDataText={"You have not made any bookings in the past"}
                />
            </div>
        )
    }
}

export default PastBookingsTable;
